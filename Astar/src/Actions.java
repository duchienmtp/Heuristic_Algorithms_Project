import java.util.ArrayList;
import java.util.List;

public class Actions {

  public List<String> actions(State state) {
    List<String> possibleActions = new ArrayList<>();
    // Add actions based on the problem requirements
    if (state.b1 < 3) {
      possibleActions.add("Full b1");
    }
    if (state.b2 < 5) {
      possibleActions.add("Full b2");
    }
    if (state.b3 < 8) {
      possibleActions.add("Full b3");
    }
    if (state.b1 > 0) {
      possibleActions.add("Emty b1");
      possibleActions.add("Pour from b1 to b2");
      possibleActions.add("Pour from b1 to b3");
    }
    if (state.b2 > 0) {
      possibleActions.add("Emty b2");
      possibleActions.add("Pour from b2 to b1");
      possibleActions.add("Pour from b2 to b3");
    }
    if (state.b3 > 0) {
      possibleActions.add("Emty b3");
      possibleActions.add("Pour from b3 to b1");
      possibleActions.add("Pour from b3 to b2");
    }
    return possibleActions;
  }

  public State result(State state, String action) {
    switch (action) {
      case "Full b1":
        return new State(3, state.b2, state.b3);
      case "Full b2":
        return new State(state.b1, 5, state.b3);
      case "Full b3":
        return new State(state.b1, state.b2, 8);
      case "Emty b1":
        return new State(0, state.b2, state.b3);
      case "Emty b2":
        return new State(state.b1, 0, state.b3);
      case "Emty b3":
        return new State(state.b1, state.b2, 0);
      case "Pour from b1 to b2":
        return new State(Math.max(0, state.b1 - (5 - state.b2)), Math.min(5, state.b1 + state.b2), state.b3);
      case "Pour from b1 to b3":
        return new State(Math.max(0, state.b1 - (8 - state.b3)), state.b2, Math.min(8, state.b1 + state.b3));
      case "Pour from b2 to b1":
        return new State(Math.min(3, state.b1 + state.b2), Math.max(0, state.b2 - (3 - state.b1)), state.b3);
      case "Pour from b2 to b3":
        return new State(state.b1, Math.max(0, state.b2 - (8 - state.b3)), Math.min(8, state.b2 + state.b3));
      case "Pour from b3 to b1":
        return new State(Math.min(3, state.b1 + state.b3), state.b2, Math.max(0, state.b3 - (3 - state.b1)));
      case "Pour from b3 to b2":
        return new State(state.b1, Math.min(5, state.b2 + state.b3), Math.max(0, state.b3 - (5 - state.b2)));
      default:
        return state;
    }
  }

}
