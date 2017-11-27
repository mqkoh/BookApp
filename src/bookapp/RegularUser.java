
package bookapp;

import static bookapp.Book.searchBook;
import static bookapp.Quiz.takeQuiz;
import static bookapp.User.editUserInfo;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class RegularUser extends User{

    public RegularUser() {
    }

    public RegularUser(User user) {
        this.user = user;
    }   
    
    public static void Menu(User user) throws Exception, IOException{
        // Menu
        Scanner input = new Scanner(System.in);
        JOptionPane.showMessageDialog(null, "Menu");
        String menu = JOptionPane.showInputDialog("Select \n1. Search Book \n2. Edit Personal Info \n3. Take Quiz \nEnter any other key to exit. ");

        switch(menu){
            case "1":
                searchBook();
                break;
            case "2":
                editUserInfo(user);
                break;
            case "3":
                takeQuiz();
                break;
            default:
                JOptionPane.showMessageDialog(null, "You have exited the program.");
                System.exit(0);
        }
        Menu(user);
    }
    
}
