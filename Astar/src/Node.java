
// nút
class Node {
    State state;
    Node parent;
    String action;
    int cost;
    int heuristic;
    int f;
  
    public Node(State state, Node parent, String action, int cost, int heuristic) {
      this.state = state;
      this.parent = parent;
      this.action = action;
      this.cost = cost;
      this.heuristic = heuristic;
      this.f = cost + heuristic;
    }
  
    public int getF() {
      return f;
    }
  
    public void setF(int f) {
      this.f = f;
    }
  
    @Override
    public String toString() {
      return "\n\tNode [state=" + state + ", cost=" + cost + ", heuristic=" + heuristic + ", f=" + f + "]";
    }
  
  }