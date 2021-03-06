package CookBook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Gerecht  implements Serializable {
    private String naam;
    private String beschrijving;
    private String bereidingstijd;
    private String bereidingswijze;
    private int favoriet;
    private String categorie;
    private double portie;

    private List<Ingredient> alleIngredienten = new ArrayList<>();

    public Gerecht(String naam, String beschrijving, String bereidingstijd, String bereidingswijze, String categorie, String portie) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.bereidingstijd = bereidingstijd;
        this.bereidingswijze = bereidingswijze;
        this.categorie = categorie;
        this.portie = Integer.parseInt(portie);
        this.favoriet = 0;
    }

    public Gerecht(String naam, String beschrijving, String bereidingstijd, String bereidingswijze, String categorie, String portie, List<Ingredient> ingredientenList) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.bereidingstijd = bereidingstijd;
        this.bereidingswijze = bereidingswijze;
        this.categorie = categorie;
        this.portie = Integer.parseInt(portie);
        this.alleIngredienten = ingredientenList;
        this.favoriet = 0;
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

    public Ingredient getIngredientbyName(String ingredient){
        return alleIngredienten.parallelStream().filter(e -> e.getNaam().equals(ingredient)).findFirst().orElse(null);
    }

    public void setFavoriet(boolean fav){
        this.favoriet += 1;
    }

    public int getFavoriet() {
        return favoriet;
    }

    public void bewerkGerecht(String beschrijving, String bereidingstijd, String bereidingswijze, String categorie, String portie){
        this.beschrijving = beschrijving;
        this.bereidingstijd = bereidingstijd;
        this.bereidingswijze = bereidingswijze;
        this.categorie =categorie;
        this.portie = Integer.parseInt(portie);
    }

    public List<Ingredient> getAlleIngredienten() {return alleIngredienten; }

    public void voegIngredient(String naam, int hoeveelheid, int calper100){
        alleIngredienten.add(new Ingredient(naam, hoeveelheid, calper100));
    }

//    //Wijzig hoeveelheid van dit ingredient
//    public void wijzigIngHvlheid(String naam, int hoeveelheid){
//        for (Ingredient ingredient : alleIngredienten){
//            if (ingredient.getNaam().equals(naam)){
//                ingredient.setHoeveelheid(hoeveelheid);
//            }
//        }
//    }


    //Verwijder Ingredient uit dit recept
    public Boolean verwijderIngredient(String naam){
        for (Ingredient ingredient : alleIngredienten){
            if (ingredient.getNaam().equals(naam)){
                alleIngredienten.remove(ingredient);
                return true;
            }
        }
        return false;
    }

    public double berekenPortie(int aantalPorties){
        return aantalPorties / portie;
    }

    public String getBereidingstijd() {
        return bereidingstijd;
    }

    public void setBereidingstijd(String bereidingstijd) {
        this.bereidingstijd = bereidingstijd;
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

    public double getPortie() {
        return portie;
    }

    public void setPortie(double portie) {
        this.portie = portie;
    }

    @Override
    public boolean equals(Object andereObject) {
        if (andereObject.getClass().equals(Gerecht.class)) {
            return ((Gerecht) andereObject).getNaam().equals(naam)
                    && ((Gerecht) andereObject).getCategorie().equals(categorie)
                    && ((Gerecht) andereObject).getBereidingstijd().equals(bereidingstijd)
                    && ((Gerecht) andereObject).getBereidingswijze().equals(bereidingswijze)
                    && ((Gerecht) andereObject).getBeschrijving().equals(beschrijving)
                    && ((Gerecht) andereObject).getFavoriet() == favoriet
                    && ((Gerecht) andereObject).getPortie() == portie
                    ;}
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Gerecht{" +
                "naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", bereidingstijd='" + bereidingstijd + '\'' +
                ", bereidingswijze='" + bereidingswijze + '\'' +
                ", favoriet=" + favoriet +
                ", categorie='" + categorie + '\'' +
                ", alleIngredienten=" + alleIngredienten +
                '}';
    }
}
