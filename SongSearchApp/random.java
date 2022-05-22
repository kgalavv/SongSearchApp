import java.util.ArrayList;

public class random {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hi");
        list.add("bye");
        list.add("thanks");
        list.add("yo");
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
    }
}
