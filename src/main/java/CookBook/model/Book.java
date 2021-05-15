package CookBook.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private List<Gerecht> alleGerechten = new ArrayList<>();

    private static Book my_CookBook = new Book();
    public static Book getBook() { return my_CookBook; }

    public static void setMy_CookBook(Book book) {my_CookBook = book; }

    public List<Gerecht> getAlleGerechten() {return alleGerechten; }

    public void addGerecht(String naam, String beschrijving, String beredingstijd, String bereidingswijze, String categorie, List<Ingredient> ingredienten){
        alleGerechten.add(new Gerecht(naam, beschrijving, beredingstijd, bereidingswijze, categorie, ingredienten));
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

    public List<Gerecht> getGerechtCategorie(String categorie){
        List<Gerecht> GerechtenCategorie = new ArrayList<>();
        for(Gerecht gerecht : alleGerechten){
            if(gerecht.getCategorie().equals(categorie)){
                GerechtenCategorie.add(gerecht);
            }
        }
        return GerechtenCategorie;
    }

}
