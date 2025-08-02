//package priv.mfurmane.szlachtownica.model;
//
//import priv.mfurmane.szlachtownica.engine.MainEngine;
//import priv.mfurmane.szlachtownica.engine.events.CalendarEvent;
//import priv.mfurmane.szlachtownica.engine.matrimonial.MatrimonialPhase;
//
//import java.util.*;
//
//public class Person {
//    private static long idCounter;
//    private final MainEngine engine;
//
//    protected Person(MainEngine engine) {
//        this.engine = engine;
//    }
//
//    private BasicInfo basicInfo;
//    private Relations relations;
//    private Info info;
//    private Factors factors;
//    private Controls controls;
//    private MatrimonialPhase matrimonialPhase;
//
//    public static long getIdCounter() {
//        return idCounter;
//    }
//
//    public static void setIdCounter(long idCounter) {
//        Person.idCounter = idCounter;
//    }
//
//    public BasicInfo getBasicInfo() {
//        return basicInfo;
//    }
//
//    public void setBasicInfo(BasicInfo basicInfo) {
//        this.basicInfo = basicInfo;
//    }
//
//    public Relations getRelations() {
//        return relations;
//    }
//
//    public void setRelations(Relations relations) {
//        this.relations = relations;
//    }
//
//    public Info getInfo() {
//        return info;
//    }
//
//    public void setInfo(Info info) {
//        this.info = info;
//    }
//
//    public Factors getFactors() {
//        return factors;
//    }
//
//    public void setFactors(Factors factors) {
//        this.factors = factors;
//    }
//
//    public Controls getControls() {
//        return controls;
//    }
//
//    public void setControls(Controls controls) {
//        this.controls = controls;
//    }
//
//    public MatrimonialPhase getMatrimonialPhase() {
//        return matrimonialPhase;
//    }
//
//    public void setMatrimonialPhase(MatrimonialPhase matrimonialPhase) {
//        this.matrimonialPhase = matrimonialPhase;
//    }
//
//    public MainEngine getEngine() {
//        return engine;
//    }
//
//    public static class BasicInfo {
//        private long id = idCounter++;
//
//        private Race race;
//        private Sex sex;
//        private Calendar born;
//        private Calendar died;
//        private Calendar mourningEnd;
//        private String name;
//        private String middleName;
//        private String plebsSurname; //?
//        private boolean plebs = false;
//
//        public long getId() {
//            return id;
//        }
//
//        public Race getRace() {
//            return race;
//        }
//
//        public void setRace(Race race) {
//            this.race = race;
//        }
//
//        public Sex getSex() {
//            return sex;
//        }
//
//        public void setSex(Sex sex) {
//            this.sex = sex;
//        }
//
//        public Calendar getBorn() {
//            return born;
//        }
//
//        public void setBorn(Calendar born) {
//            this.born = born;
//        }
//
//        public Calendar getDied() {
//            return died;
//        }
//
//        public void setDied(Calendar died) {
//            this.died = died;
//        }
//
//        public Calendar getMourningEnd() {
//            return mourningEnd;
//        }
//
//        public void setMourningEnd(Calendar mourningEnd) {
//            this.mourningEnd = mourningEnd;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getMiddleName() {
//            return middleName;
//        }
//
//        public void setMiddleName(String middleName) {
//            this.middleName = middleName;
//        }
//
//        public String getPlebsSurname() {
//            return plebsSurname;
//        }
//
//        public void setPlebsSurname(String plebsSurname) {
//            this.plebsSurname = plebsSurname;
//        }
//
//        public boolean isPlebs() {
//            return plebs;
//        }
//
//        public void setPlebs(boolean plebs) {
//            this.plebs = plebs;
//        }
//    }
//
//    public static class Relations {
//        private Family family;
//        private Family parentsFamily;
//        private Person mother;
//        private Person father;
//        private Person officialFather;
//        private List<Relationship> relationships = new ArrayList<>();
//        private Map<Person, List<Calendar>> ons = new HashMap<>();
//        private List<Person> knownKids = new ArrayList<>();
//
//        public Family getFamily() {
//            return family;
//        }
//
//        public void setFamily(Family family) {
//            this.family = family;
//        }
//
//        public Family getParentsFamily() {
//            return parentsFamily;
//        }
//
//        public void setParentsFamily(Family parentsFamily) {
//            this.parentsFamily = parentsFamily;
//        }
//
//        public Person getMother() {
//            return mother;
//        }
//
//        public void setMother(Person mother) {
//            this.mother = mother;
//        }
//
//        public Person getFather() {
//            return father;
//        }
//
//        public void setFather(Person father) {
//            this.father = father;
//        }
//
//        public Person getOfficialFather() {
//            return officialFather;
//        }
//
//        public void setOfficialFather(Person officialFather) {
//            this.officialFather = officialFather;
//        }
//
//        public List<Relationship> getRelationships() {
//            return relationships;
//        }
//
//        public void setRelationships(List<Relationship> relationships) {
//            this.relationships = relationships;
//        }
//
//        public Map<Person, List<Calendar>> getOns() {
//            return ons;
//        }
//
//        public void setOns(Map<Person, List<Calendar>> ons) {
//            this.ons = ons;
//        }
//
//        public List<Person> getKnownKids() {
//            return knownKids;
//        }
//
//        public void setKnownKids(List<Person> knownKids) {
//            this.knownKids = knownKids;
//        }
//    }
//
//    public static class Controls {
//        private List<CalendarEvent> events = new ArrayList<>();
//        private int pregnantCounter = 0;
//        private Person childFather;
//
//        public List<CalendarEvent> getEvents() {
//            return events;
//        }
//
//        public Controls setEvents(List<CalendarEvent> events) {
//            this.events = events;
//            return this;
//        }
//
//        public int getPregnantCounter() {
//            return pregnantCounter;
//        }
//
//        public Controls setPregnantCounter(int pregnantCounter) {
//            this.pregnantCounter = pregnantCounter;
//            return this;
//        }
//
//        public Person getChildFather() {
//            return childFather;
//        }
//
//        public Controls setChildFather(Person childFather) {
//            this.childFather = childFather;
//            return this;
//        }
//    }
//
//    public static class Info {
//        private int age;
//        private double importance;
//        private double membershipStrength;
//        private double lineImportance;
//        private int hornyAge;
//        private int stableAge;
//        private int marriageAge;
//        private int mourningTime;
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public double getImportance() {
//            return importance;
//        }
//
//        public void setImportance(double importance) {
//            this.importance = importance;
//        }
//
//        public double getMembershipStrength() {
//            return membershipStrength;
//        }
//
//        public void setMembershipStrength(double membershipStrength) {
//            this.membershipStrength = membershipStrength;
//        }
//
//        public double getLineImportance() {
//            return lineImportance;
//        }
//
//        public void setLineImportance(double lineImportance) {
//            this.lineImportance = lineImportance;
//        }
//
//        public int getHornyAge() {
//            return hornyAge;
//        }
//
//        public void setHornyAge(int hornyAge) {
//            this.hornyAge = hornyAge;
//        }
//
//        public int getStableAge() {
//            return stableAge;
//        }
//
//        public void setStableAge(int stableAge) {
//            this.stableAge = stableAge;
//        }
//
//        public int getMarriageAge() {
//            return marriageAge;
//        }
//
//        public void setMarriageAge(int marriageAge) {
//            this.marriageAge = marriageAge;
//        }
//
//        public int getMourningTime() {
//            return mourningTime;
//        }
//
//        public void setMourningTime(int mourningTime) {
//            this.mourningTime = mourningTime;
//        }
//    }
//
//    public static class Factors {
//        private double travel;
//        private double horny;
//        private double loyal;
//        private double homo;
//        private double interracial;
//        private double attachment;
//        private double poliamoric;
//        private double jealous;
//        private double impulsive;
//        private double proud;
//        private double amorous;
//        private double divorcable;
//        private double revengous;
//
//        public double getTravel() {
//            return travel;
//        }
//
//        public void setTravel(double travel) {
//            this.travel = travel;
//        }
//
//        public double getHorny() {
//            return horny;
//        }
//
//        public void setHorny(double horny) {
//            this.horny = horny;
//        }
//
//        public double getLoyal() {
//            return loyal;
//        }
//
//        public void setLoyal(double loyal) {
//            this.loyal = loyal;
//        }
//
//        public double getHomo() {
//            return homo;
//        }
//
//        public void setHomo(double homo) {
//            this.homo = homo;
//        }
//
//        public double getInterracial() {
//            return interracial;
//        }
//
//        public void setInterracial(double interracial) {
//            this.interracial = interracial;
//        }
//
//        public double getAttachment() {
//            return attachment;
//        }
//
//        public void setAttachment(double attachment) {
//            this.attachment = attachment;
//        }
//
//        public double getPoliamoric() {
//            return poliamoric;
//        }
//
//        public void setPoliamoric(double poliamoric) {
//            this.poliamoric = poliamoric;
//        }
//
//        public double getJealous() {
//            return jealous;
//        }
//
//        public void setJealous(double jealous) {
//            this.jealous = jealous;
//        }
//
//        public double getImpulsive() {
//            return impulsive;
//        }
//
//        public void setImpulsive(double impulsive) {
//            this.impulsive = impulsive;
//        }
//
//        public double getProud() {
//            return proud;
//        }
//
//        public void setProud(double proud) {
//            this.proud = proud;
//        }
//
//        public double getAmorous() {
//            return amorous;
//        }
//
//        public void setAmorous(double amorous) {
//            this.amorous = amorous;
//        }
//
//        public double getDivorcable() {
//            return divorcable;
//        }
//
//        public void setDivorcable(double divorcable) {
//            this.divorcable = divorcable;
//        }
//
//        public double getRevengous() {
//            return revengous;
//        }
//
//        public void setRevengous(double revengous) {
//            this.revengous = revengous;
//        }
//    }
//
//}
