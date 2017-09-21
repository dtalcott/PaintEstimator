package edu.orangecoastcollege.cs273.dtallcott.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    public static final DecimalFormat twoDP = new DecimalFormat("#.00");
    //Member Variables for the Views
    private EditText mLengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;

    private EditText mDoorsEditText;
    private EditText mWindowsEditText;

    private TextView mGallonsTextView;

    //member variable for the Model
    private InteriorRoom mRoom = new InteriorRoom();

    //member variable for SharedPreferences
    private SharedPreferences mPrefs;

    private void initializeViews()
    {
        mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
        mWidthEditText = (EditText) findViewById(R.id.widthEditText);
        mDoorsEditText = (EditText) findViewById(R.id.doorsEditText);
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);
        mWindowsEditText = (EditText) findViewById(R.id.windowsEditText);

        mGallonsTextView = (TextView) findViewById(R.id.gallonsTextView);


    }

    private void loadSharedPreferences()
    {
        mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.dtallcott.PaintEstimator", MODE_PRIVATE);
        if (mPrefs != null)
        {
            //Load all the room information
            mRoom.setDoors(mPrefs.getInt("doors", 0));
            if (mRoom.getDoors() != 0)
                mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));

            mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
            mHeightEditText.setText(String.valueOf(mRoom.getHeight()));

            mRoom.setLength(mPrefs.getFloat("length", 0.0f));
            mLengthEditText.setText(String.valueOf(mRoom.getLength()));

            mRoom.setWidth(mPrefs.getFloat("width", 0.0f));
            mWidthEditText.setText(String.valueOf(mRoom.getWidth()));

            mRoom.setWindows(mPrefs.getInt("windows", 0));
            mWindowsEditText.setText(String.valueOf(mRoom.getWindows()));

        }
    }

    private void saveSharedPreferences()
    {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putFloat("length", mRoom.getLength());
        editor.putFloat("width", mRoom.getWidth());
        editor.putFloat("height" , mRoom.getHeight());
        editor.putInt("doors", mRoom.getDoors());
        editor.putInt("windows", mRoom.getWindows());
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreferences();
    }

    protected void computeGallons(View v)
    {
        //update the model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setWindows(Integer.parseInt(mWindowsEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorsEditText.getText().toString()));

        mGallonsTextView.setText(getString(R.string.interior_surface_area) + " " + twoDP.format(mRoom.totalSurfaceArea()) + " " + getString(R.string.feet)
        + "\n" + getString(R.string.gallons_needed) + " " + twoDP.format(mRoom.gallonsOfPaintRequired()));
        saveSharedPreferences();
    }

    protected void gotoHelp(View v)
    {
        //Construct and EXPLICIT Intent to go to HelpActivity
        //Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", mRoom.gallonsOfPaintRequired());
        startActivity(helpIntent);
    }
}
