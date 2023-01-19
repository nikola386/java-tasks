import java.util.Comparator;

class Person implements Comparable<Person>{
    private String firstName;
    private String lastName;
    private String address;
    private int age;

    public Person(String firstName, String lastName, String address, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("firstName: %s, lastName: %s, address: %s, age: %d", firstName, lastName, address, age);
    }

    @Override
    public int compareTo(Person o) {
        return this.firstName.compareToIgnoreCase(o.firstName);
    }
}

class AgeCompare implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}