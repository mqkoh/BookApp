package bookapp;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class BookApp {

     public static void main(String[] args) throws Exception, IOException {
        
        JOptionPane.showMessageDialog(null, "Welcome To Readersville :)");
         
         int index = 0;
         
         int g = -1;
         while(g < 0){
             String input = JOptionPane.showInputDialog("Are you : \n1. A Regular User? \n2. An Admin \nEnter any other key to exit.");
         
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
            String userInput = JOptionPane.showInputDialog("Register/Login \n For new user, select '1' to register.\nFor registered user, select '2' to login. \nEnter any other key to exit.");

            boolean found = true;
            //Register
            if (userInput.equals("1")){

                //Create New User
                RegularUser newuser = new RegularUser();
                newuser.setType("RegularUser");

                //Input Username
                while(found){
                    String u_n = JOptionPane.showInputDialog("Login \n Enter username:");
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
                    else
                        JOptionPane.showMessageDialog(null, "Valid Username");
                }

                //Input password
                String pass = JOptionPane.showInputDialog("Enter password: ");
                newuser.setPassword(pass);

                //Input email
                String email = JOptionPane.showInputDialog("Enter email address: ");
                newuser.setEmail(email);

                //Add newuser to vector
                v.add(newuser);

                //Write the new user's data into the UsersData file
                pw.println(newuser.getType()+"\t"+newuser.getUsername()+"\t"+ newuser.getPassword()+"\t"+ newuser.getEmail());

                //Registration Successful
                JOptionPane.showMessageDialog(null, "You have successfully registered as a user. Please login to proceed.");

                //Close PrintWriter
                pw.close();
                
                //Login
                
                found = false;

                //Input Username and Password
                while(!found){
                    JOptionPane.showMessageDialog(null, "Login");
                    String username = JOptionPane.showInputDialog("Enter username: " );
                    String password = JOptionPane.showInputDialog("Enter password: ");

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
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
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
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
                }    
            }
            else{
                    JOptionPane.showMessageDialog(null, "You have exited the program.");
                    System.exit(0);
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
                }
                else
                     JOptionPane.showMessageDialog(null, "Authentication failed. Please try again.");
            }
            // Create copy of current user
            User user =(User) v.get(index);
            
            // Display Menu
            Admin.Menu(user);                   
        }
        else{
            JOptionPane.showMessageDialog(null, "You have exited the program.");
        }        
          
    }
  }
         
}
    



