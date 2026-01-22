package edu.ewu.view;

import edu.ewu.controller.DashboardController;

import javafx.application.Application;          // Application        - the base class for JavaFX apps
import javafx.geometry.Pos;
import javafx.scene.Scene;                      // Scene              - the content container
import javafx.scene.control.Button;             // Control subclasses - UI elements (Buttons, Labels, Text Fields, etc.)
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;          // Pane subclasses    - Layout managers (BorderPane, VBox, HBox, etc)
import javafx.scene.layout.VBox;
import javafx.stage.Stage;                      // Stage              - the window itself
import javafx.geometry.Insets;

public class Main extends Application           // extending Application makes this a JavaFX app
{
    private DashboardController m_controller;
    private Label               m_questionLabel;
    private Label               m_statusLabel;

    @Override
    public void start(Stage primaryStage)       // entry point called by JavaFX
    {
        // Variables
        double     buttonWidth;
        String     headertitle;

        // Objects
        BorderPane root;                                            // Layout w top/center/bottom/left/right regions
        Button     flashcardsBtn, challengeBtn, skillTreeBtn, settingsBtn;
        Button     showAnswerBtn, nextCardBtn,  saveBtn;
        Label      header;
        Scene      scene;                                           // Content area
        VBox       buttonBox, headerBox;                                       // Vertical stack of children


        m_controller     = new DashboardController();

        // Create the root layout
        root             = new BorderPane();

        root.setPadding(new Insets(20));

        // Create header
        headertitle      = "CSCD 211 Learning Ecosystem";
        header           = new Label(headertitle);

        header.setStyle("-fx-font-size: 28px;" +
                        "-fx-text-fill: #151a21;" +
                        "-fx-font-weight: bold");
        header.setAlignment(Pos.CENTER);
        header.setMaxWidth(Double.MAX_VALUE);

        m_statusLabel    = new Label("Cards loaded: " + m_controller.getCardCount());

        m_statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #382616");

        headerBox        = new VBox(5, header, m_statusLabel);

        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20));

        root.setTop(headerBox);                                                            // What is this doing?
        root.setStyle("-fx-background-color: #e8caae");                                 // #bcc2cc blue

        // Center: Flashcard display area
        m_questionLabel  = new Label("Click 'Practice Flashcards' to begin");

        m_questionLabel.setStyle("-fx-font-size: 18px;" +
                                 "-fx-padding: 20px");
        m_questionLabel.setWrapText(true);

        root.setCenter(m_questionLabel);

        // Create navigation buttons
        buttonBox        = new VBox(15);                                             // 10px spacing

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setStyle("-fx-background-color: #151a21");

        buttonWidth      = 200;

        // Flashcards Button
        flashcardsBtn    = new Button("Practice Flashcards");

        flashcardsBtn.setStyle("-fx-background-color: #f29c4e;" +
                               "-fx-text-fill: #382616;" +
                               "-fx-font-weight: bold");
        flashcardsBtn.setPrefWidth(buttonWidth);
        flashcardsBtn.setOnAction(event ->
                        {
                            m_controller.handlePracticeFlashcards();
                            updateQuestionDisplay();
                        });

        // Show Answers Button
        showAnswerBtn    = new Button("Show Answer");

        showAnswerBtn.setPrefWidth(buttonWidth);
        showAnswerBtn.setOnAction(event ->
                        {
                            m_questionLabel.setText("A: " + m_controller.getCurrentAnswer());
                        });

        // Next Card Button
        nextCardBtn      = new Button("Next Card");
        nextCardBtn  .setPrefWidth(buttonWidth);
        nextCardBtn  .setOnAction(event ->
                        {
                            m_controller.nextCard();
                            updateQuestionDisplay();
                        });

        // Challenge Button
        challengeBtn     = new Button("Take Coding Challenge");

        challengeBtn .setStyle("-fx-background-color: #f29c4e;" +
                               "-fx-text-fill: #382616;" +
                               "-fx-font-weight: bold");
        challengeBtn .setPrefWidth(buttonWidth);
        challengeBtn .setOnAction(event -> m_controller.handleTakeCodingChallenge());

        // Skill Tree Button
        skillTreeBtn     = new Button("View Skill Tree");

        skillTreeBtn .setStyle("-fx-background-color: #f29c4e;" +
                               "-fx-text-fill: #382616;" +
                               "-fx-font-weight: bold");
        skillTreeBtn .setPrefWidth(buttonWidth);
        skillTreeBtn .setOnAction(event -> m_controller.handleViewSkillTree());

        // Save Button
        saveBtn          = new Button("Save Progress");

        saveBtn      .setPrefWidth(buttonWidth);
        saveBtn      .setOnAction(event -> m_controller.handleSaveProgress());

        // Settings Button
        settingsBtn      = new Button("Settings");

        settingsBtn  .setStyle("-fx-background-color: #803c62; -fx-text-fill: #2b0d1e; -fx-font-weight: bold");
        settingsBtn  .setOnAction(event -> m_controller.handleSettings());


        buttonBox    .getChildren().addAll
                                (
                                    flashcardsBtn,
                                    showAnswerBtn,
                                    nextCardBtn,
                                    challengeBtn,
                                    skillTreeBtn,
                                    saveBtn,
                                    settingsBtn
                                );
        buttonBox.setAlignment(Pos.TOP_CENTER);

        root.setRight(buttonBox);

        // Set up the scene and stage
        scene            = new Scene(root, 600, 450);

        primaryStage.setTitle(headertitle);
        primaryStage.setScene(scene);
        primaryStage.show();                                        // Display the window
    }

    private void updateQuestionDisplay()
    {
        m_questionLabel.setText("Q: " + m_controller.getCurrentQuestion());
    }

    public static void main(String[] args)
    {
        launch(args); // Calls start() internally - starts the JavaFX runtime
    }
}
