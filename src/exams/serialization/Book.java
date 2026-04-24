package exams.serialization;
import java.io.Serial;
import java.io.Serializable;

// TODO: make it serializable
// Add getters/setters/constructor/toString

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String title;
    public String author;

    public int yearOfRelease;
    public int numberOfPages;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", numberOfPages=" + numberOfPages +
                ", rating=" + rating +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public double getRating() {
        return rating;
    }

    public Book(String title, String author, int yearOfRelease, int numberOfPages, double rating) {
        this.title = title;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
        this.numberOfPages = numberOfPages;
        this.rating = rating;
    }

    public double rating; // 1.0-5.0
}