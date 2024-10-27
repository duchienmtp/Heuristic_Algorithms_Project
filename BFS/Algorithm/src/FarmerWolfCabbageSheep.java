import java.util.ArrayList;
import java.util.Iterator;

public class FarmerWolfCabbageSheep {

    public FarmerWolfCabbageSheep() {
    }

    /**
     */
    public void printSolution(ArrayList<Node> solutions) {
        System.out.println("So giai phap:  " + solutions.size());
        ArrayList<Node> stack;

        Iterator<Node> iter = solutions.iterator();
        int i = 1;
        while (iter.hasNext()) {
            stack = new ArrayList<Node>();
            Node n = iter.next();
            stack.add(n);

            n = n.parent;
            while (n != null) {
                stack.add(n);
                n = n.parent;
            }
            System.out.println("Giai phap " + i);
            printSequence(stack);
            i++;
        }

    }

    private void printSequence(ArrayList<Node> stack) {
        StringBuffer buf = new StringBuffer();
        buf.append("So lan di chuyen: ");
        buf.append(stack.size() - 1);
        buf.append("\n");
        for (int i = stack.size() - 1; i >= 0; i--) {
            Node n = stack.get(i);
            buf.append(n.data.toString());
            if (i != 0) {
                buf.append("--");
                buf.append(stack.get(i - 1).move);
                buf.append("->>");

            }
        }
        System.out.println(buf.toString());
    }

    public void printBFS(){
    }

    public static void main(String[] args) {
        System.out.println("Giai bai toan Wolf, Sheep, Cabbage, Farmer, River Crossing\n");
        FarmerWolfCabbageSheep obj = new FarmerWolfCabbageSheep();

        System.out.println("Do thi trang thai BFS");
        bfs BFS = new bfs();
        BFS.startBreadthFirstSearch();

        System.out.println("\n\n");
        System.out.println("Loi giai cho bai toan River Crossing BFS:");
        obj.printSolution(BFS.getSolutions());

        System.out.println(
                "\n\n==================================================================================\n==================================================================================");

    }
}
