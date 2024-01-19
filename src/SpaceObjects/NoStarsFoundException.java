package SpaceObjects;
// Tutaj podobnie jak w przypadku wyjątku InvalidParameterException
public class NoStarsFoundException extends RuntimeException{
    public NoStarsFoundException(String message)
    {
        super(message);
    }
}
