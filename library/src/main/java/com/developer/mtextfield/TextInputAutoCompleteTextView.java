package com.developer.mtextfield;

import android.content.Context;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class TextInputAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    public TextInputAutoCompleteTextView(Context context) {
        super(context);
    }

    public TextInputAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextInputAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        final InputConnection ic = super.onCreateInputConnection(outAttrs);
        if (ic != null && outAttrs.hintText == null) {
            // If we don't have a hint and our parent is a TextInputLayout, use it's hint for the
            // EditorInfo. This allows us to display a hint in 'extract mode'.
            final ViewParent parent = getParent();
            if (parent instanceof TextInputLayout) {
                outAttrs.hintText = ((TextInputLayout) parent).getHint();
            }
        }
        return ic;
    }
}
