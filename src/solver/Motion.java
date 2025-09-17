package solver;
import solver.Game.*;
import java.util.*;

public class Motion {
  public class Position{
    public int x;
    public int y;

    public Position(int x, int y){
      this.x = x;
      this.y = y;
    }

    public Position move(Direction dir) {
      return new Position(x+ dir.x_dir, y+ dir.y_dir);
    }

    @Override
    public boolean equals(Object obj){
      if(!(obj instanceof Position)) return false;
      Position tempPos = (Position) obj;

      return x == tempPos.x && y == tempPos.y;
    }

    @Override
    public int hashCode() {

      return Objects.hash(x, y);
    }
  }

  public enum Direction{
    u(-1, 0), d(1, 0), l(0, -1), r(0,1);
    public int x_dir;
    public int y_dir;

    Direction(int x_dir, int y_dir){
      this.x_dir = x_dir;
      this.y_dir = y_dir;
    }

    public Direction getOpposite() {
      switch (this) {
        case u: return d;
        case d: return u;
        case l: return r;
        case r: return l;
        default:  throw new IllegalStateException("Unexpected value: " + this);
      }
    }
  }


// Check here as implementation continues
//
// NEEDS DEADLOCK DETECTION
  public static boolean isValidMove(State state, Direction dir, Set<Position> walls){
    Position next_pos = state.playerPos.move(dir);

    if(walls.contains(next_pos)) return false;

    if(state.boxesPos.contains(next_pos)){
      Position next_box = next_pos.move(dir);
      return (!walls.contains(next_box) && !state.boxesPos.contains(next_box));
    };

    return true;
  }




}
