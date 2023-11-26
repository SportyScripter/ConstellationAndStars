package SpaceObjects;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppStart {
    static String fileName = "star.dat";
    static List<Star> listOfStar = new ArrayList<>();
    public AppStart() throws IOException, ClassNotFoundException {
        if (!ChecFileIsEmpty(fileName))
        {
            listOfStar =(List<Star>) StarSerializer.deserializeStar(fileName);
        }
//        showMenu();
        addStarsToChecActionProgram();
        StarSerializer.serializerStar(fileName, (Serializable) listOfStar);
        showCatalgoName();

    }
    public void showMenu()
    {
        System.out.println("******Menu*****");
        System.out.println("1.Dodaj Gwiazde");
        System.out.println("2.Wyszukaj wszystkie gwiazdy w gwiazdozbiorze");
        System.out.println("3.Wyszukaj gwiazdy w danej odleglosci");
        System.out.println("4.Wyszukaj gwiazdy o temperaturze");
        System.out.println("5.Wyszukaj gwiazdy wedlug wielskosci ");
        System.out.println("6.Wyszukaj supernove");
        System.out.println("7.Usuń gwiazde");
    }
    public Boolean ChecFileIsEmpty(String path)
    {
        File file = new File(path);
        if(file.length() == 0)
        {
            return true;
        }
        else return false;
    }
    public void showCatalgoName() throws IOException, ClassNotFoundException {
        ObjectInputStream newInput = new ObjectInputStream(new FileInputStream(fileName));
        for (Star star : AppStart.listOfStar) {
            System.out.println(star.getCatalogName());
        }
    }
    public void addStarsToChecActionProgram() throws IOException, ClassNotFoundException {
        new Star.StarBuilder("DCH7821", "PN", "Wolazer", new Declination(75, 15, 45.44), new RightAscension(5, 10, 15), 5, 978, 2500, 46).build();
        new Star.StarBuilder("DIM2456", "PD", "Wolazer", new Declination(-60, 32, 15.17), new RightAscension(20, 50, 22), -10, 652, 3750, 15).build();
        new Star.StarBuilder("RYB1234", "PN", "Wolazer", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
        new Star.StarBuilder("RYB1234", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
        new Star.StarBuilder("RYB1234", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
        new Star.StarBuilder("RYB1234", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
    }
}
