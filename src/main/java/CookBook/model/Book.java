package CookBook.model;

import com.fasterxml.jackson.databind.JsonNode;

import javax.json.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book  implements Serializable {
    private List<Gerecht> alleGerechten = new ArrayList<>();

    private static Book my_CookBook = new Book();

    public static Book getBook() { return my_CookBook; }
    public static void setBook(Book book) {my_CookBook = book;}

    private Book(){
        ArrayList<Ingredient> Ingredienten1 = new ArrayList<>();
        Ingredienten1.add(new Ingredient("kip", 100, 100));
        Ingredienten1.add(new Ingredient("Rijst", 100, 200));
        Ingredienten1.add(new Ingredient("saus", 50, 100));

        ArrayList<Ingredient> Ingredienten2 = new ArrayList<>();
        Ingredienten2.add(new Ingredient("vis", 100, 90));
        Ingredienten2.add(new Ingredient("Rijst", 100, 200));
        Ingredienten2.add(new Ingredient("saus", 50, 100));

        ArrayList<Ingredient> Ingredienten3 = new ArrayList<>();
        Ingredienten3.add(new Ingredient("tofu", 100, 120));
        Ingredienten3.add(new Ingredient("Rijst", 100, 200));
        Ingredienten3.add(new Ingredient("saus", 50, 100));

        alleGerechten.add(new Gerecht("kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", "1", Ingredienten1));
        alleGerechten.add(new Gerecht("vis met rijst", "vis met rijst en saus", "23 min", "kook rijst", "vis", "2", Ingredienten2));
        alleGerechten.add(new Gerecht("tofu met rijst", "tofu met rijst en saus", "15 min", "kook rijst", "vega", "1", Ingredienten3));

    }

    public void createGerecht(JsonNode jsonNode) {
        String naam = jsonNode.get("naam").asText();
        String beschrijving = jsonNode.get("beschrijving").asText();
        String bereidingstijd = jsonNode.get("bereidingstijd").asText();
        String bereidingswijze = jsonNode.get("bereidingswijze").asText();
        String categorie = jsonNode.get("categorie").asText();
        String portie = jsonNode.get("portie").asText();

        JsonNode list = jsonNode.get("alleIngredienten");

        ArrayList<Ingredient> ingredienten = new ArrayList();
        int it = 0;

        for (JsonNode listItem : list){

            String naamIngr = list.get(it).get("naam").asText();
            int hoeveelheid = list.get(it).get("hoeveelheid").asInt();
            int calper100 = list.get(it).get("calper100").asInt();

            ingredienten.add(new Ingredient(naamIngr, hoeveelheid, calper100));

            it += 1;
        }


        addGerecht(new Gerecht(naam, beschrijving, bereidingstijd, bereidingswijze, categorie, portie, ingredienten));
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

    public boolean containsName(final String name){
        return alleGerechten.stream().map(Gerecht::getNaam).filter(name::equals).findFirst().isPresent();
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
