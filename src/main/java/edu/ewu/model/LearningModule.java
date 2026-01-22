package edu.ewu.model;

public abstract class LearningModule
{
    protected String moduleName;                            // subclasses can access, but hidden from unrelated classes

    public LearningModule(final String moduleName)
    {
        if (moduleName == null || moduleName.isBlank())
        {
            throw new IllegalArgumentException("module name must not be null or blank");
        }

        this.moduleName = moduleName;
    }

    public String getModuleName()
    {
        return this.moduleName;
    }

    public abstract void startSession();                 // Abstract method - each subclass must implement

    public void displayWelcome()                         // Concrete method - each subclass will inherit this
    {
        System.out.println("Welcome to: " + this.moduleName);
    }
}
