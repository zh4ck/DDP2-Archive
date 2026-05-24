public class CangkulanPlayer implements IPlayer {
    private String name;
    private Hand hand;

    // DIP: UI diisolasi atau disederhanakan, tidak hardcoded ke game logic yang kaku.
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
            // First to play, play the first card in hand (or random/strategy)
            return hand.popCard(0);
        }

        int requiredSuit = leadingCard.getSuit();
        
        // Cangkul (Draw) if no matching suit
        while (!hasSuit(requiredSuit) && !drawPile.isEmpty()) {
            Card drawn = drawPile.popCard();
            System.out.println(name + " mengcangkul (draw) " + drawn);
            hand.addCard(drawn);
        }

        // Play the matching suit card
        for (int i = 0; i < hand.size(); i++) {
            if (hand.getCard(i).getSuit() == requiredSuit) {
                return hand.popCard(i);
            }
        }

        // If draw pile is empty and still no suit, just discard any card
        if (!hand.isEmpty()) {
            return hand.popCard(0);
        }

        return null;
    }

    @Override
    public void display() {
        hand.display();
    }
}
