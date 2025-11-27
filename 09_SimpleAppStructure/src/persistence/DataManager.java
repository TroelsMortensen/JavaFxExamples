package persistence;

import java.util.ArrayList;
import java.util.List;

public class DataManager
{
    private final List<String> strings = new ArrayList<>();

    public void addString(String string)
    {
        strings.add(string);
    }

    public List<String> getStrings()
    {
        return strings;
    }

}
