package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class PulseVerseSite extends Website {
    public PulseVerseSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("health");
        categories.add("cinema");
        categories.add("sports");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("email", getPersonalInfo().getEmail());
        info.put("phone", getPersonalInfo().getPhone());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
