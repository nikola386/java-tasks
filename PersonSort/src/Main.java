/*
Create a program that sorts a List of Person based on different criteria. Follow the steps bellow:
•	Create a class Person, with fields firstName, lastName, address and age.
•	Override toString method to display Person fields information.
•	Create a method that sorts a list of people by the firstName. Implement Comparable interface in Person class and
    override the corresponding compareTo(T o) method. Use Collections.sort method to sort the elements.
•	Create a method that sorts a list of people by their age. This time create a separate class that implements
    the Comparator interface and overrides compare(T o1, T o2) method. Pass that class to Collections.sort method
    to sort the elements.
•	Create a method that sorts a list of people by their address. Implement the Comparator interface via anonymous class.
•	Write a main method to test all 3 sorting  methods.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(new Person("Chrystel", "Stella", "Gene St 7015, Astatula, Greenland, 602170", 25));
        list.add(new Person("Kaycee", "Earley", "Delay St 5830, Mountain Iron, United Arab Emirates, 647398", 5));
        list.add(new Person("Shanae", "Vereen", "William Road 1675, Nassawadox, Jamaica, 229035", 85));
        list.add(new Person("Tarna", "Pelchat", "Transfers Street 3769, Lindley, Guernsey, 019675", 31));
        list.add(new Person("Jerel", "Holler", "Bases Street 77, Bullcamp, Martinique, 171323", 45));

        Collections.sort(list);
        System.out.println("Persons sorted by firstName: ");
        list.forEach(System.out::println);

        Collections.sort(list, new AgeCompare());
        System.out.println("Persons sorted by age: ");
        list.forEach(System.out::println);

        Collections.sort(list, new Comparator<Person>() {
            public int compare(Person employee1, Person employee2) {
                return employee1.getAddress().compareToIgnoreCase(employee2.getAddress());
            }
        });
        System.out.println("Persons sorted by address: ");
        list.forEach(System.out::println);
    }
}