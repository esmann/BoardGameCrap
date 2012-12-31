package dk.esmann;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class Timer extends Activity {

    int rounds, hours, minutes, completedRounds;
    long remainingMillis;
    TextView roundTextView, countdownTextView, avgTime;
    Resources res;
    CountDownTimer countDownTimer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        Intent intent = getIntent();
        rounds = intent.getIntExtra("rounds", 1);
        completedRounds = 0;
        hours = intent.getIntExtra("hours", 0);
        minutes = intent.getIntExtra("minutes", 0);
        roundTextView = (TextView) findViewById(R.id.timerRound);
        countdownTextView = (TextView) findViewById(R.id.countdown);
        avgTime = (TextView)findViewById(R.id.avg_round_time);
        remainingMillis = (((hours * 60) + minutes) * 60) * 1000;
        res = getResources();
        roundTextView.setText(String.format(res.getString(R.string.round_x_of_z), completedRounds+1, rounds));
        countdownTextView.setText(String.format(res.getString(R.string.countdown_timer), getHoursFromMillis(remainingMillis), getMinutesFromMillis(remainingMillis), getSecondsFromMillis(remainingMillis)));
        int remainingRounds = (rounds-completedRounds);
        long avgMillis = remainingMillis/remainingRounds;
        avgTime.setText(String.format(res.getString(R.string.avg_round_time), remainingRounds, getHoursFromMillis(avgMillis), getMinutesFromMillis(avgMillis), getSecondsFromMillis(avgMillis) ));
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onStart(View view) {

            countDownTimer = new CountDownTimer(remainingMillis, 1000) {
                public void onTick(long millis) {
                    remainingMillis = millis;
                    int remainingRounds = (rounds-completedRounds);
                    long avgMillis = remainingMillis/remainingRounds;
                    countdownTextView.setText(String.format(res.getString(R.string.countdown_timer), getHoursFromMillis(millis), getMinutesFromMillis(millis), getSecondsFromMillis(millis)));
                    avgTime.setText(String.format(res.getString(R.string.avg_round_time), remainingRounds, getHoursFromMillis(avgMillis), getMinutesFromMillis(avgMillis), getSecondsFromMillis(avgMillis) ));
                }

                public void onFinish() {
                    countdownTextView.setText("done!");
                }
            };
        countDownTimer.start();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onStop(View view) {
        countDownTimer.cancel();
        completedRounds++;
    }

    private long getHoursFromMillis(long millis) {
        return (millis / (1000 * 60 * 60));
    }

    private long getMinutesFromMillis(long millis) {
        return ((millis % (1000 * 60 * 60)) / (1000 * 60));
    }

    private long getSecondsFromMillis(long millis) {
        return ((millis % (1000 * 60 * 60)) % (1000 * 60) / 1000);
    }
}