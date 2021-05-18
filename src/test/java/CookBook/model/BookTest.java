package CookBook.model;

import junit.framework.TestCase;
import org.junit.Test;

public class BookTest extends TestCase {

    Book book = Book.getBook();

    @Test
    public void testCat(){

        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees");
        ger1.voegIngredient("kip", 150, 100);
        ger1.voegIngredient("rijst", 100, 100);
        Gerecht ger2 = new Gerecht("vis met rijst", "vis met rijst en saus", "20 min", "kook rijst", "vis");
        ger2.voegIngredient("vis", 150, 100);
        ger2.voegIngredient("rijst", 100, 100);
        Gerecht ger3 = new Gerecht("tofu met rijst", "tofu met rijst en saus", "20 min", "kook rijst", "vega");
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

        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees");
        ger1.voegIngredient("kip", 150, 100);
        ger1.voegIngredient("rijst", 200, 100);

        book.addGerecht(ger1);

        assertEquals( 350, book.getGerecht(ger1).getTotaalCalorieen());
    }

    @Test
    public void testFav(){
        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees");
        ger1.voegIngredient("kip", 150, 100);
        ger1.voegIngredient("rijst", 100, 100);

        book.getGerecht(ger1).setFavoriet(true);
        assertEquals(true, book.getGerecht(ger1).isFavoriet());
    }
}