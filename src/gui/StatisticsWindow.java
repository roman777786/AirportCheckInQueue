package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsWindow extends JFrame {

    public StatisticsWindow() {
        setTitle("📊 Statistics & Reports - Airport System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(236, 240, 241));

        // Top Panel with Back Button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(155, 89, 182));
        topPanel.setPreferredSize(new Dimension(900, 100));

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(142, 68, 173));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        backPanel.setBackground(new Color(155, 89, 182));
        backPanel.add(backButton);

        JLabel titleLabel = new JLabel("📊 System Statistics & Reports", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);

        topPanel.add(backPanel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Stats Panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        int currentSize = MainFrame.getQueue().size();
        int capacity = 50;
        int processed = 0;

        // Create stat cards
        JPanel card1 = createStatCard("Current Queue Size", currentSize + " passengers", new Color(52, 152, 219));
        JPanel card2 = createStatCard("Total Capacity", capacity + " passengers", new Color(46, 204, 113));
        JPanel card3 = createStatCard("Available Slots", (capacity - currentSize) + " slots", new Color(241, 196, 15));
        JPanel card4 = createStatCard("Processed Today", processed + " passengers", new Color(155, 89, 182));
        JPanel card5 = createStatCard("Queue Status",
                currentSize == 0 ? "Empty" : currentSize > 40 ? "Near Full" : "Active",
                new Color(52, 73, 94));
        JPanel card6 = createStatCard("Utilization",
                String.format("%.1f%%", (currentSize * 100.0 / capacity)),
                new Color(231, 76, 60));

        statsPanel.add(card1);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        statsPanel.add(card2);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        statsPanel.add(card3);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        statsPanel.add(card4);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        statsPanel.add(card5);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        statsPanel.add(card6);

        JScrollPane scrollPane = new JScrollPane(statsPanel);
        scrollPane.setBorder(null);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(20, 20));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 3),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        card.setMaximumSize(new Dimension(800, 80));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(44, 62, 80));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(color);

        card.add(titleLabel, BorderLayout.WEST);
        card.add(valueLabel, BorderLayout.EAST);

        return card;
    }
}
