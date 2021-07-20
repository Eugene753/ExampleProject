package CovidCase;

import java.io.IOException;

public class CovidCase implements Comparable<CovidCase> {

    String country;
    long totalCases;
    long totalDeaths;
    double deathToCaseRation;

    public CovidCase(String country, long totalCases, long totalDeaths, double deathToCaseRation) {
        this.country = country;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.deathToCaseRation=deathToCaseRation;
    }



    @Override
    public String toString() {
        return   country +"    "
                +totalCases + "     "
                +totalDeaths;
    }



    @Override
    public int compareTo(CovidCase other) {
        int compareResult=this.country.compareTo(other.country);

        if(compareResult==0){
            return this.country.compareTo(other.country);
        }else{
            return compareResult;
        }
    }
}
