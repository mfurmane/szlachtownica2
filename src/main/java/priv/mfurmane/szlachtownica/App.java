package priv.mfurmane.szlachtownica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    static {
        // Włącz robustny silnik nakładania geometrii JTS (OverlayNG). Domyślny w
        // JTS 1.18 stary OverlayOp rzuca TopologyException "non-noded intersection"
        // na geometriach z niemal pokrywającymi się krawędziami (efekt naturalizacji
        // granic). OverlayNG noduje odpornie i naprawia ten przypadek. Musi być
        // ustawione zanim GeometryOverlay zostanie zainicjalizowany.
        System.setProperty("jts.overlay", "ng");
    }

    public static void main(String[] args) {
        System.setProperty("jts.overlay", "ng");
        SpringApplication.run(App.class, args);
    }
}