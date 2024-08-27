#include "../Tools/DrawTool/Draw.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_androidmenu_Menu_OnDrawLoad(JNIEnv *env, jclass clazz, jobject drawView, jobject canvas) {
    DrawView draw = DrawView(env, drawView, canvas);
    if (draw.isValid()) {
        draw.DrawText(Color::Blue(), "Android Menu", Vector2(draw.getWidth() / 2, draw.getHeight() / 8), Vars::textSize);
    }
}