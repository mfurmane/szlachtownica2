package priv.mfurmane.szlachtownica.model.simulation.population;

import priv.mfurmane.szlachtownica.model.simulation.goal.Goal;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Long id;
    private String name;
    private GroupType type = GroupType.FAMILY;
    private final List<Goal> goals = new ArrayList<>();
    private final List<Loyalty> loyaltiesToSeniors = new ArrayList<>();
    private final List<Loyalty> loyaltiesToVassals = new ArrayList<>();
    private final List<Loyalty> loyaltiesToAllies = new ArrayList<>();

    public Group(Long id, String name, GroupType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Group(String name, GroupType type) {
        this.name = name;
        this.type = type;
    }

    public Group() {}

    public List<Goal> getGoals() {
        return goals;
    }

    public Long getId() {
        return id;
    }

    public Group setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public GroupType getType() {
        return type;
    }

    public Group setType(GroupType type) {
        this.type = type;
        return this;
    }

    public List<Loyalty> getLoyaltiesToSeniors() {
        return loyaltiesToSeniors;
    }

    public List<Loyalty> getLoyaltiesToVassals() {
        return loyaltiesToVassals;
    }

    public List<Loyalty> getLoyaltiesToAllies() {
        return loyaltiesToAllies;
    }
}
