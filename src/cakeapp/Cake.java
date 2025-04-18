

// Package declaration for all cake-related classes
package cakeapp;


/**
 * Cake class used to manage each cake details such as 
 * its name, weight, best-before date, and when it was placed in the oven. 
 * It's used in CakeQueue class.
 * 
 * @author egshi
 */

// Importing essential classes for date formatting
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cake {
    
    // Instance variables to store cake details
    private String cakeName; // Name of the cake
    private int cakeWeight; // Weight of the cake in grams
    private String expiryDate;  // Expiry date of the cake
    private String ovenEntryTime; // Time when the cake was placed into the oven

    // Constructor to initialize the cake with name, weight, and best-before date
    public Cake(String cakeName, int cakeWeight, String expiryDate) {
        this.cakeName = cakeName; // Assigning the cake's name
        this.cakeWeight = cakeWeight; // Assigning the cake's weight
        this.expiryDate = expiryDate; // Assigning the cake's expiry date
        this.ovenEntryTime = getCurrentTime(); // Capturing the current time using getCurrentTime() when the cake is placed into the oven
    }

    // Helper method to get the current time in a specific format (yyyy-MM-dd HH:mm:ss)
    private String getCurrentTime() {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormatter.format(new Date()); // Returning the formatted current time
    }

    // toString method to display the cake's information 
    public String toString() {
        // Returning a formatted string with the cake's details
        return " Name: " + cakeName + " \n Weight: " + cakeWeight + " g \n Best before: " + expiryDate + "\n Time placed: " + ovenEntryTime ;
    }

    // Getter methods to access the cake's details
    
    public String getCakeName() {
        return cakeName; // Returning the cake's name
    }

    public int getCakeWeight() {
        return cakeWeight; // Returning the cake's weight
    }

    public String getExpiryDate() {
        return expiryDate; // Returning the cake's expiry date
    }

    public String getOvenEntryTime() {
        return ovenEntryTime; // Returning the time when the cake was placed into the oven
    }
}

