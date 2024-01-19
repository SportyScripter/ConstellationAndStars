package SpaceObjects;
import java.io.IOException;

public class StarRemover {
// klasa odpowiedzialna za usuwanie gwiazd z listy
    public static void DeleteStar(String GreekAlphabet, String ConstellationName) throws IOException, ClassNotFoundException {
        int counter = 1;
        Star starToRemove = null;

        for (Star star : AppStart.listOfStar) {
            String catalogName = GreekAlphabet.toUpperCase().concat(" ").concat(ConstellationName); // tworzy nazwe katalogowa gwiazdy ktora chcemy usunac
            if (star.getCatalogName().equals(catalogName)) { // porownuje wszystkie gwiazdy w liscie aby znalezc ta ktora chcemy usunac
                starToRemove = star; // tworzy obiekt gwiazdy do usuniecia
                break; // przerwanie petli gdy gwiazda zostanie znaleziona
            }
        }
        if (starToRemove != null) { // jesli obiekt zostal znaleziony to usuwa go z listy
            AppStart.listOfStar.remove(starToRemove); //usuwanie gwiazdy z listy poprzez podanie obiektu ktory wczesniej został stworzony
        }
        else {
            System.out.println("Nie ma takiej gwiazdy");
            return;
        }
        if (starToRemove.getCatalogIndex() == 0) { // jesli gwiazda do usuniecia była alpha to trzeba znalezc nowa alpha
            Star newAlpha = FindNewAlphaStar(ConstellationName); //znajduje nowa alpha
            if (newAlpha != null) // jesli znaleziono nowa alpha
            {
            newAlpha.setCatalogIndex(0); //ustawiamy jej index na 0
            newAlpha.setCatalogName(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(newAlpha.getCatalogIndex()).concat(" ").concat(newAlpha.getConstellation())); //zmiana nazwy katalogowej
            for (Star starsToUpdate : AppStart.listOfStar) { //aktualizacja nazw katalogowych gwiazd w danym gwiazdozbiorze
                if (starsToUpdate.getConstellation().equals(ConstellationName) && starsToUpdate.getCatalogIndex() > starToRemove.getCatalogIndex()) { //jesli gwiazda jest w tym samym gwiazdozbiorze i jej index jest wiekszy od poprzedniego indeksu nowej alpha
                    starsToUpdate.setCatalogIndex(starsToUpdate.getCatalogIndex() - 1); //zmniejszamy jej index o 1 w dół
                    starsToUpdate.setCatalogName(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(starsToUpdate.getCatalogIndex()).concat(" ").concat(starsToUpdate.getConstellation())); // zmieniamy nazwe katalogowa
                }
            }
            }
            else
            {
                System.out.println("Usunąłeś ostatnią gwiazde w gwiazdozbiorze");
            }
        }
        else // jesli gwiazda do usuniecia byłą inna niz alpha
        {
            for (Star starsToUpdate : AppStart.listOfStar) { //aktualizacja nazw katalogowych giwazd w gwiazdozbiorze
                if (starsToUpdate.getConstellation().equals(ConstellationName) && starsToUpdate.getCatalogIndex() > starToRemove.getCatalogIndex()) { //jesli gwiazda jest w tym samym gwiazdozbiorze i jej index jest wiekszy od poprzedniego indeksu gwiazdy do usuniecia
                    starsToUpdate.setCatalogIndex(starsToUpdate.getCatalogIndex() - 1); //zmniejszamy jej index o 1 w dół
                    starsToUpdate.setCatalogName(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(starsToUpdate.getCatalogIndex()).concat(" ").concat(starsToUpdate.getConstellation())); // zmieniamy nazwe katalogowa
                }
            }
        }
    }

    private static Star FindNewAlphaStar(String constellationName) { // metoda do wyszukania nowej alphy w gwiazdozbiorze czyli najjasniejszej gwiazdy
        Star newAlpha = null;
        double temp = 15.00;
        for (Star star : AppStart.listOfStar) {
            if (star.getConstellation().equals(constellationName)) {
                if (star.getObservedStellarMagnitude() < temp) {
                    temp = star.getObservedStellarMagnitude();
                    newAlpha = star;
                }
            }
        }
        return newAlpha;
    }


}
