import java.util.Iterator;

public class CustomLL implements Iterable<ListNode> {

  private int length;

  private ListNode head;

  public void add(ListNode node) {
    if(head != null && node != null) {
      node.next = head;
    }
    head = node;
    if(node != null) {
      length++;
    }
  }

  public int size() {
    return length;
  }

  public ListNode getHead() {
    return head;
  }

  @Override
  public Iterator<ListNode> iterator() {
    return new ListNodeIterator(this);
  }
}
