package bookapp;

public class Answer {
    
    private String choice;
    private String result;

    public Answer() {
    }

    public Answer(String choice, String result) {
        this.choice = choice;
        this.result = result;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
    
}
