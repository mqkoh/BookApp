package test;

import java.util.ArrayList;

public class Question {
    
    private String question;
    private String answer;
    private String choices;
                    
    public Question()
    {
        
    }
           
    public Question(String question)
    {  
        this.question = question;
    }

    public Question(String question, String choices)
    {  
        this.question = question;  
        this.choices = choices;    
    }
  
    public Question(String question, String answer,String choices)
    { 
        this.question = question;    
        this.answer = answer;        
        this.choices = choices;
    }                                
    
    public String getQuestion() 
    {
        return question;
    }

    public void setQuestion(String question) 
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }
    
    public String getChoices() 
    {   
        return choices;
    }

    public void setChoices(String choices)
    {
        this.choices = choices;
    }
    
}

    
