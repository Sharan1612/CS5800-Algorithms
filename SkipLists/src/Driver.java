import java.util.Scanner;

public class Driver {

  public static void main(String ...args) {
    SkipList<Integer> skipList = new SkipList<>(Integer.MIN_VALUE);
    skipList.insert(4);
    skipList.insert(1);
    skipList.sort();

    Scanner sc = new Scanner(System.in);

    while (sc.hasNext()) {
      String command = sc.nextLine();
      if(command.equals("q")) {
        break;
      }
      String[] parts = command.split(" ");

      if(parts[0].equals("insert")) {
        skipList.insert(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("lookup")) {
        System.out.println(skipList.search(Integer.parseInt(parts[1])).key);
      } else if(parts[0].equals("delete")) {
        skipList.delete(Integer.parseInt(parts[1]));
      } else if(parts[0].equals("sort")) {
        skipList.sort(); // prints the skiplist keys in ascending order
      } else {
        System.out.println("Unsupported command: " + parts[0]);
      }
    }
  }
}
