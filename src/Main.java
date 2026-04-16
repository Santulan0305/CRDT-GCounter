import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {

    static class GCounter {
        private int[] counter;
        private int nodeId;

        public GCounter(int numNodes, int nodeId) {
            this.counter = new int[numNodes];
            this.nodeId = nodeId;
        }

        public void increment() {
            counter[nodeId]++;
        }

        public void merge(GCounter other) {
            for (int i = 0; i < counter.length; i++) {
                counter[i] = Math.max(counter[i], other.counter[i]);
            }
        }

        public int getValue() {
            return Arrays.stream(counter).sum();
        }

        public int[] getCounter() {
            return counter;
        }
    }

    public static void main(String[] args) {

        int numNodes = 2;
        GCounter nodeA = new GCounter(numNodes, 0);
        GCounter nodeB = new GCounter(numNodes, 1);

        JFrame frame = new JFrame("CRDT G-Counter Visualization");
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        JLabel title = new JLabel("CRDT G-Counter Simulation", SwingConstants.CENTER);
        title.setBounds(50, 10, 400, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Node Panels
        JPanel panelA = new JPanel();
        panelA.setBounds(50, 60, 150, 100);
        panelA.setBackground(new Color(173, 216, 230));

        JPanel panelB = new JPanel();
        panelB.setBounds(300, 60, 150, 100);
        panelB.setBackground(new Color(144, 238, 144));

        JLabel labelA = new JLabel();
        JLabel labelB = new JLabel();

        panelA.add(new JLabel("Node A"));
        panelA.add(labelA);

        panelB.add(new JLabel("Node B"));
        panelB.add(labelB);

        // Result
        JLabel result = new JLabel("Final Value: 0", SwingConstants.CENTER);
        result.setBounds(150, 180, 200, 30);
        result.setFont(new Font("Arial", Font.BOLD, 16));

        // Buttons
        JButton incA = new JButton("Increment A");
        incA.setBounds(50, 230, 120, 30);

        JButton incB = new JButton("Increment B");
        incB.setBounds(330, 230, 120, 30);

        JButton mergeBtn = new JButton("Merge (Animated)");
        mergeBtn.setBounds(150, 280, 200, 40);

        // Update UI function
        Runnable updateUI = () -> {
            labelA.setText(Arrays.toString(nodeA.getCounter()));
            labelB.setText(Arrays.toString(nodeB.getCounter()));
            result.setText("Final Value: " + nodeA.getValue());
        };

        updateUI.run();

        // Button actions
        incA.addActionListener(e -> {
            nodeA.increment();
            updateUI.run();
        });

        incB.addActionListener(e -> {
            nodeB.increment();
            updateUI.run();
        });

        // Animation Merge
        mergeBtn.addActionListener(e -> {
            new Thread(() -> {
                try {
                    for (int i = 0; i < numNodes; i++) {
                        int finalI = i;

                        // Animate merging step by step
                        nodeA.getCounter()[finalI] =
                                Math.max(nodeA.getCounter()[finalI], nodeB.getCounter()[finalI]);

                        nodeB.getCounter()[finalI] =
                                Math.max(nodeB.getCounter()[finalI], nodeA.getCounter()[finalI]);

                        SwingUtilities.invokeLater(updateUI);

                        Thread.sleep(800); // animation delay
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        // Add components
        frame.add(title);
        frame.add(panelA);
        frame.add(panelB);
        frame.add(result);
        frame.add(incA);
        frame.add(incB);
        frame.add(mergeBtn);

        frame.setVisible(true);
    }
}