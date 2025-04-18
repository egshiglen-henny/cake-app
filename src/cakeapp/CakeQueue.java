/*
 * Student name: Egshiglen Enkhbayar
 * Student number: 2024359
 */

// Package declaration for all cake-related classes
package cakeapp;

/**
 * CakeQueue class implements a queue to manage Cake objects.
 * Methods to add, remove, and display, peek the top cakes in the queue.
 * The queue has a maximum capacity of 5 cakes.
 * 
 * @author egshi
 */

// Importing ArrayList to store the queue of cakes
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class CakeQueue implements CakeQueueInterface {
    
    // List to store the cakes in the oven queue
    private ArrayList<Cake> ovenQueue;  
    private final static int MAX_CAPACITY = 5; // Maximum 5 cakes in the queue

    // Constructor to initialize the queue
    public CakeQueue() {
        // Creating a new ArrayList to hold cake objects
        ovenQueue = new ArrayList<>();
    }
    
    // Checking if the oven is empty
    @Override
    public boolean isEmpty() {
        // Returning true if the queue has no cakes
        return ovenQueue.isEmpty();
    }
    
    // Checking if the oven is full (max capacity of 5 cakes)
    @Override
    public boolean isFull() {
        // Returning true if the queue has 5 or more cakes
        return ovenQueue.size() >= MAX_CAPACITY;
    }

    // Getting the size of the number of cakes in the oven
    @Override
    public int size() {
        return ovenQueue.size(); // Returning the size of the queue
    }
    
    // Enqueue(add) a Cake object to the end of the queue
    @Override
    public void addCake(Object element) {
        // Checking if the object is an instance of Cake
        if (element instanceof Cake cake) { 
            ovenQueue.add(cake);  // Adding Cake object to the queue
        }
    }

    // Dequeue (remove) a Cake object at the front of the queue
    @Override
    public Object removeCake() {
        return ovenQueue.remove(0);  // Removing and returning the first cake in the queue
    }

    // Method to display the cake at the front of the queue
    @Override
    public Object peekFrontCake() {
        if (!ovenQueue.isEmpty()) { // Checking if the queue has at least one cake
            return ovenQueue.get(0); // Returning the first cake in the queue
        }
        return null; // Returning null if the queue is empty
    }
    
    // Method to display all cakes in the oven (queue)
    @Override
    public String displayCakes() {
         // Initializing an empty String to build the output
        String string = new String();
        
        // Creating an iterator for theQueue to loop through each cake in the oven
        Iterator<Cake> it = ovenQueue.iterator();
        
        if (ovenQueue.isEmpty()) { // Checking if the queue has no cakes
            string = string.concat("Oven is EMPTY!"); // Returning a message indicating the queue is empty
        } else {
            // If not empty, iterate through each Cake in the queue
            while (it.hasNext()) {
                // Add a new line after each cake
                string = string.concat("\n🎂");
                // Add the cake's details to the string
                string = string.concat(it.next().toString());
            }
        }
        return string; // Returning the final list of cakes as string
    }

    @Override
    public Object peekLastCake() {
        if(!ovenQueue.isEmpty()) {
            return ovenQueue.get(ovenQueue.size() - 1); // Returning the last cake added to the queue 
        }
        return null; // If the queue is empty
    }
    
    @Override
    public Object findCakeByName(String name) {
        for(Cake cake: ovenQueue) {
            if (cake.getCakeName().equalsIgnoreCase(name)) {
                return cake; // Returning the cake with the matching name
            }
        }
        return null; // If there was no cake found with that name
    }

    @Override
    public boolean removeCakeByName(String name) {
        Iterator<Cake> iterator = ovenQueue.iterator(); // Creating an iterator to loop through the queue
    
        while (iterator.hasNext()) {
            Cake cake = iterator.next(); // Get the next cake in the queue
            if (cake.getCakeName().equalsIgnoreCase(name)) { // Comparing cake name with the input name
                iterator.remove(); // If the cake is found, remove it from the list
                return true; // Returning true if the cake was removed
            }
        }
        return false; // If no cake with that name is found, returning false
    }

    @Override
    public void emptyOven() {
        ovenQueue.clear(); // Clearing all the cakes in the oven / queue
    }

    @Override
    public String getReport() {
        int totalWeight = 0;
        for(Cake cake: ovenQueue) {
            totalWeight += cake.getCakeWeight(); // Adding all the cake weights
        }
        return "Total number of cakes: " + ovenQueue.size() + "\n Combined weight: " + totalWeight + " g";
    }


    @Override
    public String getCakeSortedByName() {
        ArrayList<Cake> sortedList = new ArrayList<>(ovenQueue); // Copying to avoid modifying the original
        sortedList.sort((cake1, cake2) -> cake1.getCakeName().compareTo(cake2.getCakeName()));

        StringBuilder builder = new StringBuilder();
        for (Cake cake : sortedList) {
            builder.append("\n🎂️ Alphabetical order: ")
                  .append("\n").append(cake.toString()).append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getCakeSortedByWeight() {
        ovenQueue.sort((cake1, cake2) -> Integer.compare(cake1.getCakeWeight(), cake2.getCakeWeight()));

        StringBuilder builder = new StringBuilder();

        for (Cake cake : ovenQueue) {
            builder.append("\n⚖️ Weight: ").append(cake.getCakeWeight()).append(" g")
                  .append("\n").append(cake.toString()).append("\n");
        }
        return builder.toString();
    }

    
    @Override
    public String getCakeSortedByExpiry() {
        ovenQueue.sort((cake1, cake2) -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = sdf.parse(cake1.getExpiryDate());
                Date date2 = sdf.parse(cake2.getExpiryDate());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                return 0;
            }
        });

        StringBuilder result = new StringBuilder();
        
        for (Cake cake : ovenQueue) {
            result.append("\n🕒 Expiry: ").append(cake.getExpiryDate())
                  .append("\n").append(cake.toString()).append("\n");
        }
        return result.toString();
    }
    
    @Override
    public String getCakeExpiringSoon(int days) {
        StringBuilder result = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        boolean found = false;

        for (Cake cake : ovenQueue) {
            try {
                Date expiryDate = sdf.parse(cake.getExpiryDate());
                long diff = expiryDate.getTime() - currentDate.getTime();
                long daysDiff = diff / (1000 * 60 * 60 * 24);

                if (daysDiff <= days) {
                    found = true;
                    result.append("\n⏳ Expiring in ").append(daysDiff).append(" day(s)")
                          .append("\n").append(cake.toString()).append("\n");
                }
            } catch (ParseException e) {
                result.append("Error parsing date for cake: ").append(cake.getCakeName()).append("\n");
            }
        }

        if (!found) {
            return "No cakes expiring within " + days + " days.";
        }
        return result.toString();
    }
    
    @Override
    public String getCakeAges() {
        StringBuilder report = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(); // Get the current date

        for (Cake cake : ovenQueue) {
            try {
                Date entryTime = simpleDateFormat.parse(cake.getOvenEntryTime());
                long differenceInMilliSeconds = currentDate.getTime() - entryTime.getTime(); // Get the difference in milliseconds
                long daysDifference = differenceInMilliSeconds / (1000 * 60 * 60 * 24); // Convert milliseconds to days

                report.append(cake.getCakeName())
                      .append(" has been in the oven for ")
                      .append(daysDifference)
                      .append(" days.\n");
            } catch (ParseException e) {
                System.out.println("There has been an error!");
            }
        }
        return report.toString(); // Returning the string with cake ages
    }
    
    @Override
    public Object generateRandomCake() {
        String[] cakeNames = {"Pineapple cake", "Strawberry cake", "Chocolate cake", "Vanilla cake", "Plain cake"};
        int randomIndex = (int) (Math.random() * cakeNames.length); // Getting a random cake name
        String randomName = cakeNames[randomIndex];
        int minWeight = 100;
        int maxWeight = 3000;
        int randomWeight = minWeight + (int)(Math.random() * (maxWeight - minWeight + 1));// Random weight between 100 and 3000 grams
        String randomExpiryDate = generateRandomExpiryDate(); // Generating random expiry date

        return new Cake(randomName, randomWeight, randomExpiryDate); // Returning a new random cake
    }

    // Helper method to generate a random expiry date
    private String generateRandomExpiryDate() {
        int randomDaysToAdd = (int) (Math.random() * 14) + 1; // Random number of days to add (1-14 days)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, randomDaysToAdd); // Adding random days to current date
        Date expiryDate = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(expiryDate); // Return formatted expiry date
    }   
}
