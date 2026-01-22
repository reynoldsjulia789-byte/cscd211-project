package edu.ewu.model;

import java.util.ArrayList;
import java.util.List;

public class QuizModule extends LearningModule
{
    private List<String> questions;
    private int          questionidx;

    public QuizModule()
    {
        super("Quiz");

        this.questions     = new ArrayList<>();
        this.questionidx   = 0;
    }

    public int getQuestionidx()
    {
        if (this.questions.isEmpty())
        {
            throw new IllegalArgumentException("quiz has no questions");
        }

        return this.questionidx;
    }

    public int getQuestionCount()
    {
        return this.questions.size();
    }

    public void addQuestion(final String question)
    {
        if (question == null || question.isBlank())
        {
            throw new IllegalArgumentException("null or blank question not allowed");
        }

        this.questions.add(question);
    }

    public void displayNextQuestion()
    {
        if (this.questions.isEmpty())
        {
            throw new IllegalArgumentException("quiz has no questions");
        }

        this.questionidx++;

        if (questionidx < questions.size())
        {
            System.out.println((this.questionidx + 1) + ") " + this.questions.get(questionidx));
        }
        else
        {
            System.out.println("Quiz complete!");
        }
    }

    @Override
    public void startSession()
    {
        if (this.questions.isEmpty())
        {
            throw new IllegalArgumentException("quiz has no questions");
        }

        this.questionidx = 0;

        System.out.println((this.questionidx + 1) + ") " + this.questions.getFirst());
    }
}
