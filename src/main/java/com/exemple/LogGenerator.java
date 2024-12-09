package com.exemple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogGenerator {
    private static final Logger logger = LogManager.getLogger(LogGenerator.class);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            logger.fatal("fatal");
            logger.info("Connexion réussie pour l'utilisateur John Doe");
            logger.error("Erreur : Échec de connexion pour l'utilisateur Jane Doe");
            Thread.sleep(2000); // Crée une pause pour simuler les logs en temps réel
        }
    }
}
