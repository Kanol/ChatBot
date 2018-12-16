import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Bot {
    private String Name;
    private int rCounter;
    private int sizeOfAnswers;
    private File about;
    private File questions;
    private File answers;

    public File GetQuestions(){
        return this.questions;
    }

    public Bot(String name,String about,String questions, String answers) {
        this.Name = name;
        this.about = new File(about);
        this.questions = new File(questions);
        this.answers = new File(answers);
    }

    public void Hello() {
        System.out.println("Привет, меня зовут бот "+Name+"!");
        try {
            ArrayList<String> inAbout = new ArrayList<String>(ReadFile.readFileInList(this.about));
            var j = 0;
            while(j<inAbout.size()) {
                String line = inAbout.get(j);
                System.out.println(line);
                j++;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean Check_answ(String inAns, String rAns) {
        var flag = false;
        try {
            double parsedInAns = Double.parseDouble(inAns);
            double parsedRAns = Double.parseDouble(rAns);
            if(parsedInAns == parsedRAns) {
                flag = true;
            }
        }
        catch (Exception ex) {
            inAns = inAns.toLowerCase();
            rAns = rAns.toLowerCase();
            if(inAns.equals(rAns)) {
                flag = true;
            }
        }
        return flag;
    }

    public void parse() {
        try {
            Scanner inAnswers = new Scanner(System.in);
            ArrayList<String> questionsArray = new ArrayList<String>(ReadFile.readFileInList(this.questions));
            ArrayList<String> answersArray = new ArrayList<String>(ReadFile.readFileInList(this.answers));
            var i = 0;
            this.sizeOfAnswers = answersArray.size();
            while (i<questionsArray.size()) {
                String line = questionsArray.get(i);
                System.out.println(line);
                String inAnswer = inAnswers.nextLine();
                if(inAnswer.equals(this.Name)) {
                    this.Hello();
                    continue;
                }
                String rAns = answersArray.get(i);
                boolean flag = Check_answ(inAnswer, rAns);
                if(flag) {
                    this.rCounter++;
                }
                i++;
            }
            inAnswers.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("Поздравляю, вы набрали "+rCounter+" очков из "+this.sizeOfAnswers);
        }
    }
}
