package edu.ewu.model;

import java.util.Objects;

public class Flashcard implements Comparable<Flashcard>, Cloneable
{
    private String            questiontxt;
    private String            answertxt;
    private FlashcardMetadata metadata;

    public Flashcard(final String questiontxt, final String answertxt)
    {
        if (questiontxt == null || answertxt == null || questiontxt.isBlank() || answertxt.isBlank())
        {
            throw new IllegalArgumentException("Can't have blank or null question/answer");
        }

        this.questiontxt = questiontxt;
        this.answertxt   = answertxt;
        this.metadata    = new FlashcardMetadata();
    }

    public String getQuestion()
    {
        return this.questiontxt;
    }

    public String getAnswer()
    {
        return this.answertxt;
    }

    public FlashcardMetadata getMetadata()
    {
        return this.metadata;
    }

    public void markReviewed()
    {
        this.metadata.incrementTimesReviewed();;
    }

    @Override
    public int compareTo(Flashcard other)
    {
        // Sort alphabetically by Question
        return this.questiontxt.compareTo(other.getQuestion());
    }

    @Override
    public boolean equals(Object unknownObj)
    {
        // 1. Same reference?
        if (this == unknownObj)
        {
            return true;
        }

        // 2. Null or different class?
        if (unknownObj == null || !(unknownObj instanceof Flashcard))           // can also use getClass() & compare
        {
            return false;
        }

        // 3. Cast and compare fields
        Flashcard other = (Flashcard) unknownObj;

        return Objects.equals(this.questiontxt, other.getQuestion()) &&
               Objects.equals(this.answertxt,   other.getAnswer());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.questiontxt, this.answertxt);
    }

    @Override
    public Flashcard clone()
    {
        try
        {
            Flashcard cloned = (Flashcard) super.clone();

            // Deep copy: clone the mutable metadata object
            cloned.metadata  = this.metadata.clone();

            return cloned;
        }
        catch (CloneNotSupportedException caught)
        {
            throw new RuntimeException("Clone failed", caught);
        }
    }

    @Override
    public String toString()
    {
        return "Q: " + this.questiontxt;
    }
}
