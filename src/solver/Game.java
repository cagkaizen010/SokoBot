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

    public class DeadlockDetector {
      public boolean cornerCheck(Position boxPos, Set<Position> walls){
        boolean nw, ne, se, sw;
        // System.out.println("Inside cornerCheck()");
        nw = walls.contains(boxPos.move(Direction.u)) && walls.contains(boxPos.move(Direction.l));
        ne = walls.contains(boxPos.move(Direction.u)) && walls.contains(boxPos.move(Direction.r));
        sw = walls.contains(boxPos.move(Direction.d)) && walls.contains(boxPos.move(Direction.l));
        se = walls.contains(boxPos.move(Direction.d)) && walls.contains(boxPos.move(Direction.r));

        return nw || ne || sw || se;
      }

      public boolean wallAdjNoGoalCheck(Position boxPos, Set<Position> walls, Set<Position> goals) {
        boolean up, down, left, right;

        System.out.println("Inside wallAdjNoGoalCheck()");
        // Check for goal north of box
        up = walls.contains(boxPos.move(Direction.u)) || goals.contains(boxPos.move(Direction.u));
        // Check for goal south of box
        down = walls.contains(boxPos.move(Direction.d)) || goals.contains(boxPos.move(Direction.d));
        // Check for goal west of box
        left = walls.contains(boxPos.move(Direction.l)) || goals.contains(boxPos.move(Direction.l));
        // Check for goal east of box
        right= walls.contains(boxPos.move(Direction.r)) || goals.contains(boxPos.move(Direction.r));

        return !(up || down || left || right);
      }

      public boolean deadlockCheck(State state, Set<Position> walls, Set<Position> goals){
        for( Position i : state.boxPos) {
          if (cornerCheck(i, walls)) return true;
          /*
           * Problem is here
           */
          if (wallAdjNoGoalCheck(i, walls, goals)) return true;
        }

        return false;
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

        return state.boxPos.equals(goals);
  }


  public List<Direction> solve(State startState, Set<Position> walls, Set<Position> goals){
          
          PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt( n -> n.priority));
          Set<State> visited = new HashSet<>();
          DeadlockDetector deadlockDetect = new DeadlockDetector();

          frontier.add(new Node(startState, new ArrayList<>(), heuristic(startState, goals)));

          // System.out.println("Traversing frontier...");
          while(!frontier.isEmpty()){
            // System.out.println("Still traversing frontier...");
                  Node node = frontier.poll();
                  State current = node.state;

                  // Skip state if deadlock is detected
                  if (deadlockDetect.deadlockCheck(current, walls, goals)) continue;

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
