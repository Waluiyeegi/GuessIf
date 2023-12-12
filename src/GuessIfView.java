import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class GuessIfView extends JFrame{
    GuessIf guessIf;
    private JButton[][] letterList;
    private JTextField inputText;
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints gbc;
    private String guessWord;

    JButton button;

    public GuessIfView(){
        guessWord = JOptionPane.showInputDialog(this, "enter word to guess").toLowerCase();
        while (guessWord.length() != 5){
            JOptionPane.showMessageDialog(this, "word length not correct please try again");
            guessWord = JOptionPane.showInputDialog(this, "enter word to guess").toLowerCase();
        }
        guessIf = new GuessIf(guessWord);

        frame = new JFrame("GuessIf");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        guessIf.setGuessIfView(this);
        gbc = new GridBagConstraints();
        this.letterList = new JButton[5][5];
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 5; y++){
                letterList[x][y] = new JButton(" ");
                letterList[x][y].setSize(300, 300);
                gbc.gridx = x;
                gbc.gridy = y;
                panel.add(letterList[x][y], gbc);
            }
        }


        inputText = new JTextField(5);
        inputText.setSize(800, 200);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(inputText, gbc);

        button = new JButton("submit");
        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(button, gbc);
        button.addActionListener(e -> guessIf.play(inputText.getText().toLowerCase()));
        frame.pack();




        frame.setVisible(true);
        this.setVisible(true);
    }

    public void update(GuessIfEvent e){
        char[][][] guessIfBoard = e.getGuessIfBoard();
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 5; y++){
                letterList[x][y].setText(String.valueOf(guessIfBoard[x][y][0]));
                if(guessIfBoard[x][y][1] == 'g'){
                    letterList[x][y].setBackground(Color.green);
                }
                if(guessIfBoard[x][y][1] == 'y'){
                    letterList[x][y].setBackground(Color.yellow);
                }
                if(guessIfBoard[x][y][1] == 'r'){
                    letterList[x][y].setBackground(Color.gray);
                }
            }
        }
        if(e.getStatus() == GuessIf.Status.WON){
            JOptionPane.showMessageDialog(this, "Congrats you won");
            this.dispose();
            frame.setVisible(false);
            this.setVisible(false);
        }
        if(e.getStatus() == GuessIf.Status.LOST){
            JOptionPane.showMessageDialog(this, "Sorry you lost, the correct word is " + guessIf.getCorrectWord());
            this.dispose();
            frame.setVisible(false);
            this.setVisible(false);
        }
        inputText.setText("");
        frame.pack();
    }

    public void invalidText(){
        JOptionPane.showMessageDialog(this, "word length not correct please try again");
        inputText.setText("");
        frame.pack();
    }
    public static void main(String[] args){
        GuessIfView gui = new GuessIfView();
    }

}
