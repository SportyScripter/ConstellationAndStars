package SpaceObjects;
import java.io.*;

public class StarSerializer {
    public static StarSerializer serializerStar(String fileName, Serializable object)
    {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(object);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
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
