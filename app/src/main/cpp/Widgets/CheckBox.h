#ifndef ANDROID_MENU_CHECKBOX_H
#define ANDROID_MENU_CHECKBOX_H

class CheckBox {

public:
    void create(JNIEnv* env, const char* name, jint ID) {
        jclass CWidgets = env->FindClass("com/androidmenu/Widgets");
        jmethodID MAddCheckBox = env->GetStaticMethodID(CWidgets, "addCheckBox", "(Ljava/lang/String;I)V");
        return env->CallStaticVoidMethod(CWidgets, MAddCheckBox, env->NewStringUTF(name), ID);
    }

};

#endif