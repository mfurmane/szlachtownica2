package priv.mfurmane.szlachtownica.model.simulation.population;

import java.util.HashMap;
import java.util.Map;

public class Affiliation {
    private AffiliationType type;
    private Long group;

    public Affiliation(AffiliationType type, Long group) {
        this.type = type;
        this.group = group;
    }

    public Affiliation() {}

    public AffiliationType getType() {
        return type;
    }

    public Affiliation setType(AffiliationType type) {
        this.type = type;
        return this;
    }

    public Long getGroup() {
        return group;
    }

    public Affiliation setGroup(Long group) {
        this.group = group;
        return this;
    }
}
