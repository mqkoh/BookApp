package bookapp;

import static bookapp.Admin.AdminAuthentication;
import static bookapp.RegularUser.RegAuthentication;
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
            RegAuthentication();
        }
        //Admin interface
        else if (selection == 2){
            AdminAuthentication();
            
            System.out.println("Select:");
            System.out.println("1. Add Book \n2. Edit Book \n3. Delete Book");
            int menu = input.nextInt();
            
            switch(menu){
                case 1:
                    AddBook();
                    break;
                case 2:
                    EditBook();
                    break;
                case 3:
                    DeleteBook();
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
    
