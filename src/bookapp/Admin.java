
package bookapp;

import static bookapp.Book.addBook;
import static bookapp.Book.deleteBook;
import static bookapp.Book.editBook;
import static bookapp.Book.searchBook;
import static bookapp.Quiz.createQuiz;
import static bookapp.User.editUserInfo;
import java.io.*;
import java.util.*;

public class Admin extends User{
    
    public Admin(){
    }

    public Admin(User user) {
        this.user = user;
    }
        
        public static void addAdmin() throws Exception, IOException{
        
            File userfile;
            userfile = new File("UsersData.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
            Scanner input = new Scanner(System.in);

            boolean add = true;
            while (add){
                System.out.println("\nAdd New Admin");
                System.out.println("--------------------");

                //Enter admin information
                Admin newA = new Admin();
                newA.setType("Admin");
                System.out.print("Enter username: ");
                newA.setUsername(input.nextLine().replace(' ', '-'));
                System.out.print("Enter password: ");
                newA.setPassword(input.nextLine().replace(' ', '-'));
                System.out.print("Enter email: ");
                newA.setEmail(input.nextLine().replace(' ', '-'));

                //Print the admin's information to UsersData.txt
                pw.println(newA.getType() + "\t" + newA.getUsername() + "\t" + newA.getPassword() + "\t" + newA.getEmail());

                //Asks if user wants to add more new administrators to the database
                System.out.println("Add more new admins? \n(Enter 'Y' to add more admins. Enter any other key if you do not wish to add any more admins.)");
                String choice = input.next();
                String extra = input.nextLine();
                if (choice.equalsIgnoreCase("Y")){
                    continue;
                }
                else{
                    add = false;
                }                
            }
            pw.close();

        }
        
        public static void Menu(User user) throws Exception, IOException{
            // Menu
            Scanner input = new Scanner(System.in);
            System.out.println("\nMenu");
            System.out.println("--------------------");
            System.out.println("Select:");
            System.out.println("1. Add Book \n2. Edit Book \n3. Delete Book \n4. Search Book \n5. Create New Quiz \n6. Add New Admin \n7. Edit Personal Info \nEnter any other key to exit.");
            String menu = input.nextLine();
            
            switch(menu){
                case "1":
                    addBook();
                    break;
                case "2":
                    editBook();
                    break;
                case "3":
                    deleteBook();
                    break;
                case "4":
                    searchBook();
                    break;
                case "5":
                    createQuiz();
                    break;                    
                case "6":
                    addAdmin();
                    break;
                case "7":
                    editUserInfo(user);
                    break;
                default:
                    System.out.println("You have exited the program.");
                    System.exit(0);
            }
            Menu(user);
        }
        
}
