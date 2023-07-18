import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Driver {

  public static void main(String ...args) throws IOException {
    Reader reader = new FileReader("C:\\Users\\shara\\OneDrive\\Desktop\\AliceInWonderland.txt");
    StringBuilder sb = new StringBuilder();
    int c = 0;
    while ((c = reader.read()) != -1) {
      sb.append((char)c);
    }
    String input = sb.toString().replaceAll(",", "");
    String[] words = input.split("\\s");
    HashTable ht = new HashTable(Integer.parseInt(args[0]));
    System.out.println("No. of words: " + words.length);
    ht.createHashTable(words);
    ht.outputAllWords();
  }

}
