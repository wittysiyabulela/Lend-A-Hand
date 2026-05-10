package com.example.lendahand;

public class DonationItem {
    private String type;
    private String quantity;
    private String location;
    private String date;

    public DonationItem(String type, String quantity, String location, String date) {
        this.type = type;
        this.quantity = quantity;
        this.location = location;
        this.date = date;
    }

    public String getType() { return type; }
    public String getQuantity() { return quantity; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
}
