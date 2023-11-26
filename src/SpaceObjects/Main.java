package SpaceObjects;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static public final String filename = "star.dat";


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        addStarsToChecActionProgram();
        showCatalgoName();
    }

    static void showCatalgoName() throws IOException, ClassNotFoundException {
        ObjectInputStream newInput = new ObjectInputStream(new FileInputStream(filename));
        List<Star> newList = new ArrayList<>();
        newList = (List<Star>) newInput.readObject();
        for (Star star : newList) {
            System.out.println(star.getCatalogName());
        }
    }

    static void addStarsToChecActionProgram() throws IOException, ClassNotFoundException {
        new Star.StarBuilder("DCH7821", "PN", "Wolazer", new Declination(75, 15, 45.44), new RightAscension(5, 10, 15), 5, 978, 2500, 46).build();
        new Star.StarBuilder("DIM2456", "PD", "Wolazer", new Declination(-60, 32, 15.17), new RightAscension(20, 50, 22), -10, 652, 3750, 15).build();
        new Star.StarBuilder("RYB1234", "PN", "Wolazer", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
        new Star.StarBuilder("RYB1234", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
        new Star.StarBuilder("RYB1234", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
        new Star.StarBuilder("RYB1234", "PN", "Orfa", new Declination(45, 52, 30.23), new RightAscension(13, 35, 45), 10, 326, 4000, 30).build();
    }
//    TODO Zrobić klikalne manu gdzie uzytkownik wybiera sobie opcje do wyboru, dane zapisywac do pliku za pomoca serializable, moze byc tak że plik serializable otwira sie na poczatku programu a pozniej na koncu działania programu sie zamyka i wszystko serializuje
//
}