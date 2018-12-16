public class main {

    public static void main(String[] args) {
        Bot bot = new Bot("Chappie", "aboutme.txt", "questions.txt", "answers.txt");
        bot.Hello();
        bot.parse();
    }

}
