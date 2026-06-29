package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class RainlyticSite extends Website {
    public RainlyticSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("weather");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("age", getPersonalInfo().getAge());
        info.put("country", getPersonalInfo().getCountry());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
