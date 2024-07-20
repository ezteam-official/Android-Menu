package com.androidmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.content.res.ColorStateList;

public class Menu {

    //-- Variables Globals --\\
    int ICON_SIZE = 70;
    int MENU_WIDTH = 220;
    int MENU_HEIGHT = 280;
    int BACKGROUND_MENU_COLOR = 0xFF212121;
    int PRIMARY_MENU_COLOR = 0xFF0182FF;
    int SECONDARY_MENU_COLOR = 0xFF383838;
    int TEXT_COLOR = 0xFFFFFFFF;
    String NAME_TEAM = "<font color='#0182FF'>Android</font>Menu"; //Support HTML tags

    //-- Context Global --\\
    Context context;

    //-- Utils Global --\\
    Utils utils;

    //-- Widgets Global --\\
    Widgets widgets;

    //-- Important widgets --\\
    WindowManager windowManager;
    WindowManager.LayoutParams windowManagerParams;
    FrameLayout frameLayout;

    //-- Layout menu widgets --\\
    ImageBase64 imageIcon;
    TextView textName;
    LinearLayout container, container_menu, container_top, container_center, container_bottom;
    ScrollView scroll_center;
    LinearLayout container_features;
    Button close;

    //-- Initialize Class --\\
    public Menu(Context globContext) {
        context = globContext;
        utils = new Utils(globContext);
        Initialized();
    }

    //-- Initialize methods --\\
    public void Initialized() {
        onCreateSystemWindow();
        onCreateLayout();
        widgets = new Widgets(context, container_features, PRIMARY_MENU_COLOR, SECONDARY_MENU_COLOR, TEXT_COLOR);
        System.loadLibrary("AndroidMenu");
        widgets.onFeatures();
        Log.i("Android-Menu", "Initialized : SUCCESS");
    }

    //-- Menu layout --\\
    public void onCreateLayout() {
        container = new LinearLayout(context);
        container.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        imageIcon = new ImageBase64(context);
        imageIcon.setLayoutParams(new LinearLayout.LayoutParams(utils.syncDPI(ICON_SIZE), utils.syncDPI(ICON_SIZE)));
        imageIcon.setImageBase64(new Images().imageIcon);
        imageIcon.setOnTouchListener(onMove());
        imageIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imageIcon.setVisibility(View.GONE);
                container_menu.setVisibility(View.VISIBLE);
                Log.i("Android-Menu", "onChangedIconVisibility : GONE");
            }
        });

        container_menu = new LinearLayout(context);
        container_menu.setLayoutParams(new LinearLayout.LayoutParams(utils.syncDPI(MENU_WIDTH), utils.syncDPI(MENU_HEIGHT)));
        container_menu.setBackgroundColor(BACKGROUND_MENU_COLOR);
        container_menu.setOrientation(LinearLayout.VERTICAL);
        container_menu.setVisibility(View.GONE);

        container_top = new LinearLayout(context);
        container_top.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        container_top.setPadding(utils.syncDPI(5), utils.syncDPI(5), utils.syncDPI(5), utils.syncDPI(5));
        container_top.setGravity(Gravity.CENTER);

        textName = new TextView(context);
        textName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textName.setText(Html.fromHtml(NAME_TEAM));
        textName.setTextColor(TEXT_COLOR);
        textName.setTextSize(24);

        container_center = new LinearLayout(context);
        container_center.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        scroll_center = new ScrollView(context);
        scroll_center.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        container_features = new LinearLayout(context);
        container_features.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        container_features.setOrientation(LinearLayout.VERTICAL);

        container_bottom = new LinearLayout(context);
        container_bottom.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, utils.syncDPI(50)));
        container_bottom.setGravity(Gravity.BOTTOM | Gravity.RIGHT);

        GradientDrawable gradient_close = new GradientDrawable();
        gradient_close.setColor(PRIMARY_MENU_COLOR);

        RippleDrawable ripple_close = new RippleDrawable(ColorStateList.valueOf(0xFF212121), gradient_close, null);

        close = new Button(context);
        close.setLayoutParams(new LinearLayout.LayoutParams(utils.syncDPI(100), utils.syncDPI(35)));
        close.setText("CLOSE");
        close.setTextColor(TEXT_COLOR);
        close.setTextSize(12);
        close.setPadding(0, 0, 0, 0);
        close.setBackground(ripple_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageIcon.setVisibility(View.VISIBLE);
                container_menu.setVisibility(View.GONE);
                Log.i("Android-Menu", "onChangedMenuVisibility : GONE");
            }
        });

        frameLayout.addView(container);
        container.addView(imageIcon);
        container.addView(container_menu);

        container_menu.addView(container_top);
        container_top.addView(textName);
        container_menu.addView(container_center);
        container_center.addView(scroll_center);
        scroll_center.addView(container_features);
        container_menu.addView(container_bottom);
        container_bottom.addView(close);

        Log.i("Android-Menu", "onCreateLayout : SUCCESS");
    }

    //-- System window --\\
    private void onCreateSystemWindow()
    {
        frameLayout = new FrameLayout(context);
        frameLayout.setAlpha(0.95f);
        frameLayout.setOnTouchListener(onMove());

        windowManager = ((Activity)context).getWindowManager();
        windowManagerParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 30, 50, WindowManager.LayoutParams.TYPE_APPLICATION, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH, PixelFormat.TRANSPARENT);
        windowManagerParams.gravity = Gravity.TOP | Gravity.LEFT;

        windowManager.addView(frameLayout, windowManagerParams);
        Log.i("Android-Menu", "onCreateSystemWindow : SUCCESS");
    }

    //-- Menu drag logic --\\
    private View.OnTouchListener onMove() {
        return new View.OnTouchListener() {

            private int x;
            private int y;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                        frameLayout.setAlpha(0.85f);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int nowX = (int) event.getRawX();
                        int nowY = (int) event.getRawY();
                        int movedX = nowX - x;
                        int movedY = nowY - y;
                        x = nowX;
                        y = nowY;
                        windowManagerParams.x = windowManagerParams.x + movedX;
                        windowManagerParams.y = windowManagerParams.y + movedY;
                        windowManager.updateViewLayout(frameLayout, windowManagerParams);
                        Log.i("Android-Menu", "onMove : " + windowManagerParams.x + "x" + windowManagerParams.y);
                        break;

                    case MotionEvent.ACTION_UP:
                        frameLayout.setAlpha(0.95f);
                        break;

                    default:
                        break;
                }
                return false;
            }
        };
    }
}