public interface RBTreeOperations<T> {

  RBTreeNodeOperations<T> search(T key);

  RBTreeNodeOperations<T> min(RBTreeNodeOperations<T> x);

  RBTreeNodeOperations<T> max(RBTreeNodeOperations<T> x);

  RBTreeNodeOperations<T> successor(RBTreeNodeOperations<T> x);

  RBTreeNodeOperations<T> predecessor(RBTreeNodeOperations<T> x);

  void sort();

  void delete(T key);
}
