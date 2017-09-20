package edu.orangecoastcollege.cs273.dtallcott.paintestimator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    protected void ReturnToMain(View v)
    {
        this.finish();
    }
}
