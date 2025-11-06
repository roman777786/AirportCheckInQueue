package models;

public class Passenger {
    private int passengerID;
    private String name;
    private String nationalID;
    private String phoneNumber;
    private String flightNumber;
    private String destination;
    private double ticketPrice;
    private String checkInTime;
    private String classType;

    public Passenger(int id, String name, String nationalID, String phone,
                     String flight, String destination, double price, String time, String type) {
        this.passengerID = id;
        this.name = name;
        this.nationalID = nationalID;
        this.phoneNumber = phone;
        this.flightNumber = flight;
        this.destination = destination;
        this.ticketPrice = price;
        this.checkInTime = time;
        this.classType = type;
    }

    // Getters
    public int getPassengerID() { return passengerID; }
    public String getName() { return name; }
    public String getNationalID() { return nationalID; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getFlightNumber() { return flightNumber; }
    public String getDestination() { return destination; }
    public double getTicketPrice() { return ticketPrice; }
    public String getCheckInTime() { return checkInTime; }
    public String getClassType() { return classType; }

    @Override
    public String toString() {
        return "ID: " + passengerID + " | " + name + " | NID: " + nationalID +
                " | Phone: " + phoneNumber + " | Flight: " + flightNumber +
                " | To: " + destination + " | Price: Rs." + ticketPrice +
                " | Class: " + classType + " | Time: " + checkInTime;
    }
}
