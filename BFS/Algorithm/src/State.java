import java.util.TreeSet;
//*Lớp State dùng để biểu diễn 1 trạng thái của các đối tượng cần qua sông: nôngdân (F), sói (W), bắp cải (C), cừu (S)
public class State {
  private String bank; // bờ mà người dân hiện đang ở
  private TreeSet<String> left, right; // bờ trái, bờ phải lưu trữ những đối tượng ở đó

  public State(String bank, TreeSet<String> left, TreeSet<String> right) {
      this.bank = bank;
      this.left = left;
      this.right = right;
  }

  /**Lấy một TreeSet<String> chứa các đối tượng ở bờ sông (trái hoặc phải) và kiểm tra xem câu đố có vi phạm ràng buộc không
   * Sói và cừu không thể ở cùng nhau nếu không có nông dân
   * Bắp cải và cừu không thể ở cùng nhau nếu không có nông dân.
   * @param b TreeSet<String> đại diện cho bờ sông và các đối tượng đang ở đó
   * @return trả về true nếu đáp ứng các ràng buộc, nếu sai trả về false */
  private boolean checkAllowBank(TreeSet<String> b) {
      // W(sói) và S(cừu) ở cùng bờ nhưng F(nông dân) k ở cùng bờ
      if (b.contains("W") && b.contains("S") && (b.contains("F") == false))
          return false;
      // S(cừu) và C(bắp cải) ở cùng bờ nhưng F(nông dân không ở cùng bờ)
      if (b.contains("S") && b.contains("C") && (b.contains("F") == false))
          return false;
      return true;
  }

  /**Phương thức để kiểm tra nếu trạng thái đáp ứng các ràng buộc
  * @return true nếu trạng thái không vi phạm ràng buộc ở cả 2 bờ, ngược lại false */
  public boolean isAllow() {
    if (checkAllowBank(left) && checkAllowBank(right))
      return true;
    else
      return false;
  }

  /** Kiểm tra trạng thái đã đúng với lời giải bài toán chưa (ví dụ đưa toàn bộ các đối tượng từ bờ trái sang hết bowd phải) 
   * @return true nếu đã đúng lời giải, ngược lại false */
  public boolean isSolution() {
    if (left.isEmpty() && right.contains("W") && right.contains("S") && right.contains("C") && right.contains("F"))
      return true;
    else
      return false;
  }

  /**Chuyển sang trạng thái mới dựa trên việc di chuyển
  * @param move tham số chứa các bước di chuyển (F, FW, FS, FC) để chuyển sang trạng thái con mới
  * @return trả về 1 State mới dựa trên việc di chuyển */
  public State transits(String move) {
    String nbank;
    TreeSet<String> nleft = new TreeSet<String>(); // tạo treeset left mới
    TreeSet<String> nright = new TreeSet<String>(); // tạo treeset left mới

    if (bank.equalsIgnoreCase("left")) // kiểm tra bờ của người dân
      nbank = "right";
    else
      nbank = "left";

    copylist(right, nright);
    copylist(left, nleft);

    for (int i = 0; i < move.length(); i++) {
      String item = move.substring(i, i + 1);
      if (bank.equalsIgnoreCase("left")) // di chuyển các đối tượng ở bờ bên trái sang bờ bên phải
      {
        if (nleft.remove(item)) // xóa đối tượng đang xét trong treeset mới
          nright.add(item); // sau đó thêm vào bờ bên phải
        else
          return null; // trả về null nếu bờ bên bên trái không có đối tượng cần di chuyển
      } else // di chuyển các đối tượng ở bờ bên phải sang bờ bên trái
      {
        if (nright.remove(item))
          nleft.add(item);
        else
          return null;
      }
    }
    return new State(nbank, nleft, nright); // trả về trạng thái mới sau khi cập nhật
  }

  /** sao chép các đối tượng trong TreeSet từ src đến dst */
  private void copylist(TreeSet<String> src, TreeSet<String> dst) {
    for (String e : src)
      dst.add(e);
  }

  /**So sánh trạng thái hiện tại với trạng thái cụ thể
   * @param s là State can so sánh
   * @return true nếu trạng thái hiện tại và trạng thái được chỉ định giống nhau, ngược lại false */
  public boolean compare(State s) {
    TreeSet<String> tmp;
    if (!s.getBank().equalsIgnoreCase(bank))
      return false;
    tmp = s.getLeft();
    for (String e : left) {
      if (!tmp.contains(e)) // nếu có trạng thái khái với trạng thái ở Left return false
        return false;
    }

    tmp = s.getRight();
    for (String e : right) {
      if (!tmp.contains(e))
        return false;
    }
    return true;
  }

  public String getBank() {
    return bank;
  }

  public TreeSet<String> getLeft() {
    return left;
  }

  public TreeSet<String> getRight() {
    return right;
  }

  @Override
  public String toString() {
    StringBuffer ret = new StringBuffer();
    ret.append("{L:");
    for (String e : left)
      ret.append(e);
    ret.append("  ");
    ret.append("R:");
    for (String e : right)
      ret.append(e);
    ret.append("}");
    return ret.toString();
  }
}
