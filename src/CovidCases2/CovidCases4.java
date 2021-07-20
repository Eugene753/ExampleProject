package CovidCases2;

import CovidCase.CovidCase;
import org.apache.commons.collections4.list.TreeList;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.jnlp.PersistenceService;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class CovidCases4 {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\full_data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook covid = new XSSFWorkbook(fileInputStream);
        Sheet sheet = covid.getSheet("full_data");
        int noOfRows = sheet.getPhysicalNumberOfRows();

        List<Person> list = new ArrayList<Person>();
        Map<String, Long> map = new TreeMap<>();
        Map<String, Long> map2 = new TreeMap<>();
        String country = "";
        long cases=0;
        long deaths=0;
        for (int i = 1; i < noOfRows; i++) {
            Row row = sheet.getRow(i);
            try {
                country = row.getCell(1).toString();
                cases = (long) row.getCell(4).getNumericCellValue();
                deaths = (long) row.getCell(5).getNumericCellValue();
                map.put(country, cases);
                map2.put(country, deaths);
                if (country.contains("World")) {
                    map.remove(country);
                }

            } catch (NullPointerException e) {

            }

        }
        Collections.sort(list);


        Set<String> set2 = map.keySet();

        List<Person> list2 = new LinkedList<Person>();

        double ration = 0;
        for (String str : set2) {
            ration = (double) map2.get(str) / map.get(str) * 100;
            double newRatio=0;
            try {
                BigDecimal bd = new BigDecimal(ration).setScale(2, RoundingMode.HALF_UP);
                newRatio = bd.doubleValue();
            }catch(NumberFormatException e){
                System.out.println("Cant convert");
            }
            Person person = new Person(str, map.get(str), map2.get(str), newRatio);

            list2.add(person);

        }

        /*System.out.println(String.format("%-30s%-30s%-30s%-30s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for(Person p:list2){
            System.out.println(String.format("%-30s%-30s%-30s%-30s",p.country,p.totalCases,p.totalDeaths,p.deathToCaseRation+"%"));

        }*/

        /*Collections.sort(list2, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Long.valueOf(o2.totalDeaths).compareTo(o1.totalDeaths);
            }
        });*/

        /*System.out.println(String.format("%-30s%-30s%-30s%-30s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (Person p : list2) {
            System.out.println(String.format("%-30s%-30s%-30s%-30s", p.country, p.totalCases, p.totalDeaths, p.deathToCaseRation + "%"));
        }*/


        /*long max=0;
        System.out.println("Countries with Minimum Deaths");
        for(Person p:list2){
            if(p.totalDeaths==0){
                System.out.println("Country: "+p.country+
                                    "\nTotal Cases: "+p.totalCases+
                                    "\nTotal Deaths: "+p.totalDeaths);
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Countries with Maximum Deaths");
        for(Person p:list2){
            if(p.totalDeaths>max){
                max=p.totalDeaths;
                System.out.println("Country: "+p.country+
                                    "\nTotal Cases: "+p.totalCases+
                                    "\nTotal Deaths: "+p.totalDeaths);
            }
        }*/







        /*Collections.sort(list2, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Long.valueOf(o2.totalCases).compareTo(o1.totalCases);
            }
        });
        System.out.println(String.format("%-30s%-30s%-30s%-30s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for(Person p:list2){
            System.out.println(String.format("%-30s%-30s%-30s%-30s",p.country,p.totalCases,p.totalDeaths,p.deathToCaseRation+"%"));
        }*/

        Collections.sort(list2, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Double.valueOf(o2.deathToCaseRation).compareTo(o1.deathToCaseRation);
            }
        });
        /*System.out.println(String.format("%-30s%-30s%-30s%-30s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for(Person p:list2){
            System.out.println(String.format("%-30s%-30s%-30s%-30s",p.country,p.totalCases,p.totalDeaths,p.deathToCaseRation+"%"));
        }*/


        /*System.out.println(String.format("%-30s%-30s%-30s%-30s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        String country1="";
        long l1=0;
        long l2=0;
        double d=0;
        for(int i=0;i<10;i++){
            country1=list2.get(i).country;
            l1=list2.get(i).totalCases;
            l2=list2.get(i).totalDeaths;
            d=list2.get(i).deathToCaseRation;
            System.out.println(String.format("%-30s%-30s%-30s%-30s",country1,l1,l2,d+"%"));
        }*/


        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter country name:");
        String country2=scanner.nextLine();
        for(Person covid2:list2){
            if(country2.equals(covid2.country)){
                System.out.println("Covid Case Data:");
                System.out.println("Country: "+covid2.country+
                        "\nTotal Cases: "+covid2.totalCases+
                        "\nTotal Deaths: "+covid2.totalDeaths+
                        "\n"+
                        "\nMortality Rate "+covid2.deathToCaseRation);

            }
        }





        }
    }




