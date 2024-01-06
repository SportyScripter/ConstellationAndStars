package SpaceObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.ScatteringByteChannel;
import java.text.Format;

import static SpaceObjects.AppStart.scanner;

public class StarFinder {
    static boolean findStarInConstellation(String constellation) {
        try {
            int i = 0;
            for (Star star : AppStart.listOfStar) {
                if (star.getConstellation().equals(constellation)) {
                    System.out.println(star);
                    i++;
                }
            }
            if (i == 0) {
                throw new InvalidParameterException("No stars found in the specifield constellation");
            }
            return true;
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    static boolean findStarsBySpecificDistance(String parsek) {
        int i = 0;
        try {
            double convertValue = Double.parseDouble(parsek);
            for (Star star : AppStart.listOfStar) {
                if (star.getdistance() <= convertValue * 3.26) {
                    System.out.println(star);
                    i++;
                }
            }
            if (i == 0) {
                throw new InvalidParameterException("No stars found in the specifield Distance");
            }
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid double value.");
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    static boolean findStarsBySpecificTemperature(String temperature) {
        int i = 0;
        try {
            Double convertNumber = Double.parseDouble(temperature);
            for (Star star : AppStart.listOfStar) {
                if (star.gettemperatures() <= convertNumber) {
                    System.out.println(star);
                    i++;
                }
            }
            if (i == 0) {
                throw new NoStarsFoundException("Invalid input. Please enter a valid value greater then 2000°C");
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input. Please enter a valid double value.");
        } catch (NoStarsFoundException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    static boolean findStarBySpecificAbsoluteStellarMagnitude(String min) {

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
                throw new NoStarsFoundException("No stars found in the specifield Absolute Stellar Magnitude");
            }
            return true;
        } catch (NoStarsFoundException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input. Please enter a valid double value.");
        }
        return false;
    }

    static void findPotentialSupernova() {
        try {

            int i = 0;
            for (Star star : AppStart.listOfStar) {
                if (star.getmass() >= 1.44) {
                    System.out.println(star);
                    i++;
                }
            }
            if (i == 0) {
                throw new NoStarsFoundException("Potential supernova don't exist in the catalog");
            }
        } catch (NoStarsFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    static boolean findStarsBySpecificHemisphere(String hemisphere) {
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
                    throw new NoStarsFoundException("No stars found in the specifield Hemisphere");
                }
                return true;
            } else {
                throw new InvalidParameterException("Invalid input. Please enter a valid value");
            }
        } catch (NoStarsFoundException | InvalidParameterException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
