package Builders;

import models.Athlete;

public interface AthleteBuilder {
    public void reset();
    public void setName(String name);
    public void setNationality(String nationality);

    public Athlete build();
}
