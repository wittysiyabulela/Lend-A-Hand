package com.example.lendahand;

public class requestItem {

    private String resourceType;
    private String quantity;
    private String location;
    private int fulfilledCount;
    private int totalCount;

    public requestItem(String resourceType, String quantity,
                       String location, int fulfilledCount, int totalCount) {
        this.resourceType  = resourceType;
        this.quantity      = quantity;
        this.location      = location;
        this.fulfilledCount = fulfilledCount;
        this.totalCount    = totalCount;
    }

    public String getResourceType()  { return resourceType; }
    public String getQuantity()      { return quantity; }
    public String getLocation()      { return location; }
    public int getFulfilledCount()   { return fulfilledCount; }
    public int getTotalCount()       { return totalCount; }


    public int getProgressPercent() {
        if (totalCount == 0) return 0;
        return (int) ((fulfilledCount / (float) totalCount) * 100);
    }


    public String getFulfilmentText() {
        if (fulfilledCount >= totalCount && totalCount > 0) return "Fully fulfilled";
        return fulfilledCount + " of " + totalCount + " fulfilled";
    }
}