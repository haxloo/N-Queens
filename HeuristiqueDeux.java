import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeuristiqueDeux {
private int n;
private PriorityQueue<Node> frontier;
private int generatedNodes;
private int expandedNodes;
public HeuristiqueDeux(int n) {
    this.n = n;
    this.frontier = new PriorityQueue<>((n1, n2) -> n1.getF() - n2.getF());
    this.generatedNodes = 0;
    this.expandedNodes = 0;
}

public Node search() {
    // Initialize the start node
    Node startNode = new Node(new int[n], 0, 0);
    startNode.getHeu(n);
    frontier.add(startNode);

    while (!frontier.isEmpty()) {
        // Choose the node with the lowest f value
        Node currentNode = frontier.poll();
        this.expandedNodes++;

        // Check if the node is a goal state
        if (currentNode.isGoal(n)) {
            return currentNode;
        }

        // Expand the node and add the children to the frontier
        ArrayList<Node> children = currentNode.getChildren(n);
        for (Node child : children) {
            child.cost = currentNode.getG() + 1;
            child.getHeu(n);
            frontier.add(child);
            this.generatedNodes++;
        }
    }

    // Return null if no solution found
    return null;
}

public int getGeneratedNodes() {
    return this.generatedNodes;
}

public int getExpandedNodes() {
    return this.expandedNodes;
}
}