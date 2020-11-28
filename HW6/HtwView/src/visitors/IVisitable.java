package visitors;

public interface IVisitable<R, T extends IVisitor<R>> {
  void receive(T visitor);
}
