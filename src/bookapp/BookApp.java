package bookapp;

import java.io.*;
import java.util.*;
public class BookApp {

    public static void main(String[] args) throws Exception, IOException {
        
        System.out.println("Are you : \n1. A Regular User? \n2. An Admin \nEnter any other key to exit.");
        Scanner input = new Scanner (System.in);
        String selection = input.nextLine();
        int index = 0;
        
        //Regular User Interface
        if (selection.equals("1")){
            
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
            String action;

            System.out.println("Register/Login");
            System.out.println("------------------");
            System.out.println("For new user, select '1' to register.\nFor registered user, select '2' to login. \nEnter any other key to exit.");
            action = input.nextLine();

            boolean found = true;
            //Register
            if (action.equals("1")){

                //Create New User
                RegularUser newuser = new RegularUser();
                newuser.setType("RegularUser");

                //Input Username
                while(found){
                    System.out.println("Login");
                    System.out.println("--------------------");

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

                //Input Username and Password
                while(!found){
                    System.out.println("\nLogin");
                    System.out.println("--------------------");

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
            else if(action.equals("2")){
                found = false;

                //Input Username and Password
                while(!found){
                    System.out.println("\nLogin");
                    System.out.println("--------------------");

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
            else{
                System.out.println("You have exited the program.");
                System.exit(0);
            }
            // Create copy of current user
            User user =(User) v.get(index);
            
            // Display Menu
            RegularUser.Menu(user);       
            
        }
        //Admin interface
        else if (selection.equals("2")){
            
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

            boolean found = false;

            //Input Username and Password
            while(!found){
                System.out.println("\nLogin");
                System.out.println("------------------");

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
            // Create copy of current user
            User user =(User) v.get(index);
            
            // Display Menu
            Admin.Menu(user);                   
        }
        else{
            System.out.println("You have exited the program.");
        }        
    }
}
    
