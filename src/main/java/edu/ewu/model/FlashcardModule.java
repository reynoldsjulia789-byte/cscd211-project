package edu.ewu.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.ewu.util.GenericLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlashcardModule extends LearningModule
{
    private GenericLinkedList<Flashcard> flashcards;

    public FlashcardModule()
    {
        super("Flashcard Practice");                // must call parent constructor as 1st line

        this.flashcards = new GenericLinkedList<>();
    }

    public void addFlashcard(final Flashcard card)
    {
        if (card == null)
        {
            throw new IllegalArgumentException("null flashcard not allowed");
        }

        this.flashcards.addLast(card);
    }

    public GenericLinkedList<Flashcard> getFlashcards()
    {
        return flashcards.copyList();                          // deep copy returned to prevent editing internal list
    }

    public int getCardCount()
    {
        return flashcards.size();
    }

    /**
     * Loads flashcards from a text file.
     * Expected format: question;answer (one per line)
     *
     * @param filename path to the file
     * @return number of cards loaded
     * @author Julia Reynolds
     */
    public int loadCardsFromFile(String filename)
    {
        int       loaded;
        String    line;
        String[]  parts;

        Flashcard card;

        loaded = 0;

        try (Scanner scanner = new Scanner(new File(filename)))                 // The try (resource) syntax ensures the Scanner closes even if an exception occurs.
        {
            while (scanner.hasNextLine())
            {
                line     = scanner.nextLine();
                parts    = line.split(";");

                if (parts.length == 2)
                {
                    card = new Flashcard(parts[0].trim(), parts[1].trim());

                    flashcards.addLast(card);

                    loaded++;
                }
            }

            // Sort flashcards alphabetically by question
            flashcards.selectionSort();
        }
        catch (FileNotFoundException caught)
        {
            System.err.println("Could not load flashcards: " + caught.getMessage());
        }

        return loaded;
    }

    public void saveCardsToFile(String filename)
    {
        if (filename == null || filename.isBlank())
        {
            throw new IllegalArgumentException("no");
        }

        try (PrintWriter writer = new PrintWriter(filename))
        {
            for(Flashcard current : flashcards)
            {
                writer.println(current.getQuestion() + ";" + current.getAnswer());
            }
        }
        catch (Exception caught)
        {
            System.err.println("could not save flashcards: " + caught.getMessage());
        }
    }

    @Override
    public void startSession()
    {
        System.out.println("Starting flashcard session with " + this.flashcards.size() + " cards...");
    }
}
