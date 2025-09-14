package priv.mfurmane.szlachtownica.model.simulation.population;

public class GroupRelation {
    private Long group1;
    private Long group2;
    private GroupRelationType type = GroupRelationType.NEUTRAL;
    private Integer strength = 0;

    public GroupRelation() {}

    public GroupRelation(Long group1, Long group2) {
        this.group1 = group1;
        this.group2 = group2;
    }

    public GroupRelation(Long group1, Long group2, GroupRelationType type, Integer strength) {
        this.group1 = group1;
        this.group2 = group2;
        this.type = type;
        this.strength = strength;
    }

    public Long getGroup1() {
        return group1;
    }

    public GroupRelation setGroup1(Long group1) {
        this.group1 = group1;
        return this;
    }

    public Long getGroup2() {
        return group2;
    }

    public GroupRelation setGroup2(Long group2) {
        this.group2 = group2;
        return this;
    }

    public GroupRelationType getType() {
        return type;
    }

    public GroupRelation setType(GroupRelationType type) {
        this.type = type;
        return this;
    }

    public Integer getStrength() {
        return strength;
    }

    public GroupRelation setStrength(Integer strength) {
        this.strength = strength;
        return this;
    }
}
