import java.util.List;
import java.util.LinkedList;

// interface (implemented with proposal)

interface SearchBackEndInterface {
  public void addSong(SongDataInterface song);

  public boolean containsSong(SongDataInterface song);

  // returns list of the titles of all songs that contain the word titleWord in their song title
  public List<String> findTitles(String titleWord);

  // returns list of the artists of all songs that contain the word titleWord in their song title
  public List<String> findArtists(String titleWord);

  // returns the number of songs that contain the word titleWord in their song title, and were
  // published in year
  public int findNumberOfSongsInYear(String titleWord, int year);
}

// public class (implemented primarilly in final app week)


/**
 * This class contains the necessary implementation for the Back End which makes use of a Hash Table
 * so as to allow users to search for songs based on the words which make up the song
 */
public class SearchBackEnd implements SearchBackEndInterface {

  // creating a hash table which implements the MapADT interface so as to store the words part of a
  // song title and the corresponding list of songs
  private MapADT<String, List<SongDataInterface>> hashTableArray;

  /**
   * Overloaded constructor method which creates an empty hash table with the help of the 
   * HashtableMap class with a passed capacity 
   * 
   * @param capacity - size of which the hash table is to be created
   */
  public SearchBackEnd(int capacity) {
    hashTableArray = new HashtableMap<>(capacity);
  }

  /**
   * Overloaded constructor method so as to create a hash table which is loaded with the songs list
   * passed as argument and is of the passed size
   * 
   * @param songsList - The list of songs to be added to the hash table
   * @param capacity - size of which the hash table is to be created
   */
  public SearchBackEnd(int capacity, List<SongDataInterface> songsList) {
    hashTableArray = new HashtableMap<>(capacity);
    // iterating through the song list via a for-loop and adding each of the individual songs in
    // hash table with the help of the addSong() method
    for (int i = 0; i < songsList.size(); ++i) {
      addSong(songsList.get(i));
    }
  }

  /**
   * This method is responsible for adding songs to the hash table structure of the back end
   * 
   * @param song - The song to be added to the back end
   */
  @Override
  public void addSong(SongDataInterface song) {
    // obtaining the individual words from the song title so as to be used as keys while
    // inserting in the hash table
    String[] titleWords = song.getTitle().split(" ");

    // iterating through the keys (words of the song title) and checking if the given key already
    // exists in the hash table or not
    for (int i = 0; i < titleWords.length; ++i) {
      // if the given key already exists, we append the song to the already existing song list
      // corresponding to that key
      if (hashTableArray.containsKey(titleWords[i])) {
        hashTableArray.get(titleWords[i]).add(song);
      }
      // if the given key does not exist, we create a new song list corresponding to that key,
      // add the song to the list, and insert the list in the hash table with the key
      else {
        List<SongDataInterface> chainedSongList = new LinkedList<SongDataInterface>();
        chainedSongList.add(song);
        hashTableArray.put(titleWords[i], chainedSongList);
      }
    }
  }

  /**
   * This method is responsible for checking whether a song is in the back end or not
   * 
   * @param song - the song whose existence is being checked
   * @return true if the song is present and false otherwise
   */
  @Override
  public boolean containsSong(SongDataInterface song) {
    // retrieving the the first word from the song's name so as to use as a key when searching
    // for the song in the back end
    String titleWord = song.getTitle().split(" ")[0];

    // if the key is present in the hash table, we iterate through the list of songs stored
    // corresponding to the key so as to find the passed song
    if (hashTableArray.containsKey(titleWord)) {
      for (int i = 0; i < hashTableArray.get(titleWord).size(); ++i) {
        if (hashTableArray.get(titleWord).get(i).equals(song)) {
          return true;
        }
      }
    }

    // if the key or song does not exist in the table, then we return false
    return false;
  }

  /**
   * This method is responsible for returning all the songs which contain the passed word
   * 
   * @param titleWord - the word we are looking for in the songs
   * @return list of songs which contain the passed word
   */
  @Override
  public List<String> findTitles(String titleWord) {
    // this list will be returned consists of the song titles which have the passed word
    List<String> songTitles = new LinkedList<String>();

    // if the passed word (key) exists in the hash table, we iterate through the song list so as
    // to add those songs the list which will be returned
    if (hashTableArray.containsKey(titleWord)) {
      for (int i = 0; i < hashTableArray.get(titleWord).size(); ++i) {
        songTitles.add(hashTableArray.get(titleWord).get(i).getTitle());
      }
    } 
    // if the key is not found, we return null
    else {
      return null;
    }
    return songTitles;
  }

  /**
   * This method is responsible for returning all the artists who have songs whose names contain the
   * passed word
   * 
   * @param titleWord - the word we are looking for in the songs
   * @return list of artists whose songs contain the passed word
   */
  @Override
  public List<String> findArtists(String titleWord) {
    // this list will be returned consists of the artists whose songs have the passed word
    List<String> songArtists = new LinkedList<String>();

    // if the passed word (key) exists in the hash table, we iterate through the song list so as
    // to add the artists of those songs the list which will be returned
    if (hashTableArray.containsKey(titleWord)) {
      for (int i = 0; i < hashTableArray.get(titleWord).size(); ++i) {
        if (!(songArtists.contains(hashTableArray.get(titleWord).get(i).getArtist()))) {
          songArtists.add(hashTableArray.get(titleWord).get(i).getArtist());
        }
      }
    } 
    // if the key is not found, we return null
    else {
      return null;
    }

    return songArtists;
  }

  /**
   * This method is responsible for returning the number of songs which contain the passed word in
   * their title and were released in the passed year
   * 
   * @param titleWord - the word we are looking for in the songs
   * @param year      - the year we are looking for the songs to be published in
   * @return the number of songs which contain the passed word in their title and were released in
   *         the passed year
   */
  @Override
  public int findNumberOfSongsInYear(String titleWord, int year) {
    int totalSongs = 0;

    // if the passed word (key) exists in the hash table, we iterate through the song list so as
    // to count the number of songs present in the list which were released in the passed year
    if (hashTableArray.containsKey(titleWord)) {
      for (int i = 0; i < hashTableArray.get(titleWord).size(); ++i) {
        if (hashTableArray.get(titleWord).get(i).getYearPublished() == year) {
          totalSongs += 1;
        }
      }
    } 

    return totalSongs;
  }

}

// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchBackEndPlaceholder implements SearchBackEndInterface {
  private SongDataInterface onlySong;

  public void addSong(SongDataInterface song) {
    this.onlySong = song;
  }

  public boolean containsSong(SongDataInterface song) {
    return onlySong.equals(song);
  }

  public List<String> findTitles(String titleWord) {
    List<String> titles = new LinkedList<>();
    if (onlySong.getTitle().contains(titleWord))
      titles.add(onlySong.getTitle());
    return titles;
  }

  public List<String> findArtists(String titleWord) {
    List<String> artists = new LinkedList<>();
    if (onlySong.getArtist().contains(titleWord))
      artists.add(onlySong.getArtist());
    return artists;
  }

  public int findNumberOfSongsInYear(String titleWord, int year) {
    if (onlySong.getYearPublished() == year)
      return 1;
    return 0;
  }
}
