/*
Create a program that saves (serializes) objects into a file and then reads them.
•	Create class User with following fields: first name, last name, and personal number (EGN).
•	Create a class UserSerializer with the following methods:
        writeUsers(List<User>)
        List<User> readUsers().
•	Store users to the file by using ObjectOutputStream.
•	Read users from the file using ObjectInputStream. Assert that the user’s values are correct.
•	Personal number (EGN) should not be stored in the file.
•	Use Java Serialization to implement the program.
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserSerializer serializer = new UserSerializer();
        List<User> users = serializer.readUsers();

        if(users == null || users.isEmpty()){
            users = new ArrayList<>();
            for(int i = 0; i < 5; i++) {
                User u = new User();
                u.setFirstName("First" + i);
                u.setLastName("Last" + i);
                u.setPersonalNumber(1234567890 + i);
                users.add(u);
            }
        }

        serializer.writeUsers(users);
        users.forEach(System.out::println);
    }
}