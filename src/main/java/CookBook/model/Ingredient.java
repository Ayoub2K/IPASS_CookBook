package CookBook.model;

public class Ingredient {
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

    @Override
    public String toString() {
        return "Ingredient{" +
                "naam='" + naam + '\'' +
                ", hoeveelheid=" + hoeveelheid +
                ", calper100=" + calper100 +
                '}';
    }
}