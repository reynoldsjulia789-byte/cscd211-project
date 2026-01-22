package edu.ewu.model;

/**
 * Interface for objects that can save and load their state
 * Implementing classes must provide their own serialization logic
 */
public interface Storable
{
    /**
     * Saves the object's state to a file
     * @param filename the file to save to
     */
    void saveProgress(String filename);

    /**
     * Loads the object's state from a file.
     * @param filename the file to load from
     */
    void loadProgress(String filename);

    /**
     * A method that classes that implement Storable inherit.
     * Can be Overridden.
     */
    default void backup()
    {
        saveProgress("backup_" + System.currentTimeMillis() + ".dat");
    }
}