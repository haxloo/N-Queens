import java.util.ArrayList;

public class Node {
    public int[] state;
    public int level;
    public int cost;
    public int h;

    public Node(int[] state, int level, int cost) {
        this.state = state;
        this.level = level;
        this.cost = cost;
    }

    public boolean isGoal(int n) {
        // Check if the state represents a goal state (no queens attacking each other)
        if (n <= 1) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (state[i] == state[j] || state[i] - i == state[j] - j || state[i] + i == state[j] + j) {
                    return false;
                }
            }
        }
        return true;
    }


    public ArrayList<Node> getChildren(int n) {
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] childState = new int[state.length];
            System.arraycopy(state, 0, childState, 0, state.length);
            if (level < state.length) {
                childState[level] = i;
                Node childNode = new Node(childState, level + 1, 0);
                children.add(childNode);
            }
        }
        return children;
    }


    public int getH(int n) {
        // Get heuristic value for A* search
        int h = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (state[i] == state[j] || state[i] - i == state[j] - j || state[i] + i == state[j] + j) {
                    h++;
                }
            }
        }
        this.h = h;
        return h;
    }
    public int getHeu(int n) {
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (state[i] != i) {
                h++;
            }
        }
        this.h = h;
        return h;
    }


    public int getF() {
        // Get f value for A* search
        return cost + h;
    }

    public int getG() {
        // Get g value for A* search
        return cost;
    }

    public String[][] printState() {
    	String [][] result = new String [state.length][state.length];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i] == j) {
                    System.out.print("Q ");
                    result[i][j]="Q ";
                } else {
                    System.out.print("- ");
                    result[i][j]="-  ";
                }
            }
            System.out.println();
        }
        System.out.println();
		return result;
    }
}
