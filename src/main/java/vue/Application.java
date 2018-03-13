package vue;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import timer.Timer;

import java.util.Calendar;

import static metier.Score.compterScore;
import static metier.Vote.reinitialiserVotes;

/**
 * Created by Ulysse Blaineau on 20/02/18.
 */

@SpringBootApplication(scanBasePackages = {"controle"})
public class Application {
    public static void main(String[] args) {

        Calendar timeOfDay = Calendar.getInstance();
        timeOfDay.set(Calendar.HOUR_OF_DAY, 13);
        timeOfDay.set(Calendar.MINUTE, 46);
        timeOfDay.set(Calendar.SECOND, 0);

        new Timer(timeOfDay, new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    // call whatever your daily task is here
                    compterScore();
                    reinitialiserVotes();
                }
                catch(Exception e)
                {
                    System.out.println("Erreur");
                }
            }
        }, "daily-housekeeping");

        SpringApplication.run(Application.class, args);
    }
}
