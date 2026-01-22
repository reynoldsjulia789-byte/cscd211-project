package edu.ewu.model;

import edu.ewu.util.GenericLinkedList;

public class SkillTree
{
    private GenericLinkedList<SkillNode> skills;

    public SkillTree()
    {
        this.skills = new GenericLinkedList<>();
    }

    public void addSkill(SkillNode skill)
    {
        this.skills.addLast(skill);
    }

    public int getSkillCount()
    {
        return this.skills.size();
    }

    /**
     * Calculate total points using iteration
     * @return total points
     */
    public int calculateTotalPointsIterative()
    {
        int total, index;

        total = 0;

        for (index = 0; index < this.skills.size(); index++)
        {
            total += this.skills.get(index).getPointValue();
        }

        return total;
    }

    /**
     * Calculate total points using recursion.
     * This is the public entry point
     * @return total points
     */
    public int calculateTotalPoints()
    {
        return calculateTotalPointsRecursive(0);
    }

    /**
     * Recursive helper method.
     * @param index current position in the list
     * @return sum of total points from index to end of list
     */
    private int calculateTotalPointsRecursive(int index)
    {
        // Base case: past the end of the list
        if (index >= this.skills.size())
        {
            return 0;
        }

        // Recursive step: current points + sum of rest
        int currentPoints, restPoints;

        currentPoints = this.skills.get(index).getPointValue();
        restPoints    = calculateTotalPointsRecursive(index + 1);

        return currentPoints + restPoints;
    }

    @Override
    public String toString()
    {
        return "SkillTree: " + this.skills.size() + " skills";
    }

    /*
        Recursion needs a base case (to exit the loop) and a recursive step (to reduce the problem)

        Two types of recursion: Head recursion and Tail recursion

        Tail recursion: do the work before making the recursive call (the recursive call is the last thing the method does)
        Head recursion: recursive call comes first. the work happens after the recursive call.
            - use to process things in reverse
     */
}
