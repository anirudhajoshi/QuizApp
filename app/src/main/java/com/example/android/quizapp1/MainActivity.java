package com.example.android.quizapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int numberOfQuestions = 5;
    int numberOfCorrectAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmit(View view) {

        // Clear out the correctly answered counter
        numberOfCorrectAnswers = 0;

        // Get Answer for Question 1. If correct increment correctly answered questions counter by 1
        CheckRadioButtonAnswer(R.id.Question1Answers, R.string.Answer1A);

        // Get Answer for Question 2. If correct increment correctly answered questions counter by 1
        CheckRadioButtonAnswer(R.id.Question2Answers, R.string.Answer2C);

        // Get Answer for Question 3. If correct increment correctly answered questions counter by 1
        CheckEditTextAnswer(R.id.edit_text_for_question3, R.string.Answer3);

        // Get Answer for Question 4. If correct increment correctly answered questions counter by 1
        CheckCheckBoxAnswers(R.id.checkbox_Answer4A2A, R.id.checkbox_Answer4A2B);

        // Get Answer for Question 5. If correct increment correctly answered questions counter by 1
        CheckRadioButtonAnswer(R.id.Question5Answers, R.string.Answer5B);

        DisplayCorrectlyAnsweredToastMessage();
    }

    private void CheckRadioButtonAnswer(int QuestionNumber, int CorrectAnswerNumber) {
        RadioGroup radioGroup = (RadioGroup) findViewById(QuestionNumber);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) { // Account for no radio button selected
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);
            String answer = selectedRadioButton.getText().toString();
            if (answer.equals(getResources().getString(CorrectAnswerNumber))) {
                numberOfCorrectAnswers++;
            }
        }
    }

    private void CheckEditTextAnswer(int QuestionNumber, int CorrectAnswerNumber) {
        EditText answer3_text = (EditText) findViewById(QuestionNumber);
        // The user can enter text with spaces or mixed case - account for it
        String answer3 = answer3_text.getText().toString().toLowerCase().trim();
        String correctAnswer3 = getResources().getString(CorrectAnswerNumber);
        if (answer3.equals(correctAnswer3))
            numberOfCorrectAnswers++;
    }

    private void CheckCheckBoxAnswers(int CorrectCheckboxAnswer1, int CorrectCheckboxAnswer2) {
        // Correct answers
        CheckBox checkbox_answer4A2A = (CheckBox) findViewById(CorrectCheckboxAnswer1);
        CheckBox checkbox_answer4A2B = (CheckBox) findViewById(CorrectCheckboxAnswer2);

        // Incorrect answers
        CheckBox checkbox_answer4A1A = (CheckBox) findViewById(R.id.checkbox_Answer4A1A);
        CheckBox checkbox_Answer4A1B = (CheckBox) findViewById(R.id.checkbox_Answer4A1B);

        // Incorrect answers
        CheckBox checkbox_answer4A3A = (CheckBox) findViewById(R.id.checkbox_Answer4A3A);
        CheckBox checkbox_answer4A3B = (CheckBox) findViewById(R.id.checkbox_Answer4A3B);

        if ((checkbox_answer4A2A.isChecked() && checkbox_answer4A2B.isChecked())    // Make sure the correct answers are checked
                && (!checkbox_answer4A1A.isChecked() && !checkbox_Answer4A1B.isChecked())   // & all the incorrect answers are unchecked
                && (!checkbox_answer4A3A.isChecked() && !checkbox_answer4A3B.isChecked())) {
            numberOfCorrectAnswers++;
        }
    }

    private void DisplayCorrectlyAnsweredToastMessage() {
        String toastSubmitMessage = getResources().getString(R.string.ToastMessage1);
        toastSubmitMessage += numberOfCorrectAnswers;
        toastSubmitMessage += getResources().getString(R.string.ToastMessage2);
        Toast.makeText(MainActivity.this, toastSubmitMessage, Toast.LENGTH_SHORT).show();
    }

    public void onReset(View view) {

        // Reset correctly answered questions counter
        numberOfCorrectAnswers = 0;

        // Uncheck all Question 1 radio buttons
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.Question1Answers);
        radioGroup.clearCheck();

        // Uncheck all Question 2 radio buttons
        radioGroup = (RadioGroup) findViewById(R.id.Question2Answers);
        radioGroup.clearCheck();

        // Clear text answer for Question 3
        EditText answer3 = (EditText) findViewById(R.id.edit_text_for_question3);
        answer3.setText("");
        answer3.clearFocus();

        // Clear checkboxes for Question 4
        CheckBox answers4 = (CheckBox) findViewById(R.id.checkbox_Answer4A1A);
        answers4.setChecked(false);
        answers4 = (CheckBox) findViewById(R.id.checkbox_Answer4A1B);
        answers4.setChecked(false);
        answers4 = (CheckBox) findViewById(R.id.checkbox_Answer4A2A);
        answers4.setChecked(false);
        answers4 = (CheckBox) findViewById(R.id.checkbox_Answer4A2B);
        answers4.setChecked(false);
        answers4 = (CheckBox) findViewById(R.id.checkbox_Answer4A3A);
        answers4.setChecked(false);
        answers4 = (CheckBox) findViewById(R.id.checkbox_Answer4A3B);
        answers4.setChecked(false);

        // Uncheck all Question 5 radio buttons
        radioGroup = (RadioGroup) findViewById(R.id.Question5Answers);
        radioGroup.clearCheck();

        // Scroll back to top
        ScrollView top = (ScrollView) findViewById(R.id.TopView);
        top.fullScroll(ScrollView.FOCUS_UP);

        String resetToastMessage = getResources().getString(R.string.PlayAgainToast);
        Toast.makeText(MainActivity.this, resetToastMessage, Toast.LENGTH_SHORT).show();
    }
}
