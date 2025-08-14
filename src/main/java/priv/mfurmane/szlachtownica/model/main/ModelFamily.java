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



//    private List<Long> membersToRemove = new ArrayList<>();
    public Long nestor;
    public int strenght;
    public int creationDate;
    public Long senior;
    public List<Long> vassals = new ArrayList<>();
    public List<Long> neighbours = new ArrayList<>();
    public List<Long> knownMembers = new ArrayList<>();

    private ModelFamily(Builder builder) {

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


        public ModelFamily build() {
            return new ModelFamily(this);
        }

    }
}
