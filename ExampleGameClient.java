
/**
 * ExampleGameClient.java
 * A sample client for the assignment handout.
 *
 * @author      Dean Hendrix (dh@auburn.edu)
 * @version     2018-03-22
 *
 */
public class ExampleGameClient {

   /** Drives execution. */
   public static void main(String[] args) {
      WordSearchGame game = WordSearchGameFactory.createGame();
      game.loadLexicon("wordfiles/words_medium.txt");
      game.setBoard(new String[]{"C","A","X","T"});
      System.out.print("CAT is on the board at the following positions: ");
      System.out.println(game.isOnBoard("CAT"));
      System.out.print("POPE is not on the board: ");
      System.out.println(game.isOnBoard("POPE"));
      System.out.println("All words of length 3 or more: ");
      System.out.println(game.getAllValidWords(3));
   }
}

/*

RUNTIME OUTPUT:

LENT is on the board at the following positions: [5, 6, 9, 13]
POPE is not on the board: []
All words of length 6 or more:
[ALEPOT, BENTHAL, PELEAN, TOECAP]

 */
