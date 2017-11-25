package bookapp;

import static bookapp.Admin.addAdmin;
import static bookapp.Book.*;
import static bookapp.User.editUserInfo;
import java.io.*;
import java.util.*;
public class BookApp {

    public static void main(String[] args) throws Exception, IOException {
        
        System.out.println("Are you : \n1. A Regular User? \n2. An Admin");
        Scanner input = new Scanner (System.in);
        int selection = input.nextInt();
        int index = 0;
        
        //Regular User Interface
        if (selection == 1){
            
            // Regular User Authentication
            File userfile;
            userfile = new File("UsersData.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
            Scanner sc = new Scanner(userfile);

            Vector v = new Vector();

            //Read all the values from the file and assign to object, then add the objects to vector
            while(sc.hasNext()){
                User user = new User();
                user.setType(sc.next());
                user.setUsername(sc.next());
                user.setPassword(sc.next());
                user.setEmail(sc.next());

                v.add(user);
                
            }

            //Set iterator
            Iterator<User> it = v.iterator();

            //Register or Login
            int action;

            System.out.println("Register/Login");
            System.out.println("------------------");
            System.out.println("For new user, select '1' to register.\nFor registered user, select '2' to login.");
            action = input.nextInt();

            boolean found = true;
            //Register
            if (action == 1){

                //Create New User
                RegularUser newuser = new RegularUser();
                newuser.setType("RegularUser");

                //Input Username
                while(found){

                    System.out.print("Enter username: ");
                    newuser.setUsername(input.next());

                    //Check if username is used
                    String username = newuser.getUsername();

                    while(it.hasNext()){
                        if(it.next().getUsername().equals(username)){
                            found = true;
                            break;
                        }
                        else
                            found = false;
                    }
                    if(found)
                        System.out.println("Invalid Username");
                    else
                        System.out.println("Valid Username");
                }

                //Input password
                System.out.print("Enter password: ");
                newuser.setPassword(input.next());

                //Input email
                System.out.print("Enter email address: ");
                newuser.setEmail(input.next());

                //Add newuser to vector
                v.add(newuser);

                //Write the new user's data into the UsersData file
                pw.println(newuser.getType()+"\t"+newuser.getUsername()+"\t"+ newuser.getPassword()+"\t"+ newuser.getEmail());

                //Registration Successful
                System.out.println("You have successfully registered as a user. Please login to proceed.");

                //Close PrintWriter
                pw.close();
                
                //Login
                
                found = false;

//                RegularUser user = new RegularUser();

                //Input Username and Password
                while(!found){

                    System.out.print("Enter username: ");
                    String username = input.next();

                    System.out.print("Enter password: ");
                    String password = input.next();

                    //Authentication
                    String id = username + password;

                    ListIterator<User> lit = v.listIterator();
                    
                    while(lit.hasNext()){
                        if(lit.next().getID().equals(id)){
                            found = true;
                            index = lit.previousIndex();
                            break;
                        }
                        else
                            found = false;
                    }
                    if(found){
                        System.out.println("Authentication successful.");
                        System.out.println("Welcome back, " + username);
                    }
                    else
                        System.out.println("Authentication failed. Please try again.");
                }
            }

            //Login
            else if(action == 2){
                found = false;

//                RegularUser user = new RegularUser();

                //Input Username and Password
                while(!found){

                    System.out.print("Enter username: ");
                    String username = input.next();

                    System.out.print("Enter password: ");
                    String password = input.next();

                    //Authentication
                    String id = username + password;

                    ListIterator<User> lit = v.listIterator();
                    
                    while(lit.hasNext()){
                        if(lit.next().getID().equals(id)){
                            found = true;
                            index = lit.previousIndex();
                         
                            break;
                        }
                        else
                            found = false;
                    }
                    if(found){
                        System.out.println("Authentication successful.");
                        System.out.println("Welcome back, " + username);
                    }
                    else
                        System.out.println("Authentication failed. Please try again.");
                }    
            }

            User user =(User) v.get(index);
            editUserInfo(user);
            
        }
        //Admin interface
        else if (selection == 2){
            
            File userfile;
            userfile = new File("UsersData.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
            Scanner sc = new Scanner(userfile);

            Vector v = new Vector();

            //Read all the values from the file and assign to object, then add the objects to vector
            while(sc.hasNext()){
                User user = new User();
                user.setType(sc.next());
                user.setUsername(sc.next());
                user.setPassword(sc.next());
                user.setEmail(sc.next());

                v.add(user);
                
            }

            //Login
            System.out.println("Login");
            System.out.println("------------------");

            boolean found = false;

//            Admin user = new Admin();

            //Input Username and Password
            while(!found){

                System.out.print("Enter username: ");
                String username = input.next();

                System.out.print("Enter password: ");
                String password = input.next();

                //Authentication
                String id = username + password;

                ListIterator<User> lit = v.listIterator();
                
                
                while(lit.hasNext()){
                    if(lit.next().getID().equals(id)){
                        found = true;
                        index = lit.previousIndex();
                        break;
                    }
                    else
                        found = false;
                }
                if(found){
                    System.out.println("Authentication successful.");
                    System.out.println("Welcome back, " + username);
                }
                else
                    System.out.println("Authentication failed. Please try again.");
            }
            User user =(User) v.get(index);
            editUserInfo(user);
            
            // Menu
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
    
