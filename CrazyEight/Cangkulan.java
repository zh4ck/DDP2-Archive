import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cangkulan {
    private List<InterfacePlayer> players;
    private Deck drawPile;
    private Hand discardPile;
    private Scanner scanner = new Scanner(System.in);
    
    public Cangkulan(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 5) {
            throw new IllegalArgumentException("Jumlah pemain harus antara 2 dan 5!");
        }

        drawPile = new Deck("Draw Pile");
        drawPile.shuffle();

        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            CangkulanPlayer player = new CangkulanPlayer("Pemain " + i);
            drawPile.deal(player.getHand(), 7);
            players.add(player);
        }
        discardPile = new Hand("Discards");
    }

    public boolean isDone() {
        for (InterfacePlayer player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int getCangkulanValue(Card card) {
        if (card.getRank() == 1) {
            return 14;
        }
        return card.getRank();
    }

    public void playGame() {
        int leadingPlayerIndex = 0; // buat pemain pertama

        while (!isDone()) {
            System.out.println("=== BurhanCangkul ===");
            List<Card> trickCards = new ArrayList<>();
            List<InterfacePlayer> trickPlayers = new ArrayList<>();

            Card leadingCard = null;
            int highestValue = -1;
            int winnerIndex = leadingPlayerIndex;

            // ganti gantian main
            for (int i = 0; i < players.size(); i++) {
                int currentPlayerIndex = (leadingPlayerIndex + i) % players.size();
                InterfacePlayer currentPlayer = players.get(currentPlayerIndex);

                System.out.println("Giliran: " + currentPlayer.getName());
                currentPlayer.display();

                Card playedCard = currentPlayer.play(leadingCard, drawPile);

                if (playedCard != null) {
                    System.out.println(currentPlayer.getName() + " mengeluarkan kartu " + playedCard);
                    trickCards.add(playedCard);
                    trickPlayers.add(currentPlayer);
                    discardPile.addCard(playedCard);
                } else {
                    // kalau jenis sama dan nilainya lebih tinggi
                    if (playedCard.getSuit() == leadingCard.getSuit()) {
                        int val = getCangkulanValue(playedCard);
                        if (val > highestValue) {
                            highestValue = val;
                            winnerIndex = currentPlayerIndex;
                        }
                    }
                }

                if (currentPlayer.getHand().isEmpty()) {
                    System.out.println("--> " + currentPlayer.getName() + " kehabisan kartu dan menang!");
                    return;
                }

                System.out.println("====================");
            }
            System.out.println("Pemenang ronde ini: " + players.get(winnerIndex).getName());

            leadingPlayerIndex = winnerIndex;
            System.out.println("Ketik ENTER untuk lanjut...");

            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan jumlah pemain Cangkulan (2-5): ");
        int numPlayers = 2;
        if (scanner.hasNextInt()) {
            numPlayers = scanner.nextInt();
        }

        Cangkulan game = new Cangkulan(Math.max(2, Math.min(5, numPlayers)));
        game.playGame();
        scanner.close();
    }
}
