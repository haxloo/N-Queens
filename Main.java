import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private JButton runButton;
    private JComboBox<String> algorithmComboBox;
    private JTextArea resultsTextArea;
    private JTextField nTextField;
    private int generatedNodes;
    private int expandedNodes;

    public Main() {
        // Create UI components
        JLabel titleLabel = new JLabel("N Queens Problem Solver");
        JLabel nLabel = new JLabel("N:");
        algorithmComboBox = new JComboBox<String>(new String[] {"DFS", "BFS", "AStar","AStar2"});
        runButton = new JButton("Run");
        resultsTextArea = new JTextArea(15, 30);
        JScrollPane resultsScrollPane = new JScrollPane(resultsTextArea);
        nTextField = new JTextField(2);

        // Add components to layout
        JPanel mainPanel = new JPanel();
        mainPanel.add(titleLabel);
        mainPanel.add(nLabel);
        mainPanel.add(nTextField);
        mainPanel.add(new JLabel("Algorithm:"));
        mainPanel.add(algorithmComboBox);
        mainPanel.add(runButton);
        mainPanel.add(resultsScrollPane);
        add(mainPanel);

        // Set up event handlers
        runButton.addActionListener(this);

        // Set window properties
        setTitle("N Queens Problem Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            // Get selected algorithm
            String algorithm = (String) algorithmComboBox.getSelectedItem();

            // Get value of n
            int n = Integer.parseInt(nTextField.getText());

            // Check if n is less than 5 and display error message if it is
            if (n < 5) {
                JOptionPane.showMessageDialog(this, "N must be greater than or equal to 8", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Run selected algorithm
            Node solution = null;
            generatedNodes = 0;
            expandedNodes = 0;
            long startTime = System.currentTimeMillis();
            if (algorithm.equals("DFS")) {
                DFS dfs = new DFS(n);
                solution = dfs.search();
                generatedNodes = dfs.getNodesGenerated();
                expandedNodes = dfs.getNodesExpanded();
            } else if (algorithm.equals("BFS")) {
                BFS bfs = new BFS(n);
                solution = bfs.search();
                generatedNodes = bfs.getNodesGenerated();
                expandedNodes = bfs.getNodesExpanded();
            } else if (algorithm.equals("AStar")) {
                AStar astar = new AStar(n);
                solution = astar.search();
                generatedNodes = astar.getNodesGenerated();
                expandedNodes = astar.getNodesExpanded();
            } else if (algorithm.equals("AStar2")) {
                HeuristiqueDeux astar2 = new HeuristiqueDeux(n);
                solution = astar2.search();
                generatedNodes = astar2.getGeneratedNodes();
                expandedNodes = astar2.getExpandedNodes();
            }

            // Display results
            long endTime = System.currentTimeMillis();
            if (solution != null) {
                resultsTextArea.setText("Solution found by " + algorithm + ":\n");
                resultsTextArea.append("Generated Nodes: " + generatedNodes + "\n");
                resultsTextArea.append("Expanded Nodes: " + expandedNodes + "\n");
                resultsTextArea.append("Time taken: " + (endTime - startTime) + "ms\n");
                String[][] stateResult = solution.printState();
            
            for (int i = 0; i < stateResult.length; i++) {
                for (int j = 0; j < stateResult.length; j++) {
                    resultsTextArea.append(stateResult[i][j]);
                }
                resultsTextArea.append("\n");
            }
        } else {
            resultsTextArea.setText("No solution found by " + algorithm + ".");
        }
    }
}

public static void main(String[] args) {
    new Main();
}}
