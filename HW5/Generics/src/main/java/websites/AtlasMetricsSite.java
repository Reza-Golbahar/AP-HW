package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class AtlasMetricsSite extends Website {
    public AtlasMetricsSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("weather");
        categories.add("sports");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("age", getPersonalInfo().getAge());
        info.put("phone", getPersonalInfo().getPhone());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
