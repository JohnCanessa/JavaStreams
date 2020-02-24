import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 
 */
public class Main {

  /**
   * Return a list of people.
   */
  private static List<Person> getPeople() {

    // **** create array of people ****
    final Person[] people = {
                            new Person("James Bond", 42, Gender.MALE),
                            new Person("Antonio Banderas", 58, Gender.MALE), 
                            new Person("Alina Smith", 33, Gender.FEMALE),
                            new Person("Helen White", 57, Gender.FEMALE), 
                            new Person("Alex Boz", 14, Gender.MALE),
                            new Person("Jamie Goa", 41, Gender.MALE), 
                            new Person("Money Penny", 42, Gender.FEMALE),
                            new Person("Anna Cook", 27, Gender.FEMALE), 
                            new Person("Zelda Brown", 35, Gender.FEMALE),
                            new Person("Dr. No", 42, Gender.MALE)
                            };

    // **** return list of people ****
    return Arrays.asList(people);
  }

  /**
   * Main entry point.
   */
  public static void main(final String[] args) {

    // **** get list of people ****
    final List<Person> people = getPeople();

    // **** **** IMPERATIVE programming approach **** ****

    // **** generate list of females ****
    List<Person> females = new ArrayList<Person>();
    for (Person p : people) {
      if (p.getGender().equals(Gender.FEMALE)) {
        females.add(p);
      }
    }

    // **** display list of females (1) ****
    System.out.println("main <<< females " + females.size() + ": " + females.toString());
    System.out.println();

    // **** display list of females (2) ****
    for (Person f : females) {
      System.out.println("main <<< f: " + f.toString());
    }
    System.out.println();

    // **** display list of females (3) ****
    females.forEach(System.out::println);
    System.out.println();

    // **** **** FUNCTIONAL / DECLARATIVE programming approach **** ****

    // **** generate list of females ****
    List<Person> femaleList = people.stream()                       // abstraction
        .filter(person -> person.getGender().equals(Gender.FEMALE)) // filter
        .collect(Collectors.toList());                              // collect

    // **** display list of females ****
    femaleList.forEach(System.out::println);
    System.out.println();

    // **** generate list of people sorted by age ****
    List<Person> ageList = people.stream()            // abstraction
        .sorted(Comparator.comparing(Person::getAge)  // person age
            .thenComparing(Person::getGender)         // person gender
            .reversed())                              // reversed sort
        .collect(Collectors.toList());                // collect

    // **** display list sorted by age ****
    ageList.forEach(System.out::println);
    System.out.println();

    // **** determine if all people are equal or over ager 14 ****
    boolean allMatch = people.stream()                // abstraction
        .allMatch(p -> p.getAge() >= 14);             // all match

    // **** display result ****
    System.out.println("main <<< allMatch: " + allMatch);

    // **** determine if there are matches when people are older than 60 ****
    boolean anyMatch = people.stream()                          // abstraction
        .anyMatch(p -> p.getAge() > 60);                        // any match

    // **** display result ****
    System.out.println("main <<< anyMatch: " + anyMatch);

    // **** check if we have do not have a person with the specified name ****
    boolean noneMatch = people.stream()                         // abstraction
        .noneMatch(p -> p.getName().equals("James Candy"));     // none match

    // **** display result ****
    System.out.println("main <<< noneMatch: " + noneMatch);
    System.out.println();

    // **** find oldest person (what if such person does not exist?) ****
    Optional<Person> max = people.stream()                      // abstraction
      .max(Comparator.comparing(Person::getAge));               // max age
    System.out.println("main <<< oldest: " + max);

    // **** find oldest person; if found display element ****
    people.stream()                                             // abstraction
      .max(Comparator.comparing(Person::getAge))                // max age
      .ifPresent(p -> {                                         // if found
        System.out.println(p);                                  // display the person
      });
    System.out.println();

    // **** find youngest person; if found display element ****
    people.stream()                                             // abstraction
      .min(Comparator.comparing(Person::getAge))                // min age
      .ifPresent(p -> {
        System.out.println("main <<< youngest: " + p);          // display the person
      });
    System.out.println();                     

    // **** group ****
    Map<Gender, List<Person>> groupByGender = people.stream()   // abstraction
      .collect(Collectors.groupingBy(Person::getGender));       // group
    
    // **** display results ****
    for (Map.Entry<Gender, List<Person>> entry : groupByGender.entrySet()) {
      Gender gender = entry.getKey();
      System.out.println(gender);
      List<Person> list = entry.getValue();
      for (Person p : list) {
        System.out.println(p.toString());
      }
    }
    System.out.println();

    // **** display same results ****
    groupByGender.forEach((gender, p) -> {
      System.out.println(gender);
      p.forEach(System.out::println);
    });
    System.out.println();

    // **** get and display oldest female name  ****
    Optional<String> oldestFemaleAge = people.stream()          // abstratcion
      .filter(p -> p.getGender().equals(Gender.FEMALE))         // filter
      .max(Comparator.comparing(Person::getAge))                // max
      .map(Person::getName);                                    // map

    oldestFemaleAge.ifPresent(name -> System.out.println("oldest female ==>" + name + "<=="));
  }
}