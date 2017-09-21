package edu.orangecoastcollege.cs273.dtallcott.paintestimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static edu.orangecoastcollege.cs273.dtallcott.paintestimator.MainActivity.twoDP;

/**
 * Gets the intent form the main activity and displays the gallons required
 * with a detailed description of how the application calculates.
 *
 * @author Devon Tallcott
 * @version 1.0
 *
 * Created on 9/21/17
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Intent intent = getIntent();
        float gallons = intent.getFloatExtra("gallons", (float)0.0);

        TextView estimatedGallonsTextView = (TextView)findViewById(R.id.estimatedGallonsTextView);

        estimatedGallonsTextView.setText(getString(R.string.estimated) + " " + twoDP.format(gallons) + " " + getString(R.string.gallons));

    }

    protected void ReturnToMain(View v)
    {
        this.finish();
    }
}
