package com.mgarciareimers.escposthermalprinter.components;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.mgarciareimers.escposthermalprinter.R;

public class ProgressBar {
    private Activity activity;
    private View view;

    private TextView textView;

    private boolean show;

    public ProgressBar(Activity activity, View view) {
        this.activity = activity;
        this.view = view;

        this.defineFields();
        this.init();
        this.hide();
    }

    /**
     * @description Method that defines the fields.
     */
    private void defineFields() {
        this.textView = this.view.findViewById(R.id.text_view);
    }

    /**
     * @description Method that initializes the variables.
     */
    private void init() {
        this.show = false;
    }

    /**
     * @description Method that shows the element.
     */
    public void show() {
        this.show = true;
        this.view.setVisibility(View.VISIBLE);
    }

    /**
     * @description Method that hides the element.
     */
    public void hide() {
        this.show = false;
        this.view.setVisibility(View.GONE);
    }

    /**
     * @description Method that sets the text.
     */
    public void setText(String text) {
        this.textView.setText(text);
    }
}