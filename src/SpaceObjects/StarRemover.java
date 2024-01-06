package SpaceObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StarRemover {


    //    public static void DeleteStar(String GreekAlphabet, String ConstellationName) throws IOException, ClassNotFoundException {
//      int counter = 1;
//       for (Star star : AppStart.listOfStar) {
//           String catalogName = GreekAlphabet.toUpperCase().concat(" ").concat(ConstellationName);
//            if (star.getCatalogName().equals(catalogName)) {
//                AppStart.listOfStar.remove(star);
//                for (Star updateCatalogName : AppStart.listOfStar) {
//                    if (updateCatalogName.getConstellation().equals(star.getConstellation())) {
//
//                        if(FindNewAlphaStar(ConstellationName).equals(updateCatalogName))
//                        {
//                            updateCatalogName.setCatalogName(updateCatalogName.getCatalogName().replace(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(updateCatalogName.getCatalogIndex()), "Alpha"));
//                        }
//                        else
//                        {
//                            updateCatalogName.setCatalogName(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(counter).concat(" ").concat(updateCatalogName.getConstellation()));
//                            counter++;
//                        }
//                    }
//                }
//            }
//        }
//    }
//    private static Star FindNewAlphaStar(String constellationName)
//    {
//        Star newAlpha = null;
//        double temp = 15.00;
//        for (Star star : AppStart.listOfStar)
//        {
//            if (star.getConstellation().equals(constellationName))
//            {
//                if (star.getObservedStellarMagnitude() < temp)
//                {
//                    temp = star.getObservedStellarMagnitude();
//                    newAlpha = star;
//                }
//            }
//        }
//        return newAlpha;
//    }
    public static void DeleteStar(String GreekAlphabet, String ConstellationName) throws IOException, ClassNotFoundException {
        int counter = 1;
        Star starToRemove = null;

        for (Star star : AppStart.listOfStar) {
            String catalogName = GreekAlphabet.toUpperCase().concat(" ").concat(ConstellationName);
            if (star.getCatalogName().equals(catalogName)) {
                starToRemove = star;
                break;
            }
        }
        if (starToRemove != null) {
            AppStart.listOfStar.remove(starToRemove); //usuwanie gwiazdy z listy
        }
        else {
            System.out.println("Nie ma takiej gwiazdy");
            return;
        }
        if (starToRemove.getCatalogIndex() == 0) { // jesli gwiazda do usuniecia była alpha to trzeba znalezc nowa alpha
            Star newAlpha = FindNewAlphaStar(ConstellationName); //znajduje nowa alpha
            if (newAlpha != null)
            {
            newAlpha.setCatalogIndex(0); //ustawiamy jej index na 0
            newAlpha.setCatalogName(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(newAlpha.getCatalogIndex()).concat(" ").concat(newAlpha.getConstellation())); //zmiana nazwy katalogowej
            for (Star starsToUpdate : AppStart.listOfStar) { //aktualizacja nazw katalogowych
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
            for (Star starsToUpdate : AppStart.listOfStar) { //aktualizacja nazw katalogowych
                if (starsToUpdate.getConstellation().equals(ConstellationName) && starsToUpdate.getCatalogIndex() > starToRemove.getCatalogIndex()) { //jesli gwiazda jest w tym samym gwiazdozbiorze i jej index jest wiekszy od poprzedniego indeksu gwiazdy do usuniecia
                    starsToUpdate.setCatalogIndex(starsToUpdate.getCatalogIndex() - 1); //zmniejszamy jej index o 1 w dół
                    starsToUpdate.setCatalogName(SpaceObjects.GreekAlphabet.ALPHA.getGreekAlphabet(starsToUpdate.getCatalogIndex()).concat(" ").concat(starsToUpdate.getConstellation())); // zmieniamy nazwe katalogowa
                }
            }
        }
    }

    private static Star FindNewAlphaStar(String constellationName) {
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
