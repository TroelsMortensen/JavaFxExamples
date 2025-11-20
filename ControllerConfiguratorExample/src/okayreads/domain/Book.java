package okayreads.domain;

public class Book
{
    private String isbn;
    private String title;
    private int publicationYear;
    private Author writtenBy;

    public Book(String isbn, String title, int publicationYear, Author writtenBy)
    {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.writtenBy = writtenBy;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public int getPublicationYear()
    {
        return publicationYear;
    }

    public Author getWrittenBy()
    {
        return writtenBy;
    }

    public String getAuthorName()
    {
        return writtenBy.getName();
    }
}
