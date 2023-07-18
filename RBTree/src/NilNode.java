public class NilNode<T> implements RBTreeNodeOperations<T> {

  RBTreeNodeOperations<T> parent;
  Color color;

  public NilNode(RBTreeNodeOperations<T> parent) {
    this.parent = this;
    this.color = Color.BLACK;
  }

  @Override
  public boolean isNill() {
    return true;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color color) {

  }

  @Override
  public T getKey() {
    return null;
  }

  @Override
  public RBTreeNodeOperations<T> leftChild() {
    return this;
  }

  @Override
  public RBTreeNodeOperations<T> rightChild() {
    return this;
  }

  @Override
  public RBTreeNodeOperations<T> getParent() {
    return parent;
  }

  @Override
  public void setLeft(RBTreeNodeOperations<T> node) {
    throw new UnsupportedOperationException("Nil node cannot have left child");
  }

  @Override
  public void setRight(RBTreeNodeOperations<T> node) {
    throw new UnsupportedOperationException("Nil node cannot have right child");
  }

  @Override
  public void setParent(RBTreeNodeOperations<T> node) {
    this.parent = node;
  }
}
