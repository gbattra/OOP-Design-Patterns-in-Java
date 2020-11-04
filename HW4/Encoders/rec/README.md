# Implementation
### `CodeNode`
In designing the interfaces for the `codes` package, my goal was to make them abstract and
generic enough to work for many encoder implementations. To this end, many of the interfaces
use generic types: `<K, S>` where `K` is the type of the codes, and `S` is the type of the
symbols. The encoder implementation in this package is of type `<String, String>`.

Let's start with the core component of the encoder: `CodeNode<K, S>`. As the name suggests,
this object represents a node in a tree.

- `K getCode();`<br>

Each node has a `code` (except for the `root` node). This code is used by the parent node
to select it. For example, in a binary code tree, the root of the tree will have one child
with code `0` and another with code `1`.

- `S getSymbol();`<br>

Each node in the tree also has a symbol. This is true for both `GroupNode`'s (node's with
children) and `LeafNodes` (node's without children). However, a group node's symbol
would be the collection of the symbols for each leaf node under that node. A group node's
symbol isn't particularly useful when encoding or decoding, however both a group node and
leaf node must support the same operations. So I had to decide if I wanted `getSymbol()`
on a group node to throw an error or return something to the user. Ultimately, I opted for
the later, with group nodes returning a `String` concatenation of the symbols of each leaf
node under that node. This implementation is friendlier to recursive operations.
```
public String getSymbol() {
    return this.children.stream().map(CodeNode::getSymbol).collect(Collectors.joining(""));
}
```

- `CodeNode<K, S> setCode(K code);`<br>

This factory method returns a new instance of the called `CodeNode` object, copying
its children and symbol, and assigning it the provided `code`. This allows the user to
instantiate nodes that may not have a code at first (such as `root` nodes), and then later
add a code without mutation side-effects. This functionality was useful in the implementation
of Huffman's encoding algorithm, as it allowed for mering root nodes under a common node.

- `CodeNode add(S symbol, K encoding);`<br>

Adds a node to the tree given a `symbol` and an `encoding`. For example: `add("A", 001)`.
This is a factory method, which copies the contents of the tree into a new instance with
the new node added. If the tree is empty or is missing group nodes to reach that encoding,
it will add the nodes it needs to reach the destination. If a node already exists at the
provided encoding, it will throw an error.

This operation allows the user to build the tree from a `Map<K, S>` or `String` which follows
the pattern: `code,symbol\n` for each code->symbol mapping, which is explained further
in the Encoder section.

- `S decode(K encoding);`<br>

Below is how the `PrefixCodeGroup` decodes an encoding. It selects the first
character in the encoding and uses it as the `code` by which to filter its children.
```
char code = encoding.charAt(0);
List<CodeNode<String, String>> filtered = this.children.stream()
        .filter(c -> c.getCode().equals(String.valueOf(code)))
        .collect(Collectors.toList());
if (filtered.isEmpty()) {
  throw new IllegalArgumentException("No symbol found for provided encoding.");
}

return filtered.get(0).decode(encoding.substring(1));
```
If the node does not have a child with that `code`, it throws an error as the encoding is invalid.
Otherwise, it recursively calls that node's `decode()` method, passing in the remainder of
the encoding to the child. If the encoding is valid, eventually a `LeafNode` will be reached,
and the node will simply return `getSymbol()`.

```
public String decode(String encoding) throws IllegalArgumentException {
    if (!encoding.isEmpty()) {
      throw new IllegalArgumentException("No symbol found for provided encoding.");
    }
    
    return this.getSymbol();
}
```
This implementation supports an arbitrary number of codes and symbols to encode. Child nodes
are kept in a list whose order depends on the order in which they were added to the tree.

- `K encode(S symbol);`<br>
- `K encode(S symbol, String encoding);`<br>

Encodes a symbol, returning its `K` representation. Uses a helper method, which accumulates
the encoding for the symbol as it traverses the code tree.

```
for (CodeNode<String, String> child : this.children) {
  try {
    encoding = child.encode(symbol, encoding + this.getCode());
    return encoding;
  } catch (Exception ignored) {
    // do nothing
  }
}
throw new IllegalArgumentException("Provided symbol not found in code tree.");
```
Since we can't know upfront where a symbol resides within a code tree without its encoding,
we must try to traverse each node in the tree until we find it. As this typically throws
`IllegalArgumentErrors` we must catch and ignore those exceptions, unless we have exhausted
each potential node in the tree, at which point we throw.

- `S next(K sequence);`<br>

A variation of `encode()` which takes an entire sequence of encodings (i.e. the encoding of
a full sentence) and returns just the very next symbol in the sequence.

- `Map<K, S> toMap();`<br>
- `Map<K, S> toMap(Map<K, S> map, K encoding);` <br>

Returns a map of each code->symbol mapping with the encodings furthest left in the tree on
top of the map and the furthest right on bottom. This map is used by the `toString()` override
which puts each entry on a new line. This map can be used as the constructor argument to another
`CodeNode`, which will build the tree from that map using the `add()` method. Uses a helper method which has an
accumulator `map` that leaf nodes add to:
```
public Map<String, String> toMap(Map<String, String> map, String encoding) {
    map.put(encoding + this.code, this.symbol);
    return map;
}
```

### `CodeTree`
The `CodeTree` object acts as an ADT around the `root` node of the tree itself. This ADT
protects the state of the tree once it has been built. It does this by only exposing three methods
to the user: `encode()`, `decode()`, and `toMap()`.
- `K encode(S sequence);`<br>
A wrapper around the `root` node's `encode()` method. The implementation of this method loops
through the sequence one character at a time, encoding it and appending that encoding onto the\
output `K`.
```
StringBuilder encoding = new StringBuilder();
while (!sequence.isEmpty()) {
  encoding.append(this.root.encode(sequence.substring(0, 1)));
  sequence = sequence.substring(1);
}

return encoding.toString();
```

- `S decode(K sequence);`<br>
A wrapper around the `root` node's `next()` method. The implementation of this method is
similar to encode, however, there are two steps: first, get the next symbol in the sequence,
then encode that symbol again and subtract the size of that encoding from the front of the
sequence before continuing the loop.

```
StringBuilder decoding = new StringBuilder();
while (!sequence.isEmpty()) {
  String next = this.root.next(sequence);
  decoding.append(next);

  String encoding = this.encode(next);
  sequence = sequence.substring(encoding.length());
}

return decoding.toString();
```

### `Encoder`
The `Encoder<K, S>` object is most a wrapper around the `CodeTree` but it supports operations outside
of just `encode()` and `decode()`, such as `load()` and `save()` methods which load the encoder
from or save it to a file. These extra functionalities are separated into their own interfaces:
`FileSavable` and `FileLoadable<T>`. I was torn on whether to implement the `load()` and `save()` methods
within the model or outside. Ultimately, I decided to keep it in the model as only the model
should have to know how to save itself. 

The `PrefixEncoder` has three constructors:

- `PrefixEncoder(String codes, String symbols);`<br>
Builds the encoder using Huffman's algorithm.

- `PrefixEncoder(Map<String, String> map);`<br>
Builds the model from a map of the code tree.

- `PrefixEncoder(String contents);`<br>
Builds the encoder from a `String` representation of the code tree. This string matches
the format: `code,symbol\n` for each code->symbol mapping. This constructor is used when
loading an encoder from file contents.

- `boolean save(String filename);`<br>
- `boolean load(String filepath);` <br>
To save the encoder, the model writes its `toString()` output to a file. As this ouput
should match the format: `code,symbol\n` for each code->symbol mapping, it can be used
to load the encoder back into memory.

```
FileWriter writer = new FileWriter(filename);
writer.write(this.toString());
writer.close();
```

Additionally, the algorithm for building an encoder lives in the `PrefixEncoder`
implementation of the `Encoder` interface:

```
codes = StringHelper.distinctCharacters(codes);
Stack<Frequency<CodeNode<String, String>>> nodes =
        FrequencyHelper.toStack(symbols.split(""), (c) -> new PrefixCodeLeaf(c));
nodes.sort(this::compareFrequencies);
```

Let's walk through this code:

First, we convert the `String` of `symbols` to a "stack" of `Frequency<T>` objects. A
frequency object is a temporary structure which ties a `symbol` to an `int` representing
the number of times that symbol appeared in the original sequence. This list is then sorted so that the least-frequent symbols are on top and the most-frequent
on bottom.

The `Frequency<T>`is able to hold any type as its "value", so to make the tree-building processes smoother,
I had each frequency object hold the leaf nodes for each symbol. As the algorithm loops,
these leaf nodes are popped off the stack, joined under a shared group node and that group
node put back in the list.

```
while (nodes.size() > 1) {
  int frequency = 0;
  List<CodeNode<String, String>> children = new ArrayList<>();
  for (int i = 0; i < codes.length(); i++) {
    if (nodes.empty()) {
      break;
    }

    Frequency<CodeNode<String, String>> item = nodes.pop();
    frequency += item.getFrequency();
    children.add(item.getValue().setCode(String.valueOf(codes.charAt(i))));
  }

  nodes.add(new FrequencyEntry<>(frequency, new PrefixCodeGroup(children)));
  nodes.sort(this::compareFrequencies);
}

return new PrefixCodeTree(nodes.get(0).getValue());
```
Until there is only one node in the stack, we iterate over the stack popping off _N_ nodes,
where _N_ is the number of codes provided. For each node, its code is set as the code
corresponding with the index used to get the node.

Each node that was popped from the stack is collected into a list which is used as the
`children` of a new `PrefixCodeGroup` node. This group node is reinserted back into the stack,
which is again sorted to ensure proper order during each iteration.

### `Controller`
The `EncoderController` serves as an interface for an encoder model. It too defines generic
types `K` and `S`. As a constructor argument, the controller takes an `EncoderFactory` which
allows for a rudimentary form of dependency injection. This way the same controller may be
used for interfacing with many types of encoders.

- `boolean loadEncoder(String filepath);`<br>
Loads an encoder instance from the `String` contents of a file located at the provided path.
The new instance is then set on the controller itself, and _not_ returned to the caller. This
protects the encoder model even further, as the user is not directly calling the encoder itself.

- `boolean newEncoder(K codes, S symbols);`<br>
Instantiates a new encoder using the provided `codes` and `symbols` and sets the new encoder
to the controller's `encoder` field.

- `boolean saveEncoder(String filename);`<br>
Saves the encoder by writing its `toString()` oupput to a file with the specified name.

And of course, the controller exposes methods to `encode()` and `decode()` sequences, returning
the output to the caller.

### `Client`
Finally, we have the `EncoderClient`, which serves (unsurprisingly) as the client of the
encoder controller. The `Client` interface exposes just a single method: `int run();`. This
spins up the client, which loops infinitely, reading user input and executing commands corresponding
to those inputs. The `int` response value indicates to the driver running the client whether
the run was successful (`1`) or failed (`0`).

I chose to implement the Command Pattern here. So for each controller method, there exists
a `Command<T>` where `T` is `EncoderController`. The client maintains a map of inputs to
the corresponding command. For example, if a user wanted to create a new encoder, they would
enter _"new"_, which maps to a function: `s -> new NewCommand(s)`, returning a new instance
of the `NewCommand` object. The client, once it has retrieved this command instance, blindly
executes the `execute(T receiver)` method, passing in its `controller` field as `T`.
```
Function<Scanner, Command<EncoderController<String, String>>> entry =
                commands.getOrDefault(next, null);

Command<EncoderController<String, String>> cmd = entry.apply(this.scanner);
cmd.execute(this.controller);
```

Below is a look inside that command class:

```
public NewCommand(String codes, String symbols, Appendable out) {
    this.codes = codes;
    this.symbols = symbols;
    this.out = out;
}

@Override
public void execute(EncoderController<String, String> receiver) throws IOException {
    try {
      boolean success = receiver.newEncoder(this.codes, this.symbols);
      this.out.append(success ? "New encoder created.\n" : "Unable to create encoder.\n");
    } catch (IllegalArgumentException e) {
      this.out.append(String.format("Failed to create new encoder. %s\n", e.getMessage()));
    }
}
```
The command is instantiated with the user-provided `codes` and `symbols`, as well as an `Appendable`
where it may write output.

# Demo
To run an interactive demo of the `codes` package, right click the `Encoders.jar` file found in
`/rec`. You will be prompted to enter among the following commands:

- `new`<br>
The new command sets up a brand new encoder instance to interact with. You will be prompted
to enter the `codes` and `symbols` for the encoder. For example:
```
Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):
> new
Enter codes:
> 01
Enter symbols:
> abcdefghijklmnopqrstuvwxyz
New encoder created.
```

- `load`<br>
You may also load an encoder from an existing file. You will be prompted to enter the `filepath`
where the encoder representation is written.
```
Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):
> load
Enter filename:
> encoder.txt
Failed to load encoder: encoder.txt
```
In this case, the load failed as there was no file `ecoder.txt` to be found. Let's fix this by
saving an existing encoder to a file.

- `save`<br>
To save an encoder, first set one up using the `new` command. Once complete, you may enter `save`
which will prompt you to name the file where the encoder will be written. (**Note:** when this
command finishes, you may not see the file at the root of the project directory until after you
_stop_ the running program. I am not sure why this is.) Now there exists a file containing the
`String` representation of our encoder and we can load it from that file using the `load` command.

- `encode`<br>
To encode a message, enter `encode`. If an encoder has not yet been loaded, this command will
not succeed, so be sure to call `new` or `load` before using `encode`. You will be prompted
to enter the sequence to encode. If you enter a character not present in the encoder's code tree
the operation will also fail. If the sequence entered is valid, the program will output the
encoded `String`. For example:
```
Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):
> encode
Enter sequence:
> hello, world!
110011010101101111100110001011001011110000
```
- `decode`<br>
We can prove that this encoding is correct by copying it to the clipboard and entering
`decode`. Paste the encoding when prompted for the sequence. The program will output the decoded
sequence.
```
Enter: 'new', 'load', 'save', 'encode', or 'decode' ('q' or 'quit' to exit):
> decode
Enter sequence:
> 110011010101101111100110001011001011110000
hello, world!
```