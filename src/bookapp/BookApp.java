package bookapp;

import static bookapp.Admin.adminAuthentication;
import static bookapp.Admin.addAdmin;
import static bookapp.RegularUser.regAuthentication;
import static bookapp.Book.*;
import java.io.*;
import java.util.*;
public class BookApp {

    public static void main(String[] args) throws Exception, IOException {
        
        System.out.println("Are you : \n1. A Regular User? \n2. An Admin");
        Scanner input = new Scanner (System.in);
        int selection = input.nextInt();
        
        //Regular User Interface
        if (selection == 1){
            regAuthentication();
            searchBook();
        }
        //Admin interface
        else if (selection == 2){
            adminAuthentication();
            
            System.out.println("Select:");
            System.out.println("1. Add Book \n2. Edit Book \n3. Delete Book \n4. Add New Admin");
            int menu = input.nextInt();
            
            switch(menu){
                case 1:
                    addBook();
                    break;
                case 2:
                    editBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    addAdmin();
                    break;
                case 5:
                    searchBook();
                    break;
                default:
                    System.out.println("You have exited the program.");
            }            
        }
        else{
            System.out.println("You have exited the program.");
        }
        
    }
    
    

    

    

}
    
