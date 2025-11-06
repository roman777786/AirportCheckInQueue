package gui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("✈️ Dashboard - Airport Queue System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(52, 152, 219));
        topPanel.setPreferredSize(new Dimension(1000, 100));

        JButton backButton = new JButton("← Back to Welcome");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(41, 128, 185));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> {
            new WelcomePage().setVisible(true);
            dispose();
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        backPanel.setBackground(new Color(52, 152, 219));
        backPanel.add(backButton);

        JLabel titleLabel = new JLabel("✈️ Dashboard - Select Operation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);

        topPanel.add(backPanel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 30, 30));
        centerPanel.setBackground(new Color(236, 240, 241));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel addPassengerCard = createMenuCard("➕ Add Passenger",
                "Register new passenger for check-in", new Color(46, 204, 113));

        JPanel queueOpsCard = createMenuCard("🔄 Queue Operations",
                "ENQUEUE, DEQUEUE, PEEK, DISPLAY", new Color(52, 152, 219));

        JPanel statsCard = createMenuCard("📊 Statistics & Reports",
                "View system statistics & reports", new Color(155, 89, 182));

        JPanel exitCard = createMenuCard("❌ Exit System",
                "Close application", new Color(231, 76, 60));

        addPassengerCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new AddPassengerWindow();
                dispose();
            }
        });

        queueOpsCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new MainFrame();
                dispose();
            }
        });

        statsCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new StatisticsWindow();
                dispose();
            }
        });

        exitCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });

        centerPanel.add(addPassengerCard);
        centerPanel.add(queueOpsCard);
        centerPanel.add(statsCard);
        centerPanel.add(exitCard);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createMenuCard(String title, String description, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(color);

        JLabel descLabel = new JLabel("<html><center>" + description + "</center></html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(new Color(127, 140, 141));

        card.add(titleLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(color);
                titleLabel.setForeground(Color.WHITE);
                descLabel.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
                titleLabel.setForeground(color);
                descLabel.setForeground(new Color(127, 140, 141));
            }
        });

        return card;
    }
}
