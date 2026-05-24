import java.util.*;

public class PromoHistory<T> {
    private ArrayList<T> history;

    public PromoHistory() {
        this.history = new ArrayList<T>();
    }

    public void addPromo(T promo){
        history.add(promo);
    }

    public T getLastPromo(){
        if (history.isEmpty()){
            return null;
        }
        return history.getLast();
    }

    public List<T> getHistory(){
        return history;
    }

}
