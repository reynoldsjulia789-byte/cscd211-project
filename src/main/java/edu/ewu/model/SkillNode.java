package edu.ewu.model;

public class SkillNode implements Comparable<SkillNode>
{
    private String skillName;
    private int    pointValue;

    public SkillNode(String skillName, int pointValue)
    {
        this.skillName  = skillName;
        this.pointValue = pointValue;
    }

    public String getSkillName()
    {
        return this.skillName;
    }

    public int getPointValue()
    {
        return this.pointValue;
    }

    @Override
    public int compareTo(SkillNode other)
    {
        return this.skillName.compareTo(other.getSkillName());
    }

    @Override
    public String toString()
    {
        return skillName + " (" + pointValue + " pts";
    }
}
