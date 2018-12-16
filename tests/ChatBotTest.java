import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class ChatBotTest {

    private Bot bot;

    @BeforeEach
    public void initTest() {
        bot = new Bot("Chappie","aboutme.txt","questions.txt","answers.txt");
    }

    @Test
    public void testCheckAnswDouble() {
        assertEquals(Bot.Check_answ("4", "4"),true);
        assertEquals(Bot.Check_answ("4.1", "4"),false);
        assertEquals(Bot.Check_answ("4.0", "4"),true);
        assertEquals(Bot.Check_answ("4,0", "4"),false);
    }

    @Test
    public void testCheckAnswStr() {
        assertEquals(Bot.Check_answ("y", "n"),false);
        assertEquals(Bot.Check_answ("Y", "y"), true);
        assertEquals(Bot.Check_answ("y", "y"), true);
    }

    @Test
    public void testFileReader() throws FileNotFoundException {
        Scanner scan = new Scanner(bot.GetQuestions(),"utf-8");
        ArrayList<String> lines = new ArrayList<String>(ReadFile.readFileInList(bot.GetQuestions()));
        var i = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            assertEquals(line,lines.get(i));
            i++;
        }
    }

}

