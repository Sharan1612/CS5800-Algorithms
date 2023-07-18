public class SkipListNode<T> {

  T key;

  SkipListNode<T> prev;

  SkipListNode<T> next;

  SkipListNode<T> up;

  SkipListNode<T> down;

  int level;

  public SkipListNode(T key) {
    this.key = key;
  }

  public SkipListNode(T key, int level) {
    this.key = key;
    this.level = level;
  }
}
