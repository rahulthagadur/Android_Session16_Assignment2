package com.example.thagadur.android_session16_assignment1;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Button;

import static android.R.attr.id;


public class MainActivity extends AppCompatActivity {
    //  Declared All the Objects of layout elements
    ProgressBar progress1, progress2, progress3;
    Button clickMe;
    LoadProgressbarTask task1, task2, task3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Call initializeID() to initialise all the objects with respective ID with layout elements
        initializeID();

        //onclick of the button call start the progress bar
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress1.setProgress(0);
                progress2.setProgress(0);
                progress3.setProgress(0);
                //Initialising the Async task
                task1 = new LoadProgressbarTask(progress1);
                task2 = new LoadProgressbarTask(progress2);
                task3 = new LoadProgressbarTask(progress3);

                // To execute all the threads in parallel
                task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                task3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

    }

    //Method which initialises all the objects of the elements
    public void initializeID() {
        clickMe = (Button) findViewById(R.id.button);
        progress1 = (ProgressBar) findViewById(R.id.progressBar);
        progress2 = (ProgressBar) findViewById(R.id.progressBar2);
        progress3 = (ProgressBar) findViewById(R.id.progressBar3);
    }

    /**
     * class  LoadProgressbarTask which extends AsyncTask which
     * accepts o input(void) onProgressUpdate an Integer Variable is passed to
     * Increase the progress bar.
     */
    class LoadProgressbarTask extends AsyncTask<Void, Integer, Void> {

        ProgressBar currentProgressBar;

        public LoadProgressbarTask(ProgressBar progressBar) {
            currentProgressBar = progressBar;
        }

        //doInBackground method not accepting any values but progress is published periodically to onProgressUpdate
        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                SystemClock.sleep(100);
            }
            return null;
        }

        // onProgressUpdate -here the updates from doInBackground are published periodically
        // Method to set the progress bar
        @Override
        protected void onProgressUpdate(Integer... values) {
            currentProgressBar.setProgress(values[0]);
        }

    }

}