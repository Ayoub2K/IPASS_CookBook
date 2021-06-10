package CookBook.model;

import java.io.Serializable;

public class Ingredient  implements Serializable {
    private String naam;
    private int hoeveelheid;
    private int calper100;

    public Ingredient(String naam, int hoeveelheid, int calper100) {
        this.naam = naam;
        this.hoeveelheid = hoeveelheid;
        this.calper100 = calper100;
    }

    public String getNaam(){
        return naam;
    }

    public int berekenCalorieen(){
        return (calper100 / 100) * hoeveelheid;
    }

    public int getHoeveelheid(){
        return hoeveelheid;
    }

    public void setHoeveelheid(int nieuweHoeveelheid){
        this.hoeveelheid = nieuweHoeveelheid;
    }

    public int getCalper100() {
        return calper100;
    }

    @Override
    public boolean equals(Object andereObject) {
        if (andereObject.getClass().equals(Ingredient.class)) {
            return ((Ingredient) andereObject).getNaam().equals(naam)
                    && ((Ingredient) andereObject).getHoeveelheid() == hoeveelheid
                    && ((Ingredient) andereObject).getCalper100() == calper100
                    ;}
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "naam='" + naam + '\'' +
                ", hoeveelheid=" + hoeveelheid +
                ", calper100=" + calper100 +
                '}';
    }
}
