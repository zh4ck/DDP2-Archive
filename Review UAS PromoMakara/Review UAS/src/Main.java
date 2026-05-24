import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidMenuCategoryException{
        MakaraPromo advisor = new MakaraPromo("Joko", "Newbie", "Makanan");
        MakaraPromo promo1 = new MakaraPromo("Budi", "VIP", "Kopi");
        MakaraPromo promo2 = new MakaraPromo("Budi", "member", "makanan");
        System.out.println(advisor.getPromo());

        PromoHistory<MakaraPromo> history = new PromoHistory<>();
        List<MakaraPromo> listPromo = new ArrayList<>(); // kenapa beda gatau kenapa di test casenya beda cok

        history.addPromo(advisor);
        history.addPromo(promo1);
        listPromo.add(advisor);
        listPromo.add(promo1);
        listPromo.add(promo2);

        System.out.println(history.getLastPromo().username); // OUTPUT: Budi
        int hitungBudi = PromoUtil.countPromosByUsername(listPromo, "Budi");
        System.out.println(hitungBudi); //OUTPUT: 2
    }
}
