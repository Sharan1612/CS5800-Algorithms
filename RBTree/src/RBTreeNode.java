public class RBTreeNode<T> implements RBTreeNodeOperations<T> {

  T key;

  private RBTreeNodeOperations<T> left;

  private RBTreeNodeOperations<T> right;

  private RBTreeNodeOperations<T> parent;

  private Color color; // 0 represents RED and 1 represents

  public RBTreeNode(T key) {
    this.key = key;
    this.left = new NilNode<>(this);
    this.right = new NilNode<>(this);
  }

  @Override
  public boolean isNill() {
    return false;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public T getKey() {
    return key;
  }

  @Override
  public RBTreeNodeOperations<T> leftChild() {
    return left;
  }

  @Override
  public RBTreeNodeOperations<T> rightChild() {
    return right;
  }

  @Override
  public RBTreeNodeOperations<T> getParent() {
    return parent;
  }

  @Override
  public void setLeft(RBTreeNodeOperations<T> node) {
    this.left = node;
  }

  @Override
  public void setRight(RBTreeNodeOperations<T> node) {
    this.right = node;
  }

  @Override
  public void setParent(RBTreeNodeOperations<T> node) {
    this.parent = node;
  }

  @Override
  public String toString() {
    return key + "(" + color + ")";
  }
}
