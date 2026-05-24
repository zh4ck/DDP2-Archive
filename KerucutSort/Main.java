package WorkbenchUAS;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Kerucut> kerucuts = new ArrayList<>();
        kerucuts.add(new Kerucut(4, 6, 1));
        kerucuts.add(new Kerucut(6, 7, 2));
        kerucuts.add(new Kerucut(10, 20, 3));

        Collections.sort(kerucuts, new SortKerucut());

        for (Kerucut kerucut : kerucuts) {
            System.out.println(kerucut.toString());
        }
    }
}
