#ifndef ANDROID_MENU_CATEGORY_H
#define ANDROID_MENU_CATEGORY_H

class Category {

public:
    void create(JNIEnv* env, const char* name) {
        jclass CWidgets = env->FindClass("com/androidmenu/Widgets");
        jmethodID MAddCategory = env->GetStaticMethodID(CWidgets, "addCategory", "(Ljava/lang/String;)V");
        return env->CallStaticVoidMethod(CWidgets, MAddCategory, env->NewStringUTF(name));
    }

};

#endif