import java.io.*;
import java.util.List;

public class UserSerializer {
    public void writeUsers(List<User> users) {
        try (FileOutputStream fileOut = new FileOutputStream("users.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)){
            out.writeObject(users);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public List<User> readUsers() {
        try (FileInputStream fileIn = new FileInputStream("users.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (List<User>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("List<User> class not found");
            c.printStackTrace();
            return null;
        }
    }
}
