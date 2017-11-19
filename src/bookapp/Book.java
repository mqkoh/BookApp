
package bookapp;

import java.io.*;
import java.util.*;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private Double price;
    
    public Book(){
    }

    public Book(String title, String author, String isbn, String genre, Double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.price = price;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    public String getISBN(){
        return isbn;
    }
    
    public void setISBN(String isbn){
        this.isbn = isbn;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public static void addBook() throws Exception, IOException{
        File bookfile;
        bookfile = new File ("bookInfo.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(bookfile, true));
        Scanner input = new Scanner (System.in);
        
        boolean add = true;
        while (add){
            System.out.println("\nAdd Book");
            System.out.println("---------------");
            
            //Enter book information
            Book newBk = new Book();
            System.out.print("Enter title: ");
            newBk.setTitle(input.nextLine().replace(' ', '-'));
            System.out.print("Enter author: ");
            newBk.setAuthor(input.nextLine().replace(' ', '-'));
            System.out.print("Enter ISBN: ");
            newBk.setISBN(input.next());
            System.out.print("Enter main genre: ");
            newBk.setGenre(input.next());
            System.out.print("Enter price: RM ");
            newBk.setPrice(Double.parseDouble(input.next()));
            
            //Prints the book's information to bookfile.txt
            pw.println(newBk.getTitle() + "\t" + newBk.getAuthor() + "\t" + newBk.getISBN() + "\t" + newBk.getGenre() + "\t" + newBk.getPrice());

            //Asks if user wants to add more books to the database
            System.out.print("Add more books? (y/n) ");
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
    
    public static void editBook() throws Exception, IOException{
        File bookfile;
        bookfile = new File ("bookInfo.txt");
        Vector<Book> v = new Vector();
        Scanner sc = new Scanner (bookfile);
        Scanner input = new Scanner (System.in);
        
        //Read all the values from the file and assign to object, then add the objects to vector
        while (sc.hasNext()){
            Book bk = new Book();
            bk.setTitle(sc.next().replace('-', ' '));
            bk.setAuthor(sc.next().replace('-', ' '));
            bk.setISBN(sc.next());
            bk.setGenre(sc.next());
            bk.setPrice(Double.parseDouble(sc.next()));
            
            v.add(bk);            
        }   
        
        //Set ListIterator
        ListIterator<Book> lit = v.listIterator();
        
        boolean more = true;
        while (more){
            
            //Set ListIterator
            lit = v.listIterator();
            
            System.out.println("\nEdit Book Information");
            System.out.println("------------------------------");
            
            //Display Books
            while (lit.hasNext()){            
                Book bk = (Book) lit.next();            
                System.out.println(lit.nextIndex() + ". Title: " + bk.getTitle() + "\tAuthor: " + bk.getAuthor() + "\tISBN: " + bk.getISBN() + "\tGenre: " + bk.getGenre() + "\tPrice: RM " + bk.getPrice());            
            }
            
            // Select book to edit
            System.out.println("Select a book that you wish to edit. ");
            int select = input.nextInt() - 1;
            if (select >= v.size()){
                System.out.println("You did not select any book.");
                break;
            }
            
            boolean edit = true;
            while (edit){
                
                // Select information to edit
                System.out.println("Select an information that you wish to edit: ");
                System.out.println("1. Title \t 2. Author \t 3. ISBN \t 4. Genre \t 5. Price ");
                int info = input.nextInt();
                
                switch (info){
                    case 1:
                        System.out.print("Enter Title: ");
                        v.get(select).setTitle(input.next());
                        break;
                    case 2:
                        System.out.print("Enter Author: ");
                        v.get(select).setAuthor(input.next());
                        break;
                    case 3:
                        System.out.print("Enter ISBN: ");
                        v.get(select).setISBN(input.next());
                        break;
                    case 4:
                        System.out.print("Enter Genre: ");
                        v.get(select).setGenre(input.next());
                        break;
                    case 5:
                        System.out.print("Enter Price: RM ");
                        v.get(select).setPrice(Double.parseDouble(input.next()));
                        break;
                    default:
                        System.out.println("You did not edit any of the information. ");
                        break;
                }
                
                // Display information of edited book
                System.out.println("Title: " + v.get(select).getTitle() + "\tAuthor: " + v.get(select).getAuthor() + "\tISBN: " + v.get(select).getISBN() + "\tGenre: " + v.get(select).getGenre() + "\tPrice: RM " + v.get(select).getPrice());
                
                // Asks if user wishs to edit other information of the book
                System.out.println("Do you wish to edit any other information of this book? (Y/N)");
                String choice = input.next();
                if (choice.equalsIgnoreCase("N")){
                    edit = false;
                }
                else if (choice.equalsIgnoreCase("Y")){
                    continue;
                }
                else{
                    break;
                }
                
            }
            
            // Asks if user wish to edit any other book
            System.out.println("Do you wish to edit any other books? (Y/N)");
            String choice = input.next();
            if (choice.equalsIgnoreCase("N")){
                more = false;
            }
            else if (choice.equalsIgnoreCase("Y")){
                continue;
            }
            else{
                break;
            }            
        }
        
        System.out.println("\nBook");
        System.out.println("---------------");
        
        lit = v.listIterator();

        //Display Books
        while (lit.hasNext()){            
            Book bk = (Book) lit.next();            
            System.out.println(lit.nextIndex() + ". Title: " + bk.getTitle() + "\tAuthor: " + bk.getAuthor() + "\tISBN: " + bk.getISBN() + "\tGenre: " + bk.getGenre() + "\tPrice: RM " + bk.getPrice());            
        }
        
        // Overwrites existing books and writes updated books to bookInfo.txt
        PrintWriter pw = new PrintWriter(new FileWriter(bookfile, false));
        Iterator<Book> it = v.iterator();
        while (it.hasNext()){            
            Book bk = (Book) it.next();
            bk.setTitle(bk.getTitle().replace(' ', '-'));
            bk.setAuthor(bk.getAuthor().replace(' ', '-'));
            pw.println(bk.getTitle() + "\t" + bk.getAuthor() + "\t" + bk.getISBN() + "\t" + bk.getGenre() + "\t" + bk.getPrice());            
        }
        pw.close();        
    }
    
    public static void deleteBook() throws Exception, IOException{
        File bookfile;
        bookfile = new File ("bookInfo.txt");
        Vector<Book> v = new Vector();
        Scanner sc = new Scanner (bookfile);
        Scanner input = new Scanner (System.in);
        
        //Read all the values from the file and assign to object, then add the objects to vector
        while (sc.hasNext()){
            Book bk = new Book();
            bk.setTitle(sc.next().replace('-', ' '));
            bk.setAuthor(sc.next().replace('-', ' '));
            bk.setISBN(sc.next());
            bk.setGenre(sc.next());
            bk.setPrice(Double.parseDouble(sc.next()));
            
            v.add(bk);            
        }
        
        //Set ListIterator
        ListIterator<Book> lit = v.listIterator();
               
        boolean more = true;
        while (more){
            lit = v.listIterator();
            
            System.out.println("\nDelete Book");
            System.out.println("--------------------");
            
            //Display Books
            while (lit.hasNext()){            
                Book bk = (Book) lit.next();            
                System.out.println(lit.nextIndex() + ". Title: " + bk.getTitle() + "\tAuthor: " + bk.getAuthor() + "\tISBN: " + bk.getISBN() + "\tGenre: " + bk.getGenre() + "\tPrice: RM " + bk.getPrice());            
            }
            
            // Select book to delete
            System.out.println("Select a book that you wish to delete. ");
            int select = input.nextInt() - 1;
            if (select >= v.size()){
                System.out.println("You did not select any book.");
                break;
            }
            
            // Delete Confirmation
            System.out.println("Delete " + v.get(select).getTitle() + "? (Y/N)");
            String choice = input.next();
            if (choice.equalsIgnoreCase("Y")){
                v.removeElementAt(select);
            }
            else if (choice.equalsIgnoreCase("N")){
                continue;
            }
            else{
                break;
            }
            
            //Asks if user wish to delete other books
            System.out.println("Do you wish to delete any other books? (Y/N)");
            String cont = input.next();
            if (cont.equalsIgnoreCase("N")){
                more = false;
            }
            else if (choice.equalsIgnoreCase("Y")){
                continue;
            }
            else{
                break;
            }
        }
        
        lit = v.listIterator();
        
        System.out.println("\nBook");
        System.out.println("---------------");
        
        //Display Books
        while (lit.hasNext()){            
                Book bk = (Book) lit.next();            
                System.out.println(lit.nextIndex() + ". Title: " + bk.getTitle() + "\tAuthor: " + bk.getAuthor() + "\tISBN: " + bk.getISBN() + "\tGenre: " + bk.getGenre() + "\tPrice: RM " + bk.getPrice());            
            }
        
        // Overwrites existing books and writes updated books to bookInfo.txt
        PrintWriter pw = new PrintWriter(new FileWriter(bookfile, false));
        Iterator<Book> it = v.iterator();
        while (it.hasNext()){            
            Book bk = (Book) it.next();
            bk.setTitle(bk.getTitle().replace(' ', '-'));
            bk.setAuthor(bk.getAuthor().replace(' ', '-'));
            pw.println(bk.getTitle() + "\t" + bk.getAuthor() + "\t" + bk.getISBN() + "\t" + bk.getGenre() + "\t" + bk.getPrice());            
        }
        pw.close();
    }    
    
}
