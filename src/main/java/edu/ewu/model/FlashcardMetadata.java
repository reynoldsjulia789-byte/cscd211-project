package edu.ewu.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlashcardMetadata implements Cloneable
{
    private LocalDate lastReviewed;
    private int       timesReviewed;

    public FlashcardMetadata()
    {
        this.lastReviewed  = LocalDate.now();
        this.timesReviewed = 0;
    }

    public LocalDate getLastReviewed()
    {
        return this.lastReviewed;
    }

    public void setLastReviewed(LocalDate date)
    {
        /*
            checking if stored date is before the date being passed
            don't want to change the last reviewed date if the date passed is not the most recent date
         */
        if (this.lastReviewed.isBefore(date))
        {
            this.lastReviewed = date;
        }
    }

    public int getTimesReviewed()
    {
        return this.timesReviewed;
    }

    /**
     * Increments times reviewed by 1 when called and sets date last reviewed to now
     */
    public void incrementTimesReviewed()
    {
        this.timesReviewed++;
        this.lastReviewed = LocalDate.now();
    }

    @Override
    public FlashcardMetadata clone()
    {
        try
        {
            return (FlashcardMetadata)  super.clone();              // what does super.clone do?
        }
        catch (CloneNotSupportedException caught)
        {
            // Should never happen since we implement Cloneable
            throw new RuntimeException("Clone failed", caught);
        }
    }

    @Override
    public String toString()
    {
        return "Reviewed " + timesReviewed + " times, last: " + lastReviewed;
    }
}
