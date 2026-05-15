package exams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookMaps {

    public static void printAuthorPages(List<Book> books, String name){
        // TODO
        // Metoda vypíše celkový počet stránek všech knížek od autora jménem "name"

    }

    public static void main(String[] args) throws IOException {
        // Dodělejte třídu Book a Chapter (gettery, settery, constructor, toString + co uznáte za vhodné)
        // Načtěte soubory books.csv a chapters.csv

        List<Book> books = Files.lines(Paths.get("data/books.csv"))
                .skip(1)
                .map(line -> line.split(",", 3))
                .map(parsed -> new Book(
                        Integer.parseInt(parsed[0]),
                        parsed[1],
                        parsed[2]
                )).toList();



        List<Chapter> chapters = Files.lines(Paths.get("data/chapters.csv"))
                .skip(1)
                .map(line -> line.split(",", 4))
                .map(split -> new Chapter(
                        Integer.parseInt(split[1]),
                        split[2],
                        Integer.parseInt(split[3])
                )).toList();

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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.chapters = new ArrayList<>();
    }
}

class Chapter{
    private int bookID;
    private String title;
    private int pages;

    public Chapter(int bookID, String title, int pages) {
        this.bookID = bookID;
        this.title = title;
        this.pages = pages;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}