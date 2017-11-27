package bookapp_ga;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class BookApp_GA {

     public static void main(String[] args) throws Exception, IOException {
        
        JOptionPane.showMessageDialog(null, "Welcome To Readersville :)");
         
         int index = 0;
         
         int g = -1;
         while(g < 0){
             String input = JOptionPane.showInputDialog("Are you : \n1. A Regular User? \n2. An Admin \nEnter any other key to exit.");
//             if (input.length() > 0){
//                 g ++;
//                 System.out.println("Thank You");
//             } else
//             {
//                 System.out.println("Enter your name");
//             }
         
         
          if (input.equals("1")){
            
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
//            String action;

            String userInput = JOptionPane.showInputDialog("Register/Login \n For new user, select '1' to register.\nFor registered user, select '2' to login. \nEnter any other key to exit.");
//            System.out.println("Register/Login");
//            System.out.println("------------------");
//            System.out.println("For new user, select '1' to register.\nFor registered user, select '2' to login. \nEnter any other key to exit.");
//            action = input.nextLine();

            boolean found = true;
            //Register
            if (userInput.equals("1")){

                //Create New User
                RegularUser newuser = new RegularUser();
                newuser.setType("RegularUser");

                //Input Username
                while(found){
                    String u_n = JOptionPane.showInputDialog("Login \n Enter username:");
//                    System.out.println("Login");
//                    System.out.println("--------------------");
//
//                    System.out.print("Enter username: ");
                    newuser.setUsername(u_n);

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
                        JOptionPane.showMessageDialog(null, "Invalid Username");
//                        System.out.println("Invalid Username");
                    else
                        JOptionPane.showMessageDialog(null, "Valid Username");
//                        System.out.println("Valid Username");
                }

                //Input password
                String pass = JOptionPane.showInputDialog("Enter password: ");
//                System.out.print("Enter password: ");
                newuser.setPassword(pass);

                //Input email
                String email = JOptionPane.showInputDialog("Enter email address: ");
//                System.out.print("Enter email address: ");
                newuser.setEmail(email);

                //Add newuser to vector
                v.add(newuser);

                //Write the new user's data into the UsersData file
                pw.println(newuser.getType()+"\t"+newuser.getUsername()+"\t"+ newuser.getPassword()+"\t"+ newuser.getEmail());

                //Registration Successful
                JOptionPane.showMessageDialog(null, "You have successfully registered as a user. Please login to proceed.");
//                System.out.println("You have successfully registered as a user. Please login to proceed.");

                //Close PrintWriter
                pw.close();
                
                //Login
                
                found = false;

                //Input Username and Password
                while(!found){
                    JOptionPane.showMessageDialog(null, "Login");
                    String username = JOptionPane.showInputDialog("Enter username: " );
                    String password = JOptionPane.showInputDialog("Enter password: ");
//                    System.out.println("\nLogin");
//                    System.out.println("--------------------");
//
//                    System.out.print("Enter username: ");
//                    String username = input.next();
//
//                    System.out.print("Enter password: ");
//                    String password = input.next();

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
                        JOptionPane.showMessageDialog(null, "Authentication successful.");
                        JOptionPane.showMessageDialog(null, "Welcome back, " + username);
//                        System.out.println("Authentication successful.");
//                        System.out.println("Welcome back, " + username);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
//                        System.out.println("Authentication failed. Please try again.");
                }
            }

            //Login
            else if(userInput.equals("2")){
                found = false;

                //Input Username and Password
                while(!found){
                    JOptionPane.showMessageDialog(null, "Login");
                    String username = JOptionPane.showInputDialog("Enter username: " );
                    String password = JOptionPane.showInputDialog("Enter password: ");
//                    System.out.println("\nLogin");
//                    System.out.println("--------------------");
//
//                    System.out.print("Enter username: ");
//                    String username = input.next();
//
//                    System.out.print("Enter password: ");
//                    String password = input.next();

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
                         JOptionPane.showMessageDialog(null, "Authentication successful.");
                        JOptionPane.showMessageDialog(null, "Welcome back, " + username);
//                        System.out.println("Authentication successful.");
//                        System.out.println("Welcome back, " + username);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
//                        System.out.println("Authentication failed. Please try again.");
                }    
            }
            else{
                    JOptionPane.showMessageDialog(null, "You have exited the program.");

//System.out.println("You have exited the program.");
//                System.exit(0);
            }
            // Create copy of current user
            User user =(User) v.get(index);
            
            // Display Menu
            RegularUser.Menu(user);       
            
        }
        //Admin interface
        else if (input.equals("2")){
            
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
                 JOptionPane.showMessageDialog(null, "Login");
                    String username = JOptionPane.showInputDialog("Enter username: " );
                    String password = JOptionPane.showInputDialog("Enter password: ");
//                System.out.println("\nLogin");
//                System.out.println("------------------");
//
//                System.out.print("Enter username: ");
//                String username = input.next();
//
//                System.out.print("Enter password: ");
//                String password = input.next();

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
                    JOptionPane.showMessageDialog(null, "Authentication successful.");
                        JOptionPane.showMessageDialog(null, "Welcome back, " + username);
//                    System.out.println("Authentication successful.");
//                    System.out.println("Welcome back, " + username);
                }
                else
                     JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
//                    System.out.println("Authentication failed. Please try again.");
            }
            // Create copy of current user
            User user =(User) v.get(index);
            
            // Display Menu
            Admin.Menu(user);                   
        }
        else{
            JOptionPane.showMessageDialog(null, "You have exited the program.");
//            System.out.println("You have exited the program.");
        }        
    }
     }
         
