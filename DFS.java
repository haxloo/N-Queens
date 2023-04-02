
import java.util.HashSet;
import java.util.Stack;

public class DFS {
private int n;
private int nodesGenerated;
private int nodesExpanded;
public DFS(int n) {
    this.n = n;
    this.nodesGenerated = 0;
    this.nodesExpanded = 0;
}

public Node search() {
    Stack<Node> stack = new Stack<>();
    HashSet<String> visited = new HashSet<>();
    Node initialNode = new Node(new int[n], 0, 0);
    stack.push(initialNode);
    visited.add(initialNode.toString());
    nodesGenerated++;

    while (!stack.isEmpty()) {
        Node curr = stack.pop();
        nodesExpanded++;
        if (curr.isGoal(n)) {
            return curr;
        }
        for (Node child : curr.getChildren(n)) {
            if (!visited.contains(child.toString())) {
                stack.push(child);
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
}
}