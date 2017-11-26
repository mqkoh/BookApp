package test;

import java.util.*;
import java.io.*;

public class Database {

    // creating question type of ArrayList
    public static ArrayList<Question> listOfQuestion = new ArrayList<Question>();
    
        // Declaring the questions and choices by adding new question into the listOfQuestion in the generateQuestion Method
        public static void generateQuestion()
        {
            try{    
                    File q = new File("Questions.txt");
                    BufferedReader br = new BufferedReader(new FileReader("Questions.txt"));
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Quiz.txt"));

                    String questions;
                        
                    while((questions = br.readLine()) != null)
                    {
                        bw.write(questions);
                        bw.newLine();
                    }
                        
                        listOfQuestion.add(new Question("What is your gender?", " A: Male \n B: Female"));
                        listOfQuestion.add(new Question
                        ("Who is your favourite author?", 
                         " A: John Green \n B: Tom Hanks \n C: Rupi Kaur"
                        + "\n D: Dan Brown \n E: Whitney Cummings \n F: Ruth Ware"
                        + "\n G: Shari Lapena \n H: André Aciman \n I: Imbolo Mbue"
                        + "\n J: Neil deGrasse Tyson \n K: Maile Meloy \n L: Jill Santopolo"
                        + "\n M: Paula Hawkins \n N: David Grann \n O: Sheryl Sandberg and Adam Grant")) ;
                        listOfQuestion.add(new Question
                        ("What is your favourite book?", 
                         " A: Turtles All the Way Down \n B: Uncommon Type \n C: The Sun and Her Flowers"
                        + "\n D: Origin \n E: I'm Fine...And Other Lies \n F: The Lying Game"
                        + "\n G: A Stranger in the House \n H: Call Me By Your Name \n I: Behold the Dreamers"
                        + "\n J: Astrophysics for People in a Hurry \n K: Do Not Become Alarmed \n L: The Light We Lost"
                        + "\n M: Into the Water \n N: Killers of the Flower Moon \n O: Option B"));
                        listOfQuestion.add(new Question
                        ("What is the last category of book you have read?",
                         " A: Fantasy \n B: Science Fiction \n C: Romance"
                        + "\n D: Classics \n E: Non-Fiction \n F: Horror"));   

                                            
                    bw.flush();
                    bw.close();
                    br.close();
                }
            catch(Exception e)
            {
                     
            }
            
                 
        }
}
