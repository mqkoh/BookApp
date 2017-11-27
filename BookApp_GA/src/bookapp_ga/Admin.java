package bookapp_ga;

import static bookapp_ga.Book.addBook;
import static bookapp_ga.Book.deleteBook;
import static bookapp_ga.Book.editBook;
import static bookapp_ga.Book.searchBook;
import static bookapp_ga.User.editUserInfo;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

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
                JOptionPane.showMessageDialog(null, "Add New Admin");
//                System.out.println("\nAdd New Admin");
//                System.out.println("--------------------");

                //Enter admin information
                Admin newA = new Admin();
                newA.setType("Admin");
                String username = JOptionPane.showInputDialog("Enter username: ");
//                System.out.print("Enter username: ");
                newA.setUsername(username.replace(' ', '-'));
                String pass = JOptionPane.showInputDialog("Enter password: ");
//                System.out.print("Enter password: ");
                newA.setPassword(pass.replace(' ', '-'));
                String email = JOptionPane.showInputDialog("Enter email: ");
//                System.out.print("Enter email: ");
                newA.setEmail(email.replace(' ', '-'));

                //Print the admin's information to UsersData.txt
                pw.println(newA.getType() + "\t" + newA.getUsername() + "\t" + newA.getPassword() + "\t" + newA.getEmail());

                //Asks if user wants to add more new administrators to the database
                String choice = JOptionPane.showInputDialog("Add more new admins? \n Enter 'Y' to add more admins. \n Enter any other key if you do not wish to add any more admins.");
//                System.out.println("Add more new admins? \n(Enter 'Y' to add more admins. Enter any other key if you do not wish to add any more admins.)");
//                String choice = input.next();
//                String extra = input.nextLine();
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
            String menu = JOptionPane.showInputDialog("Menu \n Select: \n1. Add Book \n2. Edit Book \n3. Delete Book \n4. Search Book \n5. Add New Admin \n6. Edit Personal Info \nEnter any other key to exit.");
//            System.out.println("\nMenu");
//            System.out.println("--------------------");
//            System.out.println("Select:");
//            System.out.println("1. Add Book \n2. Edit Book \n3. Delete Book \n4. Search Book \n5. Add New Admin \n6. Edit Personal Info \nEnter any other key to exit.");
//            String menu = input.nextLine();
            
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
                    addAdmin();
                    break;
                case "6":
                    editUserInfo(user);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "You have exited the program.");
//                    System.out.println("You have exited the program.");
                    System.exit(0);
            }
            Menu(user);
        }
        
}
