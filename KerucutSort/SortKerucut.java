package WorkbenchUAS;

import java.util.Comparator;

public class SortKerucut implements Comparator<Kerucut> {
    @Override
    public int compare(Kerucut a, Kerucut b) {
        return Double.compare(a.getVolume(), b.getVolume());
    }
}
