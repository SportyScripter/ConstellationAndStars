package SpaceObjects;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AppStart {
    static String key = null;
    static String fileName = "star.dat";
    static List<Star> listOfStar = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public AppStart() throws IOException, ClassNotFoundException {
        addStarsToChecActionProgram();
        showCatalgoName();
        if (!ChecFileIsEmpty(fileName)) {
            listOfStar = (List<Star>) StarSerializer.deserializeStar(fileName);
        }
        do {
            showMenu();
            switch (key)
            {
                case "1":
                    System.out.println("jestem w case 1");
                    break;
                case "2":
                    String consentrationName = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj nazwe gwiazdozbioru lub wpisz exit aby wyjsc");
                        consentrationName = scanner.nextLine();
                    if(!consentrationName.equals("exit") && StarFinder.findStarInConstellation(consentrationName))
                    {
                        break;
                    }
                    } while (!consentrationName.equals("exit"));
                    break;
                case "3":
                    System.out.println("jestem w case 3");
                    String distance = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj odleglosc w Parsekach (Uwaga! 1 parsek to 3.26 lat świetlnych) lub wpisz exit aby wyjsc");
                        distance = scanner.nextLine();
                        if(!distance.equals("exit") && StarFinder.findStarsBySpecificDistance(distance))
                        {
                            break;
                        }
                    } while (!distance.equals("exit"));
                    break;
                case "4":
                    System.out.println("jestem w case 4");
                    String temperatures = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj temperature w stopniach celcjusza  lub wpisz exit aby wyjsc");
                        temperatures = scanner.nextLine();
                        if(!temperatures.equals("exit") && StarFinder.findStarsBySpecificTemperature(temperatures))
                        {
                            break;
                        }
                    } while (!temperatures.equals("exit"));
                    break;
                case "5":
                    System.out.println("jestem w case 5");
                    String mminValueAbsolutSetllarMagnitude = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj minimalna wartość absolutnej wielkosci gwiazdowej lub wpisz exit aby wyjsc");
                        mminValueAbsolutSetllarMagnitude = scanner.nextLine();
                        if(!mminValueAbsolutSetllarMagnitude.equals("exit") && StarFinder.findStarBySpecificAbsoluteStellarMagnitude(mminValueAbsolutSetllarMagnitude))
                        {
                            break;
                        }
                    } while (!mminValueAbsolutSetllarMagnitude.equals("exit"));
                    break;
                case "6":
                    System.out.println("jestem w case 6");
                    StarFinder.findPotentialSupernova();
                    break;
                case "7":
                    System.out.println("jestem w case 7");
                    String hemisphere = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Wpisz PD dla pólkuli poludniowej lub PN dla pólkuli pólnocnej lub wpisz exit aby wyjsc");
                        hemisphere = scanner.nextLine();
                        if(!hemisphere.equals("exit") && StarFinder.findStarsBySpecificHemisphere(hemisphere))
                        {
                            break;
                        }
                    } while (!hemisphere.equals("exit"));
                    break;
                case "8":
                    System.out.println("jestem w case 8");
                    break;
            }
        } while (!key.equals("exit"));
        listOfStar.clear();
        StarSerializer.serializerStar(fileName, (Serializable) listOfStar);
    }



    public void showMenu() {
        System.out.println("******Menu*****");
        System.out.println("1.Dodaj Gwiazde"); //Done
        System.out.println("2.Wyszukaj wszystkie gwiazdy w gwiazdozbiorze"); //Done
        System.out.println("3.Wyszukaj gwiazdy w danej odleglosci"); //Done
        System.out.println("4.Wyszukaj gwiazdy o temperaturze"); //Done
        System.out.println("5.Wyszukaj gwiazdy o wielskosci w zadanym przedziale"); // Done
        System.out.println("6.Wyszukaj supernove"); //Done
        System.out.println("7.Wyszukaj gwiazdy z polkuli PN/PD"); //Done
        System.out.println("8.Usuń gwiazde"); // Done
        System.out.println("Wpisz exit aby wyjść z programu");
        System.out.println("Wpisz numer aby wybrać opcje");
        System.out.println(' ');
        key = scanner.nextLine();
    }

    public Boolean ChecFileIsEmpty(String path) {
        File file = new File(path);
        if (file.length() == 0) {
            return true;
        } else return false;
    }
    public void showCatalgoName() throws IOException, ClassNotFoundException {
        for (Star star : AppStart.listOfStar) {
            System.out.println("Star CatalogName:" +star.getCatalogName() + " Star Brightest:"+ star.getObservedStellarMagnitude());
        }
    }
//    public void showAbsoluteStellarMagnitude() throws IOException, ClassNotFoundException {
//        ObjectInputStream newInput = new ObjectInputStream(new FileInputStream(fileName));
//        for (Star star : AppStart.listOfStar) {
//            System.out.println("Star CatalogName:" +star.getCatalogName() + " Star Absolute Stellar Magnitude:"+ star.getabsoluteStellarMagnitude());
//
//        }
//    }
    public void addStarsToChecActionProgram() throws IOException, ClassNotFoundException {
        new Star.StarBuilder("DCH7821", "PN", "Wolazer", new Declination(75, 15, 45.44), new RightAscension(5, 10, 15), -5, 978, 2700, 46).build(); //Gamma
        new Star.StarBuilder("POR8643", "PN", "Wolazer", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), -1, 1326, 3500, 30).build(); // Beta
        new Star.StarBuilder("JUO3241", "PD", "Wolazer", new Declination(-60, 32, 15.17), new RightAscension(20, 50, 22), -6, 2652, 3450, 15).build(); // Alpha
        new Star.StarBuilder("HAB9999", "PD", "Wolazer", new Declination(-60, 32, 15.17), new RightAscension(20, 50, 22), 11, 3652, 3350, 15).build(); // Delta
        new Star.StarBuilder("COP0764", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 600, 2800, 30).build(); //Gamma
        new Star.StarBuilder("LOK7654", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), -8, 1500, 2900, 30).build(); //Delta
        new Star.StarBuilder("XYZ0000", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), -10, 2000, 3000, 30).build(); //Beta
        new Star.StarBuilder("POL6666", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), -2, 2200, 3100, 30).build(); //Alpha
        new Star.StarBuilder("VCX8790", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), -12, 2500, 3200, 30).build();// Epsilon
    }
}
