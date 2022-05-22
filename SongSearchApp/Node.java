/**
 * This class stores the key and its corresponding value together as a key-value pair
 * 
 * @param <KeyType> - the key which refers to the value
 * @param <ValueType> - the value corresponding to the key
 */
class Node<KeyType, ValueType> {
  KeyType key;
  ValueType value;

  /**
   * Constructor method which stores the passed key and value pair together
   * 
   * @param key - the key to be stored
   * @param value - the value corresponding to the key
   */
  public Node(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }
}
