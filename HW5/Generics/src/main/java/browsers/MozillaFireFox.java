package browsers;

import engine.PersonalInfo;
import websites.CodeNimbusSite;
import websites.SkyStackSite;

public class MozillaFireFox<T> extends Browser<T> {
    public MozillaFireFox(PersonalInfo personalInfo) {
        super(personalInfo);
        websites.add(new CodeNimbusSite(personalInfo));
        websites.add(new SkyStackSite(personalInfo));
    }

    @Override
    public String getName() {
        return "MozillaFireFox";
    }
}
