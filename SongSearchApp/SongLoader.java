import java.util.List;
import java.io.FileNotFoundException;

// interface (implemented with proposal)

interface SongLoaderInterface {
    public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException;
}

// public class (implemented primarilly in final app week)

public class SongLoader implements SongLoaderInterface {

    @Override
    public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}

class SongLoaderPlaceholder implements SongLoaderInterface {
    public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException{
        List<SongDataInterface> list = new LinkedList<>();
        list.add(new SongDataPlaceholderA());
        list.add(new SongDataPlaceholderB());
        return list;
    }
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException{
        List<SongDataInterface> list = new LinkedList<>();
        list.add(new SongDataPlaceholderA());
        list.add(new SongDataPlaceholderB());
        list.add(new SongDataPlaceholderC());
        return list;
    }
}

// placeholder(s) (implemented with proposal, and possibly added to later)
