package com.mgarciareimers.escposthermalprinter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mgarciareimers.escposthermalprinter.R;

public class MainActivity extends AppCompatActivity {

    private EditText typeTextToPrintEditText;
    private Button printTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineFields();
        defineClickListeners();
    }

    // Method that defines the fields.
    private void defineFields() {
        typeTextToPrintEditText = this.findViewById(R.id.typeTextToPrintEditText);
        printTextButton = this.findViewById(R.id.printTextButton);
    }

    // Method that defines the click listeners.
    private void defineClickListeners() {
        printTextButton.setOnClickListener(view -> onPrintButtonClicked());
    }

    // Method that is called when the user clicks the print button.
    private void onPrintButtonClicked() {
        // TODO.
    }
}