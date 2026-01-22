package edu.ewu.model;

public class CodingChallengeModule extends LearningModule
{
    private String difficulty;

    public CodingChallengeModule(final String difficulty)
    {
        /*
            when a subclass constructor runs, it must call a superclass constructor
            this is called constructor chaining
            super(...) must be the first statement in the subclass constructor
            if you don't call super(...), Java automatically inserts the no-argument super constructor
            if the parent doesn't have a no-arg constructor, you MUST explicitly call a parent constructor
         */
        super("Coding Challenge");

        if (difficulty == null || difficulty.isBlank())
        {
            throw new IllegalArgumentException("difficulty cannot be null or blank");
        }

        this.difficulty = difficulty;
    }

    public String getDifficulty()
    {
        return this.difficulty;
    }

    @Override
    public void startSession()
    {
        System.out.println("Starting " + this.difficulty + "coding challenge...");
        // TODO: implement coding challenge logic
    }
}
