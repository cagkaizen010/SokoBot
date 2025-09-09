package solver;

// import reader.*;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    Map<Character, List<int[]>> levelMap = new HashMap<>();

  private class State {
    public int[] playerPos;
    public List<int[]> boxPos;
    public List<int[]> goalPos;


    // Define state:
    //  - Position of player
    //    - Single point
    //  - Position of box
    //    - Multiple points 
    public State(int[] playerPos, int[][] boxPos){
        // this.playerPos.add(playerPos); // Store current coordinates of player
        // this.boxPos= boxPos;  // Store current coordinates of box
    }
  }

  /*
    Inputs:
      - Current position of character
      - 
  */
  private void calc_reach() {

  }

  // public void setMapData(char[][] mapData) {

  //   for(int i = 0; i < mapData.length; i++)
  //     for(int j = 0; j < mapData[i].length; j++ ){

  //       if(mapData[i][j] == '#' )
  //         walls.add(new int[]{i,j});

  //       if(mapData[i][j] == '.') 
  //         goals.add(new int[]{i,j });

  //     }   
  // }

  public void getItemsData(char[][] itemsData){
    for(int i = 0; i < itemsData.length; i++)
      System.out.println(itemsData[i]);
  }

  public void retrieveLevelData(int width, int height, char[][] mapData, char[][] itemsData){
    
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++ ){
        char c_m = mapData[i][j];
        char c_i = itemsData[i][j];
        if( c_m != ' ' || c_i != ' '){
          if( c_m != ' ') levelMap.computeIfAbsent(c_m, k -> new ArrayList<>()).add(new int[]{i, j});
          if( c_i != ' ') levelMap.computeIfAbsent(c_i, k -> new ArrayList<>()).add(new int[]{i, j});
        }

      }
    }
  }



  public String solveSokobanPuzzle(int width, int height, char[][] mapData, char[][] itemsData) {
    
    try {

      // setMapData(mapData);

      retrieveLevelData(width, height, mapData, itemsData);
      

      for (Map.Entry<Character, List<int[]>> entry : levelMap.entrySet()) {
        char symbol = entry.getKey();
        List<int[]> coords = entry.getValue();

        System.out.print(symbol + " -> ");
        for (int[] pos: coords) {
          System.out.print("(" + pos[0] + ", " + pos[1] + ")");
        }
        System.out.println();
      }
      
        for(int[] pos: levelMap.get('@')){
          System.out.println("("+ pos[0]+ ", "+ pos[1] +")");
        }
      
      

      // for(int i = 0; i < goals.size(); i++){

      //   // for(int j: goals.get(i)) System.out.print(j);
      //   System.out.println(goals.at());
      // }
      
      /*
       * Printing out variables of type List<int[]>
       */
      // for (int[] arr : goals)     
      //   System.out.println(Arrays.toString(arr));


    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return "";
  }

}
