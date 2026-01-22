package edu.ewu.controller;

import edu.ewu.model.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

public class DashboardController
{
    private List<LearningModule> modules;
    private FlashcardModule      flashcardModule;
    private SkillTree            skillTree;
    private User                 currentUser;
    private int                  currentCardIndex;

    public DashboardController()
    {
        this.modules = new ArrayList<>();

        // Initialize flashcard module
        this.flashcardModule = new FlashcardModule();
        this.flashcardModule.loadCardsFromFile("src/main/resources/flashcards.txt");
        this.modules.add(flashcardModule);

        // Initialize coding challenge module
        this.modules.add(new CodingChallengeModule("medium"));

        // Initialize skill tree
        this.skillTree = new SkillTree();
        this.skillTree.addSkill(new SkillNode("OOP Fundamentals", 10));
        this.skillTree.addSkill(new SkillNode("Inheritance", 15));
        this.skillTree.addSkill(new SkillNode("Polymorphism", 15));
        this.skillTree.addSkill(new SkillNode("Linked Lists", 20));
        this.skillTree.addSkill(new SkillNode("Recursion", 20));
        this.skillTree.addSkill(new SkillNode("File I/O", 10));
        this.skillTree.addSkill(new SkillNode("Interfaces", 10));

        // Initialize user
        this.currentUser = new User("student");

        this.currentCardIndex = 0;
    }

    public void launchModule(final LearningModule module)
    {
        // This is a generic method that works with ANY Learning Module due to polymorphism
        module.displayWelcome();
        module.startSession();
    }

    public void handlePracticeFlashcards()
    {
        if (this.flashcardModule.getCardCount() == 0)
        {
            showAlert("No Cards", "No flashcards loaded. Check your data file.");
            return;
        }

        this.currentCardIndex = 0;
        launchModule(this.flashcardModule);
        showCurrentFlashcard();
    }

    public String getCurrentQuestion()
    {
        if (this.currentCardIndex < this.flashcardModule.getCardCount())
        {
            return this.flashcardModule.getFlashcards().get(this.currentCardIndex).getQuestion();
        }

        return "No more cards!";
    }

    public String getCurrentAnswer()
    {
        if (this.currentCardIndex < this.flashcardModule.getCardCount())
        {
            return this.flashcardModule.getFlashcards().get(this.currentCardIndex).getAnswer();
        }

        return "";
    }

    public void nextCard()
    {
        currentCardIndex++;

        if (this.currentCardIndex >= this.flashcardModule.getCardCount())
        {
            showAlert("Complete!", "You've reviewed all " +
                    this.flashcardModule.getCardCount() + " cards!");

            this.currentCardIndex = 0;  // Reset for next time
        }
        else
        {
            showCurrentFlashcard();
        }
    }

    private void showCurrentFlashcard()
    {
        System.out.println("\nCard " + (this.currentCardIndex + 1) + " of " +
                           this.flashcardModule.getCardCount());
        System.out.println("Q: " + getCurrentQuestion());
    }

    public void handleTakeCodingChallenge()
    {
        System.out.println("Starting coding challenge...");

        // Find and Launch coding challenge
        for (LearningModule module : this.modules)
        {
            if (module instanceof CodingChallengeModule)
            {
                launchModule(module);
                showAlert("Coming Soon", "Coding challenges will be implemented in a future update!");
                return;
            }
        }
    }

    public void handleViewSkillTree()
    {
        int           totalPoints;
        StringBuilder message;

        totalPoints = this.skillTree.calculateTotalPoints();
        message     = new StringBuilder();

        message.append("Skills Unlocked: ").append(skillTree.getSkillCount()).append("\n");
        message.append("Total Points: ").append(totalPoints).append("\n\n");
        message.append("Keep learning to unlock more skills!");

        showAlert("Skill Tree", message.toString());
    }

    public void handleSaveProgress()
    {
        this.currentUser.addPoints(this.skillTree.calculateTotalPoints());
        this.currentUser.saveProgress("user_progress.dat");

        showAlert("Saved", "Progress saved for " + currentUser.getUsername());
    }

    private void showAlert(String title, String content)
    {
        Alert alert;

        alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Getters for view to access
    public int getCardCount()
    {
        return this.flashcardModule.getCardCount();
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

//    // Demo: launch ALL modules
//    public void launchAll()
//    {
//        for (LearningModule module : this.modules)
//        {
//            launchModule(module);
//            System.out.println("---");
//        }
//    }

    public void handleSettings()
    {
        System.out.println("Opening settings...");
        // TODO: Display settings
    }
}
