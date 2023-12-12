import java.util.ArrayList;
import java.util.Arrays;
//NOTE: Lab exam diagram shows 6 rows, but instructions stated 5 guesses, so i made rows 5
//NOTE: i did not need a controller so used e -> guessIf.play(inputText.getText().toLowerCase()) which is a way of having a
// controller with only 1 parameter, however i will keep an implementation of controller commented out.
//Caleb Lui-Yee 101187217
public class GuessIf {
    private char[][][] guessIfBoard;
    public enum Status {UNDECIDED, WON, LOST}
    private Status status;
    private GuessIfView view;
    private int turn;

    private int count;
    private char[] wordToGuess;



    public GuessIf(String guessWord) {
        this.guessIfBoard = new char[5][5][2];
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 5; y++){
                guessIfBoard[x][y][0] = ' ';
                guessIfBoard[x][y][1] = ' ';
            }
        }
        turn = 0;
        wordToGuess = guessWord.toCharArray();
        status = Status.UNDECIDED;

    }
    public void setGuessIfView(GuessIfView v){view = v;}

    public Status getStatus(){return status;}

    public String getCorrectWord(){return String.valueOf(wordToGuess);}


    public void play(String word){
        if (word.length() == 5){
            count = 0;
            char[] tempWord = new char[5];
            for (int i = 0; i<5; i++){
                guessIfBoard[i][turn][0] = word.charAt(i);
                if(guessIfBoard[i][turn][0] == wordToGuess[i]){
                    guessIfBoard[i][turn][1] = 'g';
                    if (count == 4){
                        status = Status.WON;
                    }
                    count ++;
                }
                else{
                    tempWord[i] = wordToGuess[i];
                    guessIfBoard[i][turn][1] = 'r';

                }
            }
            for (int i = 0; i<5; i++){
                if(Arrays.toString(tempWord).contains( String.valueOf(guessIfBoard[i][turn][0])) && guessIfBoard[i][turn][1] != 'g'){
                    guessIfBoard[i][turn][1] = 'y';
                }
            }

            if (turn == 4 && status != Status.WON){
                status = Status.LOST;
            }

            updateView(guessIfBoard);
            turn++;
        }
        else{
            view.invalidText();
        }
    }
    private void updateView(char[][][] guessIfBoard){
        view.update(new GuessIfEvent(this, guessIfBoard, status));
    }
}
