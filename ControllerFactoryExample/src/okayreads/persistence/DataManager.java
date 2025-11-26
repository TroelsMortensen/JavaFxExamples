package okayreads.persistence;

import okayreads.domain.Author;
import okayreads.domain.Book;
import okayreads.domain.Shelf;

import java.util.List;

public interface DataManager
{
    void addAuthor(Author author);
    List<Author> getAllAuthors();
    
    void addBook(Book book);
    List<Book> getAllBooks();
    
    void addShelf(Shelf shelf);
    List<Shelf> getAllShelves();
    Shelf getShelfByName(String name);
}

