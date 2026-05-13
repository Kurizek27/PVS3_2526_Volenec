package exams.maps;

import java.util.ArrayList;
import java.util.List;

public class BookMaps {

    public static void printAuthorPages(List<Book> books, String name){
        // TODO
        // Metoda vypíše celkový počet stránek všech knížek od autora jménem "name"
    }

    public static void main(String[] args) {
        // Dodělejte třídu Book a Chapter (gettery, settery, constructor, toString + co uznáte za vhodné)
        // Načtěte soubory books.csv a chapters.csv
        // Implementujte metodu printAuthorPages
        // Vypište, kolik knížek napsal každý autor
        // Vypište top 5 autorů dle počtu napsaných stran
    }
}

class Book{
    private int bookId;
    private String title;
    private String author;
    private List<Chapter> chapters = new ArrayList<>();
    public int getTotalPages(){
        // TODO, dodělat, nebo smazat
        return 0;
    }
}

class Chapter{
    private int bookID;
    private String title;
    private int pages;
}