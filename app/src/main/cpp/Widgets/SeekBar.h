#ifndef ANDROID_MENU_SEEKBAR_H
#define ANDROID_MENU_SEEKBAR_H

class SeekBar {

public:
    void create(JNIEnv* env, const char* name, jint value, jint max, const char* type,jint ID) {
        jclass CWidgets = env->FindClass("com/androidmenu/Widgets");
        jmethodID MAddSeekBar = env->GetStaticMethodID(CWidgets, "addSeekBar", "(Ljava/lang/String;IILjava/lang/String;I)V");
        return env->CallStaticVoidMethod(CWidgets, MAddSeekBar, env->NewStringUTF(name), value, max, env->NewStringUTF(type), ID);
    }

};

#endif