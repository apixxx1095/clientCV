module com.centrovaccinale.centrovaccinale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.rmi;
    requires java.sql;
    requires java.desktop;

    opens com.centrovaccinale.centrovaccinale to javafx.fxml;

    //Per applicazione Home
    exports com.centrovaccinale.centrovaccinale.grafica.home.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.home.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.home.main;
    opens com.centrovaccinale.centrovaccinale.grafica.home.main to javafx.fxml;

    //Per applicazione Login
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.login.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.login.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.login.main;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.login.main to javafx.fxml;

    //Per applicazione Operatore
    exports com.centrovaccinale.centrovaccinale.grafica.operatore.menu.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.operatore.menu.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.operatore.menu.main;
    opens com.centrovaccinale.centrovaccinale.grafica.operatore.menu.main to javafx.fxml;

    //Per applicazione CittadinoMenu
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.main;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.menu.main to javafx.fxml;

    //Per applicazione Signup
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.main;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.registrautente.main to javafx.fxml;

    //Per applicazione registra Centro
    exports com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.main;
    opens com.centrovaccinale.centrovaccinale.grafica.operatore.registracentro.main to javafx.fxml;

    //Per applicazione registra Cittadino
    exports com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.main;
    opens com.centrovaccinale.centrovaccinale.grafica.operatore.registravaccinato.main to javafx.fxml;

    // PER APPLICAZIONE REGISTRA EVENTO
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.main;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.registraevento.main to javafx.fxml;

    // PER APPLICAZIONE RICERCA CENTRO
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.main;
    opens com.centrovaccinale.centrovaccinale.grafica.cittadino.ricercacentro.main to javafx.fxml;

    // PER CONFIGURAZIONE CONNESSIONE SERVER
    exports com.centrovaccinale.centrovaccinale.grafica.configurazione.controller;
    opens com.centrovaccinale.centrovaccinale.grafica.configurazione.controller to javafx.fxml;
    exports com.centrovaccinale.centrovaccinale.grafica.configurazione.main;
    opens com.centrovaccinale.centrovaccinale.grafica.configurazione.main to javafx.fxml;


    exports com.centrovaccinale.centrovaccinale.rmi;
    exports com.centrovaccinale.centrovaccinale.utils;
    exports com.centrovaccinale.centrovaccinale.entita;

}