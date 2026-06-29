package browsers;

import engine.PersonalInfo;
import websites.AtlasMetricsSite;
import websites.PulseVerseSite;
import websites.RainlyticSite;

public class Opera<T> extends Browser<T> {
    public Opera(PersonalInfo personalInfo) {
        super(personalInfo);
        websites.add(new AtlasMetricsSite(personalInfo));
        websites.add(new RainlyticSite(personalInfo));
        websites.add(new PulseVerseSite(personalInfo));
    }

    @Override
    public String getName() {
        return "Opera";
    }
}
