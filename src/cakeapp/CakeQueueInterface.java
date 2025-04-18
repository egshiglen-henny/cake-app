/*
 * Student name: Egshiglen Enkhbayar
 * Student number: 2024359
 */

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
    public Object peekLastCake(); // Returning the cake in the end of the oven
    public Object findCakeByName(String name); // Searching a cake by it's name and returning it's information
    public boolean removeCakeByName(String name); // Removing a cake by name
    public void emptyOven(); // Removing all the cakes in the oven/queue
    public String getReport(); // Returning total number of cakes and combined weight
    public String getCakeSortedByExpiry(); // Returning cakes sorted by it's best before date
    public String getCakeSortedByName(); // Returning cakes sorted by name
    public String getCakeSortedByWeight(); // Returning cakes sorted by weight
    public String getCakeExpiringSoon(int days); // Returning cakes expiring within the next 3 days
    public String getCakeAges(); // Returning how many days each cake's been in the oven
    public Object generateRandomCake(); //Generating random cake for testing and returning
}
