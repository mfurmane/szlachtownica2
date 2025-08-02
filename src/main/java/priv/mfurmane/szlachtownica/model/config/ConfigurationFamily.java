package priv.mfurmane.szlachtownica.model.config;

public class ConfigurationFamily {
// age and mourning
    private Boolean plebs;
    private String plebsSurname;
    private Integer hornyAge;
    private Integer stableAge;
    private Integer marriageAge;
    private Integer mourningTime;
// sexual style
    private Double horny;
    private Double loyal;
    private Double homo;
    private Double interracial;
    private Double attachment;
    private Double amorous;
    private Double poliamoric;
// character
    private Double jealous;
    private Double impulsive;
    private Double temperament; // impulsive?
    private Double proud;
    private Double paranoid; //skłonność do paniki/uderzeń wyprzedzających
    private Double ambition;  //czy postać będzie próbować zdobyć władzę/pozycję
    private Double divorcable;
    private Double revengous;
    private Double honor; // do wpływu na decyzje o ślubie, zemście, lojalności
    private Double diplomatic;
    private Double manipulative; //chęci mącenia
    private Double cunning; //przebiegłość
    private Double moral;
    private Double charisma; //zdobywanie sympatii
    private Double intellect;
    private Double beauty;
// biology & society
    private Double fertility;
    private Double health;
    private Double influence; //umiejętność kreowania rzeczywistości
    private Double visibility; //  czy ktoś jest znany publicznie (np. królowa matka vs kochanka)

    public static Builder builder() {
        return new ConfigurationFamily.Builder();
    }

    public ConfigurationFamily(){}

    private ConfigurationFamily(Builder builder){
        this.plebs = builder.plebs;
        this.plebsSurname = builder.plebsSurname;
        this.hornyAge = builder.hornyAge;
        this.stableAge = builder.stableAge;
        this.marriageAge = builder.marriageAge;
        this.mourningTime = builder.mourningTime;
        this.horny = builder.horny;
        this.loyal = builder.loyal;
        this.homo = builder.homo;
        this.interracial = builder.interracial;
        this.attachment = builder.attachment;
        this.amorous = builder.amorous;
        this.poliamoric = builder.poliamoric;
        this.jealous = builder.jealous;
        this.impulsive = builder.impulsive;
        this.temperament = builder.temperament;
        this.proud = builder.proud;
        this.paranoid = builder.paranoid;
        this.ambition = builder.ambition;
        this.divorcable = builder.divorcable;
        this.revengous = builder.revengous;
        this.honor = builder.honor;
        this.diplomatic = builder.diplomatic;
        this.manipulative = builder.manipulative;
        this.cunning = builder.cunning;
        this.moral = builder.moral;
        this.charisma = builder.charisma;
        this.intellect = builder.intellect;
        this.beauty = builder.beauty;
        this.fertility = builder.fertility;
        this.health = builder.health;
        this.influence = builder.influence;
        this.visibility = builder.visibility;
    }

    public Boolean getPlebs() {
        return plebs;
    }

    public String getPlebsSurname() {
        return plebsSurname;
    }

    public Integer getHornyAge() {
        return hornyAge;
    }

    public Integer getStableAge() {
        return stableAge;
    }

    public Integer getMarriageAge() {
        return marriageAge;
    }

    public Integer getMourningTime() {
        return mourningTime;
    }

    public Double getHorny() {
        return horny;
    }

    public Double getLoyal() {
        return loyal;
    }

    public Double getHomo() {
        return homo;
    }

    public Double getInterracial() {
        return interracial;
    }

    public Double getAttachment() {
        return attachment;
    }

    public Double getAmorous() {
        return amorous;
    }

    public Double getPoliamoric() {
        return poliamoric;
    }

    public Double getJealous() {
        return jealous;
    }

    public Double getImpulsive() {
        return impulsive;
    }

    public Double getTemperament() {
        return temperament;
    }

    public Double getProud() {
        return proud;
    }

    public Double getParanoid() {
        return paranoid;
    }

    public Double getAmbition() {
        return ambition;
    }

    public Double getDivorcable() {
        return divorcable;
    }

    public Double getRevengous() {
        return revengous;
    }

    public Double getHonor() {
        return honor;
    }

    public Double getDiplomatic() {
        return diplomatic;
    }

    public Double getManipulative() {
        return manipulative;
    }

    public Double getCunning() {
        return cunning;
    }

    public Double getMoral() {
        return moral;
    }

    public Double getCharisma() {
        return charisma;
    }

    public Double getIntellect() {
        return intellect;
    }

    public Double getBeauty() {
        return beauty;
    }

    public Double getFertility() {
        return fertility;
    }

    public Double getHealth() {
        return health;
    }

