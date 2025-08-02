package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.Sex;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelPerson {
    private static long idCounter = 1000;
    private long id = idCounter++;
    private Race race;
    private Sex sex;
    private LocalDate born;
    private LocalDate died;
    private String name;
    private String middleName;
    private Long familyId;
    private Long motherId;
    private Long fatherId;
    private Long officialFatherId;
    private final List<Long> relationshipIds = new ArrayList<>();
    private final List<Long> knownKidIds = new ArrayList<>();
    private Double importance;
    private Double membershipStrength;
    private Double lineImportance;

    public long getId() {
        return id;
    }

    public Race getRace() {
        return race;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBorn() {
        return born;
    }

    public LocalDate getDied() {
        return died;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public Long getMotherId() {
        return motherId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public Long getOfficialFatherId() {
        return officialFatherId;
    }

    public List<Long> getRelationshipIds() {
        return relationshipIds;
    }

    public List<Long> getKnownKidIds() {
        return knownKidIds;
    }

    public Double getImportance() {
        return importance;
    }

    public Double getMembershipStrength() {
        return membershipStrength;
    }

    public Double getLineImportance() {
        return lineImportance;
    }

    private ModelPerson(Builder builder) {
        this.race = builder.race;
        this.sex = builder.sex;
        this.born = builder.born;
        this.died = builder.died;
        this.name = builder.name;
        this.middleName = builder.middleName;
        this.familyId = builder.familyId;
        this.motherId = builder.motherId;
        this.fatherId = builder.fatherId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize() {
        this.officialFatherId = determineOfficialFather();
        this.importance = calculateImportance();
        this.membershipStrength = calculateMembership();
        this.lineImportance = calculateLineImportance();

    }

    private Double calculateLineImportance() {
        //TODO
        return null;
    }

    private Double calculateMembership() {
        //TODO
        return null;
    }

    private Double calculateImportance() {
        //TODO
        return null;
    }

    private Long determineOfficialFather() {
        //TODO
        return null;
    }

    public void mergeFrom(ModelPerson other) {
        id = other.id;
        if (other.race != null) race = other.race;
        if (other.sex != null) sex = other.sex;
        if (other.born != null) born = other.born;
        if (other.died != null) died = other.died;
        if (other.name != null) name = other.name;
        if (other.middleName != null) middleName = other.middleName;
        if (other.familyId != null) familyId = other.familyId;
        if (other.motherId != null) motherId = other.motherId;
        if (other.fatherId != null) fatherId = other.fatherId;
        if (other.officialFatherId != null) officialFatherId = other.officialFatherId;
//        private final List<Long> relationshipIds = new ArrayList<>();
//        private final List<Long> knownKidIds = new ArrayList<>();
        if (other.importance != null) importance = other.importance;
        if (other.membershipStrength != null) membershipStrength = other.membershipStrength;
        if (other.lineImportance != null) lineImportance = other.lineImportance;
    }

    public static class Builder {
        private Race race;
        private Sex sex;
        private LocalDate born;
        private LocalDate died;
        private String name;
        private String middleName;
        private Long familyId;
        private Long motherId;
        private Long fatherId;

        public Builder setRace(Race race) {
            this.race = race;
            return this;
        }

        public Builder setSex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public Builder setBorn(LocalDate born) {
            this.born = born;
            return this;
        }

        public Builder setDied(LocalDate died) {
            this.died = died;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setFamilyId(Long familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder setMotherId(Long motherId) {
            this.motherId = motherId;
            return this;
        }

        public Builder setFatherId(Long fatherId) {
            this.fatherId = fatherId;
            return this;
        }

        public ModelPerson build() {
            return new ModelPerson(this);
        }
        //        private Long officialFatherId;
//        private Double importance;
//        private Double membershipStrength;
//        private Double lineImportance;
    }
}
