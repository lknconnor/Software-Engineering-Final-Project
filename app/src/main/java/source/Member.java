package source;

public abstract class Member 
{
    private String name;
    private String year;
    protected Standing standing;

    public Member(String name, String year, Standing standing)
    {
        setName(name);
        setYear(year);
        setStanding(standing);
    }

    public String getName()
    {
        return name;
    }

    public String getYear()
    {
        return year;
    }

    public Standing getStanding()
    {
        return standing;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setStanding(Standing standing)
    {
        this.standing = standing;
    }

    public double amountOwed()
    {
        return standing.getDues();
    }

    public void setDues(double dues)
    {
        standing.setDues(dues);
    }

    public String describe()
    {
        return name + ", " + year + " in " + standing.getStatus() + " standing.";
    }

    public String toFile()
    {
        return name + ", " + year + ", " + standing.getStatus() + ", " + standing.getGpa() + ", " + standing.getDues();
    }
}