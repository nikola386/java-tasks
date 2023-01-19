/*
Create a class Person, with fields firstName, lastName, address and age. Override equals(Object obj) and hashCode()
methods from java.lang.Object class. Override also toString method.

Create a main method to test the equality of objects. Follow the steps bellow:
•	Create a  new HashSet<Person> and try to add several Person objects to it. Add both: equal and non-equal objects.
    Check that equal objects are not added twice. Try to understand how HashSet works,
    how it uses internally equals() and hashcode() methods to support unique elements.
•	Create a ArrayList<Person> and try to add some Person objects.
    Test the contains() method, which implementation is also based on equals() method.
•	Try to compare equal and non-equal people by using equals method, == operator and compareTo method.
    Test cases when two Person instances are the same and not the same compared with equals, == and compareTo.
    Try to understand how equals, == and comapreTo work.
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        testHashSet();
        testArrayList();
        testEquality();
    }

    private static void testHashSet() {
        HashSet<Person> list = new HashSet<>();
        list.add(new Person("Chrystel", "Stella", "Gene St 7015, Astatula, Greenland, 602170", 25));
        list.add(new Person("Kaycee", "Earley", "Delay St 5830, Mountain Iron, United Arab Emirates, 647398", 5));
        list.add(new Person("Shanae", "Vereen", "William Road 1675, Nassawadox, Jamaica, 229035", 85));
        list.add(new Person("Kaycee", "Earley", "Delay St 5830, Mountain Iron, United Arab Emirates, 647398", 5));
        list.add(new Person("Shanae", "Vereen", "William Road 1675, Nassawadox, Jamaica, 229035", 85));

        list.forEach(System.out::println);
        System.out.println();
    }

    private static void testArrayList() {
        Person toBeChecked = new Person("Shanae", "Vereen", "William Road 1675, Nassawadox, Jamaica, 229035", 85);

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Chrystel", "Stella", "Gene St 7015, Astatula, Greenland, 602170", 25));
        list.add(new Person("Kaycee", "Earley", "Delay St 5830, Mountain Iron, United Arab Emirates, 647398", 5));
        list.add(toBeChecked);

        System.out.printf("List contains %s %s: %b%n%n", toBeChecked.getFirstName(), toBeChecked.getLastName(), list.contains(toBeChecked));
    }

    private static void testEquality() {
        Person person1 = new Person("Shanae", "Vereen", "William Road 1675, Nassawadox, Jamaica, 229035", 85);
        Person person2 = new Person("Kaycee", "Earley", "Delay St 5830, Mountain Iron, United Arab Emirates, 647398", 5);
        Person person3 = new Person("Shanae", "Vereen", "William Road 1675, Nassawadox, Jamaica, 229035", 85);

        System.out.printf("person1 == person2 should be false: %b%n", person1 == person2);
        System.out.printf("person1 == person3 should be false: %b%n", person1 == person3);

        System.out.printf("person1.equals(person2) should be false: %b%n", person1.equals(person2));
        System.out.printf("person1.equals(person3) should be true: %b%n", person1.equals(person3));

        System.out.printf("person1.compareTo(person2) should be positive: %d%n", person1.compareTo(person2));
        System.out.printf("person1.compareTo(person3) be 0: %d%n", person1.compareTo(person3));
    }
}