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
top of the map and the furthest right on bottom. Uses a helper method which has an
accumulator `map` that leaf nodes add to:
```
public Map<String, String> toMap(Map<String, String> map, String encoding) {
    map.put(encoding + this.code, this.symbol);
    return map;
}
```

// nodes and the code tree ADT

// encoder model
    - explain the 'load' and 'save' functionality
    - and why you chose to make those model methods vs. external operations

// encoder controller and factory

// encoder client and commands

# Demo

// run the JAR

// explain each command

// 'new' walk through

// 'save' walk through

// 'load' walk through

// 'encode' walk through

// 'decode' walk through