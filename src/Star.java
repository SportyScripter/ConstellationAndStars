import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Star implements Serializable {
    private static final long serialVersionUID = 6337669437643697441L;
    private String name;
    private String polkola;
    private String gwiazdozbior;
    private String catalogName;
    private Double deklinacja;
    private Double rektascensja;
    private Double obserwowanaWielkoscGwiazdowa;
    private Double absolutnaWielkoscGwiazdowa;
    private Double odleglosc;
    private Double temperatura;
    private Double masa;

    private Star() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolkola() {
        return polkola;
    }

    public void setPolkola(String polkola) {
        this.polkola = polkola;
    }

    public String getGwiazdozbior() {
        return gwiazdozbior;
    }

    public void setGwiazdozbior(String gwiazdozbior) {
        this.gwiazdozbior = gwiazdozbior;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Double getDeklinacja() {
        return deklinacja;
    }

    public void setDeklinacja(Double deklinacja) {
        this.deklinacja = deklinacja;
    }

    public Double getRektascensja() {
        return rektascensja;
    }

    public void setRektascensja(Double rektascensja) {
        this.rektascensja = rektascensja;
    }

    public Double getObserwowanaWielkoscGwiazdowa() {
        return obserwowanaWielkoscGwiazdowa;
    }

    public void setObserwowanaWielkoscGwiazdowa(Double obserwowanaWielkoscGwiazdowa) {
        this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
    }

    public Double getAbsolutnaWielkoscGwiazdowa() {
        return absolutnaWielkoscGwiazdowa;
    }

    public void setAbsolutnaWielkoscGwiazdowa(Double absolutnaWielkoscGwiazdowa) {
        this.absolutnaWielkoscGwiazdowa = absolutnaWielkoscGwiazdowa;
    }

    public Double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(Double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getMasa() {
        return masa;
    }

    public void setMasa(Double masa) {
        this.masa = masa;
    }


    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattednumber = decimalFormat.format(this.deklinacja);
        return "Star name:" + this.name +
                "\n Star position:" + this.polkola +
                "\n Star constellation:" + this.gwiazdozbior +
                "\n Star catalog name:" + this.catalogName +
                 "\nStar Declinatiom:" + formattednumber;
    }


    public static class StarBuilder {

        private Star star;
        private static int catalogIndex, i;

        int getI() {
            return i;
        }

        public StarBuilder() {
            star = new Star();
            setName();
            setPolkola();
            setConstellation();
            setCatalogName();
//            setDeklinacja();
            setDeklinationByString();
            showStar();
        }

        public Star build() {
            return star;
        }

        public StarBuilder showStar() {
            System.out.println(
                    "Star name: " + star.name +
                            "\n Star position: " + star.polkola +
                            "\n Star constellation: " + star.gwiazdozbior +
                            "\n Star catalog name: " + star.catalogName);
            System.out.printf("Star Declinatiom: " + "%,.2f", star.deklinacja);
            return this;
        }

        public StarBuilder setName() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter star name: ");
            String NameToCheck = scanner.nextLine();
            while (!NameToCheck.matches("[A-Z]{3}[0-9]{4}")) {
                System.out.println("Wrong star name. Try again: ");
                NameToCheck = scanner.nextLine();
            }
            System.out.println("Stars.Star name is correct.");
            star.name = NameToCheck;
            return this;
        }

        public StarBuilder setPolkola() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter star position PN: North and PD: South: ");
            String starPosition = scanner.nextLine();
            if (starPosition.equals("PN") || starPosition.equals("PD")) {
                star.polkola = starPosition;
            } else {
                System.out.println("Wrong star position. Try again: ");
                setPolkola();
            }
            return this;
        }

        public StarBuilder setConstellation() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter constellation name: ");
            String tempConstellationName = scanner.nextLine();
            star.gwiazdozbior = tempConstellationName;
            return this;
        }

        public StarBuilder setCatalogName() {
            star.catalogName = GreekAlphabet.MU.getGreekAlphabet(catalogIndex).concat(" ").concat(star.getGwiazdozbior());
            i = catalogIndex++;
            return this;
        }

        //Do Ustalenie którą metode zostawiamy, tutaj kazdą współrzędną uzytkownik podaje z palca
        public StarBuilder setDeklinacja() {
            int stopnie;
            int minuty;
            double sekundy;
            System.out.println("Proszę podać współżedne gwiazdy do obliczenia Deklinacji: ");
            Scanner scanner = new Scanner(System.in);
            try {
                do {
                    System.out.println("Podaj stopnie");
                    stopnie = scanner.nextInt();
                    if (stopnie > -90 && stopnie < 90) {
                        System.out.println("Dziekuje!");
                    } else {
                        System.out.println("Podana watość jest nieprawidłowa! \nProsze podać prawidłową wartość od -90 do 90");
                    }
                } while (stopnie < -90 || stopnie > 90);
                do {
                    System.out.println("Podaj minuty");
                    minuty = scanner.nextInt();
                    if (minuty > 0 && minuty < 60) {
                        System.out.println("Dziekuje!");
                    } else {
                        System.out.println("Podana watość jest nieprawidłowa! \nProsze podać prawidłową wartość od 0 do 60");
                    }
                } while (minuty < 0 || minuty > 60);
                do {
                    System.out.println("Podaj sekundy");
                    sekundy = scanner.nextDouble();
                    if (sekundy > 0 && sekundy < 60) {
                        System.out.println("Dziekuje!");
                    } else {
                        System.out.println("Podana watość jest nieprawidłowa! \nProsze podać prawidłową wartość od 0 do 60");
                    }
                } while (sekundy < 0 || sekundy > 60);
                double deklinacja = stopnie + (minuty / 60) + (sekundy / 3600);
                if (star.getPolkola().equals("PN"))
                    star.deklinacja = deklinacja;
                else if (star.getPolkola().equals("PD"))
                    star.deklinacja = -deklinacja;
            } catch (Exception e) {
                System.out.println("Podana wartość współrzędnych jest błędna!");
            }
            return this;
        }

        //A w tej niżej przyjmujesz od uzytkownika string i na nim działasz
        public StarBuilder setDeklinationByString() {
            Scanner scanner = new Scanner(System.in);
            int stopnie = -100;
            int minuty = -100;
            double sekundy = -100;
            do {
                System.out.println("Prosze podać współrzedne: [xx,yy,zz.zz] ");
                String userInput = scanner.nextLine();
                String[] coordinate = userInput.split(",");
                try {
                    stopnie = Integer.valueOf(coordinate[0]);
                    minuty = Integer.valueOf(coordinate[1]);
                    sekundy = Double.valueOf(coordinate[2]);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Dane zostały podane nieprawidłowo, prosze spróbowac ponownie!");
                }
            }
            while (stopnie < -90 || stopnie > 90 || minuty < 0 || minuty > 60 || sekundy < 0 || sekundy > 60);
            double deklinacja = stopnie + (minuty / 60) + (sekundy / 3600);
            if (star.getPolkola().equals("PN"))
                star.deklinacja = deklinacja;
            else if (star.getPolkola().equals("PD"))
                star.deklinacja = -deklinacja;
            return this;
        }

    }
}