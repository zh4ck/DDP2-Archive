import java.util.ArrayList;

abstract class PromoSystem {
    String username;
    String membershipCategory;
    String favoritCategory;

    PromoSystem(String username, String membershipCategory, String favoritCategory) {
        this.username = username;
        this.membershipCategory = membershipCategory;
        this.favoritCategory = favoritCategory;
    }
    abstract String getPromo();

}
