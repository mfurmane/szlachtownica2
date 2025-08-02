package priv.mfurmane.szlachtownica.model.simulation;

import priv.mfurmane.szlachtownica.engine.events.CalendarEvent;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.main.ModelPerson;
import priv.mfurmane.szlachtownica.model.simulation.goal.Goal;

import java.time.LocalDate;
import java.util.*;

public class SimulationPerson {
    public static final Random random = new Random();
    private ModelPerson model;
    private ConfigurationPerson conf;

    private LocalDate mourningEnd;
    private long parentsFamilyId;
    private final Map<Long, LocalDate> ons = new HashMap<>();
    private final List<CalendarEvent> events = new ArrayList<>();
    private Integer pregnantCounter;
    private Long childFatherId;
    // cele i ich siła
    private final Map<Goal, Integer> goals = new HashMap<>();

    private final List<Long> ownedPlaces = new ArrayList<>();
    private final List<Long> ownedRegions = new ArrayList<>();

    public List<Long> getOwnedPlaces() {
        return ownedPlaces;
    }

    public List<Long> getOwnedRegions() {
        return ownedRegions;
    }

    public Map<Goal, Integer> getGoals() {
        return goals;
    }

    public ModelPerson getModel() {
        return model;
    }

    public SimulationPerson setModel(ModelPerson model) {
        this.model = model;
        return this;
    }

    public ConfigurationPerson getConf() {
        return conf;
    }

    public SimulationPerson setConf(ConfigurationPerson conf) {
        this.conf = conf;
        return this;
    }

    public LocalDate getMourningEnd() {
        return mourningEnd;
    }

    public SimulationPerson setMourningEnd(LocalDate mourningEnd) {
        this.mourningEnd = mourningEnd;
        return this;
    }

    public long getParentsFamilyId() {
        return parentsFamilyId;
    }

    public SimulationPerson setParentsFamilyId(long parentsFamilyId) {
        this.parentsFamilyId = parentsFamilyId;
        return this;
    }

    public Map<Long, LocalDate> getOns() {
        return ons;
    }

    public List<CalendarEvent> getEvents() {
        return events;
    }

    public Integer getPregnantCounter() {
        return pregnantCounter;
    }

    public SimulationPerson setPregnantCounter(Integer pregnantCounter) {
        this.pregnantCounter = pregnantCounter;
        return this;
    }

    public Long getChildFatherId() {
        return childFatherId;
    }

    public SimulationPerson setChildFatherId(Long childFatherId) {
        this.childFatherId = childFatherId;
        return this;
    }

    public void initialize() {
        model.initialize();
        prepareInbornGoals();

    }

    private void prepareInbornGoals() {
        int count = random.nextInt(2, 4);
        goals.clear();
        for (int i = 0; i < count; i++) {
            accumulateWeightedGoals().forEach(goal ->
            goals.merge(goal, 1, Integer::sum));
        }
    }

