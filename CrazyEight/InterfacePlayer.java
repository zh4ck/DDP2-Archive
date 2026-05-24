public interface InterfacePlayer {
    String getName();
    Hand getHand();
    Card play(Card leadingCard, CardCollection drawPile);
    boolean hasSuit(int suit);
    void display();
}
