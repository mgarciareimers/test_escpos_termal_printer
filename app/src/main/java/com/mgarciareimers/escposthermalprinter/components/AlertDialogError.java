package com.mgarciareimers.escposthermalprinter.components;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mgarciareimers.escposthermalprinter.R;

public class AlertDialogError {
    private Activity activity;
    private String title;
    private String text;
    private String positiveButtonText;
    private String negativeButtonText;
    private boolean cancelable;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private View view;
    private TextView titleTextView, textTextView;
    private Button negativeButton, positiveButton;
    private ConstraintLayout guideline;

    public AlertDialogError(Activity activity, String title, String text, String positiveButtonText, String negativeButtonText, boolean cancelable) {
        this.activity = activity;
        this.title = title;
        this.text = text;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText = negativeButtonText;
        this.cancelable = cancelable;

        this.defineFields();
        this.setFieldValues();
    }

    /**
     * @description Method that defines the fields.
     */
    private void defineFields() {
        this.builder = new AlertDialog.Builder(this.activity);
        this.alertDialog = builder.create();

        this.view = LayoutInflater.from(this.activity).inflate(R.layout.dialog_error, null);

        this.titleTextView  = view.findViewById(R.id.title_text_view);
        this.textTextView = view.findViewById(R.id.text_text_view);
        this.negativeButton = view.findViewById(R.id.negative_button);
        this.positiveButton = view.findViewById(R.id.positive_button);
        this.guideline = view.findViewById(R.id.guideline);
    }

    /**
     * @description Method that sets the field values.
     */
    private void setFieldValues() {
        this.negativeButton.setVisibility(negativeButtonText == null ? View.INVISIBLE : View.VISIBLE);
        this.positiveButton.setVisibility(positiveButtonText == null ? View.INVISIBLE : View.VISIBLE);

        if (this.negativeButtonText == null) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
            params.horizontalBias = 0f;
            this.guideline.setLayoutParams(params);
        }

        this.titleTextView.setText(title);
        this.textTextView.setText(text);
        this.negativeButton.setText(negativeButtonText);
        this.positiveButton.setText(positiveButtonText);

        this.negativeButton.setBackgroundTintList(AppCompatResources.getColorStateList(this.activity, R.color.red));

        this.alertDialog.setCancelable(cancelable);
    }

    /**
     * @description Method that gets the positive button.
     */
    public Button getPositiveButton() {
        return this.positiveButton;
    }

    /**
     * @description Method that gets the negative button.
     */
    public Button getNegativeButton() {
        return this.negativeButton;
    }

    /**
     * @description Method that shows the alert.
     */
    public void show() {
        try {
            this.alertDialog.setView(this.view);
            this.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.alertDialog.show();
        } catch(Exception ignored) {}
    }

    /**
     * @description Method that dismisses the alert.
     */
    public void dismiss() {
        this.alertDialog.dismiss();
    }
}