package gui;

import models.Passenger;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddPassengerWindow extends JFrame {

    private JTextField nameField, nationalIDField, phoneField;
    private JComboBox<String> flightCombo, destinationCombo, classCombo;
    private JLabel priceLabel, airlineLabel;
    private static int passengerIDCounter = 1;

    private Map<String, FlightInfo> flightRoutes = new LinkedHashMap<>();
    private Map<String, Double> destinationPrices = new HashMap<>();

    class FlightInfo {
        String flightNumber;
        String airline;
        String destination;

        FlightInfo(String number, String airline, String destination) {
            this.flightNumber = number;
            this.airline = airline;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return flightNumber + " - " + airline + " → " + destination;
        }
    }

    public AddPassengerWindow() {
        flightRoutes.put("PK-300", new FlightInfo("PK-300", "PIA", "Dubai (UAE)"));
        flightRoutes.put("PK-785", new FlightInfo("PK-785", "PIA", "London (UK)"));
        flightRoutes.put("PK-711", new FlightInfo("PK-711", "PIA", "New York (USA)"));
        flightRoutes.put("PK-756", new FlightInfo("PK-756", "PIA", "Jeddah (KSA)"));
        flightRoutes.put("PK-200", new FlightInfo("PK-200", "PIA", "Riyadh (KSA)"));
        flightRoutes.put("PA-410", new FlightInfo("PA-410", "AirBlue", "Dubai (UAE)"));
        flightRoutes.put("PA-204", new FlightInfo("PA-204", "AirBlue", "Istanbul (Turkey)"));
        flightRoutes.put("ER-501", new FlightInfo("ER-501", "SereneAir", "Sharjah (UAE)"));
        flightRoutes.put("PK-898", new FlightInfo("PK-898", "PIA", "Bangkok (Thailand)"));
        flightRoutes.put("PK-782", new FlightInfo("PK-782", "PIA", "Toronto (Canada)"));
        flightRoutes.put("PA-275", new FlightInfo("PA-275", "AirBlue", "Muscat (Oman)"));
        flightRoutes.put("PK-262", new FlightInfo("PK-262", "PIA", "Doha (Qatar)"));

        destinationPrices.put("Dubai (UAE)", 45000.0);
        destinationPrices.put("Sharjah (UAE)", 43000.0);
        destinationPrices.put("London (UK)", 125000.0);
        destinationPrices.put("New York (USA)", 180000.0);
        destinationPrices.put("Jeddah (KSA)", 65000.0);
        destinationPrices.put("Riyadh (KSA)", 68000.0);
        destinationPrices.put("Istanbul (Turkey)", 75000.0);
        destinationPrices.put("Bangkok (Thailand)", 95000.0);
        destinationPrices.put("Toronto (Canada)", 165000.0);
        destinationPrices.put("Muscat (Oman)", 38000.0);
        destinationPrices.put("Doha (Qatar)", 42000.0);

        setTitle("➕ Add Passenger - Flight Registration");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(236, 240, 241));

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(46, 204, 113));
        topPanel.setPreferredSize(new Dimension(800, 100));

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(39, 174, 96));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        backPanel.setBackground(new Color(46, 204, 113));
        backPanel.add(backButton);

        JLabel titleLabel = new JLabel("➕ Passenger Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        topPanel.add(backPanel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Row 1: Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        nameField = createTextField();
        formPanel.add(nameField, gbc);

        // Row 2: National ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel nationalIDLabel = new JLabel("National ID (CNIC):");
        nationalIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(nationalIDLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        nationalIDField = createTextField();
        formPanel.add(nationalIDField, gbc);

        // Row 3: Phone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        phoneField = createTextField();
        formPanel.add(phoneField, gbc);

        // Row 4: Spacer
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        // Row 5: Flight
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        JLabel flightLabel = new JLabel("Select Flight:");
        flightLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(flightLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        String[] flightOptions = new String[flightRoutes.size()];
        int i = 0;
        for (Map.Entry<String, FlightInfo> entry : flightRoutes.entrySet()) {
            flightOptions[i++] = entry.getValue().toString();
        }
        flightCombo = new JComboBox<>(flightOptions);
        flightCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        flightCombo.setPreferredSize(new Dimension(300, 45));
        flightCombo.addActionListener(e -> updateFlightInfo());
        formPanel.add(flightCombo, gbc);

        // Row 6: Airline
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        JLabel airlineInfoLabel = new JLabel("Airline:");
        airlineInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(airlineInfoLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        airlineLabel = new JLabel("PIA");
        airlineLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        airlineLabel.setForeground(new Color(41, 128, 185));
        formPanel.add(airlineLabel, gbc);

        // Row 7: Destination
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        JLabel destLabel = new JLabel("Destination:");
        destLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(destLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        destinationCombo = new JComboBox<>(new String[]{"Dubai (UAE)"});
        destinationCombo.setEnabled(false);
        destinationCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        destinationCombo.setPreferredSize(new Dimension(300, 45));
        formPanel.add(destinationCombo, gbc);

        // Row 8: Class
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.3;
        JLabel classLabel = new JLabel("Class Type:");
        classLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        classCombo = new JComboBox<>(new String[]{"Economy", "Business"});
        classCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        classCombo.setPreferredSize(new Dimension(300, 45));
        classCombo.addActionListener(e -> updatePrice());
        formPanel.add(classCombo, gbc);

        // Row 9: Price
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 0.3;
        JLabel priceInfoLabel = new JLabel("Ticket Price:");
        priceInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formPanel.add(priceInfoLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        priceLabel = new JLabel("Rs. 45,000");
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        priceLabel.setForeground(new Color(46, 204, 113));
        formPanel.add(priceLabel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40));

        JButton submitBtn = new JButton("✅ Register & Add to Queue");
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submitBtn.setBackground(new Color(46, 204, 113));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        submitBtn.addActionListener(e -> registerPassenger());

        buttonPanel.add(submitBtn);

        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateFlightInfo();
        setVisible(true);
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setPreferredSize(new Dimension(400, 45));
        field.setMinimumSize(new Dimension(300, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        return field;
    }

    private void updateFlightInfo() {
        String selected = (String) flightCombo.getSelectedItem();
        String flightCode = selected.split(" - ")[0];

        FlightInfo info = flightRoutes.get(flightCode);
        if (info != null) {
            airlineLabel.setText(info.airline);
            destinationCombo.removeAllItems();
            destinationCombo.addItem(info.destination);
            updatePrice();
        }
    }

    private void updatePrice() {
        String destination = (String) destinationCombo.getSelectedItem();
        String classType = (String) classCombo.getSelectedItem();

        if (destination != null && destinationPrices.containsKey(destination)) {
            double basePrice = destinationPrices.get(destination);
            double finalPrice = classType.equals("Business") ? basePrice * 1.5 : basePrice;
            priceLabel.setText("Rs. " + String.format("%,d", (int)finalPrice));
        }
    }

    private void registerPassenger() {
        String name = nameField.getText().trim();
        String nationalID = nationalIDField.getText().trim();
        String phone = phoneField.getText().trim();
        String flightDisplay = (String) flightCombo.getSelectedItem();
        String flightNumber = flightDisplay.split(" - ")[0];
        String destination = (String) destinationCombo.getSelectedItem();
        String classType = (String) classCombo.getSelectedItem();

        if (name.isEmpty() || nationalID.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Please fill all required fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!nationalID.matches("\\d{13}")) {
            JOptionPane.showMessageDialog(this, "⚠️ CNIC must be 13 digits!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!phone.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "⚠️ Phone must be 11 digits!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double price = Double.parseDouble(priceLabel.getText().replace("Rs. ", "").replace(",", ""));
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        Passenger p = new Passenger(passengerIDCounter++, name, nationalID, phone, flightNumber, destination, price, time, classType);

        if (MainFrame.getQueue().enqueue(p)) {
            JOptionPane.showMessageDialog(this,
                    "✅ ENQUEUE Successful!\n\n" +
                            "Passenger: " + name + "\n" +
                            "Flight: " + flightNumber + "\n" +
                            "Destination: " + destination + "\n" +
                            "Price: Rs. " + String.format("%,d", (int)price) + "\n\n" +
                            "Added to check-in queue!",
                    "Registration Complete",
                    JOptionPane.INFORMATION_MESSAGE);

            nameField.setText("");
            nationalIDField.setText("");
            phoneField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Queue is FULL!", "Queue Full", JOptionPane.ERROR_MESSAGE);
        }
    }
}
