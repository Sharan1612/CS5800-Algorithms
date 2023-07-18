import java.util.Random;

public class SkipList<T extends Comparable<T>> {

  private final Random randomGenerator;
  private SkipListNode<T> head;

  private final T minVal;

  private int size;

  public SkipList(T minVal) {
    this.minVal = minVal;
    randomGenerator = new Random();
    head = new SkipListNode<>(minVal, 0);
    size = 0;
  }

  public SkipListNode<T> search(T key) {
    SkipListNode<T> p = head;
    while (p.down != null) {
      p = p.down; // go down
      while (p.next != null && key.compareTo(p.next.key) >= 0) {
        p = p.next; // scan forward
      }
    }
    return p;
  }

  public void insert(T key) {
    SkipListNode<T> q = search(key);
    SkipListNode<T> newNode =  new SkipListNode<>(key, q.level);
    horizontalInsert(q, newNode);
    int currentLevel = q.level;
    int headLevel = head.level;

    while(coinFlip() == 1) {
      if(currentLevel >= headLevel) {
        SkipListNode<T> newHead = new SkipListNode<>(minVal, headLevel + 1);
        verticalLink(newHead, head);
        head = newHead;
        headLevel = newHead.level;
      }

      while (q.up == null) {
        q = q.prev;
      }
      q = q.up;

      SkipListNode<T> temp = new SkipListNode<>(key, q.level);
      horizontalInsert(q, temp);
      verticalLink(temp, newNode);
      newNode = temp;
      currentLevel++;
    }
    size = size + 1;
  }

  public void delete(T key) {
    SkipListNode<T> node = head;
    SkipListNode<T> q = search(key);

    while(q != null && q.key.equals(key)) {
      SkipListNode<T> up = q.up;
      if(q.next != null) {
        q.next.prev = q.prev;
      }
      if(q.prev != null) {
        q.prev.next = q.next;
      }
      q.up = null;
      if(up != null) {
        up.down = null;
      }
      q = up;
    }
    while(head.next == null && head.down != null) {
      head = head.down;
      head.up = null;
    }
    size = size - 1;
  }

  private void horizontalInsert(SkipListNode<T> x, SkipListNode<T> y) {
    y.prev = x;
    y.next = x.next;
    if(x.next != null) {
      x.next.prev = y;
    }
    x.next = y;
  }

  private void verticalLink(SkipListNode<T> x, SkipListNode<T> y) {
    x.down = y;
    y.up = x;
  }

  private int coinFlip() {
    return randomGenerator.nextInt(2); // returns 0(tails) or 1(heads)
  }

  public void sort() {
    SkipListNode<T> node = head;

    while (node.down != null) {
      node = node.down;
    }

    while (node.prev != null) {
      node = node.prev;
    }

    while (node != null) {
      if(!node.key.equals(minVal)) {
        System.out.print(node.key + "-->");
      }
      node = node.next;
    }
    System.out.println();
   }

}
