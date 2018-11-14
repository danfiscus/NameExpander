package us.danfisc.nameexpander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadDictionary extends AppCompatActivity {
    private static final String TAG = "DictLoad";
    public static final int NUMBER_OF_ENTRIES = 370099;
    public String[] masterDictionary;
    public double updateAmount = 100/(double)NUMBER_OF_ENTRIES;
    public double realProgress = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_dictionary);

        final ProgressBar loadingBar;
        //TextView loadingMessage;

        loadingBar = findViewById(R.id.loadBar);
        //loadingMessage = findViewById(R.id.loadMsg);


        masterDictionary = new String[NUMBER_OF_ENTRIES];

        loadingBar.setMax(100);

        new Thread(new Runnable() {
            public void run() {

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(getAssets().open("words.txt")));

                    // do reading, usually loop until end of file reading
                    String mLine;
                    int wordsAdded = 0;
                    while ((mLine = reader.readLine()) != null) {
                        //process line of words.txt
                        masterDictionary[wordsAdded++] = mLine;
                        if(wordsAdded%1000==0) {
                            Log.d(TAG, "Percent Complete (/100): " + realProgress);
                        }
                        realProgress += updateAmount;
                        loadingBar.post(new Runnable() {
                            public void run() {
                                if(loadingBar.getProgress() < (int)realProgress)
                                {
                                    loadingBar.setProgress(0);
                                    loadingBar.setProgress((int)realProgress);
                                    Log.d(TAG,"Progress Bar Increased by 1");
                                }

                            }
                        });
                    }
                } catch (IOException e) {
                    //log the exception
                    Log.e(TAG, "Failed to load words.txt");
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            //log the exception
                        }
                    }
                }

            }
        }).start();
    }
}
