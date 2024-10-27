import java.util.ArrayList;

// class Node để xây dựng biểu đồ State
public class Node {
  public Node parent; // nút cha
  public State data; // trạng thái của nút
  public ArrayList<Node> childList; // ds nút con
  public int level; // độ sâu của nút
  public String move;

  public Node(State data) {
    parent = null;
    this.data = data;
    childList = new ArrayList<Node>();
    level = 0;
    move = "";
  }

  /**Kiểm tra xem một Nút có cùng Trạng thái có phải là tổ tiên của Nút hiện tại hay không.
  * @return true if a an ancestor node has the same state, false otherwise */
  public boolean isAncestor() {
    Node n = parent;
    boolean ret = false;
    while (n != null) {
      if (data.compare(n.data)) {
        ret = true;
        break;
      }
      n = n.parent;
    }
    return ret;
  }
}