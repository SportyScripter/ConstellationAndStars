package SpaceObjects;
import static SpaceObjects.AppStart.scanner;
// klasa odpowiedzialna za wyszukiwanie gwiazd
public class StarFinder {
    // metody są typu boolean, ponieważ zwracają true, jeśli wyszukiwanie się powiodło dzięki czemu w klasie Appstart pętla while zostaje przerwana
    static boolean findStarInConstellation(String constellation) {// wyszukiwanie gwiazdy w podanym gwiazdozbiorze
        try {
            int i = 0;
            for (Star star : AppStart.listOfStar) { // iteruje po wszystkich gwiazdach w liście
                if (star.getConstellation().equals(constellation)) { // porównuje ich nazwy gwiazdozbiorów
                    System.out.println(star); // wypisuje gwiazde na konsole
                    i++; //zlicza wystąpienia gwiazd w podanym gwiazdozbiorze
                }
            }
            if (i == 0) {  // jeśli podany gwiazdozbor nie istnieje to wyrzuca wyjątek
                throw new InvalidParameterException("Nie znaleziono gwiazdy w podanym gwiazdozbiorze");
            }
            return true;
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    static boolean findStarsBySpecificDistance(String parsek) { //wyszukiwanie gwiazd w podanym zakresie odległości
        int i = 0;
        try {
            double convertValue = Double.parseDouble(parsek); // konwertuje podaną wartość na double
            for (Star star : AppStart.listOfStar) { // iteruje po wszystkich gwiazdach w liście
                if (star.getdistance() <= convertValue * 3.26) { // porownuje odleglosc gwiazdy z podanym zakresem odleglosci pomnożonym o 3.26 aby uzyskac lata świetlne
                    System.out.println(star);
                    i++;
                }
            }
            if (i == 0) {
                throw new InvalidParameterException("Nie znaleziono gwiazdy w podanym zakresie odległości");
            }
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    static boolean findStarsBySpecificTemperature(String temperature) { // wyszuukiwanie gwiazd w podanym zakresie temperatury
        int i = 0;
        try {
            Double convertNumber = Double.parseDouble(temperature); // konwertuje podaną wartość na double
            for (Star star : AppStart.listOfStar) {
                if (star.gettemperatures() <= convertNumber) { // porownuje temperature gwiazdy z podanym zakresem temperatury
                    System.out.println(star); // jesli gwiazda miesci sie w podanym zakresie wypisuje ja na konsole
                    i++; // zlicza wystapienia gwiazd w podanym zakresie
                }
            }
            if (i == 0) { // jesli nie znaleziono zadnej gwiazdy w podanym zakresie wyrzuca wyjatek i zwraca wartość false aby pętla while w klasie AppStart nie została przerwana, i uzytkownik mogl ponownie podac dane i wyszukac gwiazdy
                throw new NoStarsFoundException("Podana wartosc jest nieprawidlowa, Podana wartosc musi być wieksza niż 2000°C");
            }
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
        } catch (IllegalArgumentException e) {
            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
        } catch (NoStarsFoundException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    static boolean findStarBySpecificAbsoluteStellarMagnitude(String min) {// wyszukiwanie gwiazd w podanym zakresie absolutnej wielkosci gwiazdowej

        int i = 0;  //zlicza ilosc wyszukanych gwiazd z podanego zakresu
        try {
            System.out.println("Podaj maksymalna wartosc absolutnej wielkosci gwiazdowej");
            String max = scanner.nextLine();
            Double convertNumber = Double.parseDouble(min);
            Double convertNumber2 = Double.parseDouble(max);
            for (Star star : AppStart.listOfStar) {
                if (star.getabsoluteStellarMagnitude() <= convertNumber2 && star.getabsoluteStellarMagnitude() >= convertNumber) // znajduje wszystkie gwiazdy z podanego zakresu
                {
                    System.out.println(star); //wyswietla gwiazde
                    i++;
                }
            }
            if (i == 0) // jesli nie znaleziono zadnej gwiazdy z podanego zakresu
            {
                throw new NoStarsFoundException("Nie znaleziono gwiazdy w podanym zakresie absolutnej wielkosci gwiazdowej");
            }
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
        } catch (NoStarsFoundException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Nieprawidłowe dane. Wprowadź poprawne dane.");
        }
        return false;
    }

    static void findPotentialSupernova() { // znajduje potencjalne supernowe
        try {
            int i = 0;
            for (Star star : AppStart.listOfStar) {
                if (star.getmass() >= 1.44) { // sprawdza czy masa gwiazdy jest wieksza lub rowna 1.44 masy słońca
                    System.out.println(star);
                    i++;
                }
            }
            if (i == 0) {
                throw new NoStarsFoundException("Nie znaleziono potencjalnych supernowych");
            }
        } catch (NoStarsFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    static boolean findStarsBySpecificHemisphere(String hemisphere) {//wyszukuje wszystkie gwiazdy w podanej półkuli
        int i = 0;
        try {
            if (hemisphere.equals("PN") || hemisphere.equals("PD")) {
                for (Star star : AppStart.listOfStar) {
                    if (star.getHemisphere().equals(hemisphere)) {
                        System.out.println(star);
                        i++;
                    }
                }
                if (i == 0) {
                    throw new NoStarsFoundException("Nie znaleziono gwiazdy w podanej półkuli");
                }
                return true;
            } else {
                throw new InvalidParameterException("Nieprawidłowe dane. Wprowadź poprawne dane.");
            }
        } catch (NoStarsFoundException | InvalidParameterException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
