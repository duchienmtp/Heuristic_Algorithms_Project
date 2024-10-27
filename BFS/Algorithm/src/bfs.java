import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class bfs {
  private Node root;
  private ArrayList<Node> queue;
  private ArrayList<Node> solutions;
  private String[] moves = { "F", "FW", "FS", "FC" };

  public bfs() { 
    queue = new ArrayList<Node>();
    solutions = new ArrayList<Node>();
  }

  // Phương thức bắt đầu thuật toán BFS. tìm kiếm, chuyển sang trạng thái cho phép
  public void startBreadthFirstSearch() {
    LinkedList<State> open = new LinkedList<>();
    LinkedList<State> close = new LinkedList<>();
    solutions = new ArrayList<Node>();
    TreeSet<String> left = new TreeSet<String>();
    left.add("W");
    left.add("S");
    left.add("C");
    left.add("F");

    State inits = new State("left", left, new TreeSet<String>());
    root = new Node(inits);
    root.level = 0;
    queue.add(root);
    open.add(inits);

    while (!queue.isEmpty()) {
      boolean flag = false;
      Node n = queue.remove(0);
      System.out.println("Level: " + n.level + "\n\topen: " + open + "\n\tclose: " + close
          + "\n\tTrang thai dang xet: " + n.data);
      for (String m : moves) {
        State s = n.data.transits(m); // cập nhật trạng thái s = trạng thái sau khi di chuyển m
        if (s != null) // nếu s!=null (có đối tượng cần di chuyển)
        {
          if (s.isAllow()) // các ràng buộc là hợp lệ
          {
            // cập nhật nút con, nút cha mới, tăng level, trạng thái di chuyển của m
            Node child = new Node(s);
            child.parent = n;
            child.level = n.level + 1;
            child.move = m + " moves " + child.data.getBank();

            // kiểm tra nút con có khác các nút tổ tiên của nó không
            if (!child.isAncestor()) {
              n.childList.add(child); // thêm vào danh sách
              if (child.data.isSolution() == false) // kiểm tra child còn ở bờ bên trái k
              {
                open.add(s);
                queue.add(child);
                System.out.println("\tTrang thai duoc sinh ra: " + child.data);
              } else {
                solutions.add(child);
                System.out.println(
                    "============ Da tim thay giai phap " + child.data + " ================");
                flag = true;
              }
            } else {
              System.out.println("\tTrang thai da ton tai: " + s);
            }
          } else {
            System.out.println("\tTrang thai vi pham rang buoc: " + s);
          }
        }
      }
      System.out.println("------------------\n");
      close.addFirst(open.getFirst());
      open.remove(open.getFirst());

      if (open.isEmpty() || flag == true) {
        System.out.println("Level: " + n.level + "\n\topen: " + open + "\n\tclose: " + close
            + "\n\tTrang thai dang xet: " + n.data);
        System.out.println("------------------\n");
      }
    }
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(Node root) {
    this.root = root;
  }

  public ArrayList<Node> getQueue() {
    return queue;
  }

  public void setQueue(ArrayList<Node> queue) {
    this.queue = queue;
  }

  public ArrayList<Node> getSolutions() {
    return solutions;
  }

  public void setSolutions(ArrayList<Node> solutions) {
    this.solutions = solutions;
  }

  public String[] getMoves() {
    return moves;
  }

  public void setMoves(String[] moves) {
    this.moves = moves;
  }

}
