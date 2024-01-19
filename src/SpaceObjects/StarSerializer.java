package SpaceObjects;
import java.io.*;
// klasa odpowiedzialna za serializacje i deserializacje gwiazd
public class StarSerializer {
    public static StarSerializer serializerStar(String fileName, Serializable object) // metoda do serializacji gdzie jako parametry podajemy nazwe pliku i obiekt do serializacji
    {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) { // tworzenie strumienia wyjsciowego
                outputStream.writeObject(object); // zapisywanie obiektu
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
    public static <T> T deserializeStar(String fileName){ // meotda do deserializacji gdzie jako parametr podajemy nazwe pliku
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) // tworzenie strumienia wejsciowego
        {
            return (T) inputStream.readObject(); // zwracanie obiektu
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
