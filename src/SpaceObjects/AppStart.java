package SpaceObjects;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppStart {
    static String key = null;
    static String fileName = "star.dat";
    static List<Star> listOfStar = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public AppStart() throws IOException, ClassNotFoundException {
        try {
            createFile(fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (!ChecFileIsEmpty(fileName)) {
            listOfStar = (List<Star>) StarSerializer.deserializeStar(fileName);
        }
        do {
            showMenu();
            switch (key) {
                case "1":
                    Star.StarBuilder starBuilder = new Star.StarBuilder();
                    break;
                case "2":
                    String consentrationName = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj nazwe gwiazdozbioru lub wpisz exit aby wyjsc");
                        consentrationName = scanner.nextLine();
                        if (!consentrationName.equals("exit") && StarFinder.findStarInConstellation(consentrationName)) {
                            break;
                        }
                    } while (!consentrationName.equals("exit"));
                    break;
                case "3":
                    String distance = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj odleglosc w Parsekach (Uwaga! 1 parsek to 3.26 lat świetlnych) lub wpisz exit aby wyjsc");
                        distance = scanner.nextLine();
                        if (!distance.equals("exit") && StarFinder.findStarsBySpecificDistance(distance)) {
                            break;
                        }
                    } while (!distance.equals("exit"));
                    break;
                case "4":
                    String temperatures = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj temperature w stopniach celcjusza  lub wpisz exit aby wyjsc");
                        temperatures = scanner.nextLine();
                        if (!temperatures.equals("exit") && StarFinder.findStarsBySpecificTemperature(temperatures)) {
                            break;
                        }
                    } while (!temperatures.equals("exit"));
                    break;
                case "5":
                    String mminValueAbsolutSetllarMagnitude = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj minimalna wartość absolutnej wielkosci gwiazdowej lub wpisz exit aby wyjsc");
                        mminValueAbsolutSetllarMagnitude = scanner.nextLine();
                        if (!mminValueAbsolutSetllarMagnitude.equals("exit") && StarFinder.findStarBySpecificAbsoluteStellarMagnitude(mminValueAbsolutSetllarMagnitude)) {
                            break;
                        }
                    } while (!mminValueAbsolutSetllarMagnitude.equals("exit"));
                    break;
                case "6":
                    StarFinder.findPotentialSupernova();
                    break;
                case "7":
                    String hemisphere = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Wpisz PD dla pólkuli poludniowej lub PN dla pólkuli pólnocnej lub wpisz exit aby wyjsc");
                        hemisphere = scanner.nextLine();
                        if (!hemisphere.equals("exit") && StarFinder.findStarsBySpecificHemisphere(hemisphere)) {
                            break;
                        }
                    } while (!hemisphere.equals("exit"));
                    break;
                case "8":
                    do {
                        try {
                            System.out.println(' ');
                            System.out.println("Podaj litere grecka gwiazdy ktora chcesz usunąć lub wpisz exit aby wyjsc");
                            String greekLetterStar = scanner.nextLine();
                            System.out.println("Podaj nazwe gwiazdozbioru gwiazdy ktora chcesz usunąć lub wpisz exit aby wyjsc");
                            String constellationName = scanner.nextLine();
                            if (!greekLetterStar.equals("exit") || !constellationName.equals("exit")) {
                                StarRemover.DeleteStar(greekLetterStar, constellationName);
                                break;
                            }
                        } catch (IllegalArgumentException e) {
                            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
                        } catch (NoStarsFoundException e) {
                            System.err.println(e.getMessage());
                        }
                    } while (true);
            }
        } while (!key.equals("exit"));
//        listOfStar.clear();
        StarSerializer.serializerStar(fileName, (Serializable) listOfStar);
    }


    public void showMenu() {
        System.out.println("******Menu*****");
        System.out.println("1.Dodaj Gwiazde");
        System.out.println("2.Wyszukaj wszystkie gwiazdy w gwiazdozbiorze");
        System.out.println("3.Wyszukaj gwiazdy w danej odleglosci");
        System.out.println("4.Wyszukaj gwiazdy o temperaturze");
        System.out.println("5.Wyszukaj gwiazdy o wielskosci w zadanym przedziale");
        System.out.println("6.Wyszukaj supernove");
        System.out.println("7.Wyszukaj gwiazdy z polkuli PN/PD");
        System.out.println("8.Usuń gwiazde");
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

    public static void createFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);

        try {
            Files.createFile(filePath);
            System.out.println("Plik " + fileName + " został pomyślnie utworzony.");
        } catch (FileAlreadyExistsException e) {
            throw new IOException("Plik " + fileName + " już istnieje.", e);
        }
    }
}
