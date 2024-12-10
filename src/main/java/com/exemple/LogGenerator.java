package com.exemple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class LogGenerator {

    private static final Logger logger = LogManager.getLogger(LogGenerator.class);
    private static final Random random = new Random();

    // Liste des ID récurrents
    private static final List<String> recurringUserIDs = Arrays.asList("75689", "18410", "46202", "4985", "3643");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // Générer un ID utilisateur (récurrent ou unique)
    private static String generateUserID() {
        if (random.nextInt(10) < 6) { // 60% chance d'utiliser un ID récurrent
            return recurringUserIDs.get(random.nextInt(recurringUserIDs.size()));
        } else {
            int id = random.nextInt(100000) + 1; // Générer un ID unique
            return String.valueOf(id);
        }
    }

    // Générer un timestamp entre 9h et 16h
    private static String generateTimestampBetween9and16() {
        int hour = random.nextInt(8) + 9; // Heure entre 9 et 16
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        int millisecond = random.nextInt(1000);
        LocalDateTime now = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(second).withNano(millisecond * 1_000_000);
        return now.format(formatter);
    }

    // Générer un timestamp après 16h
    private static String generateTimestampAfter16h() {
        int hour = random.nextInt(8) + 16; // Heure entre 16 et 23
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        int millisecond = random.nextInt(1000);
        LocalDateTime now = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(second).withNano(millisecond * 1_000_000);
        return now.format(formatter);
    }

    public static void main(String[] args) throws InterruptedException {

        // Générer 500 logs entre 9h et 16h
        for (int i = 0; i < 500; i++) {
            String userID = generateUserID();
            String timestamp = generateTimestampBetween9and16();

            // Vérifier l'unicité des logs
                ThreadContext.put("userID", userID);
                ThreadContext.put("timestamp", timestamp);

                logger.info("Connexion_réussie");

                ThreadContext.clearAll();
            
        }

        // Générer 15 logs après 16h
        for (int i = 0; i < 15; i++) {
            String userID = generateUserID();
            String timestamp = generateTimestampAfter16h();

            ThreadContext.put("userID", userID);
            ThreadContext.put("timestamp", timestamp);

            logger.info("Connexion_réussie");

            ThreadContext.clearAll();
        }
    }
}
