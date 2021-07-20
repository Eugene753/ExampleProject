package CovidCase;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PandemicDashboard {

    public static ArrayList<CovidCase> caseList;

    public static List<CovidCase> fillCovidCaseList(String fileName) throws IOException {

        FileInputStream fileInputStream=new FileInputStream(fileName);
        XSSFWorkbook covid=new XSSFWorkbook(fileInputStream);
        Sheet sheet=covid.getSheet("full_data");

        int noOfRows=sheet.getPhysicalNumberOfRows();
        caseList=new ArrayList<CovidCase>();
        Map<String,Long>map=new TreeMap<>();
        Map<String,Long>map2=new TreeMap<>();
        String country="";
        for(int i=1;i<noOfRows;i++){
            Row row=sheet.getRow(i);
            try{
                    country = row.getCell(1).toString();
                    long cases = (long) row.getCell(4).getNumericCellValue();
                    long deaths = (long) row.getCell(5).getNumericCellValue();
                    map.put(country, cases);
                    map2.put(country, deaths);
                if(country.contains("World")){
                    map.remove(country);
                }

            }catch (NullPointerException e){

            }
        }

        Set<String> set2=map.keySet();

        double ration=0;
        for(String str:set2) {
            ration = (double)map2.get(str) / map.get(str) * 100;
            double newRatio=0;
            try {
                BigDecimal bd = new BigDecimal(ration).setScale(2, RoundingMode.HALF_UP);
                newRatio = bd.doubleValue();
            }catch(NumberFormatException e){

            }
            CovidCase covidCase = new CovidCase(str, map.get(str), map2.get(str), newRatio);

            caseList.add(covidCase);
        }
        return caseList;
    }

    public static void displayCovidCases(List<CovidCase> caseList){

        System.out.println(String.format("%-40s%-40s%-40s%-40s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        for(CovidCase list:caseList){
            System.out.println(String.format("%-40s%-40s%-40s%-40s",list.country,list.totalCases,list.totalDeaths,list.deathToCaseRation+"%"));
        }
    }

    public static void decreasingOrderListByTotalDeaths(List<CovidCase> caseList){

        Collections.sort(caseList, new Comparator<CovidCase>() {
            @Override
            public int compare(CovidCase o1, CovidCase o2) {
                return Long.valueOf(o2.totalDeaths).compareTo(o1.totalDeaths);
            }
        });

        System.out.println(String.format("%-40s%-40s%-40s%-40s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");

        for(CovidCase covid:caseList){
            System.out.println(String.format("%-40s%-40s%-40s%-40s",covid.country,covid.totalCases,covid.totalDeaths,covid.deathToCaseRation+"%"));

        }
    }

    public static void deacreasingOrderListByTotalCases(List<CovidCase> caseList){

        Collections.sort(caseList,new Comparator<CovidCase>(){
            @Override
            public int compare(CovidCase o1,CovidCase o2){
                return Long.valueOf(o2.totalCases).compareTo(o2.totalCases);
            }
        });
        System.out.println(String.format("%-40s%-40s%-40s%-40s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        for(CovidCase covid:caseList){
            System.out.println(String.format("%-40s%-40s%-40s%-40s", covid.country, covid.totalCases, covid.totalDeaths, covid.deathToCaseRation + "%"));
        }
    }

    public static void minAndMaxNumberOfTotalDeaths(List<CovidCase> caseList){
        Collections.sort(caseList, new Comparator<CovidCase>() {
            @Override
            public int compare(CovidCase o1, CovidCase o2) {
                return Long.valueOf(o2.totalDeaths).compareTo(o1.totalDeaths);
            }
        });
        long max=0;
        System.out.println("Countries with Minimum Deaths");
        for(CovidCase covid:caseList){
            if(covid.totalDeaths==0){
                System.out.println("Country: "+covid.country+
                        "\nTotal Cases: "+covid.totalCases+
                        "\nTotal Deaths: "+covid.totalDeaths);
            }
        }
        System.out.println("-----------------------------");
        System.out.println("Countries with Maximum Deaths");
        for(CovidCase covid:caseList){
            if(covid.totalDeaths>max){
                max=covid.totalDeaths;
                System.out.println("Country: "+covid.country+
                        "\nTotal Cases: "+covid.totalCases+
                        "\nTotal Deaths: "+covid.totalDeaths);
            }
        }
    }
    public static void minAndMaxNumberOfTotalCases(List<CovidCase>caseList){
        Collections.sort(caseList, new Comparator<CovidCase>() {
            @Override
            public int compare(CovidCase o1, CovidCase o2) {
                return Long.valueOf(o2.totalDeaths).compareTo(o1.totalDeaths);
            }
        });
        System.out.println("Countries with Minimum Cases");
        for(CovidCase covid:caseList){
            if(covid.totalCases==1){
                System.out.println("Country: "+covid.country+
                        "\nTotal Cases: "+covid.totalCases+
                        "\nTotal Deaths: "+covid.totalDeaths);
            }
        }
        long max=0;
        System.out.println("----------------------------");
        System.out.println("Countries with Maximum Cases");
        for(CovidCase covid:caseList){
            if(covid.totalCases>max){
                max=covid.totalCases;
                System.out.println("Country: "+covid.country+
                        "\nTotal Cases: "+covid.totalCases+
                        "\nTotal Deaths: "+covid.totalDeaths);
            }
        }
    }

    public static void searchByMortalityRate(List<CovidCase>caseList){
        String country="";
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter country name:");
        country = scanner.nextLine();
        for(CovidCase covid:caseList){
            if(country.equalsIgnoreCase(covid.country)){
                System.out.println("Covid Case Data:");
                System.out.println("Country: "+covid.country+
                        "\nTotal Cases: "+covid.totalCases+
                        "\nTotal Deaths: "+covid.totalDeaths+
                        "\n"+
                        "\nMortality Rate "+covid.deathToCaseRation);
            }
        }
    }

    public static void MortalityRateDecreasingOrder(List<CovidCase>caseList){
        Collections.sort(caseList, new Comparator<CovidCase>() {
            @Override
            public int compare(CovidCase o1, CovidCase o2) {
                return Double.valueOf(o2.deathToCaseRation).compareTo(o1.deathToCaseRation);
            }
        });
        System.out.println(String.format("%-40s%-40s%-40s%-40s","COUNTRIES","TOTAL CASES","TOTAL DEATHS","DEATH TO CASE RATIO"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        String country="";
        long totalCases=0;
        long totalDeaths=0;
        double deathToCaseRation=0;
        for(int i=0;i<10;i++){
            country=caseList.get(i).country;
            totalCases=caseList.get(i).totalCases;
            totalDeaths=caseList.get(i).totalDeaths;
            deathToCaseRation=caseList.get(i).deathToCaseRation;
            System.out.println(String.format("%-40s%-40s%-40s%-40s",country,totalCases,totalDeaths,deathToCaseRation+"%"));
        }
    }
}
