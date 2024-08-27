#ifndef ANDROID_MENU_RECT_H
#define ANDROID_MENU_RECT_H

class Rect {
public:
  float x;
  float y;
  float w;
  float h;

  Rect() {
    this->x = 0;
    this->y = 0;
    this->w = 0;
    this->h = 0;
  }

  Rect(float x, float y, float w, float h) {
    this->x = x;
    this->y = y;
    this->w = w;
    this->h = h;
  }

  bool operator==(const Rect &src) const {
    return (src.x == this->x && src.y == this->y && src.h == this->h &&
            src.w == this->w);
  }

  bool operator!=(const Rect &src) const {
    return (src.x != this->x && src.y != this->y && src.h != this->h &&
            src.w != this->w);
  }
};

#endif