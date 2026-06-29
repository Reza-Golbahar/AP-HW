package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class CodeNimbusSite extends Website {
    public CodeNimbusSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("education");
        categories.add("technology");
        categories.add("weather");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("email", getPersonalInfo().getEmail());
        info.put("gender", getPersonalInfo().getGender());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
