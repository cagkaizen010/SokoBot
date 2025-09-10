package solver;
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
    UP(-1, 0), DOWN(1, 0), LEFT(-1, 0), RIGHT(0,1);
    public int x_dir;
    public int y_dir;

    Direction(int x_dir, int y_dir){
      this.x_dir = x_dir;
      this.y_dir = y_dir;
    }
  }
}
