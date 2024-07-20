#ifndef ANDROID_MENU_WIDGETS_H
#define ANDROID_MENU_WIDGETS_H

#include "Category.h"
#include "Switch.h"
#include "CheckBox.h"
#include "SeekBar.h"

class Widgets {

private:
    JNIEnv* env;
    Category category;
    Switch aSwitch;
    CheckBox checkBox;
    SeekBar seekBar;

public:
    Widgets(JNIEnv* glob_JNIEnv) {
        env = glob_JNIEnv;
    }

    void Category(const char* name) {
        category.create(env, name);
    }

    void Switch(const char* name, int ID) {
        aSwitch.create(env, name, ID);
    }

    void CheckBox(const char* name, int ID) {
        checkBox.create(env, name, ID);
    }

    void SeekBar(const char* name, int value, int max, const char* type, int ID) {
        seekBar.create(env, name, value, max, type, ID);
    }

};

#endif