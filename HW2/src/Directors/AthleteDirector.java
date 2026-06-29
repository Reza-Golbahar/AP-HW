package Directors;

import Builders.AthleteBuilder;
import Builders.GoalkeeperBuilder;
import Builders.PlayerBuilder;
import models.Athlete;

public class AthleteDirector {
    private AthleteBuilder builder;

    public AthleteDirector(AthleteBuilder builder) {
        this.builder = builder;
    }

    public void changeBuilder(AthleteBuilder builder) {
        this.builder = builder;
    }

    public Athlete makePlayer(String name, String nationality,
                              int shooting, int pace, int dribbling,
                              int physic, int passing, int defending) {
        builder.reset();
        builder.setName(name);
        builder.setNationality(nationality);

        if (builder instanceof PlayerBuilder playerBuilder) {
            playerBuilder.setShooting(shooting);
            playerBuilder.setPace(pace);
            playerBuilder.setDribbling(dribbling);
            playerBuilder.setPhysic(physic);
            playerBuilder.setPassing(passing);
            playerBuilder.setDefending(defending);
        }

        return builder.build();
    }

    public Athlete makeGoalie(String name, String nationality,
                                  int diving, int handling, int reflex,
                                  int positioning, int kicking, int speed) {
        builder.reset();
        builder.setName(name);
        builder.setNationality(nationality);

        if (builder instanceof GoalkeeperBuilder goalkeeperBuilder) {
            goalkeeperBuilder.setDiving(diving);
            goalkeeperBuilder.setHandling(handling);
            goalkeeperBuilder.setReflex(reflex);
            goalkeeperBuilder.setPositioning(positioning);
            goalkeeperBuilder.setKicking(kicking);
            goalkeeperBuilder.setSpeed(speed);
        }

        return builder.build();
    }
}
