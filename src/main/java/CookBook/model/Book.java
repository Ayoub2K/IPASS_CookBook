package CookBook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book  implements Serializable {
    private List<Gerecht> alleGerechten = new ArrayList<>();

    private static Book my_CookBook = new Book();
    public static Book getBook() { return my_CookBook; }

    public static void setBook(Book book) {my_CookBook = book;}

    private Book(){
        alleGerechten.add(new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1));
//        getBook().getGerechtByName("Kip met rijst").voegIngredient("kip", 150, 100);
    }

    public List<Gerecht> getAlleGerechten() {
        return alleGerechten;
    }

    public void addGerecht(Gerecht gerecht){
        alleGerechten.add(gerecht);
    }

    public Gerecht getGerecht(Gerecht gerecht){
        return gerecht;
    }

    public Gerecht getGerechtByName(String naam){
        for(Gerecht gerecht : alleGerechten){
            if(gerecht.getNaam().equals(naam)){
                return gerecht;
            }
        }
        return null;
    }

    public void verwijderGerecht(String naam){
        for(Gerecht gerecht : alleGerechten){
            if(gerecht.getNaam().equals(naam)){
                alleGerechten.remove(gerecht);
            }
        }
    }

    public List<Gerecht> getFavorieten(){
        List<Gerecht> favorietenGerechten = new ArrayList<>();
        for(Gerecht gerecht : alleGerechten){
            if(gerecht.isFavoriet()){
                favorietenGerechten.add(gerecht);
            }
        }
        return favorietenGerechten;
    }

    public List<Gerecht> getGerechtperCategorie(String categorie){
        List<Gerecht> GerechtenCategorie = new ArrayList<>();
        for(Gerecht gerecht : alleGerechten){
            if(gerecht.getCategorie().equals(categorie)){
                GerechtenCategorie.add(gerecht);
            }
        }
        return GerechtenCategorie;
    }

}
