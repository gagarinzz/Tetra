package com.example.n18;

import android.widget.EditText;

public class N1 {
    private EditText editText1;
    private EditText editText2;

    public EditText getActiveEditText() {
        if (editText1.hasFocus()) {
            return editText1;
        } else if (editText2.hasFocus()) {
            return editText2;
            //editText = findViewById(R.id.editText2);
        }
        return null;

    }
}
