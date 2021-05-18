package CookBook.model;

import junit.framework.TestCase;
import org.junit.Test;

public class BookTest extends TestCase {

    Book book = Book.getBook();

    @Test
    public void testCat(){

        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1);
        ger1.voegIngredient("kip", 150, 100);
        ger1.voegIngredient("rijst", 100, 100);
        Gerecht ger2 = new Gerecht("vis met rijst", "vis met rijst en saus", "20 min", "kook rijst", "vis",1 );
        ger2.voegIngredient("vis", 150, 100);
        ger2.voegIngredient("rijst", 100, 100);
        Gerecht ger3 = new Gerecht("tofu met rijst", "tofu met rijst en saus", "20 min", "kook rijst", "vega", 1);
        ger3.voegIngredient("tofu", 150, 100);
        ger3.voegIngredient("rijst", 100, 100);

        book.addGerecht(ger1);
        book.addGerecht(ger2);
        book.addGerecht(ger3);

        assertEquals( "[Gerecht{naam='vis met rijst', beschrijving='vis met rijst en saus', beredingstijd='20 min', bereidingswijze='kook rijst', favoriet=false, categorie='vis', alleIngredienten=[Ingredient{naam='vis', hoeveelheid=150, calper100=100}, Ingredient{naam='rijst', hoeveelheid=100, calper100=100}]}]"
                , book.getGerechtperCategorie("vis").toString());
    }

    @Test
    public void testCals(){

        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1);
        ger1.voegIngredient("kip", 150, 100);
        ger1.voegIngredient("rijst", 200, 100);

        book.addGerecht(ger1);

        assertEquals( 350, book.getGerecht(ger1).getTotaalCalorieen());
    }

    @Test
    public void testFav(){
        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1);
        ger1.voegIngredient("kip", 150, 100);
        ger1.voegIngredient("rijst", 100, 100);

        book.getGerecht(ger1).setFavoriet(true);
        assertEquals(true, book.getGerecht(ger1).isFavoriet());
    }

    @Test
    public void testVerwijderen(){
        Gerecht ger5 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1);
        ger5.voegIngredient("kip", 150, 100);
        ger5.voegIngredient("rijst", 100, 100);

        book.addGerecht(ger5);
        book.getGerecht(ger5).verwijderIngredient("kip");

        assertEquals(1, book.getGerecht(ger5).getAlleIngredienten().size());
    }


    @Test
    public void testPortie(){
        Gerecht ger5 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 2);
        ger5.voegIngredient("kip", 150, 100);
        ger5.voegIngredient("rijst", 100, 100);

        book.addGerecht(ger5);

        assertEquals(0.5, book.getGerecht(ger5).berekenPortie(1));
    }
}