package edu.ewu.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User implements Storable
{
    private int                m_totalPoints;
    private String             m_username;
    private List<StudySession> m_sessions;

    public User(final String username)
    {
        if (username == null || username.isBlank())
        {
            throw new IllegalArgumentException("no");
        }

        m_username    = username;
        m_sessions    = new ArrayList<>();
        m_totalPoints = 0;
    }

    public String getUsername()
    {
        return m_username;
    }

    public List<StudySession> getSessions()
    {
        return new ArrayList<>(m_sessions);     // Return a copy to protect internal list (defensive copy)
    }

    public void addSession(final StudySession session)
    {
        m_sessions.add(session);
    }

    public int getTotalCardsReviewed()
    {
        int total;

        total = 0;

        for (StudySession session: m_sessions)
        {
            total += session.getCardsReviewed();
        }

        return total;
    }

    public int getTotalPoints()
    {
        return m_totalPoints;
    }

    public void addPoints(int points)
    {
        m_totalPoints += points;
    }

    @Override
    public void saveProgress(String filename)
    {
        try (PrintWriter writer = new PrintWriter(new File(filename)))
        {
            writer.println(m_username);
            writer.println(m_totalPoints);
            writer.println(m_sessions.size());
            // Any other session details that should be saved?

            System.out.println(m_username + " progress saved");
        }
        catch (Exception caught)
        {
            System.err.println(m_username + " progress could not be saved");
        }
    }

    @Override
    public void loadProgress(String filename)
    {
        try (Scanner scanner = new Scanner(new File(filename)))
        {
            if (scanner.hasNextLine())
            {
                m_username = scanner.nextLine().trim();
            }

            if (scanner.hasNextLine())
            {
                m_totalPoints = scanner.nextInt();
            }

            System.out.println("Progress saved - " + m_username);
        }
        catch (Exception caught)
        {
            System.err.println("Couldn't load progress for " + m_username);
        }
    }

    @Override
    public String toString()
    {
        return "User: " + m_username + " (" + m_sessions.size() + " sessions)";
    }
}
