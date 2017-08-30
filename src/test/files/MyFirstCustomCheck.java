class MyClass {
  MyClass(MyClass mc) { }

  int     foo1() { return 0; }
  void    foo2(int value) { }
  int     foo3(int value) { return 0; } // Noncompliant
  Object  foo4(int value) { return null; }
  MyClass foo5(MyClass value) {return null; } // Noncompliant

  int     foo6(int value, String name) { return 0; }
  int     foo7(int ... values) { return 0;}
}

/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/

/*  1.
    /src/test/files/
    MyFirstCustomCheck.java
    (para ser revisado)

    Because the flagged lines do not comply with the rule.

 */