//        System.out.println("Are you : \n1. A Regular User? \n2. An Admin \nEnter any other key to exit.");
//        Scanner input = new Scanner (System.in);
//        String selection = input.nextLine();
//        int index = 0;
        
        //Regular User Interface
//        if (selection.equals("1")){
//            
//            // Regular User Authentication
//            File userfile;
//            userfile = new File("UsersData.txt");
//            PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
//            Scanner sc = new Scanner(userfile);
//
//            Vector v = new Vector();
//
//            //Read all the values from the file and assign to object, then add the objects to vector
//            while(sc.hasNext()){
//                User user = new User();
//                user.setType(sc.next());
//                user.setUsername(sc.next());
//                user.setPassword(sc.next());
//                user.setEmail(sc.next());
//
//                v.add(user);                
//            }
//
//            //Set iterator
//            Iterator<User> it = v.iterator();
//
//            //Register or Login
//            String action;
//
//            System.out.println("Register/Login");
//            System.out.println("------------------");
//            System.out.println("For new user, select '1' to register.\nFor registered user, select '2' to login. \nEnter any other key to exit.");
//            action = input.nextLine();
//
//            boolean found = true;
//            //Register
//            if (action.equals("1")){
//
//                //Create New User
//                RegularUser newuser = new RegularUser();
//                newuser.setType("RegularUser");
//
//                //Input Username
//                while(found){
//                    System.out.println("Login");
//                    System.out.println("--------------------");
//
//                    System.out.print("Enter username: ");
//                    newuser.setUsername(input.next());
//
//                    //Check if username is used
//                    String username = newuser.getUsername();
//
//                    while(it.hasNext()){
//                        if(it.next().getUsername().equals(username)){
//                            found = true;
//                            break;
//                        }
//                        else
//                            found = false;
//                    }
//                    if(found)
//                        System.out.println("Invalid Username");
//                    else
//                        System.out.println("Valid Username");
//                }
//
//                //Input password
//                System.out.print("Enter password: ");
//                newuser.setPassword(input.next());
//
//                //Input email
//                System.out.print("Enter email address: ");
//                newuser.setEmail(input.next());
//
//                //Add newuser to vector
//                v.add(newuser);
//
//                //Write the new user's data into the UsersData file
//                pw.println(newuser.getType()+"\t"+newuser.getUsername()+"\t"+ newuser.getPassword()+"\t"+ newuser.getEmail());
//
//                //Registration Successful
//                System.out.println("You have successfully registered as a user. Please login to proceed.");
//
//                //Close PrintWriter
//                pw.close();
//                
//                //Login
//                
//                found = false;
//
//                //Input Username and Password
//                while(!found){
//                    System.out.println("\nLogin");
//                    System.out.println("--------------------");
//
//                    System.out.print("Enter username: ");
//                    String username = input.next();
//
//                    System.out.print("Enter password: ");
//                    String password = input.next();
//
//                    //Authentication
//                    String id = username + password;
//
//                    ListIterator<User> lit = v.listIterator();
//                    
//                    while(lit.hasNext()){
//                        if(lit.next().getID().equals(id)){
//                            found = true;
//                            index = lit.previousIndex();
//                            break;
//                        }
//                        else
//                            found = false;
//                    }
//                    if(found){
//                        System.out.println("Authentication successful.");
//                        System.out.println("Welcome back, " + username);
//                    }
//                    else
//                        System.out.println("Authentication failed. Please try again.");
//                }
//            }
//
//            //Login
//            else if(action.equals("2")){
//                found = false;
//
//                //Input Username and Password
//                while(!found){
//                    System.out.println("\nLogin");
//                    System.out.println("--------------------");
//
//                    System.out.print("Enter username: ");
//                    String username = input.next();
//
//                    System.out.print("Enter password: ");
//                    String password = input.next();
//
//                    //Authentication
//                    String id = username + password;
//
//                    ListIterator<User> lit = v.listIterator();
//                    
//                    while(lit.hasNext()){
//                        if(lit.next().getID().equals(id)){
//                            found = true;
//                            index = lit.previousIndex();
//                         
//                            break;
//                        }
//                        else
//                            found = false;
//                    }
//                    if(found){
//                        System.out.println("Authentication successful.");
//                        System.out.println("Welcome back, " + username);
//                    }
//                    else
//                        System.out.println("Authentication failed. Please try again.");
//                }    
//            }
//            else{
//                System.out.println("You have exited the program.");
//                System.exit(0);
//            }
//            // Create copy of current user
//            User user =(User) v.get(index);
//            
//            // Display Menu
//            RegularUser.Menu(user);       
//            
//        }
//        //Admin interface
//        else if (selection.equals("2")){
//            
//            File userfile;
//            userfile = new File("UsersData.txt");
//            PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
//            Scanner sc = new Scanner(userfile);
//
//            Vector v = new Vector();
//
//            //Read all the values from the file and assign to object, then add the objects to vector
//            while(sc.hasNext()){
//                User user = new User();
//                user.setType(sc.next());
//                user.setUsername(sc.next());
//                user.setPassword(sc.next());
//                user.setEmail(sc.next());
//
//                v.add(user);                
//            }
//
//            //Login
//
//            boolean found = false;
//
//            //Input Username and Password
//            while(!found){
//                System.out.println("\nLogin");
//                System.out.println("------------------");
//
//                System.out.print("Enter username: ");
//                String username = input.next();
//
//                System.out.print("Enter password: ");
//                String password = input.next();
//
//                //Authentication
//                String id = username + password;
//
//                ListIterator<User> lit = v.listIterator();                
//                
//                while(lit.hasNext()){
//                    if(lit.next().getID().equals(id)){
//                        found = true;
//                        index = lit.previousIndex();
//                        break;
//                    }
//                    else
//                        found = false;
//                }
//                if(found){
//                    System.out.println("Authentication successful.");
//                    System.out.println("Welcome back, " + username);
//                }
//                else
//                    System.out.println("Authentication failed. Please try again.");
//            }
//            // Create copy of current user
//            User user =(User) v.get(index);
//            
//            // Display Menu
//            Admin.Menu(user);                   
//        }
//        else{
//            System.out.println("You have exited the program.");
//        }        
//    }
}
    


