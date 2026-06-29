package browsers;

import engine.PersonalInfo;
import websites.*;

public class GoogleChrome<T> extends Browser<T> {
    public GoogleChrome(PersonalInfo personalInfo) {
        super(personalInfo);
        websites.add(new InsightSphereSite(personalInfo));
        websites.add(new LogicTrailSite(personalInfo));
    }

    @Override
    public String getName() {
        return "GoogleChrome";
    }
}
