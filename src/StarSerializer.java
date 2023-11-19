import java.io.*;
public class StarSerializer {
    public static void serializerStar(String fileName, Serializable object)
    {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            outputStream.writeObject(object);
            System.out.println("Dane zostały zserializowane i zapisane do pliku" + fileName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static <T> T deserializeStar(String fileName){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            return (T) inputStream.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
