Explanations:
===================


- The `super` keyword is used to call a method of the parent class (super class) of a class. 
Example: `
class Test extends Base {
void doSomething() {
    super.doSomething();
    super.DoSomethingElse();
}` 
It can also be used to call the constructor of the parent class:
`class Test extends Base {
void Test() {
     super();
}
`


- `this` references a method or variable of the current class.
Example: `this.day` can be used to get the class variable `day` instead of a local variable `day`.

- `static` makes a method/variable usable without instantiating an object from a class. a static method can only reference other static variables/methods or its own arguments.
Example: 
`class MyClass {
   static int add(int a, int b){..} 
}`
`add` can be used as `MyClass.add` without creating an object of type MyClass.

- `null`: is a special reference type which has no value. All variables are initialized as null. A null value can be assigned to an object reference of any type. 
Example: `Integer returnNull() { return null; }` is valid, yet `int returnNull() { return null; }` is not.

- A `Local variable`: is a variable that is defined in a method or block. This variable can not be used out of the scope of the method or block.
Example: 
` void MyFunc() {
   int abc = 123;
  }`
  In this case, `abc` is a local variable.

- An `Instance variable` is a variable that is a member of an instance of a class.
Example:
`class Test {
int ps;
}`
When creating an object of type `Test` using `Test test = new Test()`, this object has an instance variable `ps`.

- A `Class variable` is a variable that is member of a class. These variables have a class-level scope, meaning, they are available from anywhere in the class. 
Example:
`class Test {
int ps;
}`
`ps` is a class variable.

- A `Constructor` is a method with the same name as the class name. It is used to create an object (instance) of a class. There can be multiple Constructors with different signatures in a class. 
Example: 
` class Test {
  public Test() { }
  }`
  `Test()` is a constructor of the class Test.

