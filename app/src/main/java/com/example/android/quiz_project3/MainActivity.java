package com.example.android.quiz_project3;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer mp;
    boolean resultQ1;
    boolean resultQ2;
    boolean resultQ3;
    boolean resultQ4;
    int score = 0;

    //Create a toast message, displayed when a user fills in an answer.

    Context context = MainActivity.this;
    CharSequence text = "Thanks for your answer!";
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView animeView = (ImageView) findViewById(R.id.anime_arrow);
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow); //Apply animation to animeView
        mp = MediaPlayer.create(MainActivity.this, R.raw.deftones_audioclip1);



        if (savedInstanceState != null) {
            resultQ1 = savedInstanceState.getBoolean("resultQ1");
            resultQ2 = savedInstanceState.getBoolean("resultQ2");
            resultQ3 = savedInstanceState.getBoolean("resultQ3");
            resultQ4 = savedInstanceState.getBoolean("resultQ4");
            score = savedInstanceState.getInt("score");
        }


    }


    /**
     * QUESTION 1: Check if each checkbox is checked or not. Then update the result to true or false,
     * whether or not exactly the right checkboxes are checked.
     *
     * @param v
     */

    public void onCheckBoxCheck(View v) {

        //Check every checkbox if they are checked or not.

        CheckBox abeCheckbox = (CheckBox) findViewById(R.id.abe_checkbox);
        boolean selectedAbe = abeCheckbox.isChecked();

        CheckBox tomCheckbox = (CheckBox) findViewById(R.id.tom_checkbox);
        boolean selectedTom = tomCheckbox.isChecked();

        CheckBox chiCheckbox = (CheckBox) findViewById(R.id.chi_checkbox);
        boolean selectedChi = chiCheckbox.isChecked();

        CheckBox steveCheckbox = (CheckBox) findViewById(R.id.steve_checkbox);
        boolean selectedSteve = steveCheckbox.isChecked();

        CheckBox frankCheckbox = (CheckBox) findViewById(R.id.frank_checkbox);
        boolean selectedFrank = frankCheckbox.isChecked();

        CheckBox chinoCheckbox = (CheckBox) findViewById(R.id.chino_checkbox);
        boolean selectedChino = chinoCheckbox.isChecked();

        CheckBox sergioCheckbox = (CheckBox) findViewById(R.id.sergio_checkbox);
        boolean selectedDominic = sergioCheckbox.isChecked();

        CheckBox maynardCheckbox = (CheckBox) findViewById(R.id.maynard_checkbox);
        boolean selectedMaynard = maynardCheckbox.isChecked();

        //if the right checkboxes are checked AND the wrong ones are not, updates the result to true.

        if ((selectedAbe && selectedSteve && selectedFrank && selectedChino && selectedDominic)
                && (!(selectedMaynard) && !(selectedTom) && !(selectedChi))) {
            resultQ1 = true;
            score++;
        } else {
            resultQ1 = false;
        }
    }

    //Display toast after submitting Question 1
    public void submitCheckBoxQ1(View v) {
        Toast.makeText(context, text, duration).show();
    }

    /**
     * QUESTION 2: EditText. Guess the image, fill in the answer in the edittext
     *
     * @param v
     */

    public void submitEditTextAlbumart(View v) {

        //Retrieves the text the user filled in, after pressing the submit button.

        EditText q2EditText = (EditText) findViewById(R.id.q2_edittext);
        String answerQ2 = q2EditText.getText().toString();
        if (answerQ2.equalsIgnoreCase("Gore")) {
            resultQ2 = true;
            score++;
        } else {
            resultQ2 = false;
        }

        //After pressing submit, deletes the text and replaces it with a hint including the given answer.

        q2EditText.setText("");
        q2EditText.setHint("You answered " + answerQ2);

        //display toast
        Toast.makeText(context, text, duration).show();

    }

    /**
     * QUESTION 3: radiobuttons. Check which radiobutton is selected and return true or false
     *
     * @param view
     */

    public void onRadioButtonClickedQ3(View view) {


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();


        // Check which radio button was clicked. Right answer updates to true, wrong to false.
        switch (view.getId()) {
            case R.id.radio_linux:
                if (checked)
                    resultQ3 = false;
                break;
            case R.id.radio_deftonesep:
                if (checked)
                    resultQ3 = false;
                break;
            case R.id.radio_linus:
                if (checked)
                    resultQ3 = true;
                score++;
                break;
            case R.id.radio_eros:
                if (checked)
                    resultQ3 = false;
                break;
        }

        //display toast
        Toast.makeText(context, text, duration).show();

    }

    /**
     * QUESTION 4: radiobuttons with audio from mediaplayer. Check which radiobutton is selected and return true or false
     *
     * @param v
     */


    //Play button to play the audioclip
    public void clickPlay(View v) {
        if (!(mp.isPlaying())) {
            mp.start();
        }
    }


    public void onRadioButtonClickedQ4(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked. Right answer updates to true, wrong to false.
        switch (v.getId()) {
            case R.id.radio_q4_bored:
                if (checked)
                    resultQ4 = false;
                break;
            case R.id.radio_q4_school:
                if (checked)
                    resultQ4 = false;
                break;
            case R.id.radio_q4_tempest:
                if (checked)
                    resultQ4 = false;
                break;
            case R.id.radio_q4_swerve:
                if (checked)
                    resultQ4 = true;
                score++;
                break;
        }

        // Stops the music when a radiobutton is clicked/an answer is given.
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }

        //display toast
        Toast.makeText(context, text, duration).show();

    }

    /**
     * Uses variables of the questions to create a summary String of your efforts.
     *
     * @return finalMessage String
     */

    public String finalMessage() {

        //Creates Strings to display according to the score of the user.
        String resultSummary = "";
        String resultFinalYou = "";

        switch (score) {
            case 0:
                resultSummary = "Ugh, ";
                resultFinalYou = " have probably never heard of Deftones.";
                break;
            case 1:
                resultSummary = "Meh, ";
                resultFinalYou = " need to listen to some more Deftones.";
                break;
            case 2:
                resultSummary = "Alright, ";
                resultFinalYou = " might want to listen to a couple more Deftones songs.";
                break;
            case 3:
                resultSummary = "Pretty good, ";
                resultFinalYou = " really know your stuff!";
                break;
            case 4:
                resultSummary = "Wow, ";
                resultFinalYou = " are a true fan!";
                break;
        }

        //Takes the answers of the questions and updates the finalMessage String, according to the answer being right or wrong.

        String messageQ1;
        String messageQ2;
        String messageQ3;
        String messageQ4;

        if (resultQ1) {
            messageQ1 = "nailed";
        } else {
            messageQ1 = "failed";
        }

        if (resultQ2) {
            messageQ2 = "correct";
        } else {
            messageQ2 = "incorrect";
        }

        if (resultQ3) {
            messageQ3 = "no";
        } else {
            messageQ3 = "quite a";
        }

        if (resultQ4) {
            messageQ4 = "right";
        } else {
            messageQ4 = "wrong";
        }

        //Concatenate all the strings to a finalMessage and return it.

        String finalMessage = resultSummary + "you got " + score + " right.";
        finalMessage += "\n\nYou " + messageQ1 + " question 1.";
        finalMessage += "\nQuestion 2 was " + messageQ2 + ".";
        finalMessage += "\nQuestion 3 turned out to be " + messageQ3 + " problem.";
        finalMessage += "\nAnd finally you got the " + messageQ4 + " answer for question 4.";
        finalMessage += "\nYou " + resultFinalYou;
        return finalMessage;
    }


    /**
     * When the last button is clicked, your final score and the finalMessage summary is displayed.
     *
     * @param view
     */

    public void clickResult(View view) {

        //Update the pink 'result' title with the actual score.
        TextView scoreTitle = (TextView) findViewById(R.id.result_text);
        scoreTitle.setText("Result: " + score + " out of 4!");

        //Displays finalMessage in the TextView over the bandpicture.
        TextView resultSummaryText = (TextView) findViewById(R.id.result_summary_text);
        resultSummaryText.setText(finalMessage());

    }

    //Save the state of the variables and text after the screen orientation changes.

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("resultQ1", resultQ1);
        savedInstanceState.putBoolean("resultQ2", resultQ2);
        savedInstanceState.putBoolean("resultQ3", resultQ3);
        savedInstanceState.putBoolean("resultQ4", resultQ4);
        savedInstanceState.putInt("score", score);
        super.onSaveInstanceState(savedInstanceState);


    }

    // Reset button at the bottom. Resets the whole thing.

    public void clickReset(View v) {
        score = 0;
        resultQ1 = false;
        resultQ2 = false;
        resultQ3 = false;
        resultQ4 = false;

        //Uncheck all checkboxes in Question 1
        CheckBox abeCheckbox = (CheckBox) findViewById(R.id.abe_checkbox);
        abeCheckbox.setChecked(false);
        CheckBox tomCheckbox = (CheckBox) findViewById(R.id.tom_checkbox);
        tomCheckbox.setChecked(false);
        CheckBox chiCheckbox = (CheckBox) findViewById(R.id.chi_checkbox);
        chiCheckbox.setChecked(false);
        CheckBox steveCheckbox = (CheckBox) findViewById(R.id.steve_checkbox);
        steveCheckbox.setChecked(false);
        CheckBox frankCheckbox = (CheckBox) findViewById(R.id.frank_checkbox);
        frankCheckbox.setChecked(false);
        CheckBox chinoCheckbox = (CheckBox) findViewById(R.id.chino_checkbox);
        chinoCheckbox.setChecked(false);
        CheckBox sergioCheckbox = (CheckBox) findViewById(R.id.sergio_checkbox);
        sergioCheckbox.setChecked(false);
        CheckBox maynardCheckbox = (CheckBox) findViewById(R.id.maynard_checkbox);
        maynardCheckbox.setChecked(false);

        //Return Edittext hint in Question 2 to original state
        EditText q2EditText = (EditText) findViewById(R.id.q2_edittext);
        q2EditText.setHint(R.string.albumtitle);

        //Uncheck the radiobuttons of Question 3
        RadioGroup radioGroupQ3 = (RadioGroup) findViewById(R.id.radiogroup_q3);
        radioGroupQ3.clearCheck();

        //Uncheck the radiobuttons in Question 4
        RadioGroup radioGroupQ4 = (RadioGroup) findViewById(R.id.radiogroup_q4);
        radioGroupQ4.clearCheck();

        //Update the pink 'result' title to the initial title
        TextView scoreTitle = (TextView) findViewById(R.id.result_text);
        scoreTitle.setText(R.string.title_result);

        //Displays nothing in the TextView over the bandpicture.
        TextView resultSummaryText = (TextView) findViewById(R.id.result_summary_text);
        resultSummaryText.setText("");
    }

}