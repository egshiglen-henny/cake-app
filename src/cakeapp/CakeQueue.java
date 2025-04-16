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
import java.util.ArrayList; 
import java.util.Iterator;

public class CakeQueue implements CakeQueueInterface {
    
    // List to store the cakes in the oven queue
    private ArrayList<Cake> theQueue;  

    // Constructor to initialize the queue
    public CakeQueue() {
        // Creating a new ArrayList to hold cake objects
        theQueue = new ArrayList<>();
    }
    
    // Checking if the oven is empty
    public boolean isEmpty() {
        // Returning true if the queue has no cakes
        return theQueue.isEmpty();
    }
    
    // Checking if the oven is full (max capacity of 5 cakes)
    public boolean isFull() {
        // Returning true if the queue has 5 or more cakes
        return theQueue.size() >= 5;
    }

    // Getting the size of the number of cakes in the oven
    public int size() {
        return theQueue.size(); // Returning the size of the queue
    }
    
    // Enqueue(add) a Cake object to the end of the queue
    public void addCake(Object element) {
        if (isFull()) { // Checking if the queue is full
            System.out.println("No more capacity."); // Printing a message if the queue is full
            return; // Exiting the method without adding the cake
        }
        // Checking if the object is an instance of Cake
        if (element instanceof Cake) { 
            theQueue.add((Cake) element);  // Adding Cake object to the queue
        }
    }

    // Dequeue (remove) a Cake object at the front of the queue
    public Object removeCake() {
        if (!theQueue.isEmpty()) { // Checking if the queue is not empty
            return theQueue.remove(0);  // Removing and returning the first cake in the queue
        }
        return null; // Returning null if the queue is empty
    }

    // Method to display the cake at the front of the queue
    public Object peekFrontCake() {
        if (theQueue.size() > 0) { // Checking if the queue has at least one cake
            return theQueue.get(0); // Returning the first cake in the queue
        }
        return null; // Returning null if the queue is empty
    }
    
    // Method to display all cakes in the oven (queue)
    public String displayCakes() {
         // Initializing an empty String to build the output
        String string = new String();
        
        // Creating an iterator for theQueue to loop through each cake in the oven
        Iterator<Cake> it = theQueue.iterator();
        
        if (theQueue.isEmpty()) { // Checking if the queue has no cakes
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






    
}
