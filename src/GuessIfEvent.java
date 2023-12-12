import java.util.EventObject;

public class GuessIfEvent extends EventObject {
    private GuessIf.Status status;
    private char[][][] guessIfBoard;

    public GuessIfEvent(GuessIf guessIf, char[][][] guessIfBoard, GuessIf.Status status){
        super(guessIf);
        this.status = status;
        this.guessIfBoard = guessIfBoard;
    }

    public GuessIf.Status getStatus(){return status;}

    public char[][][] getGuessIfBoard(){return guessIfBoard;}
}
