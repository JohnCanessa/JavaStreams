/**
 * 
 */
public class Person {

  // **** ****
  private String name;
  private int age;
  private Gender gender;

  /**
   * constructor
   */
  public Person(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  /**
   * Getters.
   */
  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public Gender getGender() {
    return this.gender;
  }

  /**
   * toString
   */
  @Override
  public String toString() {
    return "Person{" +
      "name ==>" + this.name + "<==" + 
      " age: " + this.age + 
      " gender: " + this.gender + 
      "}";
  }
}