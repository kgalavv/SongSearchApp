import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements the necessary functionality for the Hash Table data structure
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private LinkedList<Node>[] hashTableArray;    // LinkedList representing the hash table 
  private int tableCapacity;    // the maximum size or number of elements capable of being
                                // stored in the hash table
  private int tableSize = 0;    // number of elements currently in the hash table

  /**
   * The default constructor which creates a LinkedList of capacity 20 which represents the hash
   * table 
   */
  public HashtableMap() { 
    this.tableCapacity = 20;
    hashTableArray = new LinkedList[tableCapacity];
  }

  /**
   * Constructor which creates a LinkedList of a specified capacity which represents the hash table
   * 
   * @param capacity - the maximum size or number of elements capable of being stored in the hash
   *                   table
   */
  public HashtableMap(int capacity) {
    this.tableCapacity = capacity;
    hashTableArray = new LinkedList[tableCapacity];
  }

  /**
   * This method is responsible for adding the key-value pair to the hash table. If the load factor
   * of the hash table is crossed, this method also resizes the table and rehashes the present 
   * elements
   * 
   * @param key - the key to be added to the hash table
   * @param value - the value to be added to the hash table
   * @return true if the key-value pair is inserted successfully and false otherwise
   */
  public boolean put(KeyType key, ValueType value) {
    // if the passed key is null, we can not add it to the hash table and false is returned
    if (key == null) {
      return false;
    } else {
      // calculating the index of the hash table for insertion using the passed key
      int tableIndex = Math.abs(key.hashCode()) % tableCapacity;   
      // the key and value are stored together as a node so that they can be inserted into the hash
      // table together
      Node node = new Node(key, value);
      // the LinkedList used for chaining at a specific index of the hash table is captured
      LinkedList<Node> chainedList = hashTableArray[tableIndex];

      // the variable created in the previous step is checked, if that variable is null, then at 
      // that specific index of the hash table a LinkedList does not exist and is created
      if (chainedList == null) {
        chainedList = new LinkedList<Node>();
        hashTableArray[tableIndex] = chainedList;
      } else {    // else, the chained list at that index is traversed to verify that the same key
                  // does not already exist
        for (int i = 0; i < chainedList.size(); ++i) {
          if (chainedList.get(i).key.equals(key)) {
            return false;
          }
        }
      }

      // the node containing the key-value pair is added to the chained list at the calculated
      // index of the hash table and the number of elements in the table is incremented
      chainedList.add(node);
      tableSize += 1;

      // if load factor of the hash table crosses the threshold, a new hash table with double the
      // capacity is created and the elements from the old hash table are rehashed into the new one
      if (((((double) tableSize) / ((double) tableCapacity)) * 100) >= 80) {
        tableSize = 0;
        tableCapacity *= 2;
        LinkedList<Node>[] tempHashTableArray = new LinkedList[tableCapacity];

        // the hash table is iterated over to capture the chained lists at different indexes
        for (int i = 0; i < hashTableArray.length; ++i) {
          chainedList = hashTableArray[i];

          // if the variable in the previous step is null, this means that a chained list does not
          // exist at index and hence we skip over that iteration
          if (chainedList == null) {
            continue;
          }

          // we iterate over the LinkedList captured at a specific index of the hash table so as to
          // rehash the elements present in that chained list
          for (int k = 0; k < chainedList.size(); ++k) {
            // calculating an index for the larger hash table using the key of an element stored
            // in the chained list
            tableIndex = Math.abs(chainedList.get(k).key.hashCode()) % tableCapacity;
            // the key and value stored in that particular element of the chained list is used to 
            // create a new node 
            node = new Node(chainedList.get(k).key, chainedList.get(k).value);
            // the chained list is then attempted to be captured from the newly calculated index  
            // for the larger hash table
            chainedList = tempHashTableArray[tableIndex];
            
            // if the variable in the previous step is null, we can conclude that a chained list
            // does not exist in that index and hence we create one at that index
            if (chainedList == null) {
              chainedList = new LinkedList<Node>();
              tempHashTableArray[tableIndex] = chainedList;
            }
            
            // the node with the key and value pair is added to the chained list at the newly
            // calculated index of the larger hash table and the size of the new hash table is
            // incremented by 1
            chainedList.add(node);
            tableSize += 1; 
          }
        }

        // after the complete iteration over the old hash table, the reference of the new hash 
        // table is stored in the old hash table 
        hashTableArray = tempHashTableArray;
      }
    }

    return true;
  }

  /**
   * This method returns a value using the corresponding key as input. If the key is null or a 
   * value does not exist for the key, NoSuchElementException is thrown
   * 
   * @param key - the key used to retrieve the value
   * @return the value corresponding to the passed key otherwise a NoSuchElementException is thrown  
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (key == null) {
      throw new NoSuchElementException();
    }

    // calculating the hash table index using the passed key and attempting to retrieve a chained 
    // list at this index
    int tableIndex = Math.abs(key.hashCode()) % tableCapacity;
    LinkedList<Node> chainedList = hashTableArray[tableIndex];

    // if the variable from the previous step is null, this indicates that no chained LinkedList
    // exists at the calculated index and thus a value does not exist for the passed key
    if (chainedList == null) {
      throw new NoSuchElementException();
    } else {    // else, we traverse the obtained chained LinkedList till we find the element 
                // corresponding to our passed key
      for (int i = 0; i < chainedList.size(); ++i) {
        if (chainedList.get(i).key.equals(key)) {
          return (ValueType) chainedList.get(i).value;
        }
      }

      // if the element corresponding to our passed key is not found, we thrown an exception
      throw new NoSuchElementException();
    }
  }

  /**
   * This method returns the number of key-value pairs currently stored in the hash table
   * 
   * @return the number of key-value pairs currently in the hash table
   */
  public int size() {
    return tableSize;
  }

  /**
   * This method checks whether a key passed as input exists in the hash table or not 
   * 
   * @param key - the key to be checked
   * @return true if the key is found and false otherwise
   */
  public boolean containsKey(KeyType key) {
    if (key == null) {
      return false;
    } else {
      // calculating the hash table index using the passed key and attempting to retrieve a chained 
      // list at this index
      int tableIndex = Math.abs(key.hashCode()) % tableCapacity;
      LinkedList<Node> chainedList = hashTableArray[tableIndex];

      // if the variable from the previous step is null, this indicates that no chained LinkedList
      // exists at the calculated index and thus the passed key does not exist in the hash table
      if (chainedList == null) {
        return false;
      } else {    // else, we traverse the obtained chained LinkedList till we find the node which
                  // which corresponds to the passed key
        for (int i = 0; i < chainedList.size(); ++i) {
          if (chainedList.get(i).key.equals(key)) {
            return true;
          }
        }
      }
    }
    
    // if the passed key is not found even after traversing the chained list, this means the key
    // does not exist in the hash table
    return false;
  }

  /**
   * This method takes a key and removes and returns the value corresponding to that key
   * 
   * @param key - the key whose corresponding value ought to be removed
   * @return the value corresponding to the passed key or otherwise null if the key can not be 
   *         found or can not be searched
   */
  public ValueType remove(KeyType key) {
    if (key == null) {
      return null;
    } else {
      int tableIndex = Math.abs(key.hashCode()) % tableCapacity;
      LinkedList<Node> chainedList = hashTableArray[tableIndex];

      if (chainedList == null) {
        return null;
      } else {    
        ValueType value = null;

        // traversing the obtained chained list at a specific index of the hash table to find the
        // passed key
        for (int i = 0; i < chainedList.size(); ++i) {
          if (chainedList.get(i).key.equals(key)) {
            value = (ValueType) chainedList.get(i).value;
            chainedList.remove(chainedList.get(i));
            break;
          }
        }
        
        return value;
      }
    }
  }

  /**
   * This method clears the hash table by reseting the number of elements to zero and creating a
   * new reference of the LinkedList to refer to the hash table
   */
  public void clear() {
    tableSize = 0;
    hashTableArray = new LinkedList[tableCapacity];
  }
}