package okayreads.domain;

import java.util.ArrayList;
import java.util.List;

public class Author
{
    private String name;

    public Author(String name)
    {
        if (name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
