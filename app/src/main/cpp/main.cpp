#include <jni.h>
#include <android/log.h>
#include <thread>

#include "Tools/KittyMemory/Import.h"
#include "Tools/SubstrateHook/Import.h"
#include "Hack/Import.h"

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
    widgets.Category("Draw Config.");
    widgets.SeekBar("Draw Size", Vars::textSize, 50, "px", 0);

    LOGI("Widgets : Created!");

}

extern "C"
JNIEXPORT void JNICALL
Java_com_androidmenu_Widgets_onChanges(JNIEnv *env, jclass clazz, jint id, jint value) {
    LOGI("onChanges : Loaded!");
    LOGI("ID => %i | Value => %i", id, value);

    switch (id) {
        case 0:
            Vars::textSize = value;
            break;
    }
}

__attribute__((constructor))
void libMain() {
    pthread_t processThreadID;
    pthread_create(&processThreadID, nullptr, hackThread, nullptr);
    LOGI("Thread => Created");
}
