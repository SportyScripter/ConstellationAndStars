package SpaceObjects;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import static java.lang.System.*;

public class Star implements Serializable {
    @Serial
    private static final long serialVersionUID = -897856973823710492L;
    private String name; //Done
    private String hemisphere; //Done
    private String constellation; //Done
    private String catalogName; //Done
    private Declination declination; //Done
    private RightAscension rightAscension; //Done
    private Double observedStellarMagnitude; //Done
    private Double absoluteStellarMagnitude; //Done
    private Double distance; //Done
    private Double temperatures; //Done
    private Double mass; //Done
    private int catalogIndex;
    static Scanner scanner = new Scanner(System.in);

    private Star() {
    }

    private Star(String name, String hemisphere, String constellation, Declination declination, RightAscension rightAscension, Double observedStellarMagnitude, Double absoluteStellarMagnitude, Double distance, Double temperatures, Double mass) {
        this.name = name;
        this.hemisphere = hemisphere;
        this.constellation = constellation;
        this.declination = declination;
        this.rightAscension = rightAscension;
        this.observedStellarMagnitude = observedStellarMagnitude;
        this.absoluteStellarMagnitude = absoluteStellarMagnitude;
        this.distance = distance;
        this.temperatures = temperatures;
        this.mass = mass;
    }

    public int getCatalogIndex() {
        return catalogIndex;
    }

    public void setCatalogIndex(int catalogIndex) {
        this.catalogIndex = catalogIndex;
    }

