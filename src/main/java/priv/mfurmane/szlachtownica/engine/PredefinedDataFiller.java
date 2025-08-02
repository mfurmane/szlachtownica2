package priv.mfurmane.szlachtownica.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.exceptions.PersonNotCreatedException;
import priv.mfurmane.szlachtownica.model.PersonFactory;

import java.io.File;

@Component
public class PredefinedDataFiller {
    private MainEngine engine;

    Logger logger = LoggerFactory.getLogger(getClass());

    public void setEngine(MainEngine engine) {
        this.engine = engine;
    }

//    public void fillData() {
//        PersonFactory personFactory = engine.getPersonFactory();
//        EventManager eventManager = engine.getEventManager();
//        PersonBuilder personBuilder;
//
//        File file = new File("");
//        // TAGARA
//        personBuilder = personFactory.createFileBuilder(file);
//        registerPerson(personBuilder, eventManager);
//        // TAGARA HUSBAND
//        personBuilder = personFactory.createBuilder();
//        registerPerson(personBuilder, eventManager);
//    }
//
//    private void registerPerson(PersonBuilder personBuilder, EventManager eventManager) {
//        try {
//            Person tagara = personBuilder.build();
//            tagara.getControls().getEvents().forEach(eventManager::registerFutureEvent);
//        } catch (PersonNotCreatedException e) {
//            logger.error(e.getMessage(), e);
//        }
//    }


}
