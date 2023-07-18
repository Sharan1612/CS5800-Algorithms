import java.util.Iterator;

public class ListNodeIterator implements Iterator<ListNode> {
  ListNode current;

  public ListNodeIterator(CustomLL list) {
    current = list.getHead();
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public ListNode next() {
    ListNode node = current;
    current = current.next;
    return node;
  }
}