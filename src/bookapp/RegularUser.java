
package bookapp;

import java.io.*;
import java.util.*;

public class RegularUser extends User{

    public RegularUser() {
    }

    public RegularUser(String username, String password, String email) {
        super(username, password, email);
    }
    
    public static void regAuthentication() throws Exception, IOException {
        File userfile;
        userfile = new File("UsersData.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(userfile, true));
        Scanner sc = new Scanner(userfile);
        Scanner input = new Scanner (System.in);

        Vector v = new Vector();

        //Read all the values from the file and assign to object, then add the objects to vector
        while(sc.hasNext()){
            RegularUser user = new RegularUser();
            user.setType(sc.next());
            user.setUsername(sc.next());
            user.setPassword(sc.next());
            user.setEmail(sc.next());

            //check if user is a Regular User and only add Regular Users to the vector
            if (user.getType().equals("RegularUser")){                   
                v.add(user);
            }
            else{
                continue;
            }
        }

        //Set iterator
        Iterator<RegularUser> it = v.iterator();

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
            System.out.println("You have successfully registered as a user.");

            //Close PrintWriter
            pw.close();
        }

        //Login
        else if(action == 2){
            found = false;

            //Not needed?
            //User user = new User();

            //Input Username and Password
            while(!found){

                System.out.print("Enter username: ");
                String username = input.next();

                System.out.print("Enter password: ");
                String password = input.next();

                //Authentication
                String id = username + password;

                it = v.iterator();
                while(it.hasNext()){
                    if(it.next().getID().equals(id)){
                        found = true;
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
    }    
}
