package SpaceObjects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppStart {
    static String fileName = "star.dat";
    static List<Star> listOfStar = new ArrayList<>();
    public AppStart()
    {
        if (ChecFileIsEmpty(fileName))
        {

        }
        else
        {
            listOfStar = StarSerializer.deserializeStar(fileName);
        }

        StarSerializer.serializerStar(fileName, (Serializable) listOfStar);

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
}
