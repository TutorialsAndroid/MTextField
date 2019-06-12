package com.developer.mtextfield;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import android.util.AttributeSet;

public class ExtendedEditText extends TextInputAutoCompleteTextView {

    private int DEFAULT_TEXT_COLOR;
    private OnFocusChangeListener defaultFocusListener;
    private final CompositeListener focusListener = new CompositeListener();

    /**
     * prefix Label text at the start.
     */
    private String prefix;

    /**
     * suffix Label text at the end.
     */
    private String suffix;

    /**
     * Prefix text color. DEFAULT_TEXT_COLOR by default.
     */
    private int prefixTextColor;

    /**
     * Suffix text color. DEFAULT_TEXT_COLOR by default.
     */
    private int suffixTextColor;

    public ExtendedEditText(Context context) {

        this(context, null);
        super.setOnFocusChangeListener(focusListener);
        initDefaultColor();
    }

    public ExtendedEditText(Context context, AttributeSet attrs) {

        this(context, attrs, android.R.attr.editTextStyle);
        super.setOnFocusChangeListener(focusListener);
        initDefaultColor();
        handleAttributes(context, attrs);
    }

    public ExtendedEditText(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        super.setOnFocusChangeListener(focusListener);
        initDefaultColor();
        handleAttributes(context, attrs);
    }

    private void initDefaultColor() {

        Resources.Theme theme = getContext().getTheme();
        TypedArray themeArray;

        /* Get Default Text Color From Theme */
        themeArray = theme.obtainStyledAttributes(new int[]{android.R.attr.textColorTertiary});
        DEFAULT_TEXT_COLOR = themeArray.getColor(0, 0);

        themeArray.recycle();
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        /* Texts */
        setPrefix(this.prefix);
        setSuffix(this.suffix);

        /* Colors */
        setPrefixTextColor(this.prefixTextColor);
        setSuffixTextColor(this.suffixTextColor);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {

        focusListener.clearListeners();
        focusListener.registerListener(defaultFocusListener);
        focusListener.registerListener(l);
    }

    void setDefaultOnFocusChangeListener(OnFocusChangeListener l) {

        defaultFocusListener = l;
        focusListener.registerListener(l);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        try {

            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.ExtendedEditText);

            /* Texts */
            this.prefix = styledAttrs.getString(R.styleable.ExtendedEditText_prefix)
                    == null ? "" : styledAttrs.getString(R.styleable.ExtendedEditText_prefix);
            this.suffix = styledAttrs.getString(R.styleable.ExtendedEditText_suffix)
                    == null ? "" : styledAttrs.getString(R.styleable.ExtendedEditText_suffix);

            /* Colors */
            this.prefixTextColor = styledAttrs
                    .getInt(R.styleable.ExtendedEditText_prefixTextColor, DEFAULT_TEXT_COLOR);
            this.suffixTextColor = styledAttrs
                    .getInt(R.styleable.ExtendedEditText_suffixTextColor, DEFAULT_TEXT_COLOR);
            styledAttrs.recycle();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TextDrawable extends Drawable {

        private TextDrawable() {

            setBounds(0, 0, (int) getPaint().measureText(prefix) + 2, (int) getTextSize());
            setPadding(0, 0, (int) getPaint().measureText(suffix) - 2, 0);
        }

        @Override
        public void draw(@NonNull Canvas canvas) {

            final int lineBase = getLineBounds(0, null);
            final int lineBottom = getLineBounds(getLineCount() - 1, null);
            final float endX = getWidth() - getPaint().measureText(suffix) - 2;

            Paint paint = getPaint();
            paint.setColor(prefixTextColor);
            canvas.drawText(prefix, 0, canvas.getClipBounds().top + lineBase, paint);
            paint.setColor(suffixTextColor);
            canvas.drawText(suffix, endX, canvas.getClipBounds().top + lineBottom, paint);
        }

        @Override
        public void setAlpha(int alpha) {/* Not supported */}

        @Override
        public void setColorFilter(ColorFilter colorFilter) {/* Not supported */}

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }
    }

    private void setPrefix(String prefix) {

        this.prefix = prefix;
        this.setCompoundDrawables(new TextDrawable(), null, null, null);
    }

    private void setSuffix(String suffix) {

        this.suffix = suffix;
        this.setCompoundDrawables(new TextDrawable(), null, null, null);
    }

    private void setPrefixTextColor(int color) {
        this.prefixTextColor = color;
    }

    private void setSuffixTextColor(int color) {
        this.suffixTextColor = color;
    }
}
