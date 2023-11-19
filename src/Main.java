import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String filename = "star.dat";
    static List<Star> wczytywaneGwizady = StarSerializer.deserializeStar(filename);

    public static void main(String[] args) {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star.StarBuilder().build());
        if (!stars.isEmpty()) {
            wczytywaneGwizady.addAll(stars);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Zapisywanie danych przed zakończeniem programu...");
                StarSerializer.serializerStar(filename, (Serializable) wczytywaneGwizady);
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