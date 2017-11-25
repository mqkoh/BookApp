
package bookapp;

import java.io.*;
import java.util.*;

public class Admin extends User{
    
    public Admin(){
    }

    public Admin(User user) {
        this.user = user;
    }
    
    public Admin(String username, String password, String email){
        super(username, password, email);
    }
    
//        public static void Authentication() throws Exception, IOException {
//            
//        File userfile;
//        userfile = new File("UsersData.txt");
//        PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
//        Scanner sc = new Scanner(userfile);
//        Scanner input = new Scanner (System.in);
//
//        Vector v = new Vector();
//
//        //Read all the values from the file and assign to object, then add the objects to vector
//        while(sc.hasNext()){
//            Admin user = new Admin();
//            user.setType(sc.next());
//            user.setUsername(sc.next());
//            user.setPassword(sc.next());
//            user.setEmail(sc.next());
//
//            //check if user is an Admin and only adds Admins to the vector
//            if (user.getType().equals("Admin")){                    
//                v.add(user); //adding admin records into vector
//            }
//            else{
//                continue;
//            }
//        }
//
//        //Set iterator
//        Iterator<Admin> it = v.iterator();
//
//        //Login
//        System.out.println("Login");
//        System.out.println("------------------");
//
//        boolean found = false;
//
//        //Not needed?
//        //User user = new User();
//
//        //Input Username and Password
//        while(!found){
//
//            System.out.print("Enter username: ");
//            String username = input.next();
//
//            System.out.print("Enter password: ");
//            String password = input.next();
//
//            //Authentication
//            String id = username + password;
//
//            it = v.iterator();
//            while(it.hasNext()){
//                if(it.next().getID().equals(id)){
//                    found = true;
//                    break;
//                }
//                else
//                    found = false;
//            }
//            if(found){
//                System.out.println("Authentication successful.");
//                System.out.println("Welcome back, " + username);
//            }
//            else
//                System.out.println("Authentication failed. Please try again.");
//        }    
//    }
        
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
            System.out.println("Add more new admins? (y/n)");
            String choice = input.next();
            if (choice.equalsIgnoreCase("N")){
                add = false;
            }
            else if (choice.equalsIgnoreCase("Y")){
                continue;
            }
            else{
                break;
            }
            pw.close();
        }
        
    }
        
}
