import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
private int n;
private int nodesGenerated;
private int nodesExpanded;
public BFS(int n) {
    this.n = n;
    this.nodesGenerated = 0;
    this.nodesExpanded = 0;
}

public Node search() {
    Queue<Node> queue = new LinkedList<>();
    HashSet<String> visited = new HashSet<>();
    Node initialNode = new Node(new int[n], 0, 0);
    queue.add(initialNode);
    visited.add(initialNode.toString());
    nodesGenerated++;

    while (!queue.isEmpty()) {
        Node curr = queue.poll();
        nodesExpanded++;
        if (curr.isGoal(n)) {
            return curr;
        }
        for (Node child : curr.getChildren(n)) {
            if (!visited.contains(child.toString())) {
                queue.add(child);
                visited.add(child.toString());
                nodesGenerated++;
            }
        }
    }
    return null;
}
public int getNodesGenerated() {
    return nodesGenerated;
}

public int getNodesExpanded() {
    return nodesExpanded;
}}