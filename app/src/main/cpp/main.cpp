#include <jni.h>
#include <android/log.h>

#define LOG_TAG "AndroidMenu-LOG"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#include "Widgets/Widgets.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_androidmenu_Widgets_onFeatures(JNIEnv *env, jobject thiz) {

    LOGI("onFeatures : Loaded!");

    //-- Create Widgets Class
    auto widgets = Widgets(env);

    //-- Create Widgets
    widgets.Category("Category");
    widgets.Switch("Switch", 0);
    widgets.CheckBox("CheckBox", 1);
    widgets.SeekBar("SeekBar", 0, 10, "x", 2);

    LOGI("Widgets : Created!");

}

extern "C"
JNIEXPORT void JNICALL
Java_com_androidmenu_Widgets_onChanges(JNIEnv *env, jclass clazz, jint id, jint value) {

    LOGI("onChanges : Loaded!");
    LOGI("ID => %i | Value => %i", id, value);

    switch (id) {
        case 1:
            LOGI("CASE 1 : Loaded!");
            break;
        case 2:
            LOGI("CASE 2 : Loaded!");
            break;
        case 3:
            LOGI("CASE 3 : Loaded!");
            break;
    }
    
}