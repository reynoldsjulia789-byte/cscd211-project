# CSCD 211: Programming Principles II

A hands-on project-based learning environment for mastering object-oriented programming in Java. This repository is designed for students preparing to test out of CSCD 211 or those who want a structured, self-paced path through the material.

## Two Ways to Learn

**Exploration Mode** (recommended first): Stay on the `main` branch. Browse the finished implementation, run the quizzes, read the commit history to see how each feature was built step by step.

**Build Mode**: Create your own branch from `student-starting-point`, then implement everything yourself using only the quest documentation. When stuck, switch to `main` to see how it was done.

```bash
# Exploration Mode - just clone and explore
git clone https://github.com/jdoner02/cscd211-prep.git
cd cscd211-prep

# Build Mode - start fresh and implement yourself
git checkout -b my-adventure student-starting-point
```

See [Your Adventure Begins](./docs/adventure/day0-mission-control/your-adventure-begins.md) for detailed branching instructions.

## Getting Started

**Prerequisites:**
- Java 21 (LTS)
- Maven 3.8+
- A code editor (VS Code with Java extensions or IntelliJ IDEA)

**First steps:**
1. Clone this repository
2. Open `docs/START_HERE.md` in your editor's Markdown preview
3. Follow the quest system to navigate the material

```bash
git clone https://github.com/jdoner02/cscd211-prep.git
cd cscd211-prep
mvn compile
```

## Self-Assessment Quizzes

Test your understanding with interactive quizzes:

```bash
mvn exec:java -Dexec.mainClass="edu.ewu.view.Main"
```

This runs the quiz system, which presents multiple-choice questions covering linked lists, OOP concepts, and other CSCD 211 topics. Use these quizzes to identify areas that need more review before attempting the APE.

## What's In This Repository

This repository contains both **learning documentation** and **reference implementations**. The code in `src/main/java` demonstrates the concepts discussed in the quest documentation. You should:

1. Read the quest documentation to understand concepts
2. Study the existing code to see how concepts are implemented
3. Run the tests to see how the code behaves
4. Take the quizzes to verify your understanding

The 180+ unit tests serve as executable documentation showing how each class should behave.

## Documentation

The learning content lives in `docs/`. Start with:

- **[Start Here](./docs/START_HERE.md)** - Your entry point into the quest system
- **[Quest Map](./docs/adventure/quest-map.md)** - Visual overview of all learning paths

## Project Structure

```
cscd211/
├── docs/                    # Learning documentation (start here!)
│   ├── START_HERE.md
│   └── adventure/           # Quest-based learning modules
├── src/main/java/           # Reference implementations to study
│   └── edu/ewu/
│       ├── model/           # Domain classes (Skill, Question, Quiz, etc.)
│       └── util/            # Data structures (GenericLinkedList, Sorter)
├── src/main/resources/      # Quiz files and sample data
├── src/test/java/           # Unit tests demonstrating expected behavior
├── source-content/          # Raw course materials for reference
└── pom.xml                  # Maven build configuration
```

## External References

These external documents provide additional context:

- [Course Learning Outcomes](https://docs.google.com/document/d/1N2L3NHlxE_rLblOBJQ6Q9cuSfdeKATg6la1n9T7Ag48/edit?tab=t.0)
- [Final Exam Review Guide](https://docs.google.com/document/d/1e0q6RNwgsvmLTqD6s1EBKtsW8rfGtou0upucviXP29U/edit?usp=sharing)
- [Sample Final Exam](https://docs.google.com/document/d/1df6c8-fIhaTOeYtKRPFXIUhzcg3JXldpPby5chc6l5Y/edit?usp=sharing)
- [NotebookLM (with all CSCD211 lecture transcripts)](https://notebooklm.google.com/notebook/fc204e0e-a1b0-4fc8-8922-299b88fa0834)
- [14-Day Field Guide (original)](https://docs.google.com/document/d/1MNn-lUmOyZA70kevLqZ8VhiR50W_zzP-IZaQV_sLANU/edit?usp=sharing)