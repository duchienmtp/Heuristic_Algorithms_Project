package ColorGraph;

public class ColorGraphGreedy {
    public int n, sm;
    public int[] m;
    public int[][] matrix;

    public ColorGraphGreedy() {
        sm = 0;
        n = 5;
        m = new int[5];
        matrix = new int[][] {
                { 0, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 1, 1 },
                { 0, 1, 1, 0, 0 },
                { 1, 0, 1, 0, 0 }
        };
    }

    public void color() {
        int check;
        for (int i = 0; i < n; i++) {
            if (m[i] == 0) {
                sm++;
                m[i] = sm;
                for (int j = i + 1; j < n; j++)
                    if ((matrix[i][j] == 0) && (m[j] == 0))
                    {
                        check = 1;
                        for (int k = i + 1; k < j; k++)
                            if ((matrix[k][j] == 1) && (m[i] == m[k]))
                            {
                                check = 0;
                                break;
                            }
                        if (check == 1)
                            m[j] = sm;
                    }
            }
        }
    }

    public void printColor() {
        System.out.println("Matrix: ");
        for (int i = 0; i < 5; i ++) {
            for (int j = 0; j < 5; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        for (int i = 0; i < sm; i++)
        {
            System.out.print("Color " + (i + 1) + ": ");
            for (int j = 0; j < n; j++)
                if (m[j] == (i + 1))
                    System.out.print((j + 1) + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ColorGraphGreedy obj = new ColorGraphGreedy();
        obj.color();
        obj.printColor();
    }
}
