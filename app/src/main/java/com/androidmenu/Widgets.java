package com.androidmenu;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.graphics.drawable.GradientDrawable;
import android.graphics.PorterDuff;
import android.widget.Switch;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.SeekBar;

public class Widgets {

    //-- Context Global --\\
    static Context context;

    //-- Utils Global --\\
    static Utils utils;

    //-- LinearLayout Features Global --\\
    static LinearLayout containerFeatures;

    //-- Variable Globals --\\
    static int PRIMARY_COLOR;
    static int SECONDARY_COLOR;
    static int TEXT_COLOR;

    //-- Initialize Class --\\
    public Widgets(Context globContext, LinearLayout globContainerFeatures, int globPRIMARY_COLOR, int globSECONDARY_COLOR, int globTEXT_COLOR) {
        utils = new Utils(globContext);
        context = globContext;
        PRIMARY_COLOR = globPRIMARY_COLOR;
        SECONDARY_COLOR = globSECONDARY_COLOR;
        TEXT_COLOR = globTEXT_COLOR;
        containerFeatures = globContainerFeatures;
    }

    /*
    Move this method to JNI [ public native void onChanges(int ID, int value); ]
    @int ID : Feature ID
    @int value : Value SeekBar
    */
    public static native void onChanges(int ID, int value);

    public native void onFeatures();

    /*
    @String name : Category name
    */
    public static void addCategory(String name) {
        LinearLayout.LayoutParams params_category = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, utils.syncDPI(30));
        params_category.setMargins(utils.syncDPI(4), 0, utils.syncDPI(4), utils.syncDPI(8));

        GradientDrawable gradient_category = new GradientDrawable();
        gradient_category.setColor(PRIMARY_COLOR);
        gradient_category.setCornerRadius(utils.syncDPI(4));

        TextView text_category = new TextView(context);
        text_category.setLayoutParams(params_category);
        text_category.setText(name);
        text_category.setTextColor(TEXT_COLOR);
        text_category.setTextSize(14);
        text_category.setBackground(gradient_category);
        text_category.setAllCaps(false);
        text_category.setGravity(Gravity.CENTER);

        containerFeatures.addView(text_category);
    }

    /*
    @String name : Feature name
    @int ID : Feature ID
    */
    public static void addCheckBox(String name, final int ID) {
        LinearLayout.LayoutParams params_checkbox = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params_checkbox.setMargins(utils.syncDPI(4), 0, utils.syncDPI(4), utils.syncDPI(8));

        final CheckBox checkBox = new CheckBox(context);
        checkBox.setLayoutParams(params_checkbox);
        checkBox.setText(name);
        checkBox.setTextSize(14);
        checkBox.setTextColor(TEXT_COLOR);
        checkBox.getButtonDrawable().setColorFilter(SECONDARY_COLOR, PorterDuff.Mode.SRC_IN);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                if (check) {
                    checkBox.getButtonDrawable().setColorFilter(PRIMARY_COLOR, PorterDuff.Mode.SRC_IN);
                } else {
                    checkBox.getButtonDrawable().setColorFilter(SECONDARY_COLOR, PorterDuff.Mode.SRC_IN);
                }
                onChanges(ID, 0);
            }
        });

        containerFeatures.addView(checkBox);
    }

    /*
    @String name : Feature name
    @int ID : Feature ID
    */
    public static void addSwitch(String name, final int ID) {
        LinearLayout.LayoutParams params_switch = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params_switch.setMargins(utils.syncDPI(4), 0, utils.syncDPI(4), utils.syncDPI(8));

        final Switch w_switch = new Switch(context);
        w_switch.setLayoutParams(params_switch);
        w_switch.setPadding(utils.syncDPI(5), 0, 0, 0);
        w_switch.setText(name);
        w_switch.setTextSize(14);
        w_switch.setTextColor(TEXT_COLOR);
        w_switch.getThumbDrawable().setColorFilter(SECONDARY_COLOR, PorterDuff.Mode.SRC_IN);
        w_switch.getTrackDrawable().setColorFilter(SECONDARY_COLOR, PorterDuff.Mode.SRC_IN);
        w_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                if (check) {
                    w_switch.getThumbDrawable().setColorFilter(PRIMARY_COLOR, PorterDuff.Mode.SRC_IN);
                    w_switch.getTrackDrawable().setColorFilter(PRIMARY_COLOR, PorterDuff.Mode.SRC_IN);
                } else {
                    w_switch.getThumbDrawable().setColorFilter(SECONDARY_COLOR, PorterDuff.Mode.SRC_IN);
                    w_switch.getTrackDrawable().setColorFilter(SECONDARY_COLOR, PorterDuff.Mode.SRC_IN);
                }
                onChanges(ID, 0);
            }
        });

        containerFeatures.addView(w_switch);
    }

    /*
    @String name : Feature name
    @int progress : SeekBar initial progress
    @int max : SeekBar maximum value
    @int ID : Feature ID
    */
    public static void addSeekBar(final String name, int progress, int max, final String type, final int ID) {
        LinearLayout.LayoutParams params_container = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params_container.setMargins(utils.syncDPI(4), 0, utils.syncDPI(4), utils.syncDPI(8));

        LinearLayout container = new LinearLayout(context);
        container.setLayoutParams(params_container);
        container.setPadding(utils.syncDPI(5), 0, utils.syncDPI(5), 0);
        container.setOrientation(LinearLayout.VERTICAL);

        final TextView text_value = new TextView(context);
        text_value.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text_value.setText(name.concat(": ") + progress + type);
        text_value.setTextSize(14);
        text_value.setTextColor(TEXT_COLOR);

        final SeekBar seekBar = new SeekBar(context);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        seekBar.setProgress(progress);
        seekBar.getThumb().setColorFilter(PRIMARY_COLOR, PorterDuff.Mode.SRC_IN);
        seekBar.getProgressDrawable().setColorFilter(PRIMARY_COLOR, PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar p1, int p2, boolean p3) {
                text_value.setText(name.concat(": ") + p2 + type);
                onChanges(ID, p2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar p1) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar p1) {

            }
        });

        containerFeatures.addView(container);
        container.addView(text_value);
        container.addView(seekBar);

    }

}
