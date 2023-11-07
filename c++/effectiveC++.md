# Effective C++

## 1 view c++ as a federation of languages

C++ contains:

* C

* Object-Oriented C++ (C with classes)

* Template C++

* STL

## 2 Prefer consts, enums and inlines to #define

When you define a variable with #define (like # define RATIO 1.5), the RATIO maybe replaced by 1.5 before compile. If you got a compile error, It will shows 1.5 rather than RATIO, It's not easy the find the bug was caused by RATIO.

**For simple constant, use consts or enums instead of #define.**

## 3 Use const whenever possible

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

## 4 Make sure that objects are initialized before they're used

**initialize vs assignment**

assignment

```
Person::Person(const std::string& name, const std::string& gender) {
    name = name;
    gender = gender;
}
```

initialize

```
Person::Person(const std::string& name, const std::string& gender) : name(name), gender(gender) {}
```

Initialize is better than assignment in most situation. Assignment will first initialize with default value, and then assign new value. It will cost more resource.

**initialize sequence**

First base class, then derived class. Member variable in the order they are declared.

**local static can be very useful in some situation, but need to pay attention to thread safe**

## 5 Know what functions c++ silently writes and calls

When declare a class, c++ will automatically create **copy construct** method, **assignment construct** method, **destructor** method and a **default construct** method if not exists.

```
class Empty {}


class Empty {
    Empty() { ... }
    Empty(const Empty& empty) { ... }
    ~Empty() { ... }
    Empty& operator=(const Empty& empty) { ... }
}
```

## 6 Explicitly disallow the use of compiler-generated functions you do not want

If you don't want a compiler-generated function, you could declare it as private and do not implement it.

```
class Home {
    public:
        ...
    private:
        Home(const Home&);             --do not generate copy construct
        Home& operator=(const Home&);  --do not generate assignment construct
}
```

## 7 Declare destructors virtual in polymorphic base classes

If a class has s virtual method, It also should have a virtual destructor.

if a class doesn't have a virtual method, It should not have a virtual destructor.

## 8 Prevent exception from leaving destructor

Do not throw exception in destructor. If destructor call a function that may throw exception, destructor should catch and ignore it or end the program.

If a function may throw exception and others want to handle the exception, class should provide a normal function rather than do it in destructor.
