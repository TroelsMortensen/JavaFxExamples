package okayreads.domain;

import java.util.ArrayList;
import java.util.List;

public class Shelf
{
    private String name;
    private List<Book> books = new ArrayList<>();

    public Shelf(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void addBook(Book book)
    {
        books.add(book);
    }
}