    private List<Goal> accumulateWeightedGoals() {
        Map<Goal, Integer> chances = new HashMap<>();
        determine(conf.getHorny(), chances, new Goal[]{Goal.FREEDOM, Goal.FUN, Goal.LUST, Goal.LUST, Goal.LOVE, Goal.REPRODUCTION});
        determine(conf.getLoyal(), chances, new Goal[]{Goal.CHILDREN_STATUS, Goal.CHILDREN_WEALTH, Goal.STABILIZATION, Goal.LOVE, Goal.BELONGING,Goal.DYNASTY_SURVIVE});
        determine(conf.getHomo(), chances, new Goal[]{});
        determine(conf.getInterracial(), chances, new Goal[]{Goal.FREEDOM, Goal.INDEPENDENCE});
        determine(conf.getAttachment(), chances, new Goal[]{Goal.LOVE, Goal.SECURITY, Goal.BELONGING, Goal.CALMNESS, Goal.ATTENTION});
        determine(conf.getAmorous(), chances, new Goal[]{Goal.LOVE, Goal.BELONGING, Goal.LUST, Goal.ATTENTION});
        determine(conf.getPoliamoric(), chances, new Goal[]{Goal.LUST, Goal.INFLUENCE, Goal.REPRODUCTION, Goal.FUN, Goal.ATTENTION, Goal.FREEDOM, Goal.INDEPENDENCE});
        determine(conf.getJealous(), chances, new Goal[]{Goal.PRESTIGE, Goal.RESPECT, Goal.CONTROLLED_IMAGE, Goal.CONTROL, Goal.STABILIZATION, Goal.SECURITY, Goal.BELONGING, Goal.BLOODLINE_PURITY, Goal.LEGACY, Goal.CALMNESS, Goal.ATTENTION});
        determine(conf.getImpulsive(), chances, new Goal[]{Goal.CHAOS, Goal.RESPECT, Goal.CONTROLLED_IMAGE, Goal.DESTABILIZATION, Goal.LUST, Goal.REPRODUCTION, Goal.LEGACY, Goal.PERSONAL_SURVIVE, Goal.FUN, Goal.ATTENTION, Goal.FREEDOM, Goal.INDEPENDENCE});
        determine(conf.getTemperament(), chances, new Goal[]{Goal.CHAOS, Goal.CHAOS, Goal.RECOGNITION, Goal.INFLUENCE, Goal.INDEPENDENCE, Goal.CONTROL, Goal.DESTABILIZATION, Goal.LUST, Goal.KNOWLEDGE, Goal.POWER, Goal.LEGACY, Goal.ATTENTION});
        determine(conf.getProud(), chances, new Goal[]{Goal.LUXURY, Goal.CHILDREN_STATUS, Goal.PRESTIGE, Goal.RESPECT, Goal.RECOGNITION, Goal.CONTROLLED_IMAGE, Goal.POWER, Goal.CONTROL, Goal.DYNASTY_SURVIVE, Goal.BLOODLINE_PURITY, Goal.KNOWLEDGE, Goal.LEGACY, Goal.FAITH});
        determine(conf.getParanoid(), chances, new Goal[]{Goal.CHILDREN_WEALTH, Goal.CONTROLLED_IMAGE, Goal.STABILIZATION, Goal.SECURITY, Goal.BELONGING, Goal.DYNASTY_SURVIVE, Goal.PERSONAL_SURVIVE, Goal.PERSONAL_SURVIVE, Goal.CALMNESS});
        determine(conf.getAmbition(), chances, new Goal[]{Goal.GREED, Goal.LUXURY, Goal.CHILDREN_WEALTH, Goal.CHILDREN_STATUS, Goal.PRESTIGE, Goal.RESPECT, Goal.RECOGNITION, Goal.CONTROLLED_IMAGE, Goal.POWER, Goal.POWER, Goal.CONTROL, Goal.DESTABILIZATION, Goal.REPRODUCTION, Goal.LEGACY, Goal.FAITH, Goal.ATTENTION, Goal.INDEPENDENCE});
        determine(conf.getDivorcable(), chances, new Goal[]{Goal.PRESTIGE, Goal.RESPECT, Goal.CONTROLLED_IMAGE, Goal.CONTROL, Goal.BLOODLINE_PURITY, Goal.LEGACY, Goal.FAITH, Goal.CALMNESS});
        determine(conf.getRevengous(), chances, new Goal[]{Goal.PRESTIGE, Goal.RESPECT, Goal.RECOGNITION, Goal.POWER, Goal.CONTROL, Goal.BELONGING, Goal.FAITH, Goal.PERSONAL_SURVIVE});
        determine(conf.getHonor(), chances, new Goal[]{Goal.RESPECT, Goal.CONTROLLED_IMAGE, Goal.STABILIZATION, Goal.SECURITY, Goal.BLOODLINE_PURITY, Goal.KNOWLEDGE, Goal.LEGACY, Goal.FAITH});
        determine(conf.getDiplomatic(), chances, new Goal[]{Goal.RESPECT, Goal.INFLUENCE, Goal.CONTROL, Goal.STABILIZATION, Goal.SECURITY, Goal.CALMNESS});
        determine(conf.getManipulative(), chances, new Goal[]{Goal.CHAOS, Goal.GREED, Goal.PRESTIGE, Goal.POWER, Goal.INFLUENCE, Goal.CONTROL, Goal.DESTABILIZATION, Goal.LEGACY, Goal.PERSONAL_SURVIVE, Goal.INDEPENDENCE});
        determine(conf.getCunning(), chances, new Goal[]{Goal.POWER, Goal.INFLUENCE, Goal.KNOWLEDGE, Goal.FAITH, Goal.RESPECT, Goal.RECOGNITION, Goal.PERSONAL_SURVIVE, Goal.ATTENTION, Goal.FREEDOM});
        determine(conf.getMoral(), chances, new Goal[]{Goal.CHILDREN_WEALTH, Goal.RESPECT, Goal.STABILIZATION, Goal.SECURITY, Goal.LOVE, Goal.BELONGING, Goal.KNOWLEDGE, Goal.FAITH, Goal.CALMNESS});
        determine(conf.getCharisma(), chances, new Goal[]{Goal.PRESTIGE, Goal.RESPECT, Goal.RECOGNITION, Goal.CONTROLLED_IMAGE, Goal.INFLUENCE, Goal.CALMNESS, Goal.FUN, Goal.ATTENTION, Goal.FREEDOM});
        determine(conf.getIntellect(), chances, new Goal[]{Goal.CONTROLLED_IMAGE, Goal.POWER, Goal.INFLUENCE, Goal.CONTROL, Goal.KNOWLEDGE, Goal.KNOWLEDGE, Goal.INDEPENDENCE, Goal.FREEDOM});
        determine(conf.getBeauty(), chances, new Goal[]{Goal.GREED, Goal.LUXURY, Goal.PRESTIGE, Goal.RESPECT, Goal.RECOGNITION, Goal.INFLUENCE, Goal.CONTROL, Goal.LOVE, Goal.LUST, Goal.BELONGING, Goal.PERSONAL_SURVIVE, Goal.CALMNESS, Goal.SECURITY, Goal.FUN, Goal.ATTENTION, Goal.INDEPENDENCE, Goal.FREEDOM});
        determine(1 - conf.getHealth(), chances, new Goal[]{Goal.STABILIZATION, Goal.SECURITY, Goal.LOVE, Goal.BELONGING, Goal.FAITH, Goal.PERSONAL_SURVIVE, Goal.CALMNESS});
        if (conf.getInfluence() != null) determine(1 - conf.getInfluence(), chances, new Goal[]{Goal.GREED, Goal.LUXURY, Goal.PRESTIGE, Goal.RESPECT, Goal.POWER, Goal.INFLUENCE, Goal.INFLUENCE, Goal.CONTROL, Goal.SECURITY, Goal.LEGACY, Goal.ATTENTION});
        if (conf.getVisibility() != null) determine(1 - conf.getVisibility(), chances, new Goal[]{Goal.INFLUENCE, Goal.PRESTIGE, Goal.BELONGING, Goal.ATTENTION, Goal.ATTENTION});
        //random
        for (int i = 0; i < 1; i++) determine(0.5, chances, new Goal[]{Goal.CHAOS, Goal.GREED, Goal.LUXURY, Goal.PRESTIGE, Goal.RESPECT, Goal.RECOGNITION, Goal.CONTROLLED_IMAGE, Goal.POWER, Goal.INFLUENCE, Goal.CONTROL, Goal.DESTABILIZATION, Goal.STABILIZATION, Goal.SECURITY, Goal.LOVE, Goal.LUST, Goal.BELONGING, Goal.DYNASTY_SURVIVE, Goal.REPRODUCTION, Goal.BLOODLINE_PURITY, Goal.KNOWLEDGE, Goal.LEGACY, Goal.FAITH, Goal.PERSONAL_SURVIVE, Goal.CALMNESS, Goal.FUN, Goal.ATTENTION, Goal.FREEDOM, Goal.INDEPENDENCE});
        if (conf.getPlebs()) {
            chances.remove(Goal.BLOODLINE_PURITY);
            chances.remove(Goal.DYNASTY_SURVIVE);
            determine(0.5, chances, new Goal[]{Goal.PERSONAL_SURVIVE, Goal.SECURITY, Goal.PRESTIGE, Goal.GREED, Goal.POWER});
        }
//        System.out.println("#####################################################################");
//        chances.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue).reversed()).forEach(entry -> {
//            System.out.println(entry.getValue() + ": " + entry.getKey());
//        });
        return chances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey).toList();
//                .sorted(Comparator.comparing(Map.Entry::getValue).reversed()).limit(3)
//                .max(Comparator.comparing(Map.Entry::getValue)).orElseThrow().getKey();
    }

    private void determine(double comparable, Map<Goal, Integer> chances, Goal[] goals) {
        Arrays.stream(goals).forEach(goal -> {
            if (random.nextDouble() < comparable)
                chances.merge(goal,1, Integer::sum);
        });
    }
}
