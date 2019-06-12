package com.developer.mtextfield;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

class CompositeListener implements View.OnFocusChangeListener {

    private final List<View.OnFocusChangeListener> registeredListeners = new ArrayList<>();

    void registerListener (View.OnFocusChangeListener listener) {
        registeredListeners.add(listener);
    }

    void clearListeners() {
        registeredListeners.clear();
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        for(View.OnFocusChangeListener listener:registeredListeners) {
            listener.onFocusChange(view, b);
        }
    }
}
