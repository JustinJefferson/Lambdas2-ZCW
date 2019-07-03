import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class PersonChecker {

    public static List<Person> checking(List<Person> persons, CheckPerson tester) {
        List<Person> list = new LinkedList<>();
        for(Person person : persons) {
            if(tester.test(person)) list.add(person);
        }
        return list;
    }

    class LocalChecker implements CheckPerson {
        public boolean test(Person person) {
            return (person.getGender().equals(Person.Sex.MALE) && person.getAge() >= 18);
        }
    }

    public CheckPerson anonymous() {
        CheckPerson anonymousChecker = new CheckPerson() {
            public boolean test(Person person) {
                return (person.getGender().equals(Person.Sex.MALE) && person.getAge() >= 18);
            }
        };
        return anonymousChecker;
    }

    class LambdaChecker implements CheckPerson {

        Predicate<Person> predicate;

        LambdaChecker(Predicate<Person> predicate) {
            this.predicate = predicate;
        }

        public boolean test(Person person) {
            return predicate.test(person);
        }
    }
}
