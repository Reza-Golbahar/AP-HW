package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class SkyStackSite extends Website {
    public SkyStackSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("weather");
        categories.add("sports");
        categories.add("technology");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("country", getPersonalInfo().getCountry());
        info.put("phone", getPersonalInfo().getPhone());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
