package websites;

import engine.PersonalInfo;

import java.util.HashMap;

public class InsightSphereSite extends Website {
    public InsightSphereSite(PersonalInfo personalInfo) {
        super(personalInfo);
        categories.add("education");
        categories.add("finance");
        categories.add("crime");
    }

    @Override
    public LoginProcess login() {
        HashMap<String, String> info = new HashMap<>();
        info.put("password", getPersonalInfo().getPassword());
        info.put("age", getPersonalInfo().getAge());
        info.put("email", getPersonalInfo().getEmail());
        return new LoginProcess(this, getPersonalInfo().getUsername(), info);
    }
}
