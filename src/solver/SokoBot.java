package solver;

// import reader.*;
import java.util.ArrayList;

public class SokoBot {

    // State? Position of player and boxes
    // Action? Player moving and colliding with boxes
    // Cost? Amount of moves player makes


    // End points:
    //  - $ cant move adjacent, END
    //  - $ can only move in one direction, but . isnt in same axis as goal, END
    //  - 

    /* Intermediate Actions:
      - Define reachability()
        - Player can go to adjacent block

      -- basic actions 
      - Define move()

      --  used to undo if state cant work
      - Define unmove()

    */


    // Define actions
    //  - Push
    //    - When can we push?
    //      - Reachable by the player
    //      - Tile opposite of player is empty
    // public void push(){
      
    // }


    // BACKTRACKING (naive solution)
    // This cannot be done through backtracking, explain later.
    /*

    */


  private class State {
    public char[][] playerPos;
    public char[][] boxPos;
    public char[][] goalPos;


    // Define state:
    //  - Position of player
    //  - Position of box
    //  - Position of goal
    public State(char[][] playerPos, char[][] boxPos, char[][] goalPos){
        this.playerPos = playerPos; // Store current coordinates of player
        this.boxPos= boxPos;  // Store current coordinates of box
        this.goalPos= goalPos;  // Store current coordinates of goal
    }
  }

  private ArrayList<int[]> walls = new ArrayList<>(); 
  private ArrayList<int[]> goals = new ArrayList<>();
  // private int[] boxes =[];
  

  public int setMapData(char[][] mapData) {

    // for( int j = 0; j < mapData.length; j++){       // rows
    //   for(int i = 0; i < mapData[j].length; i++ )   // cols
    //     System.out.print(mapData[j][i]);
    //   System.out.println("");
    // }


    for(int i = 0; i < mapData.length; i++)
      for(int j = 0; j < mapData[i].length; j++ ){
        // if(mapData[i][j] == '#' ) System.out.println(i+ " " + j);
        // if(mapData[i][j] == '.') System.out.println("goal point: " + i + " " + j);


        if(mapData[i][j] == '#' ){
          int[] input = {i, j};
          walls.add(input);

        }
        if(mapData[i][j] == '.') {
          // int[] input = {i, j};
          goals.add(Arrays.asList(i, j));
        }
      }

    return 0;
  }

  public void getItemsData(char[][] itemsData){
    for(int i = 0; i < itemsData.length; i++)
      System.out.println(itemsData[i]);
  }



  public String solveSokobanPuzzle(int width, int height, char[][] mapData, char[][] itemsData) {
    /*
     * YOU NEED TO REWRITE THE IMPLEMENTATION OF THIS METHOD TO MAKE THE BOT SMARTER
     */
    /*
     * Default stupid behavior: Think (sleep) for 3 seconds, and then return a
     * sequence
     * that just moves left and right repeatedly.
     */



    try {
      // Thread.sleep(3000);
      // System.out.println(mapData[1][1]);
      // getMapData(mapData[][]);
      setMapData(mapData);

      for(int i = 0; i < goals.size(); i++){

        for(int j: goals.get(i)) System.out.print(j);
        System.out.println(goals.contains(Arrays.asList(3,4)));
      }


      // System.out.println(walls)
      // getItemsData(itemsData);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return "";
  }

}
