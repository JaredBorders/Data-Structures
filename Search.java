import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Methods needed to play a word search game.
 *
 * JARED BORDERS
 * @version 11/24/18
 */

public class Search implements WordSearchGame {

// fields
   private TreeSet<String> lexicon;
   private boolean lex;
   private int minLength;
   private SortedSet<String> validWords;
   private StringBuilder wordSoFar;

// 2D area to search
   private String[][] board;

// visited positions in the search area
   private boolean[][] visited;

// dimensions of the search area
   private int width;
   private int height;

// order in which positions are visited
// used to enumerate positions in the grid.
   private int order;


/**
 *  Construct a new Search object initialized
 *  with a default board.
 */
   public Search() {
      lex = false;
      lexicon = null;
      setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H", "N", "B", "O", "Q", "T", "T", "Y"});
   }

/**
 *  Initializes visited to false.
 */
   private void markAllUnvisited() {
      visited = new boolean[width][height];
      for (boolean[] row : visited) {
         Arrays.fill(row, false); } }

//////////////////////////////////////////////////
// LEXICON METHODS //
//////////////////////////////////////////////////

/**
 * Loads the lexicon into a data structure for later use.
 *
 * @param fileName A string containing the name of the file to be opened.
 * @throws IllegalArgumentException if fileName is null
 * @throws IllegalArgumentException if fileName cannot be opened.
 */
   public void loadLexicon(String fileName) {
      lexicon = new TreeSet<String>();
      if (fileName == null) { throw new IllegalArgumentException(); }

      try
      {
         Scanner scan = new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (scan.hasNext()) {
            String str = scan.next();
            lexicon.add(str.toUpperCase());
            scan.nextLine(); }
      }

      catch (java.io.FileNotFoundException e)
      { throw new IllegalArgumentException(); }

      lex = true;
   }

/**
 * Determines if the given word is in the lexicon.
 *
 * @param wordToCheck The word to validate
 * @return true if wordToCheck appears in lexicon, false otherwise.
 * @throws IllegalArgumentException if wordToCheck is null.
 * @throws IllegalStateException if loadLexicon has not been called.
 */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) { throw new IllegalArgumentException(); }
      if (lex == false) { throw new IllegalStateException(); }
      return lexicon.contains(wordToCheck.toUpperCase()); }

/**
 * Determines if there is at least one word in the lexicon with the
 * given prefix.
 *
 * @param prefixToCheck The prefix to validate
 * @return true if prefixToCheck appears in lexicon, false otherwise.
 * @throws IllegalArgumentException if prefixToCheck is null.
 * @throws IllegalStateException if loadLexicon has not been called.
 */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) { throw new IllegalArgumentException(); }
      if (lex == false) { throw new IllegalStateException(); }

      if (lexicon.ceiling(prefixToCheck) != null) {
         return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
      }
      return false;
   }

//////////////////////////////////////////////////
// BOARD METHODS //
//////////////////////////////////////////////////

   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    *
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) { throw new IllegalArgumentException(); }

      int l = letterArray.length; // length used for indexing
      double n = Math.sqrt(l);

      if (n % 1 != 0) { throw new IllegalArgumentException();
      } else {
         board = new String[(int) n][(int) n];
         width = (int) n;
         height = (int) n;

         int i = 0;
         for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
               board[row][col] = letterArray[i];
               i++;
            }
         }
      }
   }

   /**
    * Creates a String representation of the board, suitable for printing to
    *  standard out. Note that this method can always be called since
    *  implementing classes should have a default board.
    */
   public String getBoard() {
      String strBoard = "";
      for (int i = 0; i < height; i ++)
      {

         if (i > 0) { strBoard += "\n"; }

         for (int j = 0; j < width; j++) {
            strBoard += board[i][j] + " "; }

      }
      return strBoard;
   }


//////////////////////////////////////////////////
// GAME PLAY METHODS //
//////////////////////////////////////////////////

   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */

   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) { throw new IllegalArgumentException(); }
      if (lex == false) { throw new IllegalStateException(); }

      wordSoFar = new StringBuilder();
      markAllUnvisited();
      List<Integer> path = new ArrayList<Integer>();
      wordToCheck = wordToCheck.toUpperCase();

      if (isValidPrefix(wordToCheck)) {
         for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
            {

               if (wordToCheck.equals(board[i][j]))
               {
                  path.add((width * i) + j);
                  return path;
               }

               if (wordToCheck.startsWith(board[i][j].toString()))
               {
                  if (dfs_OneWord(i, j, wordToCheck, wordSoFar, path))
                  {
                     return path;
                  }
               }
            }
         }
      }
      return path;
   }

   private boolean dfs_OneWord(int i, int j, String wordToCheck, StringBuilder wordSoFar, List<Integer> path) {
      Position p = new Position(i, j);

     // is position on BOARD?
      if (!isValid(p)) {
         return false;
      }
     // has position already been visited
      if (isVisited(p)) {
         return false;
      }
     // is position a dead end?
      if (!wordToCheck.startsWith(wordSoFar.toString())) {
         return false;
      }

     // haven't been here before and wordSoFar is a prefix to wordToCheck
      visit(p);
      wordSoFar.append((board[i][j].toString()));
      path.add((width * i) + j);

      if (wordSoFar.toString().equals(wordToCheck)) {
         return true;
      }

      for (Position neighbor : p.neighbors()) {
         if (!isVisited(neighbor)) {
            if (dfs_OneWord(neighbor.x, neighbor.y, wordToCheck, wordSoFar, path)) {
               return true;
            }
         }
      }

     // clean up and backtrack
      visited[i][j] = false;
      path.remove(path.size() - 1);
      wordSoFar.setLength(wordSoFar.length() - board[i][j].length());
      return false;
   }


   /**
    * Computes the cummulative score for the scorable words in the given set.
    * To be scorable, a word must (1) have at least the minimum number of characters,
    * (2) be in the lexicon, and (3) be on the board. Each scorable word is
    * awarded one point for the minimum number of characters, and one point for
    * each character beyond the minimum number.
    *
    * @param words The set of words that are to be scored.
    * @param minimumWordLength The minimum number of characters required per word
    * @return the cummulative score of all scorable words in the set
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (lex == false) { throw new IllegalStateException(); }

      if (!(minimumWordLength >= 1)) { throw new IllegalArgumentException(); }

      int score = 0;
      for (String word: words)
      {
         int length = word.length();
         score += 1 + (length - minimumWordLength);
      }
      return score;
   }

   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    *
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) { throw new IllegalArgumentException(); }
      if (lex == false) { throw new IllegalStateException(); }

      validWords = new TreeSet<String>();
      for (String str : lexicon)
      {
         if (str.length() >= minimumWordLength)
         {
            str = str.toUpperCase();
            int i = (isOnBoard(str)).size();
            if (i != 0)
            {
               validWords.add(str);
            }
         }
      }
      return validWords;
   }

   ////////////////////////////////////////
   // Position class //
   ////////////////////////////////////////

   /**
    * Models an (x,y) position in the grid.
    */
   private class Position {
      int x;
      int y;

      /** Constructs a Position with coordinates (x,y). */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }

      /** Returns a string representation of this Position. */
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }

      /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[8];
         int count = 0;
         Position p;
         // generate all eight neighbor positions
         // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }

   /**
    * Is this position valid.
    */
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < width) && (p.y >= 0) && (p.y < height);
   }

   /**
    * Has this valid position been visited. ??
    */
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }

   /**
    * Mark this valid position as having been visited.
    */
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }
}
