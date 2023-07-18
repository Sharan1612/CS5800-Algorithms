public interface RBTreeNodeOperations<T> {

  boolean isNill();

  Color getColor();

  void setColor(Color color);

  T getKey();

  RBTreeNodeOperations<T> leftChild();

  RBTreeNodeOperations<T> rightChild();

  RBTreeNodeOperations<T> getParent();

  void setLeft(RBTreeNodeOperations<T> node);

  void setRight(RBTreeNodeOperations<T> node);

  void setParent(RBTreeNodeOperations<T> node);
}
