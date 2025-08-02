package priv.mfurmane.szlachtownica.engine;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.events.CalendarEvent;
import priv.mfurmane.szlachtownica.engine.events.DeathEvent;
import priv.mfurmane.szlachtownica.engine.events.KidEvent;
import priv.mfurmane.szlachtownica.engine.events.MarriageEvent;
import priv.mfurmane.szlachtownica.model.config.FromFilePerson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class EventManager {
    private MainEngine engine;
    List<CalendarEvent> knownEvents = new ArrayList<>();

    public void registerFutureEvent(CalendarEvent event) {
        knownEvents.add(event);
    }

    public void runEvent(CalendarEvent event) {
        event.act();
    }

    public void handleDate(Calendar date) {
        List<CalendarEvent> toDelete = new ArrayList<>();
        knownEvents.forEach(event -> {
            if (event.getTime().after(date)) {
                event.act();
                toDelete.add(event);
            }
        });
        toDelete.forEach(event -> knownEvents.remove(event));
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void registerEventsFor(FromFilePerson fromFile) {
        //TODO
        fromFile.getModel().getKnownKidIds().forEach(kid -> {
//            registerFutureEvent(KidEvent);
        });
        fromFile.getModel().getRelationshipIds().forEach(rel -> {
//            registerFutureEvent(MarriageEvent);
        });
        if (fromFile.getModel().getDied() != null) {
//            registerFutureEvent(DeathEvent);
        }
    }
}
