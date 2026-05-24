public class CangkulanPlayer implements InterfacePlayer {
    private String name;
    private Hand hand;

    public CangkulanPlayer(String name) {
        this.name = name;
        this.hand = new Hand(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public boolean hasSuit(int suit) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.getCard(i).getSuit() == suit) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Card play(Card leadingCard, CardCollection drawPile) {
        if (leadingCard == null) {
            return hand.popCard(0);
        }

        int requiredSuit = leadingCard.getSuit();

        // draw
        while (!hasSuit(requiredSuit) && !drawPile.isEmpty()) {
            Card drawn = drawPile.popCard();
            // buat debugging/testing aja
            System.out.println(name + " mengambil kartu " + drawn);
            hand.addCard(drawn);
        }

        // play 
        for (int i = 0; i < hand.size(); i++) {
            if (hand.getCard(i).getSuit() == requiredSuit) {
                return hand.popCard(i);
            }
        }

        // drawpile = empty, gak ada suit
        if (!hand.isEmpty()) {
            return null;
        }

        return null;
    }

    @Override
    public void display() {
        hand.display();
    }
}
