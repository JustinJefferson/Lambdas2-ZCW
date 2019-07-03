import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class CheckPersonTest {

    List<Person> list = new LinkedList<>();
    Person person1 = new Person("Bob", LocalDate.of(1999, 1, 23), Person.Sex.MALE, "notanemail@gmail.com");
    Person person2 = new Person("Amy", LocalDate.of(1979, 4, 2), Person.Sex.FEMALE, "notanemail@gmail.com");
    Person person3 = new Person("Christoper", LocalDate.of(1992, 7, 13), Person.Sex.MALE, "notanemail@gmail.com");
    Person person4 = new Person("Vivian", LocalDate.of(2002, 5, 5), Person.Sex.FEMALE, "notanemail@gmail.com");
    Person person5 = new Person("Bob", LocalDate.of(1990, 10, 30), Person.Sex.NEITHER, "notanemail@gmail.com");

    @Before
    public void setUp() {

        list.clear();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);

    }

    @Test
    public void localClassTest() {

        //Given
        PersonChecker personChecker = new PersonChecker();
        CheckPerson tester = personChecker.new LocalChecker();

        //When
        List<Person> expected = PersonChecker.checking(list, tester);

        //Then
        assertTrue(hasExpected(expected, person1, person3));
    }

    @Test
    public void anonymousClassTest() {

        //Given
        PersonChecker personChecker = new PersonChecker();
        CheckPerson tester = personChecker.anonymous();

        //When
        List<Person> expected = PersonChecker.checking(list, tester);

        //Then
        assertTrue(hasExpected(expected, person1, person3));
    }

    @Test
    public void lambdaClassTest() {

        //Given
        Predicate<Person> predicate = (person) ->
                person.getGender() == Person.Sex.MALE && person.getAge() >= 18;
        PersonChecker personChecker = new PersonChecker();
        CheckPerson tester = personChecker.new LambdaChecker(predicate);

        //When
        List<Person> expected = PersonChecker.checking(list, tester);

        //Then
        assertTrue(hasExpected(expected, person1, person3));
    }

    @Test
    public void lambdaClassTest1() {

        //Given
        Predicate<Person> predicate = (person) ->
                person.getGender() == Person.Sex.FEMALE;
        PersonChecker personChecker = new PersonChecker();
        CheckPerson tester = personChecker.new LambdaChecker(predicate);

        //When
        List<Person> expected = PersonChecker.checking(list, tester);

        //Then
        assertTrue(hasExpected(expected, person2, person4));
    }

    @Test
    public void lambdaClassTest2() {

        //Given
        Predicate<Person> predicate = (person) ->
                person.getEmailAddress().equals("notanemail@gmail.com");
        PersonChecker personChecker = new PersonChecker();
        CheckPerson tester = personChecker.new LambdaChecker(predicate);

        //When
        List<Person> expected = PersonChecker.checking(list, tester);

        //Then
        assertTrue(hasExpected(expected, list.toArray(new Person[]{})));
    }

    public Boolean hasExpected(List<Person> list, Person... persons) {

        list.stream().forEach(person -> System.out.print(person.getName() + " "));
        System.out.println();
        for(Person person : persons) {
            if(!list.contains(person)) return false;
        }
        return true;
    }
}
