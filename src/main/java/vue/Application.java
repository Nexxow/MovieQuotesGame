package vue;


import metier.Score;
import metier.Vote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import timer.Timer;

import java.util.Calendar;

/**
 * Created by Ulysse Blaineau on 20/02/18.
 */

@SpringBootApplication(scanBasePackages = {"controle"})
public class Application {

    public static void main(String[] args) {

        Score score = new Score();
        Vote vote = new Vote();

        SpringApplication.run(Application.class, args);

        Calendar timeOfDay = Calendar.getInstance();
        timeOfDay.set(Calendar.HOUR_OF_DAY, 13);
        timeOfDay.set(Calendar.MINUTE, 58);
        timeOfDay.set(Calendar.SECOND, 0);

        new Timer(timeOfDay, new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    // call whatever your daily task is here
                    score.compterScore();
                    vote.reinitialiserVotes();
                }
                catch(Exception e)
                {
                    System.out.println("Erreur");
                }
            }
        }, "daily-reset");
    }
}
