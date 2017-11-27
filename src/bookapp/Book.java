
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
    
    public Book(Book book){
        this.title = book.title;
        this.author = book.author;
        this.isbn = book.isbn;
        this.genre = book.genre;
        this.price = book.price;
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
            newBk.setISBN(input.nextLine());
            System.out.print("Enter main genre: ");
            newBk.setGenre(input.nextLine());
            boolean price = false;
            while (!price){
                System.out.print("Enter price: RM ");
                try{
                    newBk.setPrice(Double.parseDouble(input.nextLine()));
                    price = true;
                }catch (NumberFormatException e){
                    System.out.println("Please enter numerical input.");
                }
            }
            
            //Prints the book's information to bookfile.txt
            pw.println(newBk.getTitle() + "\t" + newBk.getAuthor() + "\t" + newBk.getISBN() + "\t" + newBk.getGenre() + "\t" + newBk.getPrice());
            pw.flush();
            
            //Asks if user wants to add more books to the database
            System.out.println("Add more books? \n(Enter 'Y' to add more books. Enter any other key to continue.) ");
            String choice = input.next();
            String next = input.nextLine();
            if (choice.equalsIgnoreCase("Y")){
                continue;
            }
            else{
                add = false;
            }            
        }
        pw.close();
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
            try{ // Catch NumberFormatException
                System.out.println("Select a book that you wish to edit. ");
                int select = Integer.parseInt(input.nextLine()) - 1;
                if ((select >= v.size())||(select < 1)){
                    System.out.println("You did not select any book.");
                    break;
                }

                boolean edit = true;
                while (edit){

                    // Select information to edit
                    System.out.println("Select an information that you wish to edit: ");
                    System.out.println("1. Title \t 2. Author \t 3. ISBN \t 4. Genre \t 5. Price  \nEnter any other key if you do not wish to make any edits..");
                    String info = input.nextLine();

                    switch (info){
                        case "1":
                            System.out.print("Enter Title: ");
                            v.get(select).setTitle(input.next());
                            break;
                        case "2":
                            System.out.print("Enter Author: ");
                            v.get(select).setAuthor(input.next());
                            break;
                        case "3":
                            System.out.print("Enter ISBN: ");
                            v.get(select).setISBN(input.next());
                            break;
                        case "4":
                            System.out.print("Enter Genre: ");
                            v.get(select).setGenre(input.next());
                            break;
                        case "5":
                            boolean price = false;
                            while (!price){
                                System.out.print("Enter Price: RM ");
                                try{
                                    v.get(select).setPrice(Double.parseDouble(input.next()));
                                    price = true;
                                }catch(NumberFormatException e){
                                    System.out.println("Please enter numerical input.");
                                }
                            }
                            break;
                        default:
                            System.out.println("You did not edit any of the information. ");
                            break;
                    }

                    // Display information of edited book
                    System.out.println("Title: " + v.get(select).getTitle() + "\tAuthor: " + v.get(select).getAuthor() + "\tISBN: " + v.get(select).getISBN() + "\tGenre: " + v.get(select).getGenre() + "\tPrice: RM " + v.get(select).getPrice());

                    // Asks if user wishs to edit other information of the book
                    System.out.println("Do you wish to edit any other information of this book? \n(Enter 'Y' to edit other informations. Enter any other key if you do not wish to make any edits.)");
                    String choice = input.next();
                    String next = input.nextLine();
                    if (choice.equalsIgnoreCase("Y")){
                        continue;
                    }
                    else{
                        edit = false;
                    }
                }
            
            }catch (NumberFormatException e){
                System.out.println("You did not select any book.");
                break;
            }
            
            // Asks if user wish to edit any other book
            System.out.println("Do you wish to edit any other books? \n(Enter 'Y' to edit more books. Enter any other key if you do not wish to make any more edits.)");
            String choice = input.next();
            String next = input.nextLine();
            if (choice.equalsIgnoreCase("Y")){
                continue;
            }
            else{
                more = false;
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
            try{ // Catch NumberFormatException
                System.out.println("Select a book that you wish to delete. ");
                int select = Integer.parseInt(input.nextLine()) - 1;
                if ((select >= v.size())||(select < 1)){
                    System.out.println("You did not select any book.");
                    break;
                }

                // Delete Confirmation
                System.out.println("Delete " + v.get(select).getTitle() + "? \n(Enter 'Y' to delete. Enter any other key if you do not wish to delete this book.)");
                String choice = input.next();
                String next = input.nextLine();
                if (choice.equalsIgnoreCase("Y")){
                    v.removeElementAt(select);
                }
                else{
                    continue;
                }

                //Asks if user wish to delete other books
                System.out.println("Do you wish to delete any other books? \n(Enter 'Y' to delete more books. Enter any other key if you do not wish to delete more books.)");
                String cont = input.next();
                next = input.nextLine();
                if (cont.equalsIgnoreCase("Y")){
                    continue;
                }
                else{
                    more = false;
                }
            }catch (NumberFormatException e){
                System.out.println("You did not select any book.");
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

    public static void searchBook() throws Exception, IOException{
        
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
        
        // Prompts user to enter keyword for searching books
        System.out.println("Enter keyword to search (title/author/genre): ");
        String keyword = input.nextLine();
        
        Vector vi = new Vector();
        int index;
        
        // Checks for matches with the keyword
        boolean found = false;
        while (lit.hasNext()){
            Book next = new Book((Book) lit.next());
            if((next.getTitle().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))||(next.getAuthor().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))||(next.getGenre().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))){
                index = lit.previousIndex();
                vi.add(index);
                found = true;
            }
        }
        
        ListIterator li = vi.listIterator();
        
        // Display results
        System.out.println("\nSEARCH RESULTS");
        System.out.println("----------------------------------------------------");        
        
        if (!found){            
            System.out.println("\nSorry, your search did not match any books.");
        }
        else{
            
            while (li.hasNext()){
                int i = (int) li.next();
                System.out.println("\n" + (i+1) + ". Title: " + v.get(i).getTitle());
                System.out.println("Author: " + v.get(i).getAuthor());
                System.out.println("ISBN: " + v.get(i).getISBN());
                System.out.println("Genre: " + v.get(i).getGenre());
                System.out.println("Price: RM " + v.get(i).getPrice());                
            }
            
        }
        
    }
    
    public static void displayBook(String results) throws Exception, IOException{
        
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
        
        int i = 1;
        System.out.println("\nBooks You Might Like");
        System.out.println("---------------------------------");
        while (lit.hasNext()){
            Book bk = lit.next();
            if (bk.genre.equals(results)){
                System.out.println("\n" + i + ". Title: " + bk.getTitle());
                System.out.println("Author: " + bk.getAuthor());
                System.out.println("ISBN: " + bk.getISBN());
                System.out.println("Price: RM " + bk.getPrice());
            }
            i++;
        }
    }
    
}
