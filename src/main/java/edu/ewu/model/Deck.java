package edu.ewu.model;

import java.util.ArrayList;
import java.util.List;

public class Deck
{
    private String m_deckname;
    private List<Flashcard> m_cards;

    public Deck(final String deckname)
    {
        if (deckname == null || deckname.isBlank())
        {
            throw new IllegalArgumentException("can't have blank or empty deckname");
        }

        m_deckname = deckname;
        m_cards    = new ArrayList<>();
    }

    public void addFlashCard(final Flashcard card)
    {
        if (card == null)
        {
            throw new IllegalArgumentException("flashcard must exist to be added to deck");
        }

        m_cards.add(card);
    }

    public void addFlashCard(final Flashcard card1, final Flashcard card2, final Flashcard card3)
    {
        if (card1 == null || card2 == null || card3 == null)
        {
            throw new IllegalArgumentException("flashcards must exist to be added to deck");
        }

        m_cards.add(card1);
        m_cards.add(card2);
        m_cards.add(card3);
    }

    public void addFlashCard(final Flashcard card1, final Flashcard card2,
                             final Flashcard card3, final Flashcard card4, final Flashcard card5)
    {
        if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null)
        {
            throw new IllegalArgumentException("flashcards must exist to be added to deck");
        }

        m_cards.add(card1);
        m_cards.add(card2);
        m_cards.add(card3);
        m_cards.add(card4);
        m_cards.add(card5);
    }

    public void removeFlashCard(final Flashcard card)
    {
        m_cards.remove(card);
    }

    public void renameDeck(final String newDeckname)
    {
        if (newDeckname == null || newDeckname.isBlank())
        {
            throw new IllegalArgumentException("can't have blank or empty deckname");
        }

        m_deckname = newDeckname;
    }

    public String getDeckname()
    {
        return m_deckname;
    }

    public List<Flashcard> getCards()
    {
        return new ArrayList<>(m_cards);
    }

    public int deckSize()
    {
        return m_cards.size();
    }

    @Override
    public String toString()
    {
        return m_deckname + " (" + this.deckSize() + " Cards)";
    }
}
