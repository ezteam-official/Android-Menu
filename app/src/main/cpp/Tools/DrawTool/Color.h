#ifndef ANDROID_MENU_COLOR_H
#define ANDROID_MENU_COLOR_H

class Color
{
public:
  float r;
  float g;
  float b;
  float a;

  Color() : r(0), g(0), b(0), a(0) {}

  Color(float r, float g, float b, float a) : r(r), g(g), b(b), a(a) {}

  Color(float r, float g, float b) : r(r), g(g), b(b), a(255) {}

  static Color Red()
  {
    return Color(255, 0, 0);
  }

  static Color Green()
  {
    return Color(0, 255, 0);
  }

  static Color White()
  {
    return Color(255, 255, 255);
  }

  static Color Black()
  {
    return Color(0, 0, 0);
  }

  static Color Yellow()
  {
    return Color(255, 255, 0);
  }

  static Color Blue()
  {
    return Color(1, 130, 255);
  }

};

#endif