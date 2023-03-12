# Effective C++

## view c++ as a federation of languages

C++ contains:

* C

* Object-Oriented C++ (C with classes)

* Template C++

* STL

## Prefer consts, enums and inlines to #define

When you define a variable with #define (like # define RATIO 1.5), the RATIO maybe replaced by 1.5 before compile. If you got a compile error, It will shows 1.5 rather than RATIO, It's not easy the find the bug was caused by RATIO.

**For simple constant, use consts or enums instead of #define.**
