package SpaceObjects;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        try {
            AppStart start = new AppStart();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
