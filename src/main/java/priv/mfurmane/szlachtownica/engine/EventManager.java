package priv.mfurmane.szlachtownica.engine;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.events.CalendarEvent;
import priv.mfurmane.szlachtownica.model.config.FromFilePerson;

import java.util.Calendar;
import java.util.Comparator;
import java.util.PriorityQueue;

@Component
public class EventManager {
    private MainEngine engine;

    /**
     * Kolejka przyszłych zdarzeń uporządkowana po (czas, kolejność rejestracji).
     * Stabilny tie-break (sequence) daje deterministyczny porządek dla zdarzeń o tej
     * samej dacie — warunek konieczny pod odtwarzalny replay (odbijanie alternatywnych
     * linii czasu z tej samej daty i ziarna).
     */
    private final PriorityQueue<Scheduled> queue = new PriorityQueue<>(
            Comparator.comparing((Scheduled s) -> s.event().getTime())
                    .thenComparingLong(Scheduled::seq)
    );
    private long sequence = 0;

    public void registerFutureEvent(CalendarEvent event) {
        queue.add(new Scheduled(sequence++, event));
    }

    public void runEvent(CalendarEvent event) {
        event.act();
    }

    /**
     * Odpala wszystkie zdarzenia, których czas nastał do (włącznie) podanej daty,
     * w porządku chronologicznym. Zdarzenie może w {@code act()} zaplanować kolejne —
     * jeśli i ono jest już wymagalne względem {@code now}, zostanie obsłużone w tym
     * samym wywołaniu (kaskada tego samego dnia).
     *
     * <p>Poprawia wcześniejszą usterkę: stary warunek {@code getTime().after(date)}
     * odpalał zdarzenia z przyszłości względem daty (odwrotnie), a liniowy skan
     * {@code ArrayList} nie gwarantował chronologii.
     */
    public void handleDate(Calendar now) {
        Scheduled head;
        while ((head = queue.peek()) != null && isDue(head.event(), now)) {
            queue.poll();
            head.event().act();
        }
    }

    /** Najbliższe zaplanowane zdarzenie bez zdejmowania z kolejki (np. do sterowania krokiem). */
    public CalendarEvent peekNext() {
        Scheduled head = queue.peek();
        return head == null ? null : head.event();
    }

    public int pendingCount() {
        return queue.size();
    }

    private boolean isDue(CalendarEvent event, Calendar now) {
        return !event.getTime().after(now); // time <= now
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void registerEventsFor(FromFilePerson fromFile) {
        //TODO: odtworzyć zdarzenia (kids/relacje/śmierć) z danych z pliku
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

    /** Wpis kolejki: zdarzenie + numer rejestracji dla stabilnego, deterministycznego porządku. */
    private record Scheduled(long seq, CalendarEvent event) {
    }
}
