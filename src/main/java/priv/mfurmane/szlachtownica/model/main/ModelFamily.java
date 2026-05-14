package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.FamilyType;
import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelFamily {
    private static long idCounter = 1000;

    private long id = idCounter++;
    private FamilyType type;
    private String surname;
    private Race race;
    private ModelPerson nestor;
    public int creationYear;
    public int extinctionYear;
    public int strength;
    public ModelPerson nobilitedBy;
    public ModelFamily senior;

    public List<ModelFamily> vassals = new ArrayList<>();
    public List<ModelFamily> neighbours = new ArrayList<>();
    public List<ModelPerson> knownMembers = new ArrayList<>();

//    private List<Long> membersToRemove = new ArrayList<>();

    private ModelFamily(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.surname = builder.surname;
        this.race = builder.race;
        this.nestor = builder.nestor;
        this.creationYear = builder.creationYear;
        this.extinctionYear = builder.extinctionYear;
        this.strength = builder.strength;
        this.nobilitedBy = builder.nobilitedBy;
        this.senior = builder.senior;
    }

    public long getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize() {

    }

    public void mergeFrom(ModelFamily other) {
        id = other.id;

    }

    public static class Builder {
        private long id = 0;
        private FamilyType type;
        private String surname;
        private Race race;
        private ModelPerson nestor;
        public int creationYear;
        public int extinctionYear;
        public int strength;
        public ModelPerson nobilitedBy;
        public ModelFamily senior;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setType(FamilyType type) {
            this.type = type;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setRace(Race race) {
            this.race = race;
            return this;
        }

        public Builder setNestor(ModelPerson nestor) {
            this.nestor = nestor;
            return this;
        }

        public Builder setCreationYear(int creationYear) {
            this.creationYear = creationYear;
            return this;
        }

        public Builder setExtinctionYear(int extinctionYear) {
            this.extinctionYear = extinctionYear;
            return this;
        }

        public Builder setStrength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder setNobilitedBy(ModelPerson nobilitedBy) {
            this.nobilitedBy = nobilitedBy;
            return this;
        }

        public Builder setSenior(ModelFamily senior) {
            this.senior = senior;
            return this;
        }

        public ModelFamily build() {
            return new ModelFamily(this);
        }

    }
}
