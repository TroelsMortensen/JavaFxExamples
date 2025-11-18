package okayreads.persistence;

import okayreads.domain.Author;
import okayreads.domain.Book;
import okayreads.domain.Shelf;

import java.util.ArrayList;
import java.util.List;

public class DataContainer
{
    private List<Author> authors;
    private List<Book> books;
    private List<Shelf> shelves;

    public DataContainer()
    {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        shelves = new ArrayList<>();
    }

    public List<Author> getAuthors()
    {
        return authors;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public List<Shelf> getShelves()
    {
        return shelves;
    }
}