    public Double getInfluence() {
        return influence;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void mergeFrom(ConfigurationFamily other) {
        if (other.plebs != null) this.plebs = other.plebs;
        if (other.plebsSurname != null) this.plebsSurname = other.plebsSurname;
        if (other.hornyAge != null) this.hornyAge = other.hornyAge;
        if (other.stableAge != null) this.stableAge = other.stableAge;
        if (other.marriageAge != null) this.marriageAge = other.marriageAge;
        if (other.mourningTime != null) this.mourningTime = other.mourningTime;
        if (other.horny != null) this.horny = other.horny;
        if (other.loyal != null) this.loyal = other.loyal;
        if (other.homo != null) this.homo = other.homo;
        if (other.interracial != null) this.interracial = other.interracial;
        if (other.attachment != null) this.attachment = other.attachment;
        if (other.amorous != null) this.amorous = other.amorous;
        if (other.poliamoric != null) this.poliamoric = other.poliamoric;
        if (other.jealous != null) this.jealous = other.jealous;
        if (other.impulsive != null) this.impulsive = other.impulsive;
        if (other.temperament != null) this.temperament = other.temperament;
        if (other.proud != null) this.proud = other.proud;
        if (other.paranoid != null) this.paranoid = other.paranoid;
        if (other.ambition != null) this.ambition = other.ambition;
        if (other.divorcable != null) this.divorcable = other.divorcable;
        if (other.revengous != null) this.revengous = other.revengous;
        if (other.honor != null) this.honor = other.honor;
        if (other.diplomatic != null) this.diplomatic = other.diplomatic;
        if (other.manipulative != null) this.manipulative = other.manipulative;
        if (other.cunning != null) this.cunning = other.cunning;
        if (other.moral != null) this.moral = other.moral;
        if (other.charisma != null) this.charisma = other.charisma;
        if (other.intellect != null) this.intellect = other.intellect;
        if (other.beauty != null) this.beauty = other.beauty;
        if (other.fertility != null) this.fertility = other.fertility;
        if (other.health != null) this.health = other.health;
        if (other.influence != null) this.influence = other.influence;
        if (other.visibility != null) this.visibility = other.visibility;
    }

    public static class Builder {
        public Boolean plebs = false;
        public String plebsSurname;
        // age and mourning
        private Integer hornyAge;
        private Integer stableAge;
        private Integer marriageAge;
        private Integer mourningTime;
        // sexual style
        private Double horny;
        private Double loyal;
        private Double homo;
        private Double interracial;
        private Double attachment;
        private Double amorous;
        private Double poliamoric;
        // character
        private Double jealous;
        private Double impulsive;
        private Double temperament; // impulsive?
        private Double proud;
        private Double paranoid; //skłonność do paniki/uderzeń wyprzedzających
        private Double ambition;  //czy postać będzie próbować zdobyć władzę/pozycję
        private Double divorcable;
        private Double revengous;
        private Double honor; // do wpływu na decyzje o ślubie, zemście, lojalności
        private Double diplomatic;
        private Double manipulative; //chęci mącenia
        private Double cunning; //przebiegłość
        private Double moral;
        private Double charisma; //zdobywanie sympatii
        private Double intellect;
        private Double beauty;
        // biology & society
        private Double fertility;
        private Double health;
        private Double influence; //umiejętność kreowania rzeczywistości
        private Double visibility; //  czy ktoś jest znany publicznie (np. królowa matka vs kochanka)

        public Builder setPlebs(Boolean plebs) {
            this.plebs = plebs;
            return this;
        }

        public Builder setPlebsSurname(String plebsSurname) {
            this.plebsSurname = plebsSurname;
            return this;
        }

        public Builder setHornyAge(Integer hornyAge) {
            this.hornyAge = hornyAge;
            return this;
        }

        public Builder setStableAge(Integer stableAge) {
            this.stableAge = stableAge;
            return this;
        }

        public Builder setMarriageAge(Integer marriageAge) {
            this.marriageAge = marriageAge;
            return this;
        }

        public Builder setMourningTime(Integer mourningTime) {
            this.mourningTime = mourningTime;
            return this;
        }

        public Builder setHorny(Double horny) {
            this.horny = horny;
            return this;
        }

        public Builder setLoyal(Double loyal) {
            this.loyal = loyal;
            return this;
        }

        public Builder setHomo(Double homo) {
            this.homo = homo;
            return this;
        }

        public Builder setInterracial(Double interracial) {
            this.interracial = interracial;
            return this;
        }

        public Builder setAttachment(Double attachment) {
            this.attachment = attachment;
            return this;
        }

        public Builder setAmorous(Double amorous) {
            this.amorous = amorous;
            return this;
        }

        public Builder setPoliamoric(Double poliamoric) {
            this.poliamoric = poliamoric;
            return this;
        }

        public Builder setJealous(Double jealous) {
            this.jealous = jealous;
            return this;
        }

        public Builder setImpulsive(Double impulsive) {
            this.impulsive = impulsive;
            return this;
        }

        public Builder setTemperament(Double temperament) {
            this.temperament = temperament;
            return this;
        }

        public Builder setProud(Double proud) {
            this.proud = proud;
            return this;
        }

        public Builder setParanoid(Double paranoid) {
            this.paranoid = paranoid;
            return this;
        }

        public Builder setAmbition(Double ambition) {
            this.ambition = ambition;
            return this;
        }

        public Builder setDivorcable(Double divorcable) {
            this.divorcable = divorcable;
            return this;
        }

        public Builder setRevengous(Double revengous) {
            this.revengous = revengous;
            return this;
        }

        public Builder setHonor(Double honor) {
            this.honor = honor;
            return this;
        }

        public Builder setDiplomatic(Double diplomatic) {
            this.diplomatic = diplomatic;
            return this;
        }

        public Builder setManipulative(Double manipulative) {
            this.manipulative = manipulative;
            return this;
        }

        public Builder setCunning(Double cunning) {
            this.cunning = cunning;
            return this;
        }

        public Builder setMoral(Double moral) {
            this.moral = moral;
            return this;
        }

        public Builder setCharisma(Double charisma) {
            this.charisma = charisma;
            return this;
        }

        public Builder setIntellect(Double intellect) {
            this.intellect = intellect;
            return this;
        }

        public Builder setBeauty(Double beauty) {
            this.beauty = beauty;
            return this;
        }

        public Builder setFertility(Double fertility) {
            this.fertility = fertility;
            return this;
        }

        public Builder setHealth(Double health) {
            this.health = health;
            return this;
        }

        public Builder setInfluence(Double influence) {
            this.influence = influence;
            return this;
        }

        public Builder setVisibility(Double visibility) {
            this.visibility = visibility;
            return this;
        }

        public ConfigurationFamily build() {
            return new ConfigurationFamily(this);
        }
    }


}
