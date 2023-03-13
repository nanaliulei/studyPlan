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

## Use const whenever possible

**declare variable as const:**

```
char greeting[] = "Hello";
char* p = greeting;                // non-const pointer, non-const data
const char* p = greeting;          // non-const pointer, const data
char* const p = greeting;          // const pointer, non-const data
const char* const p = greeting;    // const pointer, const data
```

**declare things as const when possible can make compiler detect errors**

declare function's return value as const, can avoid error like function() = a.

**member function of class can overload with difference constness**

```
class A {
    void functionA();        // mark as 1
    void functionA() const;  // mark as 2
}

A a;
const A constA;
a.functionA();        // call 1
constA.functionA();   // call 2
```

**when member function and const member function have same implement, non-const function can call the const function to avoid repeat**
