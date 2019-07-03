import java.time.LocalDate;
import java.time.Year;

public class Person {

    public enum Sex {
        MALE, FEMALE, NEITHER
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public Person() {
        name = "";
        birthday = LocalDate.now();
        gender = Sex.NEITHER;
        emailAddress = "";
    }

    public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public void printPerson() {
        System.out.println(
                "NAME: " + name + "\n"
                + "DOB: " + birthday.getMonth() + "/" + birthday.getDayOfMonth() + "/" + birthday.getYear() + "\n"
                + "GENDER: " + gender.toString() + "\n"
                + "EMAIL: " + emailAddress
                );
    }
}
