package test;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    
    // Initializing ArrayList
    // Initializing a Question type of ArrayList
    private ArrayList <Question> questionList;    
        
    public Quiz(ArrayList <Question> questionList)
    {
        this.questionList = questionList;
    }
    
    public ArrayList <Question> getQuestionList()
    {
        return questionList;
    }
       
    public void setQuestionList(ArrayList <Question> questionList)
    {
        this.questionList = questionList;
    }
    
    // the question list will be shuffled every time when running the program
    public void shuffleQuiz()
    {
        Collections.shuffle(questionList);
    }
    
    public boolean SearchQuestion(String input)
    {  
        for(Question question:questionList)
        {
            if(question.getQuestion().equals(input))
            {
                System.out.println(question.getQuestion());
                System.out.println(question.getChoices());
                System.out.println(input + " is found");
                
                return true;
            }         
        }
        System.out.println(input + " is not found");
        return false;
    }
    
       public boolean AddQuestion(String input,ArrayList<String> choices)
        {
            String combinedChoice = null;
            for(Question question: questionList)
            {
                if(!question.getQuestion().equals(input))
                {
                    for(String choice : choices){
                        combinedChoice = combinedChoice+ " " +"\n"+ choice;
                    }
                    questionList.add(new Question(input,combinedChoice));
                    System.out.println(input);
                    System.out.println(choices);
                    return true;
                }
            }
        return false;
    }
       
    //Overloaded 
    public boolean EditQuestion(String input,String newQuestion){
        int counter = 0;
        
            for(Question question:questionList)
            {
                if(question.getQuestion().equals(input))
                {
                    questionList.get(counter).setQuestion(input);
                    return true;

                    
                }
                counter++;
            }
        
        return false;
    }
     
    //Overloaded 
    public boolean EditQuestion(String input,String newQuestion, ArrayList<String> questionChoice){
        int counter = 0;
        String newChoice = " ";
       
            for(Question question:questionList)
            {
                if(question.getQuestion().equals(input))
                {
                    // the counter is used to keep track of the index in UpdateQuestion (questionList.get (Index) )
                    if(questionChoice.get(0) != null){
                       questionList.get(counter).setQuestion(newQuestion);
                       for(String inputChoice : questionChoice){
                        newChoice = newChoice+ " " +"\n"+ inputChoice;
                       }
                       questionList.get(counter).setChoices(newChoice);
                       return true;
                    } 
                }
                counter++;
            }
        return false;
    }
    
    
    public boolean DeleteQuestion(String input, String secretPassword)
    {
        // use Secret password to delete a question, if it is wrong then NO delete occurs
        if(secretPassword.equals("Readersville"))
        {
            for(Question question: questionList)
            {
                 System.out.println(question.getQuestion());
                if(question.getQuestion().equals(input))
                {
                   questionList.remove(question);
                    return true;
                }
            }
        }  
        return false;
    } 
    
}
