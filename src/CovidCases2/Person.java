package CovidCases2;


public class Person implements Comparable<Person> {

   String country;
   long totalCases;
   long totalDeaths;
   double deathToCaseRation;

    public Person(String country, long totalCases, long totalDeaths, double deathToCaseRation) {
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
    public int compareTo(Person other) {
        int compareResult=this.country.compareTo(other.country);

        if(compareResult==0){
             return this.country.compareTo(other.country);
        }else{
            return compareResult;
        }
    }


}
