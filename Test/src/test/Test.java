package test;

import java.io.*;
import java.util.*;

public class Test {

    public static void main(String[] args) throws Exception, IOException {
                  
       try{    
                    Quiz quiz = new Quiz(Database.listOfQuestion); 
                    File questChoices = new File("Questions_Choices.txt");
//                    File qz = new File("Quiz.txt");
                    Scanner sc = new Scanner(questChoices);
//                    PrintWriter pw = new PrintWriter(new FileWriter(qz, true));
                    BufferedReader br = new BufferedReader(new FileReader("Questions_Choices.txt"));
                    BufferedWriter bw = new BufferedWriter(new FileWriter("QuizTest.txt"));

                    String qC;
                                        
                    while((qC = br.readLine()) != null)
                    {
                        bw.write(qC);
                        bw.newLine();
                    }
                                                
                    bw.flush();
                    bw.close();
                    br.close();

//                    while(sc.hasNext())
//                    {
//                        quiz.setQuestionList(Database.listOfQuestion);
//   
//                        
//                    }

            Scanner kb = new Scanner(System.in);

            // userInput is being used in the "method", so have to initialize the instance variable with null value
            // without it will throw compile time error
            String userInput = null; 
            // creating an instance for quiz by accessing the qustions in database
            quiz = new Quiz(Database.listOfQuestion); 
            //Generate Question List from the database class
            Database.generateQuestion(); 
        
            String [] inputs = new String[4];
            // creating a quiz instance by uaing a list of questions in the databse
            quiz = new Quiz(Database.listOfQuestion); 
          
            quiz.shuffleQuiz();
            
            int counter = 0;
            
            // each question will be displayed from the quiz's QuestionList
            // foreach: allow the system to access an array object easily by pointing towards the object
            for(Question quest: quiz.getQuestionList())  
            {
                // display the questions and choices from Question class
                System.out.println(quest.getQuestion());
                System.out.println(quest.getChoices());
                inputs[counter] = kb.next(); 
                System.out.println("");
                counter ++;    
            }
            
            System.out.println("");
                    
            
            for(int a = 0 ; a < Database.listOfQuestion.size(); a++) 
            {
                System.out.println(inputs[a]);
            }
            
            System.out.println("");
                           
            boolean y = true;

            do
                {
                    System.out.println("");
                    System.out.println("=====================");
                    System.out.println(" Select Your Options: ");
                    System.out.println("=====================");
                    System.out.println(" 1. Search questions \n 2. Add questions into the list"
                                          + " \n 3. Edit questions \n 4. Delete questions \n 5. To exit");

                    System.out.println("");

                    // allow the administrator to select whether they want to search question, add question, edit question, delete question or exit the loop
                    System.out.print("Enter your choice: ");	
                    int option = Constants.input.nextInt();
                        
                    int count = 0; 
                    
                    switch(option)
                    { 
                        case 1: // search questions from quiz class
                            Scanner inputString1 = new Scanner(System.in); 
                            System.out.println("");
                            System.out.println("==============");
                            System.out.println("Question List:");
                            System.out.println("==============");
                            for(Question question: quiz.getQuestionList())
                            {
                                System.out.println(question.getQuestion());
                            }
                            System.out.println("");
                            System.out.print("Enter questions to be searched: ");
                            userInput = inputString1.nextLine();
                            quiz.SearchQuestion(userInput);
                            break;
                            
                        case 2: // add questions into the quiz
                            Boolean createChoices = true;
                            ArrayList<String> choices = new ArrayList<String>();
                            Scanner inputString2 = new Scanner(System.in); 
                            for(Question question: quiz.getQuestionList())
                            {
                                System.out.println(question.getQuestion());
                            }
                            System.out.println("");
                            System.out.print("Enter question that you wanted to add: ");
                            userInput = inputString2.nextLine();
                            System.out.println("Enter the choices for the question (enter 'exit' to stop): ");
                            do{
                                    String inputChoice = inputString2.next();
                                    if(inputChoice.equals("exit"))
                                    {
                                        createChoices = false;
                                    }
                            else
                            {
                                choices.add(inputChoice);
                            }
                            }while(createChoices == true);
                            boolean created = quiz.AddQuestion(userInput,choices);
                            if(created)     
                            {
                                System.out.println("Question is created");
                            }
                            else
                            {
                                System.out.println("This question already existed in the system. Please try again. ");
                            }
                            break; 
                            
                       case 3: // update existing quiz
                            boolean  edited = false;
                            ArrayList<String> editChoices = new ArrayList<String>();
                            Scanner input10 = new Scanner(System.in);  
                            System.out.println("Question List");
                            for(Question question : quiz.getQuestionList())
                            {
                                count++;
                                System.out.println(count + " " + question.getQuestion());  
                            }
                            System.out.println("Choose one: ");
                            String choosenQuestion = input10.nextLine();
                            System.out.print("Type in editted Question: ");
                            String input3 = input10.nextLine();  
                            System.out.print("Do you want to edit choices? (y/n)");
                            String input4 = input10.nextLine();
                            System.out.println("Enter 'exit' to stop");
                            if(input4.equals("y"))
                                    
                            {
                                Boolean editChoice = true;
                                do{
                                    String inputChoice = input10.nextLine();
                                    if(inputChoice.equals("exit"))
                                    {
                                        editChoice = false;
                                    }
                                    else
                                    {
                                        editChoices.add(inputChoice);
                                    }
                                }while(editChoice == true);
                               edited = quiz.EditQuestion(choosenQuestion,input3,editChoices);
                            }
                            else
                            {
                               edited = quiz.EditQuestion(choosenQuestion,input3);

                            }
                            
                            if(edited)
                            {
                                for(Question question : quiz.getQuestionList()){
                                    System.out.println(question.getQuestion());
                                    System.out.println(question.getChoices());
                                }
                                System.out.println("Edited success");
                            }
                            else
                            {
                                System.out.println("Failed");
                            }                                 
                            break;
                                                        
                        case 4: // delete questions from the quiz
                            Scanner inputString3 = new Scanner(System.in); 
                            System.out.print("Enter the questions that you want to delete: ");
                            userInput = inputString3.nextLine();
                            System.out.print("Enter secret passord: ");   
                            String secretPass = inputString3.nextLine();        
                            boolean deleted = quiz.DeleteQuestion(userInput,secretPass);
                            if(deleted) 
                            {
                                System.out.println("Deletion success");
                            }
                            else
                            {
                                System.out.println("Deletion fail. Please check your secretPass or question");
                            } 
                            break;
                            
                        case 5: // exit from the system
                            System.exit(0);
                            break;
                            
                        default: // if the option does not fall under one of these cases, it will display this output
                            System.out.println("Please enter a valid value");
                            break;
                        }
                    
                        System.out.println("");
                    
                        Scanner inputString4 = new Scanner(System.in); 

                        System.out.print("Do you want to continue? y/n  "); 
                        String quest = inputString4.nextLine();
                        if(quest.equals("n"))
                        {
                            y = false;
                        }
                        else
                        {
                            y = true;

                        }
                    }
                    while(y != false);
                }
            catch(Exception e)
            {
                     
            }
                     
    }    
}
