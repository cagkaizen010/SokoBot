package solver;

// import reader.*;
import solver.Motion.*;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Position playerPos;
    public Set<Position> boxPos;

    // Define state:
    //  - Position of player
    //    - Single point
    //  - Position of box
    //    - Multiple points 
    public State(Position playerPos , Set<Position> boxPos){ 
      this.playerPos = playerPos; // Store current coordinates of player
      this.boxPos= new HashSet<>(boxPos);  // Store current coordinates of box
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if ( !(obj instanceof State) ) return false;
      State other = (State) obj;
      return playerPos.equals(other.playerPos) && boxPos.equals(other.boxPos);
    }

    @Override
    public int hashCode() {
      return Objects.hash(playerPos, boxPos);
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


  public void retrieveLevelData(int width, int height, char[][] mapData, char[][] itemsData, Map<Character, List<int[]>> levelMap){
    
    // Insert level data into a map
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
      Map<Character, List<int[]>> levelMap = new HashMap<>();
      retrieveLevelData(width, height, mapData, itemsData, levelMap);

      /*
       *  Output Map data
       */
      // for (Map.Entry<Character, List<int[]>> entry : levelMap.entrySet()) {
      //   char symbol = entry.getKey();
      //   List<int[]> coords = entry.getValue();

      //   System.out.print(symbol + " -> ");
      //   for (int[] pos: coords) {
      //     System.out.print("(" + pos[0] + ", " + pos[1] + ")");
      //   }
      //   System.out.println();
      // }
     
      
      /*
       * Output the player position
       */
      // for(int[] pos: levelMap.get('@')){
      //   System.out.println("("+ pos[0]+ ", "+ pos[1] +")");
      // }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return "";
  }

}
