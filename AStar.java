import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
private int n;
private PriorityQueue<Node> frontier;
private int nodesGenerated;
private int nodesExpanded;
public AStar(int n) {
    this.n = n;
    this.frontier = new PriorityQueue<>((n1, n2) -> n1.getF() - n2.getF());
    this.nodesGenerated = 0;
    this.nodesExpanded = 0;
}

public Node search() {
    // Initialize the start node
    Node startNode = new Node(new int[n], 0, 0);
    startNode.getH(n);
    frontier.add(startNode);
    nodesGenerated++;

    while (!frontier.isEmpty()) {
        // Choose the node with the lowest f value
        Node currentNode = frontier.poll();
        nodesExpanded++;

        // Check if the node is a goal state
        if (currentNode.isGoal(n)) {
            return currentNode;
        }

        // Expand the node and add the children to the frontier
        ArrayList<Node> children = currentNode.getChildren(n);
        for (Node child : children) {
            child.cost = currentNode.getG() + 1;
            child.getH(n);
            if (!frontier.contains(child)) {
                frontier.add(child);
                nodesGenerated++;
            }
        }
    }

    // Return null if no solution found
    return null;
}

public int getNodesGenerated() {
    return nodesGenerated;
}

public int getNodesExpanded() {
    return nodesExpanded;
}
}