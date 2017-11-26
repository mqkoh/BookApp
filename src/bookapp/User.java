
package bookapp;

import java.util.*;
import java.io.*;

public class User {
    protected String username;
    protected String password;
    protected String email;
    protected String type;
    protected User user = null;
    
    public User(){
    }
    
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public User(User user){
        this.user = user;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String getID(){
        return username + password;
    }
    
    public static void editUserInfo(User user) throws Exception, IOException{
        
        File userfile;
        userfile = new File("UsersData.txt");
        Scanner sc = new Scanner(userfile);
        Scanner input = new Scanner (System.in);

        Vector v = new Vector();

        //Read all the values from the file and assign to object, then add the objects to vector
        while(sc.hasNext()){
            User u = new User();
            u.setType(sc.next());
            u.setUsername(sc.next());
            u.setPassword(sc.next());
            u.setEmail(sc.next());
            
            v.add(u);
        }
        
         //Set list iterator
        ListIterator<User> lit = v.listIterator();
        
        // Get index of current user
        int index = 0;
        while (lit.hasNext()){
            if (lit.next().getUsername().equals(user.getUsername())){
                index = lit.previousIndex();
            }
        }
        
        // Create a copy of the current user
        User us = ((User) v.get(index));
        
        // Edit
        boolean next = true;
        while (next){
            
            // Prompts user to choose information to edit
            System.out.println("Choose information to edit: ");
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("3. Email");
            System.out.println("Enter any other key if you do not wish edit any information.");

            String edit = input.nextLine();        
        
            switch (edit){
                case "1":
                    System.out.println("Enter username: ");
                    us.setUsername(input.nextLine());
                    break;
                case "2":
                    System.out.println("Enter password: ");
                    us.setPassword(input.nextLine());
                    break;
                case "3":
                    System.out.println("Enter email: ");
                    us.setEmail(input.nextLine());
                    break;
                default:
                    System.out.println("You did not make any edits.");
                    break;

            }
            // Asks if user wish edit any other detail
            System.out.println("Edit other details? \n(Enter 'Y' to edit more details. Enter any other key if you do not wish to do any more edits.)");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("Y")){
                continue;
            }
            else{
                next = false;
            }
        }
        
        // Sets the user
        user.setUser(us);        
        
        // Remove old data and add new data
        v.remove(index);
        v.add(index, us);
        
        // Prints updated information to UsersData.txt
        PrintWriter pw = new PrintWriter(new FileWriter(userfile, false));
        Iterator<User> it = v.iterator();
        while (it.hasNext()){
            User u = (User) it.next();
            pw.println(u.getType() + "\t" + u.getUsername() + "\t" + u.getPassword() + "\t" + u.getEmail());
        }
        pw.close();
        
    }
}
