package edu.ewu.model;

import java.time.LocalDateTime;

public class StudySession implements Comparable<StudySession>
{
    private LocalDateTime m_startTime;
    private int           m_cardsReviewed;

    public StudySession()
    {
        m_startTime     = LocalDateTime.now();
        m_cardsReviewed = 0;
    }

    public LocalDateTime getStartTime()
    {
        return m_startTime;
    }

    public int getCardsReviewed()
    {
        return m_cardsReviewed;
    }

    public void incrementCardsReviewed()
    {
        m_cardsReviewed++;
    }

    @Override
    public int compareTo(StudySession other)
    {
        return this.m_startTime.compareTo(other.getStartTime());
    }

    @Override
    public String toString()
    {
        return "Session started: " + m_startTime + ", cards: " + m_cardsReviewed;
    }
}
