package edu.ewu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTreeTest
{
    @Test
    @DisplayName("recursive point calculation yields correct answer")
    void calculateTotalPointsRecursively()
    {
        SkillTree tree = new SkillTree();
        tree.addSkill(new SkillNode("A", 5));
        tree.addSkill(new SkillNode("B", 10));
        tree.addSkill(new SkillNode("C", 15));

        assertEquals(30, tree.calculateTotalPoints());
    }

    @Test
    @DisplayName("should be no points in empty tree")
    void emptyTreeReturnsZero()
    {
        SkillTree tree = new SkillTree();

        assertEquals(0, tree.calculateTotalPoints());
    }

    @Test
    @DisplayName("returns correct value for single skill")
    void singleSkillReturnsItsValue()
    {
        SkillTree tree = new SkillTree();
        tree.addSkill(new SkillNode("Only", 42));

        assertEquals(42, tree.calculateTotalPoints());
    }
}
