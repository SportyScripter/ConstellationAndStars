import java.util.Scanner;

public class Star {
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
            star.catalogName = star.getGwiazdozbior().concat(GreekAlphabet.MU.getGreekAlphabet(catalogIndex));
            i = catalogIndex++;
            return this;
        }

    }
}