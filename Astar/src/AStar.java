import java.util.*;

public class AStar {
  State initialState; // trạng thái bắt đầu
  PriorityQueue<Node> openSet; // tập open
  Set<Node> closedSet; // tập close

  public AStar(State initialState) {
    this.initialState = initialState;
    this.openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
    this.closedSet = new HashSet<>();
  }

  public static int heuristic(State state, State goal) {
    return Math.abs(state.b1 - goal.b1) + Math.abs(state.b2 - goal.b2) + Math.abs(state.b3 - goal.b3);
  }

  // kiểm tra trạng thái của nút có tồn tại trong open chưa
  private static Node getNodeIfExistsOpen(PriorityQueue<Node> openSet, State targetState) {
    for (Node node : openSet) {
      if (node.state.equals(targetState)) {
        return node;
      }
    }
    return null; // Node không tồn tại
  }

  // kiểm tra trạng thái của nút có tồn tại trong close chưa
  private static Node getNodeIfExistsClose(Set<Node> closeSet, State targetState) {
    for (Node node : closeSet) {
      if (node.state.equals(targetState)) {
        System.out.println("------------------------------------------- " + node.state + ", f=" + node.f);
        return node;
      }
    }
    return null; // Node không tồn tại
  }

  public List<String> solve(State goal) {
    Node initialNode = new Node(initialState, null, null, 0, heuristic(initialState, goal));
    openSet.add(initialNode);
    int steps = 0;
    while (!openSet.isEmpty()) {
      System.out.println("Steps: " + steps++);
      System.out.println("open" + openSet + "\n");
      System.out.println("close" + closedSet + "\n");
      Node currentNode = openSet.poll();
      System.out.println("status under consideration: " + currentNode.state);
      openSet.remove(currentNode);
      closedSet.add(currentNode);

      if (currentNode.state.b1 == 4 || currentNode.state.b2 == 4 || currentNode.state.b3 == 4) {
        return constructPath(currentNode);
      }

      System.out.println("states are born: ");
      for (String action : new Actions().actions(currentNode.state)) {
        State childState = new Actions().result(currentNode.state, action);
        Node childNode = new Node(
            childState,
            currentNode,
            action,
            currentNode.cost + 1,
            heuristic(childState, goal));

        Node oldNodeOpen = getNodeIfExistsOpen(openSet, childState);
        Node oldNodeClose = getNodeIfExistsClose(closedSet, childState);
        if (oldNodeOpen == null && oldNodeClose == null) {
          openSet.add(childNode);
          System.out.println(childNode);
        } else {
          if (!(oldNodeOpen == null) && oldNodeOpen.f > childNode.f) {
            oldNodeOpen.action = childNode.action;
            oldNodeOpen.cost = childNode.cost;
            oldNodeOpen.heuristic = childNode.heuristic;
            oldNodeOpen.parent = childNode.parent;
            oldNodeOpen.f = childNode.f;

            System.out.println(oldNodeOpen);
          }
          if (!(oldNodeClose == null) && oldNodeClose.f > childNode.f) {
            closedSet.remove(oldNodeClose);
            openSet.add(childNode);
            System.out.println(childNode);
          }
        }
      }
      System.out.println("\n---------------------------------------------------------------\n");
    }
    return null;
  }

  public List<String> constructPath(Node node) {
    List<String> path = new ArrayList<>();
    Node originalNode = node;

    int stepsCount = -1;
    while (node != null) {
      stepsCount++;
      node = node.parent;
    }
    System.out.println("-----------------------------");
    System.out.println();
    System.out.println("Duong di:");

    // Reset the node reference to the original node
    node = originalNode;
    while (node != null) {
      if (node.action != null) {
        path.add("\tStep " + (stepsCount--) + ": " + node.action + " -> " + node.state + ", f=" + node.f);
      }
      node = node.parent;
    }
    Collections.reverse(path);
    return path;
  }
}