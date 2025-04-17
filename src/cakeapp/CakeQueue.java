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
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    public boolean isEmpty() {
        // Returning true if the queue has no cakes
        return ovenQueue.isEmpty();
    }
    
    // Checking if the oven is full (max capacity of 5 cakes)
    public boolean isFull() {
        // Returning true if the queue has 5 or more cakes
        return ovenQueue.size() >= MAX_CAPACITY;
    }

    // Getting the size of the number of cakes in the oven
    public int size() {
        return ovenQueue.size(); // Returning the size of the queue
    }
    
    // Enqueue(add) a Cake object to the end of the queue
    public void addCake(Object element) {
        // Checking if the object is an instance of Cake
        if (element instanceof Cake) { 
            ovenQueue.add((Cake) element);  // Adding Cake object to the queue
        }
    }

    // Dequeue (remove) a Cake object at the front of the queue
    public Object removeCake() {
        return ovenQueue.remove(0);  // Removing and returning the first cake in the queue
       

    }

    // Method to display the cake at the front of the queue
    public Object peekFrontCake() {
        if (ovenQueue.size() > 0) { // Checking if the queue has at least one cake
            return ovenQueue.get(0); // Returning the first cake in the queue
        }
        return null; // Returning null if the queue is empty
    }
    
    // Method to display all cakes in the oven (queue)
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
                // Add the cake's details to the string
                string = string.concat(it.next().toString());
                // Add a new line after each cake
                string = string.concat("\n");
            }
        }
        return string; // Returning the final list of cakes as string
    }

    public Object peekLastCake() {
        if(!ovenQueue.isEmpty()) {
            return ovenQueue.get(ovenQueue.size() - 1); // Returning the last cake 
        }
        return null; // If the queue is empty
    }
    
    public Object findCakeByName(String name) {
        for(Cake cake: ovenQueue) {
            if (cake.getCakeName().equalsIgnoreCase(name)) {
                return cake; // Returning the cake with the matching name
            }
        }
        return false; // If there was no cake found with that name
    }

    public boolean removeCakeByName(String name) {
        for(Cake cake: ovenQueue) {
            if (cake.getCakeName().equals(name)) {
                ovenQueue.remove(cake);
                return true; // Returning true if the cake is found by its name and removing
            }
        }
        return false; // Returning false if no cake was found by that name
    }

    public void clearOven() {
        ovenQueue.clear(); // Clearing all the cakes in the oven / queue
    }

    public String getReport() {
        int totalWeight = 0;
        for(Cake cake: ovenQueue) {
            totalWeight += cake.getCakeWeight(); // Adding all the cake weights
        }
        return "Total number of cakes: " + ovenQueue.size() + "\n Combined weight: " + totalWeight + "grams";
    }

    public List<Object> getCakeSortedByExpiry() {
        ovenQueue.sort((cake1, cake2) -> {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = simpleDateFormat.parse(cake1.getExpiryDate());
                Date date2 = simpleDateFormat.parse(cake2.getExpiryDate());
                return date1.compareTo(date2);
            } catch (Exception e) {
                return 0; // Return 0 in case of exception
            }
        });
        return (List<Object>) (List<?>) ovenQueue;
    }

    public List<Object> getCakeSortedByName() {
        ovenQueue.sort((cake1, cake2) -> cake1.getCakeName().compareTo(cake2.getCakeName())); // Sorting by cake name
        return (List<Object>) (List<?>) ovenQueue;
    }


    public List<Object> getCakeSortedByWeight() {
        ovenQueue.sort((cake1, cake2) -> Integer.compare(cake1.getCakeWeight(), cake2.getCakeWeight())); // Sorting by cake weight
        return (List<Object>) (List<?>) ovenQueue;
    }
    
    public List<Object> getCakeExpiringSoon(int days) {
        List<Object> expiringCakes = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(); // Get the current date

        for (Cake cake : ovenQueue) {
            try {
                Date expiryDate = simpleDateFormat.parse(cake.getExpiryDate());
                long differenceInMilliSeconds = expiryDate.getTime() - currentDate.getTime(); // Get the difference in milliseconds
                long daysDifference = differenceInMilliSeconds / (1000 * 60 * 60 * 24); // Convert milliseconds to days

                if (daysDifference <= days) { // If the cake is expiring soon
                    expiringCakes.add(cake);
                }
            } catch (Exception e) {
                System.out.println("There has been an error!");;
            }
        }
        return (List<Object>) (List<?>) expiringCakes; // Returning the list of expiring cakes
    }


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
            } catch (Exception e) {
                System.out.println("There has been an error!");
            }
        }
        return report.toString(); // Returning the string with cake ages
    }
    
    public Object generateRandomCake() {
        String[] cakeNames = {"Chocolate Cake", "Vanilla Cake", "Strawberry Cake", "Lemon Cake", "Carrot Cake"};
        int randomIndex = (int) (Math.random() * cakeNames.length); // Getting a random cake name
        String randomName = cakeNames[randomIndex];
        int randomWeight = (int) (Math.random() * 500) + 100; // Random weight between 100 and 600 grams
        String randomExpiryDate = generateRandomExpiryDate(); // Generating random expiry date

        return new Cake(randomName, randomWeight, randomExpiryDate); // Returning a new random cake
    }

    // Helper method to generate a random expiry date
    private String generateRandomExpiryDate() {
        int randomDaysToAdd = (int) (Math.random() * 30) + 1; // Random number of days to add (1-30 days)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, randomDaysToAdd); // Adding random days to current date
        Date expiryDate = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(expiryDate); // Return formatted expiry date
    }   
}
