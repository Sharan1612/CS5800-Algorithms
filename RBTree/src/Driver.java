import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

  public static void main(String ...args) throws IOException {
    RBTree<Integer> rbTree = new RBTree<>();
    FileReader reader = new FileReader("C:\\Users\\shara\\OneDrive\\Desktop\\RBTreeInput.txt");
    StringBuilder sb = new StringBuilder();
    int c;
    while ((c = reader.read()) != -1) {
      sb.append((char)c);
    }
    for(String val : sb.toString().split(" ")) {
      rbTree.insert(Integer.parseInt(val));
    }
    rbTree.min(null);
    Scanner sc = new Scanner(System.in);
    while(sc.hasNext()) {
      String instruction = sc.nextLine();
      if(instruction.equals("q")) {
        break;
      }
      String[] parts = instruction.split(" ");
      if(parts[0].equals("insert")) {
        rbTree.insert(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("delete")) {
        rbTree.delete(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("search")) {
        int key = Integer.parseInt(parts[1]);
        RBTreeNodeOperations<Integer> node = rbTree.search(Integer.parseInt(parts[1]));
        if(node.getKey() == key) {
          System.out.println("Node with key:  " + key + "(" + node.getColor() + ")" + " found");
        }
      } else if(parts[0].equals("minimum")) {
        if(parts.length < 2) {
          System.out.println("Minimum is: " + rbTree.min(null));
        } else {
          System.out.println("Minimum is: " + rbTree.min(rbTree.search(Integer.parseInt(parts[1]))));
        }

      } else if(parts[0].equals("maximum")) {
        if(parts.length < 2) {
          System.out.println("Maximum is: " + rbTree.max(null));
        } else {
          System.out.println("Maximum is: " + rbTree.max(rbTree.search(Integer.parseInt(parts[1]))));
        }
      } else if(parts[0].equals("predecessor")) {
        RBTreeNodeOperations<Integer> node = rbTree.predecessor(rbTree.search(Integer.parseInt(parts[1])));
        if(!node.isNill()) {
          System.out.println("Predecessor is: " + node);
        }
      } else if(parts[0].equals("successor")) {
        RBTreeNodeOperations<Integer> node = rbTree.successor(rbTree.search(Integer.parseInt(parts[1])));
        if(!node.isNill()) {
          System.out.println("Successor is: " + node);
        }
      } else if(parts[0].equals("sort")) {
        rbTree.sort();
        System.out.println();
      } else {
        System.out.println("Unsupported operation: " + parts[0]);
      }
    }
  }
}
