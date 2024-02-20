package source;

public abstract class Standing 
{
    private String status;
    private double gpa;
    private double dues;

    public Standing(String status, double gpa, double dues)
    {
        this.status = status;
        this.gpa = gpa;
        this.dues = dues;
    }

    public static Standing createStanding(String status, double gpa, double dues) {
        switch (status) {
            case "Good":
                return new GoodStanding(gpa, dues);
            case "Poor":
                return new PoorStanding(gpa, dues);
            default:
                return null;
        }
    }
    
 

    public String getStatus()
    {
        return status;
    }

    public Double getGpa()
    {
        return gpa;
    }

    public Double getDues()
    {
        return dues;
    }

    public void setDues(double dues)
    {
        this.dues = dues;
    }

    public void setGpa(double gpa)
    {
        this.gpa = gpa;
    }

}
