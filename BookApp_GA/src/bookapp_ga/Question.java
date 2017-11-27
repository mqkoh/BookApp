package bookapp_ga;

import java.util.Vector;

public class Question {
    
    private String question;
    private Vector answers;

    public Question() {
    }

    public Question(String question, Vector answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Vector getAnswers() {
        return answers;
    }

    public void setAnswers(Vector answers) {
        this.answers = answers;
    }
    
}
