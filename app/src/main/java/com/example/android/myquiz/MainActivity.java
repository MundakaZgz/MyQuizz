package com.example.android.myquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Submits the answer
     * @param view
     */
    public void submitAnswer(View view) {
        validateAnswers(view);
    }

    /**
     * Validates the answers from the quizz
     * @param view
     */
    private void validateAnswers(View view) {
        CheckBox verify = (CheckBox) findViewById(R.id.verify_checkbox);
        if (!verify.isChecked()) {
            showVerificationExpectedMessage();
            return;
        }

        EditText signature = (EditText) findViewById(R.id.signature_text);
        if(signature.getText().length() == 0) {
            showSignatureExpectedMessage();
            return;
        }

        correctAnswers = 0;

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.country_1);
        RadioGroup rg2 = (RadioGroup) findViewById(R.id.country_2);
        RadioGroup rg3 = (RadioGroup) findViewById(R.id.country_3);
        RadioGroup rg4 = (RadioGroup) findViewById(R.id.country_4);
        RadioGroup rg5 = (RadioGroup) findViewById(R.id.country_5);
        RadioGroup rg6 = (RadioGroup) findViewById(R.id.country_6);
        RadioGroup rg7 = (RadioGroup) findViewById(R.id.country_7);
        RadioGroup rg8 = (RadioGroup) findViewById(R.id.country_8);
        RadioGroup rg9 = (RadioGroup) findViewById(R.id.country_9);
        RadioGroup rg10 = (RadioGroup) findViewById(R.id.country_10);

        if (rg1.getCheckedRadioButtonId() == R.id.right_city_1) {
            correctAnswers++;
        }
        if (rg2.getCheckedRadioButtonId() == R.id.right_city_2) {
            correctAnswers++;
        }
        if (rg3.getCheckedRadioButtonId() == R.id.right_city_3) {
            correctAnswers++;
        }
        if (rg4.getCheckedRadioButtonId() == R.id.right_city_4) {
            correctAnswers++;
        }
        if (rg5.getCheckedRadioButtonId() == R.id.right_city_5) {
            correctAnswers++;
        }
        if (rg6.getCheckedRadioButtonId() == R.id.right_city_6) {
            correctAnswers++;
        }
        if (rg7.getCheckedRadioButtonId() == R.id.right_city_7) {
            correctAnswers++;
        }
        if (rg8.getCheckedRadioButtonId() == R.id.right_city_8) {
            correctAnswers++;
        }
        if (rg9.getCheckedRadioButtonId() == R.id.right_city_9) {
            correctAnswers++;
        }
        if (rg10.getCheckedRadioButtonId() == R.id.right_city_10) {
            correctAnswers++;
        }

        Button sendReportButton = (Button) findViewById(R.id.send_report_button);

        if (correctAnswers >= 5) {
            showGreetingsMessage();
            sendReportButton.setText(R.string.report_button_text);
            sendReportButton.setVisibility(View.VISIBLE);
            sendReportButton.setEnabled(true);
        } else {
            showFailMessage();
            sendReportButton.setText(R.string.try_again_button_text);
            sendReportButton.setVisibility(View.GONE);
            sendReportButton.setEnabled(false);
        }

    }

    public void sendReport(View view) {
        sendEmail();
    }

    /**
     * Sends the email with the suitable information
     */
    private void sendEmail() {

        EditText emailAddressEditText = (EditText) findViewById(R.id.email_address);
        String emailAddress = emailAddressEditText.getText().toString();

        if(emailAddress.length() == 0) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject));

        String message = getResources().getString(R.string.email_message).replace("%s", Integer.toString(correctAnswers));

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);

        if(checkBox.isChecked()) {
            message += getResources().getString(R.string.user_added_to_database);
        }

        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Shows greeting message and resets the quiz
     */
    private void showGreetingsMessage() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.congratulations).replace("%s", Integer.toString(correctAnswers));
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        reset();
    }

    /**
     * Shows verification message and resets the quiz
     */
    private void showVerificationExpectedMessage() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.verification_expected);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        reset();
    }

    /**
     * Shows signature message and resets the quiz
     */
    private void showSignatureExpectedMessage() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.signature_expected);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        reset();
    }


    /**
     * Shows failure message and resets the quiz
     */
    private void showFailMessage() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.try_again).replace("%s", Integer.toString(correctAnswers));
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        reset();
    }

    /**
     * Resets the quizz
     */
    private void reset() {
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.country_1);
        RadioGroup rg2 = (RadioGroup) findViewById(R.id.country_2);
        RadioGroup rg3 = (RadioGroup) findViewById(R.id.country_3);
        RadioGroup rg4 = (RadioGroup) findViewById(R.id.country_4);
        RadioGroup rg5 = (RadioGroup) findViewById(R.id.country_5);
        RadioGroup rg6 = (RadioGroup) findViewById(R.id.country_6);
        RadioGroup rg7 = (RadioGroup) findViewById(R.id.country_7);
        RadioGroup rg8 = (RadioGroup) findViewById(R.id.country_8);
        RadioGroup rg9 = (RadioGroup) findViewById(R.id.country_9);
        RadioGroup rg10 = (RadioGroup) findViewById(R.id.country_10);

        rg1.clearCheck();
        rg2.clearCheck();
        rg3.clearCheck();
        rg4.clearCheck();
        rg5.clearCheck();
        rg6.clearCheck();
        rg7.clearCheck();
        rg8.clearCheck();
        rg9.clearCheck();
        rg10.clearCheck();

        correctAnswers = 0;
    }
}
