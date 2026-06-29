package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class LogicTrailSite extends Website {
    public LogicTrailSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("health");
        categories.add("crime");
        categories.add("education");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("gender", getPersonalInfo().getGender());
        info.put("country", getPersonalInfo().getCountry());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
