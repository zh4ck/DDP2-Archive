import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MakaraPromo extends PromoSystem{
    public MakaraPromo(String Username, String membershipCategory, String favoritCategory){
        super(Username, membershipCategory, favoritCategory);
    }

    @Override
    public String getPromo() {
        if (membershipCategory.equalsIgnoreCase("newbie") && (favoritCategory.equalsIgnoreCase("makanan") || favoritCategory.equalsIgnoreCase("dessert"))){
            return "Promo Makanan dan Dessert belum tersedia untuk tingkat Newbie.";
        }
        if (membershipCategory.equalsIgnoreCase("member") && favoritCategory.equalsIgnoreCase("dessert")){
            return "Promo Dessert Khusus untuk pelanggan VIP.";
        }

        Random random = new Random();
        String[] listPromo;

        try {
            switch (favoritCategory.toLowerCase()) {
                case "kopi":
                    listPromo = new String[]{
                            "Diskon 10% Kopi Susu",
                            "Gratis Espresso Shot",
                            "Kopi Beli 1 Gratis 1"
                    };
                    break;
                case "camilan":
                    listPromo = new String[]{
                            "Diskon Kentang Goreng",
                            "Gratis Mendoan",
                            "Ekstra Saus Keju"
                    };
                    break;
                case "makanan":
                    listPromo = new String[]{
                            "Diskon Nasi Goreng",
                            "Gratis Telur Ceplok",
                            "Potongan Harga Ayam Penyet"
                    };
                    break;
                case "dessert":
                    listPromo = new String[]{
                            "Diskon Brownies",
                            "Gratis Waffle",
                            "Ekstra Scoop Es Krim"
                    };
                    break;
                default:
                    throw new InvalidMenuCategoryException("Kategori menu " + favoritCategory + " tidak valid!");
            }
            int randomInt = random.nextInt(2);
            String namaPromo = listPromo[randomInt];

            return "Today's Promo:" + namaPromo;
        }catch (InvalidMenuCategoryException e){
            return e.getMessage();
        }
    }
}
