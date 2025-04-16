// Package declaration for all cake-related classes
package cakeapp;

/**
 * CakeQueueInterface defines methods for managing a queue of cakes.
 * Any class implementing this interface has implementations
 * for these methods to handle cake queue operations.
 * 
 * @author egshi
 */

public interface CakeQueueInterface {

    public boolean isEmpty(); // Returning true if the queue has no cakes
    public boolean isFull(); // Returning true if the queue has reached its maximum capacity
    public int size(); // Returning the number of cakes currently in the queue
    public void addCake(Object element); // Adding a new cake to the end of the queue
    public Object removeCake(); // Removing and returning the cake at the front of the queue
    public Object peekFrontCake(); // Returning the cake at the front without removing it
    public String displayCakes(); // Returning a string listing all cakes in the queue
}
