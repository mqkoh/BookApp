
package bookapp;

import static bookapp.Book.searchBook;
import static bookapp.Quiz.takeQuiz;
import static bookapp.User.editUserInfo;
import java.io.*;
import java.util.*;

public class RegularUser extends User{

    public RegularUser() {
    }

    public RegularUser(User user) {
        this.user = user;
    }   
    
    public static void Menu(User user) throws Exception, IOException{
        // Menu
        Scanner input = new Scanner(System.in);
        System.out.println("\nMenu");
        System.out.println("------------------");
        System.out.println("Select:");
        System.out.println("1. Search Book \n2. Edit Personal Info \n3. Start Book Preference Quiz \nEnter any other key to exit.");
        String menu = input.nextLine();

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
                System.out.println("You have exited the program.");
                System.exit(0);
        }
        Menu(user);
    }
    
}
