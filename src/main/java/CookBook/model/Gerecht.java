package CookBook.model;

import java.util.ArrayList;
import java.util.List;

public class Gerecht {
    private String naam;
    private String beschrijving;
    private String beredingstijd;
    private String bereidingswijze;
    private boolean favoriet;
    private String categorie;
    private double portie;

    private List<Ingredient> alleIngredienten = new ArrayList<>();

    public Gerecht(String naam, String beschrijving, String beredingstijd, String bereidingswijze, String categorie, int portie) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.beredingstijd = beredingstijd;
        this.bereidingswijze = bereidingswijze;
        this.categorie = categorie;
        this.portie = portie;
    }

    public String getNaam(){
        return naam;
    }

    public int getTotaalCalorieen(){
        int totaalCal = 0;
        for(Ingredient ingredient : alleIngredienten){
            totaalCal += ingredient.berekenCalorieen();
        }
        return totaalCal;
    }

    public void setFavoriet(boolean fav){
        this.favoriet = fav;
    }

    public boolean isFavoriet() {
        return favoriet;
    }

    public List<Ingredient> getAlleIngredienten() {return alleIngredienten; }

    public void voegIngredient(String naam, int hoeveelheid, int calper100){
        alleIngredienten.add(new Ingredient(naam, hoeveelheid, calper100));
    }

    //Wijzig hoeveelheid van dit ingredient
    public void wijzigIngHvlheid(String naam, int hoeveelheid){
        for (Ingredient ingredient : alleIngredienten){
            if (ingredient.getNaam().equals(naam)){
                ingredient.setHoeveelheid(hoeveelheid);
            }
        }
    }

    //Verwijder Ingredient uit dit recept
    public void verwijderIngredient(String naam){
        for (Ingredient ingredient : alleIngredienten){
            if (ingredient.getNaam().equals(naam)){
                alleIngredienten.remove(ingredient);
            }else {
                System.out.println(ingredient);
            }
        }
    }

    public double berekenPortie(int aantalPorties){
        return aantalPorties / portie;
    }

    public String getBeredingstijd() {
        return beredingstijd;
    }

    public void setBeredingstijd(String beredingstijd) {
        this.beredingstijd = beredingstijd;
    }

    public String getBereidingswijze() {
        return bereidingswijze;
    }

    public void setBereidingswijze(String bereidingswijze) {
        this.bereidingswijze = bereidingswijze;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    @Override
    public String toString() {
        return "Gerecht{" +
                "naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", beredingstijd='" + beredingstijd + '\'' +
                ", bereidingswijze='" + bereidingswijze + '\'' +
                ", favoriet=" + favoriet +
                ", categorie='" + categorie + '\'' +
                ", alleIngredienten=" + alleIngredienten +
                '}';
    }
}
