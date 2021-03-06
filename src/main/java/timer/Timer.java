package timer;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Ulysse Blaineau on 13/03/18 from https://stackoverflow.com/questions/20387881/how-to-run-certain-task-every-day-at-a-particular-time-using-scheduledexecutorse#20388073.
 */
public class Timer
{
    private final Runnable dailyTask;
    private final int hour;
    private final int minute;
    private final int second;
    private final String runThreadName;

    public Timer(Calendar timeOfDay, Runnable dailyTask, String runThreadName)
    {
        this.dailyTask = dailyTask;
        this.hour = timeOfDay.get(Calendar.HOUR_OF_DAY);
        this.minute = timeOfDay.get(Calendar.MINUTE);
        this.second = timeOfDay.get(Calendar.SECOND);
        this.runThreadName = runThreadName;
    }

    public void start()
    {
        startTimer();
    }

    private void startTimer()
    {
        new java.util.Timer(runThreadName, true).schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                dailyTask.run();
                startTimer();
            }
        }, getNextRunTime());
    }


    private Date getNextRunTime()
    {
        Calendar startTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
        startTime.set(Calendar.SECOND, second);
        startTime.set(Calendar.MILLISECOND, 0);

        if(startTime.before(now) || startTime.equals(now))
        {
            startTime.add(Calendar.DATE, 1);
        }

        return startTime.getTime();
    }
}