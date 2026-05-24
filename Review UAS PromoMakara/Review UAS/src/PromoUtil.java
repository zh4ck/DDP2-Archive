import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class PromoUtil {

    public static int countPromosByUsername(List<? extends PromoSystem> promos, String username){
        List<?> filtered = promos.stream().filter(promoSystem -> promoSystem.username.equalsIgnoreCase(username)).collect(Collectors.toList());
        return filtered.size();
    }
}
