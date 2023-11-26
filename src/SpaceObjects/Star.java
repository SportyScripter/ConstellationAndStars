package SpaceObjects;

import org.w3c.dom.css.ElementCSSInlineStyle;

import java.util.List;
import javax.xml.xpath.XPath;
import java.io.*;
import java.sql.ClientInfoStatus;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.load;


public class Star implements Serializable {
    @Serial
    private static final long serialVersionUID = -897856973823710492L;
    private String name; //Done
    private String hemisphere; //Done
    private String constellation; //Done
    private String catalogName;
    private Declination declination; //Done
    private RightAscension rightAscension; //Done
    private Double observedStellarMagnitude; //Done
    private Double absoluteStellarMagnitude; //Done
    private Double distance; //Done
    private Double temperatures; //Done
    private Double mass; //Done
    private int catalogIndex;

    private Star() {
    }

    public Double getObservedStellarMagnitude() {
        return observedStellarMagnitude;
    }

    public void setObservedStellarMagnitude(Double observedStellarMagnitude) {
        this.observedStellarMagnitude = observedStellarMagnitude;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }

    public void setRightAscension(RightAscension rightAscension) {
        this.rightAscension = rightAscension;
    }

    public Declination getDeclination() {
        return declination;
    }

    public void setDeclination(Declination declination) {
        this.declination = declination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
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

    public void setabsoluteStellarMagnitude(Double absoluteStellarMagnitude) {
        this.absoluteStellarMagnitude = absoluteStellarMagnitude;
    }

    public Double getdistance() {
        return distance;
    }

    public void setdistance(Double distance) {
        this.distance = distance;
    }

    public Double gettemperatures() {
        return temperatures;
    }

    public void settemperatures(Double temperatures) {
        this.temperatures = temperatures;
    }

    public Double getmass() {
        return mass;
    }

    public void setmass(Double mass) {
        this.mass = mass;
    }


    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return "Star name: " + this.name +
                "\nStar position: " + this.hemisphere +
                "\nStar constellation: " + this.constellation +
                "\nStar catalog name: " + this.catalogName +
                "\nStar Declinatiom: " + this.declination.getXx() + "°" + this.declination.getYy() + "'" + decimalFormat.format(this.declination.getZz()) +
                "\nStar Right Ascension: " + this.rightAscension.getXx() + "°" + this.rightAscension.getYy() + "'" + decimalFormat.format(this.rightAscension.getZz()) +
                "\nStar Distance: " + this.distance + "Light years" +
                "\nStar Observed stellar magnitude: " + this.observedStellarMagnitude + " [jednostek magnitudo] " +
                "\nStar Absolute stellar magnitude: " + this.absoluteStellarMagnitude + " [wartość magnitudo]" +
                "\nStar Temperature: " + this.temperatures + "°" +
                "\nStar Mass: " + this.mass + "\n";
    }

    static class StarBuilder implements Serializable {
        private static final long serialVersionUID = -9223365651070458532L;
        private final Star star;
        private static int catalogIndex, i;

        public static int getCatalogIndex() {
            return catalogIndex;
        }

        private static void setCatalogIndex(int catalogIndex) {
            StarBuilder.catalogIndex = catalogIndex;
        }

        int getI() {
            return i;
        }

        public StarBuilder(String name, String hemisphere, String constellation, Declination declination, RightAscension rightAscension, double observedStellarMagnitude, double distance, double temperature, double mass) throws IOException, ClassNotFoundException {
            star = new Star();
            setName(name);
            setHemisphere(hemisphere);
            setConstellation(constellation);
            SetDeclination(declination);
            SetRightAscenios(rightAscension);
            SetObservedStellarMagnitude(observedStellarMagnitude);
            SetDistnce(distance);
            SetAbsoluteStellarMagnitude();
            SetTemperatures(temperature);
            SetMass(mass);
            setCatalogName();
            AppStart.listOfStar.add(this.star);
        }


        public Star build() {
            return star;
        }

        public StarBuilder showStar() {
            System.out.println(star);
            return this;
        }

        public void setName(String name) {
            while (!name.matches("[A-Z]{3}[0-9]{4}")) {
                System.out.println("Wrong star name. Try again: ");
            }
            star.name = name;
        }

