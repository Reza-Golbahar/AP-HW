package browsers;

import engine.PersonalInfo;
import engine.QueryHolder;
import websites.Website;

import java.util.ArrayList;

public abstract class Browser<T> {
    protected PersonalInfo personalInfo;
    protected ArrayList<Website> websites;

    public Browser(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        websites = new ArrayList<>();
    }

    public abstract String getName();

    public boolean supportCategory(String category) {
        for (Website website : websites)
            if (website.getCategories().contains(category))
                return true;
        return false;
    }

    public QueryHolder<T> getRelevantWebsites(String category) {
        QueryHolder<T> result = new QueryHolder<>();
        for (Website website : websites)
            if (website.getCategories().contains(category))
                result.push((T) website);
        return result;
    }
}
