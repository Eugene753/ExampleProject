package CovidCase;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayDashboard {

    public static void displayDashboard() throws IOException {
        int num=0;
        String path = "C:\\Users\\imark\\IdeaProjects\\ExampleProject\\covidcases\\full_data.xlsx";
        PandemicDashboard.fillCovidCaseList(path);
        do {
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Dashboard for reporting Covid Cases");
                    System.out.println("-----------------------------------");
                    System.out.println("Select one of the following options:");
                    System.out.println("1.Report Covid Cases by Countries." +
                            "\n2.Report Covid Cases by Total Deaths(decreasing order)" +
                            "\n3.Report Covid Cases by Total Cases(decreasing order)" +
                            "\n4.Report Countries with minimum and maximum number of Total Deaths" +
                            "\n5.Report Countries with minimum and maximum number of Total Cases" +
                            "\n6.Search for a Country and report their Mortality Rate (death-to-case %)" +
                            "\n7.Report top 10 countries by their Mortality Rate(decreasing order)" +
                            "\n8.Exit from the program !");
                    num = scanner.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Wrong Input");
                }
            switch (num){
                case 1:
                    PandemicDashboard.displayCovidCases(PandemicDashboard.caseList);
                    break;
                case 2:
                    PandemicDashboard.decreasingOrderListByTotalDeaths(PandemicDashboard.caseList);
                    break;
                case 3:
                    PandemicDashboard.deacreasingOrderListByTotalCases(PandemicDashboard.caseList);
                    break;
                case 4:
                    PandemicDashboard.minAndMaxNumberOfTotalDeaths(PandemicDashboard.caseList);
                    break;
                case 5:
                    PandemicDashboard.minAndMaxNumberOfTotalCases(PandemicDashboard.caseList);
                    break;
                case 6:
                    PandemicDashboard.searchByMortalityRate(PandemicDashboard.caseList);
                    break;
                case 7:
                    PandemicDashboard.MortalityRateDecreasingOrder(PandemicDashboard.caseList);
                    break;
                case 8:
                    System.out.println("Good Bye");
                    break;
                default:
                    System.out.println("Try again");
            }
        }while(num!=8);
    }
}
