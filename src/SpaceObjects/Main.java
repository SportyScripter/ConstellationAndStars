package SpaceObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String filename = "star.dat";
//    static List<Star> wczytywaneGwizady = StarSerializer.deserializeStar(filename);

    public static void main(String[] args) {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star.StarBuilder("RYB1234","PN","Wolazer",new Declination(45,52,30.23),new RightAscension(13,35,45),10,326,4000,30).build());
        stars.add(new Star.StarBuilder("DIM2456","PD","Wolazer",new Declination(-60,32,15.17),new RightAscension(20,50,22),-10,652,3750,15).build());
        stars.add(new Star.StarBuilder("DCH7821","PN","Wolazer",new Declination(75,15,45.44),new RightAscension(5,10,15),5,978,2500,46).build());
        if (!stars.isEmpty()) {
//            wczytywaneGwizady.addAll(stars);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                StarSerializer.serializerStar(filename, (Serializable) stars);
                System.out.println("Zapisywanie danych przed zakończeniem programu...");
            }));
        } else {
            System.out.println("Nie ma danych do zapisania");
        }
        List<Star> wczytywaneGwizady = StarSerializer.deserializeStar(filename);
        if (wczytywaneGwizady != null) {
            for (Star star : wczytywaneGwizady) {
                System.out.println(star);
            }
        }
    }
}