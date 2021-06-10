package CookBook.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest{

    Book book = Book.getBook();

    @BeforeEach
    public void setup(){

        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1,
                new ArrayList<Ingredient>() {
                    {new Ingredient("kip",150,100); new Ingredient("rijst", 100, 100);}
                });
        Gerecht ger2 = new Gerecht("vis met rijst", "vis met rijst en saus", "20 min", "kook rijst", "vis",1 );
        ger2.voegIngredient("vis", 150, 100);
        ger2.voegIngredient("rijst", 100, 100);
        Gerecht ger3 = new Gerecht("tofu met rijst", "tofu met rijst en saus", "20 min", "kook rijst", "vega", 1);
        ger3.voegIngredient("tofu", 150, 100);
        ger3.voegIngredient("rijst", 100, 100);

        book.addGerecht(ger1);
        book.addGerecht(ger2);
        book.addGerecht(ger3);
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
        Gerecht ger1 = new Gerecht("Kip met rijst", "kip met rijst en saus", "20 min", "kook rijst", "vlees", 1,
                new ArrayList<Ingredient>() {
                    {new Ingredient("kip",150,100); new Ingredient("rijst", 100, 100);}
                });

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

        assertEquals(2.5, book.getGerecht(ger5).berekenPortie(5));
    }
}