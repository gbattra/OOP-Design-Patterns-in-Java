package visitors;

public interface IVisitor<T> {
  void visit(T receiver);
}
