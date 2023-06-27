package com.mgarciareimers.escposthermalprinter.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.mgarciareimers.escposthermalprinter.R;
import com.mgarciareimers.escposthermalprinter.commons.constants.Numbers;
import com.mgarciareimers.escposthermalprinter.commons.utils.Utilities;
import com.mgarciareimers.escposthermalprinter.components.AlertDialogError;
import com.mgarciareimers.escposthermalprinter.components.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText typeTextToPrintEditText;
    private Button printTextButton;
    private ProgressBar progressBar;
    private AlertDialogError alertDialogError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineFields();
        defineClickListeners();
    }

    /**
     * @description Method that defines the fields.
     */
    private void defineFields() {
        typeTextToPrintEditText = this.findViewById(R.id.type_text_to_print_edit_text);
        printTextButton = this.findViewById(R.id.print_text_button);

        progressBar = new ProgressBar(this, this.findViewById(R.id.element_progress_bar));
        alertDialogError = new AlertDialogError(this, this.getString(R.string.error_validation), this.getString(R.string.error_validation_edit_text), this.getString(R.string.retry), this.getString(R.string.cancel), true);
    }

    /**
     * @description Method that defines the click listeners.
     */
    private void defineClickListeners() {
        printTextButton.setOnClickListener(view -> onPrintButtonClicked());
        alertDialogError.getPositiveButton().setOnClickListener(view -> onAlertDialogErrorPositiveButtonClicked());
        alertDialogError.getNegativeButton().setOnClickListener(view -> onAlertDialogErrorNegativeButtonClicked());
    }

    /**
     * @description Method that is called when the user clicks the error alert dialog positive button.
     */
    private void onAlertDialogErrorPositiveButtonClicked() {
        alertDialogError.dismiss();
        onPrintButtonClicked();
    }

    /**
     * @description Method that is called when the user clicks the error alert dialog negative button.
     */
    private void onAlertDialogErrorNegativeButtonClicked() {
        alertDialogError.dismiss();
    }

    /**
     * @description Method that is called when the user clicks the print button.
     */
    private void onPrintButtonClicked() {
        // Check text.
        if (typeTextToPrintEditText.getText().toString().isEmpty()) {
            alertDialogError.show();
            return;
        }

        // Check permissions.
        List<String> permissions = getPendingPermissions();

        if (!permissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, Utilities.transformListToArrayStrings(permissions), Numbers.requestCodePermissions);
            return;
        }

        // Print test.
        try {
            EscPosPrinter printer = new EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 48);

            printer.printFormattedText(
                "[L]<font size='small'>" + typeTextToPrintEditText.getText().toString() + "</font>" +
                "[C]<font size='small'>\n________________________________________________</font>"
            );
        } catch(Exception e) {
            Log.d("TAG", "onPrintButtonClicked: ");
        }
    }

    /**
     * @description Method that gets the user pending permissions.
     */
    private List<String> getPendingPermissions() {
        List<String> permissions = new ArrayList<>();

        if (!Utilities.checkPermissions(this, Manifest.permission.BLUETOOTH_SCAN)) {
            permissions.add(Manifest.permission.BLUETOOTH_SCAN);
        }

        if (!Utilities.checkPermissions(this, Manifest.permission.BLUETOOTH_CONNECT)) {
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT);
        }

        return permissions;
    }
}