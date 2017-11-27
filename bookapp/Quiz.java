package bookapp;

import java.io.*;
import java.util.*;
import static bookapp.Book.displayBook;
import javax.swing.JOptionPane;

public class Quiz {
    
    public static void createQuiz() throws Exception, IOException{
        File quizfile;
        quizfile = new File("Quiz.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(quizfile, false));
        Scanner sc = new Scanner(quizfile);
        
        Scanner input = new Scanner(System.in);
        
        int qnum = 0;
        
        boolean num = false;
        while (!num){
            String in = JOptionPane.showInputDialog("Number of questions? ");
            try{
                qnum = Integer.parseInt(in);
                num = true;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a numerical input.");
            }
        }
                    
        Vector<Question> questions = new Vector();
        
        int i = 1;
        while (i <= qnum){
            Question q = new Question();
            String question = JOptionPane.showInputDialog("Enter question: ");
            q.setQuestion(question);
            
            Vector<Answer> answers = new Vector();
                        
            boolean next = true;
            while (next){
                Answer a = new Answer();
                JOptionPane.showMessageDialog(null, "Enter choices");
                String text = JOptionPane.showInputDialog("Enter display text: ");
                a.setChoice(text);
                String result = JOptionPane.showInputDialog("Enter corresponding result: ");
                a.setResult(result);
                answers.add(a);
                
                String answer = JOptionPane.showInputDialog("Do you wish to add more choices for this question? \nEnter 'Y' to add more choices for this question. \nEnter any other key to proceed.");

                if (answer.equalsIgnoreCase("Y")){
                    continue;
                }
                else {
                    next = false;
                }                
            }
            q.setAnswers(answers);
            questions.add(q); 
            
            i++;            
        }
        
        Iterator<Question> itq = questions.iterator();
        while (itq.hasNext()){
            Question question = itq.next();
            pw.println("Q " + question.getQuestion());
            Iterator<Answer> ita = question.getAnswers().iterator();
            while (ita.hasNext()){
                Answer answer = ita.next();
                pw.println("C " + answer.getChoice());
                pw.println("R " + answer.getResult());
            }
            pw.println("-");
        }
        pw.flush();
        pw.close();
        
    }
    public static void takeQuiz() throws Exception, IOException{
        
        File quizfile;
        quizfile = new File("Quiz.txt");
        Scanner sc = new Scanner(quizfile);
        
        // Uses Quiz - Copy.txt if Quiz.txt is empty
        if (!sc.hasNext()){
            quizfile = new File("Quiz - Copy.txt");
            sc = new Scanner(quizfile);
        }
        
        // Initializes vectors, Question and QuizAnswer
        Vector<Question> questions = new Vector();
        Vector<Answer> answers = new Vector();
        Question q = null;
        Answer a = null;
        
        // Reads input from file to set questions and answers
        while (sc.hasNext()){
            String line = sc.nextLine();
            if (line.startsWith("Q ")){
                q = new Question();
                q.setQuestion(line.replace("Q ", ""));
                answers = new Vector();                
            }
            else if (line.startsWith("C ")){
                a = new Answer();
                a.setChoice(line.replace("C ", ""));
            }
            else if (line.startsWith("R ")){
                a.setResult(line.replace("R ", ""));
                answers.add(a);
            }
            else if (line.equals("-")){
                q.setAnswers(answers);
                questions.add(q);
            }
        }
        
        Scanner input = new Scanner (System.in);
        boolean userinput = true;
        String choice;
        Question question = null;
        Answer answer = null;
        
        // Create vector for user input (choices)
        Vector c = new Vector();
        
        // Run quiz
        ListIterator<Question> itq = questions.listIterator();
        ListIterator<Answer> ita = null;
        
        JOptionPane.showMessageDialog(null, "Quiz");

        while (itq.hasNext()){
            while (userinput){
                question = itq.next();
                JOptionPane.showMessageDialog(null, "\n" + question.getQuestion());
                ita = question.getAnswers().listIterator();
                while (ita.hasNext()){
                    answer = ita.next();
                    JOptionPane.showMessageDialog(null, ((ita.previousIndex()+1) + answer.getChoice()));
                }
                userinput = false;
            }
            // User input
            choice = input.nextLine();
            ita = question.getAnswers().listIterator();
            while (ita.hasNext()){
                answer = ita.next();
                if (ita.previousIndex() == Integer.parseInt(choice) - 1){
                    c.add(answer.getChoice());
                }
            }          
            
            userinput = true;
        }
        
        ListIterator itc = c.listIterator();
        itq = questions.listIterator();
        Vector result = new Vector();
        
        
        while (itc.hasNext()){
            choice = (String) itc.next();
            CHOICE:
            while (itq.hasNext()){
                question = itq.next();
                answers = question.getAnswers();
                ita = answers.listIterator();
                while (ita.hasNext()){
                    answer = ita.next();
                    if (choice.equals(answer.getChoice())){
                        result.add(answer.getResult());
                        break CHOICE;
                    }
                }
            }              
        }
        
        String key = null;
        String results = null;
        int f = 0;
        int max = 0;
        
        Iterator it = result.iterator();
        while (it.hasNext()){
            key = (String) it.next();
            f = Collections.frequency(result, key);
            
            if (f > max){
                max = f;
                results = key;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Your results: " + results);
        displayBook(results);
    }
}