    public Double getObservedStellarMagnitude() {
        return observedStellarMagnitude;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public String getConstellation() {
        return constellation;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Double getabsoluteStellarMagnitude() {
        return absoluteStellarMagnitude;
    }

    public Double getdistance() {
        return distance;
    }

    public Double gettemperatures() {
        return temperatures;
    }

    public Double getmass() {
        return mass;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return "Star name: " + this.name +
                "\nStar hemisphere: " + this.hemisphere +
                "\nStar constellation: " + this.constellation +
                "\nStar catalog name: " + this.catalogName +
                "\nStar Declinatiom: " + this.declination.getXx() + "°" + this.declination.getYy() + "'" + decimalFormat.format(this.declination.getZz()) +
                "\nStar Right Ascension: " + this.rightAscension.getXx() + "°" + this.rightAscension.getYy() + "'" + decimalFormat.format(this.rightAscension.getZz()) +
                "\nStar Distance: " + this.distance + " [Light years]" +
                "\nStar Observed stellar magnitude: " + this.observedStellarMagnitude + " [jednostek magnitudo] " +
                "\nStar Absolute stellar magnitude: " + this.absoluteStellarMagnitude + " [wartość magnitudo]" +
                "\nStar Temperature: " + this.temperatures + "°C" +
                "\nStar Mass: " + this.mass + "\n";
    }

    static class StarBuilder implements Serializable {
        private static final long serialVersionUID = -9223365651070458532L;
        private Star star;

        public StarBuilder() {
            build();
        }

        public void build() {
            try {
                String name = setName();
                String hemisphere = setHemisphere();
                String constellation = setConstellation();
                Declination declination1 = SetDeclination(hemisphere);
                RightAscension rightAscension1 = SetRightAscenios();
                Double observedStellarManitude = SetObservedStellarMagnitude();
                Double distance = SetDistnce();
                SetAbsoluteStellarMagnitude(observedStellarManitude, distance);
                Double temperatures = SetTemperatures();
                Double mass = SetMass();
                star = new Star(name, hemisphere, constellation, declination1, rightAscension1, observedStellarManitude, SetAbsoluteStellarMagnitude(observedStellarManitude, distance), distance, temperatures, mass);
                setCatalogName();
                AppStart.listOfStar.add(this.star);
                out.println("Twoja gwiazda została utworzona pomyślnie");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("Nie podano wszystkich danych");
            } catch (IOException e) {
                System.err.println("Błąd wejścia/wyjścia");
            } catch (ClassNotFoundException e) {
                System.err.println("Nie znaleziono klasy");
            } catch (Exception e) {
                System.err.println("Wystąpił nieznany błąd");
            }
        }

        public String setName() {
            do {
                String name = " ";
                try {
                    out.println("Podaj nazwe gwiazdy skladajaca sie z 3 duzych liter i 4 cyfr");
                    name = scanner.nextLine();
                    if (name.matches("[A-Z]{3}[0-9]{4}")) {
                        return name;
                    } else {
                        throw new IllegalArgumentException("Nazwa Gwiazdy jest nieprawidłowa, podaj prawidłową nazwę");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            } while (true);
        }

        public String setHemisphere() {
            do {
                try {
                    String hemisphere = null;
                    System.out.println("Wpisz PD dla pólkuli poludniowej lub PN dla pólkuli pólnocnej");
                    hemisphere = scanner.nextLine();
                    if (hemisphere.equals("PN") || hemisphere.equals("PD")) {
                        return hemisphere;
                    } else {
                        throw new IllegalArgumentException("Wrong star position. Try again: ");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            } while (true);
        }

        public String setConstellation() {
            String constellation = null;
            do {
                try {
                    System.out.println("Podaj nazwe gwiazdozbioru");
                    constellation = scanner.nextLine();
                    if (constellation.matches("[a-zA-Z]+")) {
                        return constellation;
                    } else {
                        throw new IllegalArgumentException("Podana nazwa gwiazdozbioru jest nieprawidłowa");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            } while (true);
        }

        public void setCatalogName() throws IOException, ClassNotFoundException {
            int i = 0;
            if (!AppStart.listOfStar.isEmpty()) { // jesli lista nie jest pusta
                for (Star deserializerStar : AppStart.listOfStar) {
                    if (deserializerStar.constellation.equals(star.getConstellation())) { //wyszukanie wszystkich gwiazd w danym gwiazdozbiorze
                        i++;
                    }
                }
                if (star.getObservedStellarMagnitude() < findTheBrightestStar()) {
                    star.catalogName = GreekAlphabet.ALPHA.getGreekAlphabet(0).concat(" ").concat(star.constellation);
                    star.setCatalogIndex(0); // ustawienie najjasniejszej gwiazdy na index 0
                    for (Star deserializerStar : AppStart.listOfStar) {
                        if (deserializerStar.constellation.equals(star.getConstellation())) {
                            deserializerStar.catalogName = GreekAlphabet.ALPHA.getGreekAlphabet(deserializerStar.getCatalogIndex() + 1).concat(" ").concat(deserializerStar.constellation);
                            deserializerStar.setCatalogIndex(deserializerStar.getCatalogIndex() + 1);  // zmiana indeksu katalogu na +1
                        }
                    }
                } else {
                    star.catalogName = GreekAlphabet.ALPHA.getGreekAlphabet(i).concat(" ").concat(star.constellation); // jeśli gwiazda nie jest najjaśniejsza ustaw katalog indeks jako ostatni
                    star.setCatalogIndex(i);
                }
            } else { // jesli lista jest pusta
                star.catalogName = GreekAlphabet.ALPHA.getGreekAlphabet(i).concat(" ").concat(star.constellation);
                star.setCatalogIndex(i); // ustaw najjaśniejszą gwiazdę na index 0
            }
        }

        private double findTheBrightestStar() {
            double tempBrightesValue = 15.00;
            for (Star brightesStar : AppStart.listOfStar) {
                if (brightesStar.constellation.equals(star.getConstellation())) {
                    if (tempBrightesValue > brightesStar.observedStellarMagnitude)
                        tempBrightesValue = brightesStar.observedStellarMagnitude;
                }
            }
            return tempBrightesValue;
        }

        public Declination SetDeclination(String hemisphere) {
            do {
                try {
                    String declinationString = null;
                    out.println("Podaj deklinacje w formacie [XX,YY,ZZ.zz]\n XX - stopnie [0-90], YY - minuty [0-60], ZZ - sekundy[0.00-60.00]");
                    declinationString = scanner.nextLine();
                    String[] declinationArray = declinationString.split(",");
                    Declination declination = new Declination(Integer.parseInt(declinationArray[0]), Integer.parseInt(declinationArray[1]), Double.parseDouble(declinationArray[2]));
                    if (hemisphere.equals("PN")) {
                        if (declination.getXx() < 0 || declination.getXx() > 90) {
                            throw new IllegalArgumentException("Deklinacja musi być z przedziału 0-90");
                        } else if (declination.getYy() > 60 || declination.getYy() < 0) {
                            throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                        } else if (declination.getZz() > 60 || declination.getZz() < 0) {
                            throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                        } else if (declination.getXx() < 90 || declination.getXx() > 0) {
                            return declination;
                        }
                    } else if (hemisphere.equals("PD")) {
                        if (declination.getXx() > 0 || declination.getXx() < -90) {
                            throw new IllegalArgumentException("Deklinacja musi być z przedziału -90-0");
                        } else if (declination.getYy() > 60 || declination.getYy() < 0) {
                            throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                        } else if (declination.getZz() > 60 || declination.getZz() < 0) {
                            throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                        } else if (declination.getXx() < 0 || declination.getXx() > -90) {
                            return declination;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Podane dane są nieprawidłowe");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Wystąpił nieznany błąd, sprawdz czy podałeś poprawną wartość");
                }

            } while (true);
        }

        public RightAscension SetRightAscenios() {
            do {
                try {
                    String rightAscensionString = null;
                    out.println("Podaj rektascensje w formacie [XX,YY,ZZ]\n godziny [0-24], YY - minuty [0-60], ZZ - sekundy[0-60]");
                    rightAscensionString = scanner.nextLine();
                    String[] rightAscensionArray = rightAscensionString.split(",");
                    RightAscension rightAscension = new RightAscension(Integer.parseInt(rightAscensionArray[0]), Integer.parseInt(rightAscensionArray[1]), Integer.parseInt(rightAscensionArray[2]));
                    if (rightAscension.getXx() > 24 || rightAscension.getXx() < 0) {
                        throw new IllegalArgumentException("Rekatascensja musi byc z przedziału 00-24");
                    } else if (rightAscension.getYy() > 60 || rightAscension.getYy() < 0) {
                        throw new IllegalArgumentException("Minuty musza byc z przedziału 0-60");
                    } else if (rightAscension.getZz() > 60 || rightAscension.getZz() < 0) {
                        throw new IllegalArgumentException("Sekundy musza byc z przedziału 0-60");
                    } else if (rightAscension.getXx() > 0 && rightAscension.getXx() < 24) {
                        return rightAscension;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Podane dane są nieprawidłowe");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Wystąpił nieznany błąd, sprawdz czy podałeś poprawną wartość");
                }
            } while (true);
        }

        public Double SetObservedStellarMagnitude() {
            String stellarMagnitudeString = null;
            do {
                try {
                    out.println("Podaj obserwowana wielkosc gwiazdy w zakresie [-26.74 - 15.00]");
                    stellarMagnitudeString = scanner.nextLine();
                    Double stellarMagnitude = Double.parseDouble(stellarMagnitudeString);
                    if (stellarMagnitude >= -26.74 && stellarMagnitude <= 15.00) {
                        return stellarMagnitude;
                    } else {
                        throw new IllegalArgumentException("Podana obserwowana wielkość gwiazdy jest poza zakresem");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Podane dane są nieprawidłowe");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Wystąpił nieznany błąd, sprawdz czy podałeś poprawną wartość");
                }
            } while (true);
        }

        public Double SetDistnce() { // ustala odleglosc w latach swietlnych
            String distanceString = null;
            do {
                try {
                    out.println("Podaj odleglosc w latach swietlnych");
                    distanceString = scanner.nextLine();
                    Double distance = Double.parseDouble(distanceString);
                    if (distance < 0) {
                        throw new IllegalArgumentException("Podana wartość nie może być ujemna");
                    } else {
                        return distance;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Podane dane są nieprawidłowe");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Wystąpił nieznany błąd, sprawdz czy podałeś poprawną wartość");
                }
            } while (true);
        }

        public Double SetAbsoluteStellarMagnitude(Double observedStellarManitude, Double distance) { // oblicza absolutna wielkosc gwiazdy
            return observedStellarManitude - (5 * Math.log10(distance / 3.26)) + 5;
        }

        public Double SetTemperatures() {
            String temperaturesString = null;
            do {
                try {
                    out.println("Podaj temperature gwiazdy w stopniach celcjusza (Temperatura musi byc wieksza niz 2000°C)");
                    temperaturesString = scanner.nextLine();
                    Double temperatures = Double.parseDouble(temperaturesString);
                    if (temperatures <= 2000) {
                        throw new IllegalArgumentException("Podana temperatura jest za niska!");
                    } else {
                        return temperatures;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Podane dane są nieprawidłowe");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Wystąpił nieznany błąd, sprawdz czy podałeś poprawną wartość");
                }
            } while (true);
        }

        public Double SetMass() {
            String massString = null;
            do {
                try {
                    out.println("Podaj mase gwiazdy w odnieisienu do masy słońca w zakresie [0.1-50.0]");
                    massString = scanner.nextLine();
                    Double mass = Double.parseDouble(massString);
                    if (mass > 50 && mass < 0.1) {
                        throw new IllegalArgumentException("Podana mass jest nieprawidłowa, prosze podać mase w zakresie [0.1-50.0]");
                    } else {
                        return mass;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Podane dane są nieprawidłowe");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("Wystąpił nieznany błąd, sprawdz czy podałeś poprawną wartość");
                }
            } while (true);
        }
    }
}