import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class GuessIfTest {

    GuessIf guessIf;
    GuessIfView view;


    @Test
    public void testPlay(){
        //note you have to type in hello for both
        guessIf =new GuessIf("hello");
        view =new GuessIfView();
        guessIf.setGuessIfView(view);
        guessIf.play("caleb");
        assertEquals(GuessIf.Status.UNDECIDED, guessIf.getStatus());
        guessIf.play("hello");
        assertEquals(GuessIf.Status.WON, guessIf.getStatus());

        guessIf =new GuessIf("hello");
        view =new GuessIfView();
        guessIf.setGuessIfView(view);
        guessIf.play("caleb");
        guessIf.play("caleb");
        guessIf.play("caleb");
        guessIf.play("caleb");
        guessIf.play("caleb");
        assertEquals(GuessIf.Status.LOST, guessIf.getStatus());

        guessIf =new GuessIf("hello");
        view =new GuessIfView();
        guessIf.setGuessIfView(view);
        guessIf.play("caleb");
        guessIf.play("caleb");
        guessIf.play("caleb");
        guessIf.play("caleb");
        guessIf.play("hello");
        assertEquals(GuessIf.Status.WON, guessIf.getStatus());

    }

}
