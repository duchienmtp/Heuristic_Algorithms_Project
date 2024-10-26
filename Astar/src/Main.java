import java.util.*;

//thuật toán


public class Main {
  public static void main(String[] args) {
    //trạng thái bắt đầu
    State initialState = new State(0, 0, 0);
    //trạng thái đích
    State goalState = new State(3, 4, 3);
      
    AStar aStar = new AStar(initialState);
    List<String> solution = aStar.solve(goalState);

    if (solution != null) {
      for (String step : solution) {
        System.out.println(step);
      }
    } else {
      System.out.println("No solution found.");
    }
  }
}
