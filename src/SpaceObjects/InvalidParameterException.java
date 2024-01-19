package SpaceObjects;
// klasa wyjątku, wsumie nie była potrzebna, ale chciałem nauczyć sie jak w razie czego tworzyć własne wyjątki :)
public class InvalidParameterException extends IllegalArgumentException {
    public InvalidParameterException(String message){
        super(message);
    }
}
