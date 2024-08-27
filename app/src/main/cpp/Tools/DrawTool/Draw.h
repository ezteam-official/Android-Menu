#include "Color.h"
#include "../Vectors/Vector2.h"
#include "../Vectors/Rect.h"

#ifndef ANDROID_MENU_DRAW_H
#define ANDROID_MENU_DRAW_H

class DrawView
{
private:
  JNIEnv *_env;
  jobject _cvsView;
  jobject _cvs;

public:
  DrawView()
  {
    _env = nullptr;
    _cvsView = nullptr;
    _cvs = nullptr;
  }

  DrawView(JNIEnv *env, jobject cvsView, jobject cvs)
  {
    this->_env = env;
    this->_cvsView = cvsView;
    this->_cvs = cvs;
  }

  bool isValid() const
  {
    return (this->_env != nullptr && this->_cvsView != nullptr && this->_cvs != nullptr);
  }

  int getWidth() const
  {
    if (isValid())
    {
      return _env->CallIntMethod(_cvs, _env->GetMethodID(_env->GetObjectClass(_cvs), "getWidth", "()I"));
    }
    return 0;
  }

  int getHeight() const
  {
    if (isValid())
    {
      return _env->CallIntMethod(_cvs, _env->GetMethodID(_env->GetObjectClass(_cvs), "getHeight", "()I"));
    }
    return 0;
  }

  void DrawLine(Color color, float thickness, Vector2 start, Vector2 end)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawLine", "(Landroid/graphics/Canvas;IIIIFFFFF)V"), _cvs, (int) color.a, (int) color.r, (int) color.g, (int) color.b, thickness, start.X, start.Y, end.X, end.Y);
    }
    return;
  }

  void DrawText(Color color, const char * str, Vector2 pos, float size)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawText", "(Landroid/graphics/Canvas;IIIIFLjava/lang/String;FFF)V"), _cvs, (int) color.a, (int) color.r, (int) color.g, (int) color.b, 0.6f, _env->NewStringUTF(str), pos.X, pos.Y, size);
    }
    return;
  }

  void DrawCircle(Color color, float stroke, Vector2 pos, float radius)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawCircle", "(Landroid/graphics/Canvas;IIIIFFFF)V"), _cvs, (int) color.a, (int) color.r, (int) color.g, (int) color.b, stroke, pos.X, pos.Y, radius);
    }
    return;
  }

  void DrawRoundRect(Color color, float stroke, int cx, int cy, Rect rect)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawRoundRect", "(Landroid/graphics/Canvas;IIIIFIIFFFF)V"), _cvs, (int) color.a, (int) color.r, (int) color.g, (int) color.b, stroke, cx,cy, rect.x, rect.y, rect.w, rect.h);
    }
    return;
  }

  void DrawTextRect(Color color, int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, Vector2 pos)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawTextRect", "(Landroid/graphics/Canvas;IIIIIIIIIIIIFF)V"), _cvs, (int) color.a, (int) color.r,(int) color.g, (int) color.b, a1, a2, a3, a4, a5, a6, a7, a8, pos.X, pos.Y);
    }
    return;
  }

  void DrawFilledBox(Color color, Rect rect)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawFilledRect", "(Landroid/graphics/Canvas;IIIIFFFF)V"), _cvs, (int) atoi("157"), (int) color.r, (int) color.g, (int) color.b, rect.x, rect.y, rect.w, rect.h);
    }
    return;
  }

  void DrawFilledRect(Color color, Rect rect)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawFilledRect", "(Landroid/graphics/Canvas;IIIIFFFF)V"), _cvs, (int) color.a, (int) color.r,(int) color.g, (int) color.b, rect.x, rect.y, rect.w, rect.h);
    }
    return;
  }

  void DrawFilledCircle(Color color, Vector2 pos, float radius)
  {
    if (isValid())
    {
      _env->CallVoidMethod(_cvsView, _env->GetMethodID(_env->GetObjectClass(_cvsView), "DrawFilledCircle", "(Landroid/graphics/Canvas;IIIIFFF)V"), _cvs, (int) color.a, (int) color.r, (int) color.g, (int) color.b, pos.X, pos.Y, radius);
    }
    return;
  }

  void DrawBox(Color color, float stroke, Rect rect)
  {
      Vector2 v1 = Vector2(rect.x, rect.y);
    Vector2 v2 = Vector2(rect.x + rect.w, rect.y);
    Vector2 v3 = Vector2(rect.x + rect.w, rect.y + rect.h);
    Vector2 v4 = Vector2(rect.x, rect.y + rect.h);

    DrawLine(color, stroke, v1, v2); // LINE UP
    DrawLine(color, stroke, v2, v3); // LINE RIGHT
    DrawLine(color, stroke, v3, v4); // LINE DOWN
    DrawLine(color, stroke, v4, v1); // LINE LEFT
  }

  void DrawCornerBox(Color color, float stroke, Rect rect, int cx, int cy)
  {
    DrawLine(color, stroke, Vector2(rect.x, rect.y), Vector2(rect.x + (rect.w / cx), rect.y));
    DrawLine(color, stroke, Vector2(rect.x, rect.y), Vector2(rect.x, rect.y + (rect.h / cy)));

    DrawLine(color, stroke, Vector2(rect.x + rect.w, rect.y), Vector2(rect.x + rect.w - (rect.w / cx), rect.y));
    DrawLine(color, stroke, Vector2(rect.x + rect.w, rect.y), Vector2(rect.x + rect.w, rect.y + (rect.h / cy)));

    DrawLine(color, stroke, Vector2(rect.x, rect.y + rect.h), Vector2(rect.x + (rect.w / cx), rect.y + rect.h));
    DrawLine(color, stroke, Vector2(rect.x, rect.y + rect.h), Vector2(rect.x, rect.y + rect.h - (rect.h / cy)));

    DrawLine(color, stroke, Vector2(rect.x + rect.w, rect.y + rect.h), Vector2(rect.x + rect.w - (rect.w / cx), rect.y + rect.h));
    DrawLine(color, stroke, Vector2(rect.x + rect.w, rect.y + rect.h), Vector2(rect.x + rect.w, rect.y + rect.h - (rect.h / cy)));
  }
};

#endif