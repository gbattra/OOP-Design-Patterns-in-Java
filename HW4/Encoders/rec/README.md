# Encoders

### Implementation

In designing the interfaces for the `codes` package, my goal was to make them abstract and
generic enough to work for many encoder implementations. To this end, many of the interfaces
use generic types: `<K, S>` where `K` is the type of the codes, and `S` is the type of the
symbols. The encoder implementation in this package is of type `<String, String>`.

Let's start with the core component of the encoder: `CodeNode<K, S>`. As the name suggests,
this object represents a node in a tree.

- `K getCode();`<br>

Each node has a `code`. This code is used by the parent node to select it. For example,
in a binary code tree, the root of the tree will have one child with code `0` and another
with code `1`. Below is how the `PrefixCodeGroup` decodes an encoding. It selects the first
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

// nodes and the code tree ADT

// encoder model
    - explain the 'load' and 'save' functionality
    - and why you chose to make those model methods vs. external operations

// encoder controller and factory

// encoder client and commands

### Demo

// run the JAR

// explain each command

// 'new' walk through

// 'save' walk through

// 'load' walk through

// 'encode' walk through

// 'decode' walk through