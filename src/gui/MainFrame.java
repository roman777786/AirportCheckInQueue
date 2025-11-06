package gui;

import datastructure.PassengerQueue;
import models.Passenger;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private static PassengerQueue queue = new PassengerQueue();

    private JTextArea logArea;
    private JTable queueTable;
    private DefaultTableModel tableModel;
    private JLabel frontLabel, rearLabel, sizeLabel, statusLabel;
    private JProgressBar queueBar;

    private Color primaryColor = new Color(52, 152, 219);
    private Color successColor = new Color(46, 204, 113);
    private Color dangerColor = new Color(231, 76, 60);
    private Color warningColor = new Color(241, 196, 15);
    private Color darkColor = new Color(44, 62, 80);
    private Color lightBg = new Color(236, 240, 241);

    public static PassengerQueue getQueue() {
        return queue;
    }

    public MainFrame() {
        setTitle("🔄 Queue Operations - Airport System");
        setSize(1400, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(lightBg);

        JPanel topPanel = createTopPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel rightPanel = createRightPanel();

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        updateQueueStats();
        refreshTable();
        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(primaryColor);
        panel.setPreferredSize(new Dimension(1400, 140));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Back Button
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(41, 128, 185));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        // Title
        JLabel titleLabel = new JLabel("🔄 Queue Operations Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        // Stats Panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 3, 15, 10));
        statsPanel.setOpaque(false);

        frontLabel = createStatLabel("Front: -", "👉");
        rearLabel = createStatLabel("Rear: -", "👈");
        sizeLabel = createStatLabel("Size: 0", "📊");
        statusLabel = createStatLabel("Status: Empty", "🟢");

        queueBar = new JProgressBar(0, 50);
        queueBar.setStringPainted(true);
        queueBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        queueBar.setForeground(successColor);

        JLabel capacityLabel = new JLabel("Capacity: 50", SwingConstants.CENTER);
        capacityLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        capacityLabel.setForeground(Color.WHITE);

        statsPanel.add(frontLabel);
        statsPanel.add(rearLabel);
        statsPanel.add(sizeLabel);
        statsPanel.add(statusLabel);
        statsPanel.add(queueBar);
        statsPanel.add(capacityLabel);

        JPanel titleStatsPanel = new JPanel();
        titleStatsPanel.setLayout(new BoxLayout(titleStatsPanel, BoxLayout.Y_AXIS));
        titleStatsPanel.setOpaque(false);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleStatsPanel.add(titleLabel);
        titleStatsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        titleStatsPanel.add(statsPanel);

        panel.add(backPanel, BorderLayout.WEST);
        panel.add(titleStatsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createStatLabel(String text, String emoji) {
        JLabel label = new JLabel(emoji + " " + text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(lightBg);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        // Table Panel
        JPanel tablePanel = createStyledPanel("📋 Queue Visualization", darkColor);
        tablePanel.setLayout(new BorderLayout());

        String[] columns = {"Pos", "ID", "Name", "NID", "Phone", "Flight", "Destination", "Price", "Class", "Time"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        queueTable = new JTable(tableModel);
        queueTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        queueTable.setRowHeight(28);
        queueTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        queueTable.getTableHeader().setBackground(darkColor);
        queueTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane tableScroll = new JScrollPane(queueTable);
        tablePanel.add(tableScroll, BorderLayout.CENTER);

        // Log Panel
        JPanel logPanel = createStyledPanel("📝 Activity Log", successColor);
        logPanel.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        logArea.setBackground(Color.WHITE);
        logArea.setText("✈️ System Ready. Queue Operations Active.\n");

        JScrollPane logScroll = new JScrollPane(logArea);
        logPanel.add(logScroll, BorderLayout.CENTER);

        panel.add(tablePanel);
        panel.add(logPanel);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(lightBg);
        panel.setPreferredSize(new Dimension(340, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        // Queue Operations
        JPanel queueOpsPanel = createStyledPanel("🔄 Queue Operations (DSA)", warningColor);
        queueOpsPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton dequeueBtn = createStyledButton("DEQUEUE() - Process First", primaryColor);
        JButton peekBtn = createStyledButton("PEEK() - View Front", warningColor);
        JButton displayBtn = createStyledButton("DISPLAY() - Refresh All", darkColor);
        JButton updateBtn = createStyledButton("UPDATE() - Modify Passenger", new Color(142, 68, 173));
        JButton searchBtn = createStyledButton("SEARCH() - Find Passenger", new Color(52, 73, 94));

        dequeueBtn.addActionListener(e -> dequeuePassenger());
        peekBtn.addActionListener(e -> peekPassenger());
        displayBtn.addActionListener(e -> displayQueue());
        updateBtn.addActionListener(e -> updatePassenger());
        searchBtn.addActionListener(e -> searchPassenger());

        queueOpsPanel.add(dequeueBtn);
        queueOpsPanel.add(peekBtn);
        queueOpsPanel.add(displayBtn);
        queueOpsPanel.add(updateBtn);
        queueOpsPanel.add(searchBtn);

        // Utility
        JPanel utilPanel = createStyledPanel("⚙️ Utilities", dangerColor);
        utilPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton clearBtn = createStyledButton("CLEAR() - Reset Queue", dangerColor);
        JButton sizeBtn = createStyledButton("SIZE() - Get Count", new Color(52, 73, 94));

        clearBtn.addActionListener(e -> clearQueue());
        sizeBtn.addActionListener(e -> showSize());

        utilPanel.add(clearBtn);
        utilPanel.add(sizeBtn);

        panel.add(queueOpsPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(utilPanel);

        return panel;
    }

    private JPanel createStyledPanel(String title, Color headerColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel headerLabel = new JLabel(title);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headerLabel.setForeground(headerColor);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void updateQueueStats() {
        int size = queue.size();
        sizeLabel.setText("📊 Size: " + size);
        queueBar.setValue(size);

        if (queue.isEmpty()) {
            statusLabel.setText("🟢 Status: Empty");
            frontLabel.setText("👉 Front: -");
            rearLabel.setText("👈 Rear: -");
        } else {
            statusLabel.setText(size > 40 ? "🔴 Status: Near Full" : "🟡 Status: Active");
            Passenger front = queue.peek();
            if (front != null) {
                frontLabel.setText("👉 Front: " + front.getName());
            }
            rearLabel.setText("👈 Rear: Position " + size);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < queue.size(); i++) {
            Passenger p = queue.getPassengerAt(i);
            if (p != null) {
                tableModel.addRow(new Object[]{
                        i + 1,
                        p.getPassengerID(),
                        p.getName(),
                        p.getNationalID(),
                        p.getPhoneNumber(),
                        p.getFlightNumber(),
                        p.getDestination(),
                        "Rs. " + String.format("%.0f", p.getTicketPrice()),
                        p.getClassType(),
                        p.getCheckInTime()
                });
            }
        }
    }

    private void dequeuePassenger() {
        Passenger p = queue.dequeue();
        if (p != null) {
            logArea.append("[DEQUEUE] ✅ Processed: " + p.getName() + " | Flight: " + p.getFlightNumber() + "\n");
            refreshTable();
            updateQueueStats();
            JOptionPane.showMessageDialog(this,
                    "✅ DEQUEUE Successful!\n\n" +
                            "Passenger: " + p.getName() + "\n" +
                            "Destination: " + p.getDestination() + "\n" +
                            "Price: Rs. " + String.format("%.2f", p.getTicketPrice()),
                    "Check-in Complete", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "ℹ️ Queue is EMPTY!", "Empty Queue", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void peekPassenger() {
        Passenger p = queue.peek();
        if (p != null) {
            JOptionPane.showMessageDialog(this,
                    "👁️ PEEK() - Front Passenger:\n\n" +
                            "ID: " + p.getPassengerID() + "\n" +
                            "Name: " + p.getName() + "\n" +
                            "NID: " + p.getNationalID() + "\n" +
                            "Phone: " + p.getPhoneNumber() + "\n" +
                            "Flight: " + p.getFlightNumber() + "\n" +
                            "Destination: " + p.getDestination() + "\n" +
                            "Price: Rs. " + String.format("%.2f", p.getTicketPrice()) + "\n" +
                            "Class: " + p.getClassType(),
                    "Peek Result", JOptionPane.INFORMATION_MESSAGE);
            logArea.append("[PEEK] 👁️ Viewed: " + p.getName() + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "ℹ️ Queue is EMPTY!", "Empty Queue", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayQueue() {
        refreshTable();
        updateQueueStats();
        logArea.append("[DISPLAY] 📋 Queue refreshed | Total: " + queue.size() + "\n");
        JOptionPane.showMessageDialog(this, "📋 DISPLAY() Complete!\nTable updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updatePassenger() {
        int selectedRow = queueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Select a passenger from table!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = (String) tableModel.getValueAt(selectedRow, 2);
        String currentPhone = (String) tableModel.getValueAt(selectedRow, 4);

        String newPhone = JOptionPane.showInputDialog(this,
                "Update phone number for: " + name + "\n\nCurrent: " + currentPhone,
                "Update Phone",
                JOptionPane.QUESTION_MESSAGE);

        if (newPhone != null && !newPhone.trim().isEmpty()) {
            if (!newPhone.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(this, "⚠️ Phone must be 11 digits!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            tableModel.setValueAt(newPhone, selectedRow, 4);
            logArea.append("[UPDATE] 🔄 Modified: " + name + " → Phone: " + newPhone + "\n");
            JOptionPane.showMessageDialog(this, "✅ Phone number updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchPassenger() {
        String searchName = JOptionPane.showInputDialog(this, "Enter passenger name to search:", "Search", JOptionPane.QUESTION_MESSAGE);
        if (searchName == null || searchName.trim().isEmpty()) return;

        for (int i = 0; i < queue.size(); i++) {
            Passenger p = queue.getPassengerAt(i);
            if (p != null && p.getName().toLowerCase().contains(searchName.toLowerCase())) {
                JOptionPane.showMessageDialog(this,
                        "🔍 SEARCH Result:\n\n" +
                                "Found: " + p.getName() + "\n" +
                                "Position: " + (i + 1) + "\n" +
                                "Flight: " + p.getFlightNumber() + "\n" +
                                "Destination: " + p.getDestination(),
                        "Search Result", JOptionPane.INFORMATION_MESSAGE);
                logArea.append("[SEARCH] 🔍 Found: " + p.getName() + " at position " + (i + 1) + "\n");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "❌ Passenger not found!", "Search Result", JOptionPane.WARNING_MESSAGE);
    }

    private void clearQueue() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "⚠️ CLEAR entire queue?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            queue = new PassengerQueue();
            tableModel.setRowCount(0);
            logArea.append("[CLEAR] 🗑️ Queue reset\n");
            updateQueueStats();
        }
    }

    private void showSize() {
        int size = queue.size();
        JOptionPane.showMessageDialog(this,
                "📊 SIZE() = " + size + "\n\nPassengers in queue: " + size,
                "Queue Size",
                JOptionPane.INFORMATION_MESSAGE);
        logArea.append("[SIZE] 📊 Current: " + size + "\n");
    }
}
