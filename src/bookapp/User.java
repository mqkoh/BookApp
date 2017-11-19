
package bookapp;

import java.util.*;
import java.io.*;

public class User {
    protected String username;
    protected String password;
    protected String email;
    private String type;
    
    public User(){
    }
    
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
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
    
    public String getID(){
        return username + password;
    }
    
    public static void editUserInfo() throws Exception, IOException{
        
        
        
    }
}
