//package priv.mfurmane.szlachtownica.model;
//
//import priv.mfurmane.szlachtownica.engine.MainEngine;
//import priv.mfurmane.szlachtownica.engine.events.CalendarEvent;
//import priv.mfurmane.szlachtownica.exceptions.PersonNotCreatedException;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//public class PersonBuilder {
//    private final PersonFactory factory;
//    private final MainEngine engine;
//
//    private Race race = null;
//    private Sex sex = null;
//    private Calendar born = null;
//    private Calendar died = null;
//    private Calendar mourningEnd = null;
//    private String name = null;
//    private String middleName = null;
//    private String plebsSurname = null;
//    private Boolean plebs = null;
//    private Family family = null;
//    private Family parentsFamily = null;
//    private Person mother = null;
//    private Person father = null;
//    private Person officialFather = null;
//    private List<Relationship> relationships = new ArrayList<>();
//    private Map<Person, List<Calendar>> ons = new HashMap<>();
//    private List<Person> knownKids = new ArrayList<>();
//    private List<CalendarEvent> events = new ArrayList<>();
//    private Integer pregnantCounter = null;
//    private Person childFather = null;
//    private Integer age = null;
//    private Double importance = null;
//    private Double membershipStrength = null;
//    private Double lineImportance = null;
//    private Integer hornyAge = null;
//    private Integer stableAge = null;
//    private Integer marriageAge = null;
//    private Integer mourningTime = null;
//    private Double travel = null;
//    private Double horny = null;
//    private Double loyal = null;
//    private Double homo = null;
//    private Double interracial = null;
//    private Double attachment = null;
//    private Double poliamoric = null;
//    private Double jealous = null;
//    private Double impulsive = null;
//    private Double proud = null;
//    private Double amorous = null;
//    private Double divorcable = null;
//    private Double revengous = null;
//
//
//    protected PersonBuilder(MainEngine engine, PersonFactory factory) {
//        this.engine = engine;
//        this.factory = factory;
//    }
//
//    public PersonBuilder(MainEngine engine, PersonFactory factory, File file) {
//        this.engine = engine;
//        this.factory = factory;
//        Properties props = new Properties();
//        try (FileInputStream in = new FileInputStream(file)) {
//            props.load(in);
//            race = engine.getRacesManager().byName(props.getProperty("race"));
//            sex = Sex.valueOf(props.getProperty("sex"));
//            born = null;
//            died = null;
//            mourningEnd = null;
//            name = null;
//            middleName = null;
//            plebsSurname = null;
//            plebs = null;
//            family = null;
//            parentsFamily = null;
//            mother = null;
//            father = null;
//            officialFather = null;
//            pregnantCounter = null;
//            childFather = null;
//            age = null;
//            importance = null;
//            membershipStrength = null;
//            lineImportance = null;
//            hornyAge = null;
//            stableAge = null;
//            marriageAge = null;
//            mourningTime = null;
//            travel = null;
//            horny = null;
//            loyal = null;
//            homo = null;
//            interracial = null;
//            attachment = null;
//            poliamoric = null;
//            jealous = null;
//            impulsive = null;
//            proud = null;
//            amorous = null;
//            divorcable = null;
//            revengous = null;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Person build() throws PersonNotCreatedException {
//        Person person = new Person(engine);
//        person.setBasicInfo(prepareBasicInfo());
//        person.setRelations(prepareRelations());
//        person.setFactors(prepareFactors());
//        person.setControls(prepareControls());
//        person.setInfo(prepareInfo());
//        return person;
//    }
//
//    private Person.BasicInfo prepareBasicInfo() throws PersonNotCreatedException {
//        if (race == null) throw new PersonNotCreatedException();
//        Person.BasicInfo basicInfo = new Person.BasicInfo();
//        basicInfo.setRace(race);
//        if (born != null) basicInfo.setBorn(born);
//        if (died != null) basicInfo.setDied(died);
//        basicInfo.setName(name != null ? name : factory.chooseName(race, sex));
//        basicInfo.setMiddleName(middleName != null ? middleName : factory.chooseMiddleName(race, sex));
//        if (mourningEnd != null) basicInfo.setMourningEnd(mourningEnd);
//        basicInfo.setPlebs(plebs != null ? plebs : false);
//        if (plebsSurname != null) basicInfo.setPlebsSurname(plebsSurname);
//        return basicInfo;
//    }
//
//    private Person.Info prepareInfo() {
//        Person.Info info = new Person.Info();
//        info.setAge(age != null ? age : 0);
//        if (importance != null) info.setImportance(importance);
//        info.setHornyAge(hornyAge != null ? hornyAge : factory.chooseHornyAge(race));
//        if (lineImportance != null) info.setLineImportance(lineImportance);
//        info.setMarriageAge(marriageAge != null ? marriageAge : factory.chooseMarriageAge(race));
//        if (membershipStrength != null) info.setMembershipStrength(membershipStrength);
//        info.setMourningTime(mourningTime != null ? mourningTime : factory.chooseMourningTime(race));
//        info.setStableAge(stableAge != null ? stableAge : factory.chooseStableAge(race));
//        return info;
//    }
//
//    private Person.Controls prepareControls() {
//        Person.Controls controls = new Person.Controls();
//        if (!events.isEmpty()) controls.setEvents(events);
//        if (childFather != null) controls.setChildFather(childFather);
//        if (pregnantCounter != null) controls.setPregnantCounter(pregnantCounter);
//        return  controls;
//    }
//
//    private Person.Factors prepareFactors() {
//        Person.Factors factors = new Person.Factors();
//        factors.setAmorous(amorous != null ? amorous : factory.chooseAmorous(race));
//        factors.setAttachment(attachment != null ? attachment : factory.chooseAttachment(race));
//        factors.setDivorcable(divorcable != null ? divorcable : factory.chooseDivorcable(race));
//        factors.setHomo(homo != null ? homo : factory.chooseHomo(race));
//        factors.setHorny(horny != null ? horny : factory.chooseHorny(race));
//        factors.setImpulsive(impulsive != null ? impulsive : factory.chooseImpulsive(race));
//        factors.setInterracial(interracial != null ? interracial : factory.chooseInterracial(race));
//        factors.setLoyal(loyal != null ? loyal : factory.chooseLoyal(race));
//        factors.setJealous(jealous != null ? jealous : factory.chooseJealous(race));
//        factors.setProud(proud != null ? proud : factory.chooseProud(race));
//        factors.setTravel(travel != null ? travel : factory.chooseTravel(race));
//        factors.setRevengous(revengous != null ? revengous : factory.chooseRevengous(race));
//        factors.setPoliamoric(poliamoric != null ? poliamoric : factory.choosePoliamoric(race));
//        return factors;
//    }
//
//    private Person.Relations prepareRelations() {
//        Person.Relations relations = new Person.Relations();
//        if (family != null) relations.setFamily(family);
//        if (father != null) relations.setFather(father);
//        if (mother != null) relations.setMother(mother);
//        if (officialFather != null) relations.setOfficialFather(officialFather);
//        if (parentsFamily != null) relations.setParentsFamily(parentsFamily);
//        if (!ons.isEmpty()) relations.setOns(ons);
//        if (!knownKids.isEmpty()) relations.setKnownKids(knownKids);
//        if (!relationships.isEmpty()) relations.setRelationships(relationships);
//        return relations;
//    }
//
//    public PersonBuilder setRace(Race race) {
//        this.race = race;
//        return this;
//    }
//
//    public PersonBuilder setSex(Sex sex) {
//        this.sex = sex;
//        return this;
//    }
//
//    public PersonBuilder setBorn(Calendar born) {
//        this.born = born;
//        return this;
//    }
//
//    public PersonBuilder setDied(Calendar died) {
//        this.died = died;
//        return this;
//    }
//
//    public PersonBuilder setMourningEnd(Calendar mourningEnd) {
//        this.mourningEnd = mourningEnd;
//        return this;
//    }
//
//    public PersonBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public PersonBuilder setMiddleName(String middleName) {
//        this.middleName = middleName;
//        return this;
//    }
//
//    public PersonBuilder setPlebsSurname(String plebsSurname) {
//        this.plebsSurname = plebsSurname;
//        return this;
//    }
//
//    public PersonBuilder setPlebs(boolean plebs) {
//        this.plebs = plebs;
//        return this;
//    }
//
//    public PersonBuilder setFamily(Family family) {
//        this.family = family;
//        return this;
//    }
//
//    public PersonBuilder setParentsFamily(Family parentsFamily) {
//        this.parentsFamily = parentsFamily;
//        return this;
//    }
//
//    public PersonBuilder setMother(Person mother) {
//        this.mother = mother;
//        return this;
//    }
//
//    public PersonBuilder setFather(Person father) {
//        this.father = father;
//        return this;
//    }
//
//    public PersonBuilder setOfficialFather(Person officialFather) {
//        this.officialFather = officialFather;
//        return this;
//    }
//
//    public PersonBuilder addRelationship(Relationship relationship) {
//        this.relationships.add(relationship);
//        return this;
//    }
//
//    public PersonBuilder addOns(Person person, Calendar date) {
//        if (!this.ons.containsKey(person))
//            this.ons.put(person, new ArrayList<>());
//        this.ons.get(person).add(date);
//        return this;
//    }
//
//    public PersonBuilder addKnownKid(Person knownKid) {
//        this.knownKids.add(knownKid);
//        return this;
//    }
//
//    public PersonBuilder addEvents(CalendarEvent event) {
//        this.events.add(event);
//        return this;
//    }
//
//    public PersonBuilder setPregnantCounter(int pregnantCounter) {
//        this.pregnantCounter = pregnantCounter;
//        return this;
//    }
//
//    public PersonBuilder setChildFather(Person childFather) {
//        this.childFather = childFather;
//        return this;
//    }
//
//    public PersonBuilder setAge(int age) {
//        this.age = age;
//        return this;
//    }
//
//    public PersonBuilder setImportance(double importance) {
//        this.importance = importance;
//        return this;
//    }
//
//    public PersonBuilder setMembershipStrength(double membershipStrength) {
//        this.membershipStrength = membershipStrength;
//        return this;
//    }
//
//    public PersonBuilder setLineImportance(double lineImportance) {
//        this.lineImportance = lineImportance;
//        return this;
//    }
//
//    public PersonBuilder setHornyAge(int hornyAge) {
//        this.hornyAge = hornyAge;
//        return this;
//    }
//
//    public PersonBuilder setStableAge(int stableAge) {
//        this.stableAge = stableAge;
//        return this;
//    }
//
//    public PersonBuilder setMarriageAge(int marriageAge) {
//        this.marriageAge = marriageAge;
//        return this;
//    }
//
//    public PersonBuilder setMourningTime(int mourningTime) {
//        this.mourningTime = mourningTime;
//        return this;
//    }
//
//    public PersonBuilder setTravelFactor(double travel) {
//        this.travel = travel;
//        return this;
//    }
//
//    public PersonBuilder setHornyFactor(double horny) {
//        this.horny = horny;
//        return this;
//    }
//
//    public PersonBuilder setLoyalFactor(double loyal) {
//        this.loyal = loyal;
//        return this;
//    }
//
//    public PersonBuilder setHomoFactor(double homo) {
//        this.homo = homo;
//        return this;
//    }
//
//    public PersonBuilder setInterracialFactor(double interracial) {
//        this.interracial = interracial;
//        return this;
//    }
//
//    public PersonBuilder setAttachmentFactor(double attachment) {
//        this.attachment = attachment;
//        return this;
//    }
//
//    public PersonBuilder setPoliamoricFactor(double poliamoric) {
//        this.poliamoric = poliamoric;
//        return this;
//    }
//
//    public PersonBuilder setJealousFactor(double jealous) {
//        this.jealous = jealous;
//        return this;
//    }
//
//    public PersonBuilder setImpulsiveFactor(double impulsive) {
//        this.impulsive = impulsive;
//        return this;
//    }
//
//    public PersonBuilder setProudFactor(double proud) {
//        this.proud = proud;
//        return this;
//    }
//
//    public PersonBuilder setAmorousFactor(double amorous) {
//        this.amorous = amorous;
//        return this;
//    }
//
//    public PersonBuilder setDivorcableFactor(double divorcable) {
//        this.divorcable = divorcable;
//        return this;
//    }
//
//    public PersonBuilder setRevengousFactor(double revengous) {
//        this.revengous = revengous;
//        return this;
//    }
//}
