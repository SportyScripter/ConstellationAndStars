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
            createFile(fileName); //tworzy plik jesli nie istnieje
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (!ChecFileIsEmpty(fileName)) { // sprawdza czy plik jest pusty
            listOfStar = (List<Star>) StarSerializer.deserializeStar(fileName); // jesli są w nim jakies dany to je deserializuje i zapisuje do listy
        }
        do {
            showMenu(); // wyswietlenie menu
            switch (key) {
                case "1":
                    Star.StarBuilder starBuilder = new Star.StarBuilder(); // tworzy obiekt budowniczego gwiazdy
                    break;
                case "2":
                    String consentrationName = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj nazwe gwiazdozbioru lub wpisz exit aby wyjsc");
                        consentrationName = scanner.nextLine();
                        if (!consentrationName.equals("exit") && StarFinder.findStarInConstellation(consentrationName)) { // wywołuje metode wyszukującą gwiazdy w podanym gwiazdozbiorze
                            break;
                        }
                    } while (!consentrationName.equals("exit")); // działa dopóki nie wpiszemy exit
                    break;
                case "3":
                    String distance = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj odleglosc w Parsekach (Uwaga! 1 parsek to 3.26 lat świetlnych) lub wpisz exit aby wyjsc");
                        distance = scanner.nextLine();
                        if (!distance.equals("exit") && StarFinder.findStarsBySpecificDistance(distance)) { // wywoałanie metody wyszukującej gwiazdy w podanym zakresie odległości
                            break;
                        }
                    } while (!distance.equals("exit")); // działa dopóki nie wpiszemy exit
                    break;
                case "4":
                    String temperatures = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj temperature w stopniach celcjusza  lub wpisz exit aby wyjsc");
                        temperatures = scanner.nextLine();
                        if (!temperatures.equals("exit") && StarFinder.findStarsBySpecificTemperature(temperatures)) { // wywoałanie metody wyszukującej gwiazdy w podanym zakresie temperatury
                            break;
                        }
                    } while (!temperatures.equals("exit")); // działa dopóki nie wpiszemy exit
                    break;
                case "5":
                    String mminValueAbsolutSetllarMagnitude = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Podaj minimalna wartość absolutnej wielkosci gwiazdowej lub wpisz exit aby wyjsc");
                        mminValueAbsolutSetllarMagnitude = scanner.nextLine();
                        if (!mminValueAbsolutSetllarMagnitude.equals("exit") && StarFinder.findStarBySpecificAbsoluteStellarMagnitude(mminValueAbsolutSetllarMagnitude)) { //wywoałanie metody wyszukującej gwiazdy w podanym zakresie absolutnej wielkości gwiazdowej
                            break;
                        }
                    } while (!mminValueAbsolutSetllarMagnitude.equals("exit")); // działa dopoóki nie wpiszemy exit
                    break;
                case "6":
                    StarFinder.findPotentialSupernova(); // wywoałanie metody wyszukującej potencjalne supernowe
                    break;
                case "7":
                    String hemisphere = null;
                    do {
                        System.out.println(' ');
                        System.out.println("Wpisz PD dla pólkuli poludniowej lub PN dla pólkuli pólnocnej lub wpisz exit aby wyjsc");
                        hemisphere = scanner.nextLine();
                        if (!hemisphere.equals("exit") && StarFinder.findStarsBySpecificHemisphere(hemisphere)) { // wywoałanie metody wyszukującej gwiazdy w podanej półkuli
                            break;
                        }
                    } while (!hemisphere.equals("exit"));// działa pokóki nie wpiszemy exit
                    break;
                case "8":
                    do {
                        try {
                            System.out.println(' ');
                            System.out.println("Podaj litere grecka gwiazdy ktora chcesz usunąć lub wpisz exit aby wyjsc");
                            String greekLetterStar = scanner.nextLine();
                            System.out.println("Podaj nazwe gwiazdozbioru gwiazdy ktora chcesz usunąć lub wpisz exit aby wyjsc");
                            String constellationName = scanner.nextLine();
                            if (!greekLetterStar.equals("exit") || !constellationName.equals("exit")) { // sprawdza czy podane dane nie są exi
                                StarRemover.DeleteStar(greekLetterStar, constellationName); // wywoałanie metody usuwającej gwiazde
                                break;
                            }
                        } catch (IllegalArgumentException e) {
                            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
                        } catch (NoStarsFoundException e) {
                            System.err.println(e.getMessage());
                        }
                    } while (true); // działa dopoki nie wpiszemy exit
            }
        } while (!key.equals("exit")); // działa dopóki nie wpiszemy exit
        StarSerializer.serializerStar(fileName, (Serializable) listOfStar); // po wpisaniu exit serializuje liste do pliku
    }

// meotda wywołująca menu
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

// metoda sprawdzająca czy plik jest pusty i zwraca typ boolean
    public Boolean ChecFileIsEmpty(String path) {
        File file = new File(path);
        if (file.length() == 0) {
            return true;
        } else return false;
    }

// metoda tworząca plik jako parametr przyjmuje nazwe pliku
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
