package solver;

// import reader.*;
import solver.Motion.*;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class SokoBot {


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
      Motion motion = new Motion();
      Game game = new Game();
      Map<Character, List<int[]>> levelMap = new HashMap<>();
      List<Motion.Direction> output;
      retrieveLevelData(width, height, mapData, itemsData, levelMap);

      // Loading player position


      // int x=-1, y=-1;
      Game.State state;
      Motion.Position player = motion.new Position(-1, -1);
      Set<Motion.Position> boxes = new HashSet<Motion.Position>();
      Set<Motion.Position> walls = new HashSet<Motion.Position>();
      Set<Motion.Position> goals = new HashSet<Motion.Position>();

      // Load player position
      for(int[] i: levelMap.get('@')){
        // x= i[0]; y=i[1];
        player.x = i[0];
        player.y = i[1];
      }

      // Load box positions
      for(int[] i: levelMap.get('$')){
        Motion.Position temp = motion.new Position(i[0], i[1]);
        boxes.add(temp);
      }

      // Load wall positions
      for(int[] i : levelMap.get('#') ){
        Motion.Position temp = motion.new Position(i[0], i[1]);
        walls.add(temp);
      }

      // Load goal positions
      for(int[] i : levelMap.get('.') ){
        Motion.Position temp = motion.new Position(i[0], i[1]);
        goals.add(temp);
      }

      state = game.new State(player, boxes);

      output = game.solve(state, walls, goals);

      String outputString ="";
      if(output == null){
        System.out.println("output is empty");
      }
      else 
      for(Direction i :output ){
        // System.out.println(i);
        outputString += i.toString();
      }

      System.out.println(outputString);
      return outputString;

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return "";
  }

}
