import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.soap.Node;

public class HashTable {

  private int maxHash;

  private CustomLL[] hashTable;
  public HashTable(int maxHash) {
    this.maxHash = maxHash;
    this.hashTable = new CustomLL[maxHash];
  }

  private int hash(String word) {
    int hash = 0;
    int a = 19;
    word.hashCode();

    for(int i = 0; i < word.length(); i++) {
      int temp = (a % maxHash)*(hash % maxHash) % maxHash;
      hash = (temp % maxHash + (int)word.charAt(i) % maxHash) % maxHash;
//      hash += (int)word.charAt(i) + i^2;
    }
//    hash = hash % maxHash;
    return hash;
  }

  public void createHashTable(String[] words) {
    for(String word : words) {
      int hashKey = hash(word);

      if(hashTable[hashKey] == null) {
        ListNode node = new ListNode(word, 1);
        hashTable[hashKey] = new CustomLL();
        hashTable[hashKey].add(node);
      } else {
        boolean wordExists = false;
        for(ListNode node : hashTable[hashKey]) {
          if (node.text.equals(word)) {
            node.count++;
            wordExists = true;
            break;
          }
        }
        if(!wordExists) {
          ListNode node = new ListNode(word, 1);
          hashTable[hashKey].add(node);
        }
      }
    }
  }

  public void outputAllWords() {
    try {
      FileWriter writer = new FileWriter("C:\\Users\\shara\\OneDrive\\Desktop\\output.txt");
      for(int i = 0; i < maxHash; i++) {
        if(hashTable[i] != null) {
          System.out.println("Collision list " + i + ", Size: " + hashTable[i].size());
          for(ListNode node : hashTable[i]) {
            //          System.out.println("Word: " + node.text + ", Count: " + node.count);
            writer.write("Word: " + node.text + ", Count: " + node.count);
            writer.write(System.lineSeparator());
          }
        }
      }
      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
