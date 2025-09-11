package solver;
// import solver.Motion.*;
import solver.Motion.*;
import java.util.*;

import static solver.Motion.*;

public class Game {
    public class Move {
      public State state;
      public Direction dir;

      public Move(State state, Direction dir){
        this.state = state;
        this.dir = dir;
      }
    }

    public class Node {
      public State state;
      public List<Direction> path; 
      public int priority;

      public Node(State state, List<Direction> path, int priority){
        this.state= state;
        this.path = path;
        this.priority = priority;
      }
    }



    public class State {
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
          // System.out.println("(obj instanceof State)? " + (obj instanceof State));
          if ( !(obj instanceof State) ) return false;
          State other = (State) obj;
          return playerPos.equals(other.playerPos) && boxPos.equals(other.boxPos);
        }

        @Override
        public int hashCode() {
          return Objects.hash(playerPos, boxPos);
        }
    }




  public State applyMove(State state, Direction dir){
    Position new_player_pos = state.playerPos.move(dir);
    Set<Position> new_box_pos = new HashSet<>(state.boxPos);

    if (new_box_pos.contains(new_player_pos)){
      Position box_newPos = new_player_pos.move(dir);
      new_box_pos.remove(new_player_pos);
      new_box_pos.add(box_newPos);
    }

    return new State(new_player_pos, new_box_pos);
  }

  public List<Move> retrieveSuccessors(State state, Set<Position> walls){
        List<Move> move_list = new ArrayList<>();
        // List<Move> move_list = new ArrayList();
        
        for (Direction dir: Direction.values()){
                if(isValidMove(state, dir, walls)){

                  State nextState = applyMove(state, dir);
                  move_list.add(new Move(nextState, dir));
                }
        }

        return move_list;
  }

  public static boolean isGoal(State state, Set<Position> goals){
        // System.out.println("Goal found? " + state.boxPos.equals(goals));

        // System.out.println("State: " + state.boxPos);
        // System.out.println("Goal: "  );
        return state.boxPos.equals(goals);
  }




  public List<Direction> solve(State startState, Set<Position> walls, Set<Position> goals){
          
          PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt( n -> n.priority));
          Set<State> visited = new HashSet<>();

          frontier.add(new Node(startState, new ArrayList<>(), heuristic(startState, goals)));

          // System.out.println("Traversing frontier...");
          while(!frontier.isEmpty()){
            System.out.println("Still traversing frontier...");
                  Node node = frontier.poll();
                  State current = node.state;

                  if(isGoal(current, goals)) return node.path;
                  if(visited.contains(current)) continue;

                  visited.add(current);

                  for(Move move : retrieveSuccessors(current, walls)){
                    if(!visited.contains(move.state)) {


                      List<Direction> newPath = new ArrayList<>(node.path);
                      newPath.add(move.dir);

                      int cost= newPath.size() + heuristic(move.state, goals);
                      frontier.add(new Node(move.state, newPath, cost));
                    }
              }      
          }
          // System.out.println("No solution found :(");
          return null; // no solution
  }

  public int heuristic(State state, Set<Position> goals){
    int sum = 0;
    for (Position box : state.boxPos){
      int min_distance = Integer.MAX_VALUE;
      for (Position goal: goals){
        int dist = Math.abs(box.x - goal.x) + Math.abs(box.y - goal.y);
        min_distance= Math.min(min_distance, dist);
      }
      sum += min_distance;
    }

    // System.out.println("Heuristic Value: " + sum);
    return sum;
  }


}
