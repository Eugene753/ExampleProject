package CovidCaseTraningClass;

public class CovidCase {

    public String country;
    public long totalCases;
    public long totalDeaths;
    public double deathToCaseRatio;

    public CovidCase(String country, long totalCases, long totalDeaths, double deathToCaseRatio) {
        this.country = country;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.deathToCaseRatio = deathToCaseRatio;
    }




}
