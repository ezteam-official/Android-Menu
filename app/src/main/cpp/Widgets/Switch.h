#ifndef ANDROID_MENU_SWITCH_H
#define ANDROID_MENU_SWITCH_H

class Switch {

public:
    void create(JNIEnv* env, const char* name, jint ID) {
        jclass CWidgets = env->FindClass("com/androidmenu/Widgets");
        jmethodID MAddSwitch = env->GetStaticMethodID(CWidgets, "addSwitch", "(Ljava/lang/String;I)V");
        return env->CallStaticVoidMethod(CWidgets, MAddSwitch, env->NewStringUTF(name), ID);
    }

};

#endif