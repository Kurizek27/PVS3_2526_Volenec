package exams.serialization;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

// TODO: make it serializable
public class BookList {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Book> books;
    private double avgRating;

    @Override
    public String toString() {
        return "BookList{" +
                "books=" + books +
                ", avgRating=" + avgRating +
                '}';
    }
}
