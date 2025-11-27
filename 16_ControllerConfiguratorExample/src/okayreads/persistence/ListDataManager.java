package okayreads.persistence;

import okayreads.domain.Author;
import okayreads.domain.Book;
import okayreads.domain.Shelf;

import java.util.List;

public class ListDataManager implements DataManager
{
    private static DataContainer data = new DataContainer();

    @Override
    public void addAuthor(Author author)
    {
        data.getAuthors().add(author);
    }

    @Override
    public List<Author> getAllAuthors()
    {
        return data.getAuthors();
    }

    @Override
    public void addBook(Book book)
    {
        data.getBooks().add(book);
    }

    @Override
    public List<Book> getAllBooks()
    {
        return data.getBooks();
    }

    @Override
    public void addShelf(Shelf shelf)
    {
        data.getShelves().add(shelf);
    }

    @Override
    public List<Shelf> getAllShelves()
    {
        return data.getShelves();
    }

    @Override
    public Shelf getShelfByName(String name)
    {
        for (Shelf shelf : data.getShelves())
        {
            if (shelf.getName().equals(name))
            {
                return shelf;
            }
        }
        return null;
    }
}

