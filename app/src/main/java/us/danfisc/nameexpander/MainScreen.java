package us.danfisc.nameexpander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity {
    public static final  String EXTRA_NAME = "us.danfisc.nameexpander.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    /**Called when the user taps the Send button*/
    public void elongateName(View view) {
        Intent intent = new Intent(this, LoadingLongNameActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        intent.putExtra(EXTRA_NAME, name);
        startActivity(intent);
    }
}
