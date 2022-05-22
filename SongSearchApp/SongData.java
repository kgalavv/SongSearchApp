// interface (implemented with proposal)

interface SongDataInterface {
    public String getTitle();
    public String getArtist();
    public int getYearPublished();
}

// public class (implemented primarilly in final app week)

public class SongData implements SongDataInterface {

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getArtist() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getYearPublished() {
        // TODO Auto-generated method stub
        return 0;
    }

}

class SongDataPlaceholderA implements SongDataInterface {
    public String getTitle() { return "Song A Vowel"; }
    public String getArtist() { return "Artist X"; }
    public int getYearPublished() { return 1900; }
}
class SongDataPlaceholderB implements SongDataInterface {
    public String getTitle() { return "Song B Consonant"; }
    public String getArtist() { return "Artist Y"; }
    public int getYearPublished() { return 2000; }
}
class SongDataPlaceholderC implements SongDataInterface {
    public String getTitle() { return "Song C Consonant"; }
    public String getArtist() { return "Artist X"; }
    public int getYearPublished() { return 2021; }
}

// placeholder(s) (implemented with proposal, and possibly added to later)
