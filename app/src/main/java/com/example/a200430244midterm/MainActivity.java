package com.example.a200430244midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //View Objects
    EditText firstName;
    EditText lastName;
    Spinner selectedItem;
    TextView androidChoice;
    Button playButton;
    Button resetButton;

    DatabaseReference databaseReference;

    private final Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("results");

        //Getting View Objects
        firstName = (EditText) findViewById(R.id.editTextFname);
        lastName = (EditText) findViewById(R.id.editTextLname);
        selectedItem = (Spinner) findViewById(R.id.spinnerChoice);
        androidChoice = (TextView) findViewById(R.id.androidChoice);
        playButton = (Button) findViewById(R.id.playBtn);
        resetButton = (Button) findViewById(R.id.resetBtn);

        //Game Starts
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBtnClick();//Method for Play Button Click
            }
        });

        //Clearing All The Fields
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.setText("");
                lastName.setText("");
                androidChoice.setText("");
                selectedItem.setSelection(0);
            }
        });

    }

    //Method invoked when clicked on Play Button
    public void playBtnClick() {
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String result = "";

        if (!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname)) {
            int randInt = rand.nextInt(100)+1;

            //Android Choices
            if (randInt < 34) {
                androidChoice.setText("Rock");
            } else if (randInt > 33 && randInt < 67) {
                androidChoice.setText("Paper");
            } else if (randInt >66) {
                androidChoice.setText("Scissors");
            }

            //Game Rules
            if (selectedItem.getSelectedItem().toString().equalsIgnoreCase("Rock") && androidChoice.getText().toString().equalsIgnoreCase("Scissors")) {
                Toast.makeText(getApplicationContext(), "USER WINS", Toast.LENGTH_LONG).show();
                result = "USER - " +fname + lname;
            } else if (selectedItem.getSelectedItem().toString().equalsIgnoreCase("Paper") && androidChoice.getText().toString().equalsIgnoreCase("Rock")) {
                Toast.makeText(getApplicationContext(), "USER WINS", Toast.LENGTH_LONG).show();
                result = "USER - " +fname + lname;
            } else if (selectedItem.getSelectedItem().toString().equalsIgnoreCase("Scissors") && androidChoice.getText().toString().equalsIgnoreCase("Paper")) {
                Toast.makeText(getApplicationContext(), "USER WINS", Toast.LENGTH_LONG).show();
                result = "USER - " +fname + lname;
            } else if ((selectedItem.getSelectedItem().toString().equalsIgnoreCase("Scissors") && androidChoice.getText().toString().equalsIgnoreCase("Scissors")) ||
                    (selectedItem.getSelectedItem().toString().equalsIgnoreCase("Paper") && androidChoice.getText().toString().equalsIgnoreCase("Paper")) ||
                    (selectedItem.getSelectedItem().toString().equalsIgnoreCase("Rock") && androidChoice.getText().toString().equalsIgnoreCase("Rock"))) {
                Toast.makeText(getApplicationContext(), "TIE - NO ONE", Toast.LENGTH_LONG).show();
                result = "TIE - NO ONE";
            } else {
                Toast.makeText(getApplicationContext(), "ANDROID WINS", Toast.LENGTH_LONG).show();
                result = "ANDROID";
            }
        } else {
            Toast.makeText(getApplicationContext(), "FIRST NAME & LAST NAME CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
        }
        String uChoice = selectedItem.getSelectedItem().toString();
        String aChoice = androidChoice.getText().toString();

        String id = databaseReference.push().getKey();
        System.out.println("=====================id: "+id);
        System.out.println("=====================fname: "+fname);
        System.out.println("=====================lname: "+lname);
        System.out.println("=====================uChoice: "+uChoice);
        System.out.println("=====================aChoice: "+aChoice);
        System.out.println("=====================winner: "+result);

        Game game = new Game(id, fname, lname, uChoice, aChoice, result);
        databaseReference.child(id).setValue(game);
    }
}