        public void setHemisphere(String hemisphere) {
            if (hemisphere.equals("PN") || hemisphere.equals("PD")) {
                star.hemisphere = hemisphere;
            } else {
                System.out.println("Wrong star position. Try again: ");
            }
        }

        public void setConstellation(String constellation) {
            star.constellation = constellation;
        }

        public void setCatalogName() throws IOException, ClassNotFoundException {
            i = 0;
            if (!AppStart.listOfStar.isEmpty()) {
                for (Star deserializerStar : AppStart.listOfStar) {
                    if (deserializerStar.constellation.equals(star.getConstellation())) {
                        i++;
                    }
                }
                star.catalogName = GreekAlphabet.ALPHA.getGreekAlphabet(i).concat(" ").concat(star.constellation);
                setCatalogIndex(i);
            } else {
                star.catalogName = GreekAlphabet.ALPHA.getGreekAlphabet(i).concat(" ").concat(star.constellation);
                setCatalogIndex(i);
            }
        }

        public void SetDeclination(Declination declination) {
            if (star.getHemisphere().equals("PN")) {
                if (declination.getXx() < 0 || declination.getXx() > 90) {
                    throw new IllegalArgumentException("Deklinacja musi być z przedziału 0-90");
                } else if (declination.getYy() > 60 || declination.getYy() < 0) {
                    throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                } else if (declination.getZz() > 60 || declination.getZz() < 0) {
                    throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                } else if (declination.getXx() < 90 || declination.getXx() > 0) {
                    star.declination = declination;
                }
            } else if (star.getHemisphere().equals("PD")) {
                if (declination.getXx() > 0 || declination.getXx() < -90) {
                    throw new IllegalArgumentException("Deklinacja musi być z przedziału -90-0");
                } else if (declination.getYy() > 60 || declination.getYy() < 0) {
                    throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                } else if (declination.getZz() > 60 || declination.getZz() < 0) {
                    throw new IllegalArgumentException("Trzymaj sie prawidłowych jednostek czasu");
                } else if (declination.getXx() < 0 || declination.getXx() > -90) {
                    star.declination = declination;
                }
            }
        }

        public void SetRightAscenios(RightAscension rightAscension) {
            if (rightAscension.getXx() > 24 || rightAscension.getXx() < 0) {
                throw new IllegalArgumentException("Rekatascensja musi byc z przedziału 00-24");
            } else if (rightAscension.getYy() > 60 || rightAscension.getYy() < 0) {
                throw new IllegalArgumentException("Minuty musza byc z przedziału 0-60");
            } else if (rightAscension.getZz() > 60 || rightAscension.getZz() < 0) {
                throw new IllegalArgumentException("Sekundy musza byc z przedziału 0-60");
            } else if (rightAscension.getXx() > 0 && rightAscension.getXx() < 24) {
                star.rightAscension = rightAscension;
            }
        }

        public void SetObservedStellarMagnitude(double stellarMagnitude) {
            if (stellarMagnitude >= -26.74 && stellarMagnitude <= 15.00) {
                star.observedStellarMagnitude = stellarMagnitude;
            } else {
                throw new IllegalArgumentException("Podana obserwowana wielkość gwiazdy jest poza zakresem");
            }
        }

        public void SetDistnce(double distance) {
            if (distance < 0) {
                throw new IllegalArgumentException("Wartość nie może być ujemna");
            } else {
                star.distance = distance;
            }
        }

        public void SetAbsoluteStellarMagnitude() {
            star.absoluteStellarMagnitude = star.getObservedStellarMagnitude() - 5 * Math.log10(star.getdistance() / 3.26) + 5;
        }

        public void SetTemperatures(double temperatures) {
            if (temperatures <= 2000) {
                throw new IllegalArgumentException("Podana temperatura jest za niska!");
            } else {
                star.temperatures = temperatures;
            }
        }

        public void SetMass(double mass) {
            if (mass > 50 && mass < 0.1) {
                throw new IllegalArgumentException("Podana mass jest nieprawidłowa, prosze podać mase w zakresie [0.1-50.0]");
            } else {
                star.mass = mass;
            }
        }

        public Boolean ChecFileIsEmpty(String path) {
            File file = new File(path);
            if (file.length() == 0) {
                return false;
            } else return true;
        }

    }
}