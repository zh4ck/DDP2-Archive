import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cangkulan {

    private List<IPlayer> players; // List ini boleh berisi objek dari class apa pun, asalkan class tersebut mengimplementasikan interface IPlayer
    private Deck drawPile;
    private Hand discardPile;
    private Scanner in;

    public Cangkulan(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 5) {
            throw new IllegalArgumentException("Jumlah pemain harus antara 2 hingga 5.");
        }

        drawPile = new Deck("Draw Pile");
        drawPile.shuffle();

        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            CangkulanPlayer player = new CangkulanPlayer("Pemain " + i);
            drawPile.deal(player.getHand(), 7); // Bagikan 7 kartu per pemain
            players.add(player);
        }

        discardPile = new Hand("Discards");
        in = new Scanner(System.in);
    }

    public boolean isDone() {
        for (IPlayer p : players) {
            if (p.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private int getCangkulanValue(Card card) {
        if (card.getRank() == 1) { // Ace lebih tinggi dari King
            return 14;
        }
        return card.getRank();
    }

    public void playGame() {
        int leadingPlayerIndex = 0; // Pemain 1 memulai ronde pertama

        while (!isDone()) {
            System.out.println("\n--- RONDE BARU ---");
            List<Card> trickCards = new ArrayList<>();
            List<IPlayer> trickPlayers = new ArrayList<>();

            Card leadingCard = null;
            int highestValue = -1;
            int winnerIndex = leadingPlayerIndex;

            // Setiap pemain bergiliran dalam satu ronde (trick)
            for (int i = 0; i < players.size(); i++) {
                int currentPlayerIndex = (leadingPlayerIndex + i) % players.size();
                IPlayer currentPlayer = players.get(currentPlayerIndex);

                System.out.println("Giliran: " + currentPlayer.getName());
                // Tampilkan tangan pemain (Optional)
                currentPlayer.display();

                Card playedCard = currentPlayer.play(leadingCard, drawPile);
                
                if (playedCard != null) {
                    System.out.println(currentPlayer.getName() + " mengeluarkan kartu " + playedCard);
                    trickCards.add(playedCard);
                    trickPlayers.add(currentPlayer);
                    discardPile.addCard(playedCard);

                    if (leadingCard == null) {
                        leadingCard = playedCard;
                        highestValue = getCangkulanValue(playedCard);
                        winnerIndex = currentPlayerIndex;
                    } else {
                        // Jika jenis sama dan nilainya lebih tinggi
                        if (playedCard.getSuit() == leadingCard.getSuit()) {
                            int val = getCangkulanValue(playedCard);
                            if (val > highestValue) {
                                highestValue = val;
                                winnerIndex = currentPlayerIndex;
                            }
                        }
                    }
                }

                // Cek jika pemain ini kehabisan kartu dan menang, hentikan trick
                if (currentPlayer.getHand().isEmpty()) {
                    System.out.println(">>> " + currentPlayer.getName() + " KEHABISAN KARTU DAN MENANG! <<<");
                    return;
                }

                System.out.println("-----------------");
            }

            System.out.println("Pemenang ronde ini adalah: " + players.get(winnerIndex).getName());
            leadingPlayerIndex = winnerIndex; // Pemenang jalan pertama di ronde berikutnya
            
            System.out.println("Ketik ENTER untuk lanjut...");
            in.nextLine();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan jumlah pemain Cangkulan (2-5): ");
        int numPlayers = 2;
        if (scanner.hasNextInt()) {
            numPlayers = scanner.nextInt();
        }
        
        Cangkulan game = new Cangkulan(Math.max(2, Math.min(5, numPlayers)));
        game.playGame();
        scanner.close();
    }
}
