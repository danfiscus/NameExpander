package us.danfisc.nameexpander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingLongNameActivity extends AppCompatActivity {

    private ProgressBar spinner;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_long_name);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String shortname = intent.getStringExtra(MainScreen.EXTRA_NAME);

        //Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.searchName);
        textView.setText(shortname);

        //Set the loading bar to the spinner and start the animation
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
    }

}
