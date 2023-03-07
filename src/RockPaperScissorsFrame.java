import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame
{
    JTextField tiesTf;
    JTextField playWinTf;
    JTextField compWinTf;
    JLabel playWinLbl;
    JLabel compWinLbl;
    JLabel tiesLbl;
    JPanel mainPnl;
    JPanel topPnl;
    JPanel centerPnl;
    JPanel statsPnl;
    JButton quitBtn;
    JScrollPane pane;
    TextArea rpsTA;
    JButton rockBtn;
    JButton scisBtn;
    JButton paperBtn;
    Random rnd = new Random();
    String PlayerMove = "";
    int getComMove=0;
    int playerWins = 1;
    int comWins =1;
    int tie = 1;
    int playRock = 0;
    int playPap = 0;
    int playScis = 0;



    public RockPaperScissorsFrame()
    {
        CreateGUI();
        setTitle("Rock, Paper, Scissors!!!!");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
    private void CreateGUI()
    {
        mainPnl = new JPanel();
        topPnl = new JPanel();
        centerPnl = new JPanel();
        statsPnl = new JPanel();


        mainPnl.setLayout(new BorderLayout());
        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);
        createCenterPnl();
        mainPnl.add(centerPnl, BorderLayout.CENTER);
        createStatsPnl();
        mainPnl.add(statsPnl, BorderLayout.SOUTH);

        add(mainPnl);



    }
    private void createCenterPnl()
    {

        rpsTA = new TextArea(15, 50);

        pane = new JScrollPane(rpsTA);
        centerPnl.add(pane);
    }

    private void createTopPnl()
    {
        topPnl.setLayout(new GridLayout(1,4));
        rockBtn = new JButton("Rock");
        rockBtn.setIcon(new ImageIcon(new ImageIcon("src//rock.png").getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT)));
        rockBtn.addActionListener((ActionEvent ae) ->
        {
            String cpuMove = "R";
            playRock ++;
            resolveMove("R");
        });
        paperBtn = new JButton("Paper" /*new ImageIcon("src//paper.jpg"*/);
        paperBtn.setIcon(new ImageIcon(new ImageIcon("src//paper.jpg").getImage().getScaledInstance(130, 100, Image.SCALE_DEFAULT)));
        paperBtn.addActionListener((ActionEvent ae) ->
        {
            String cpuMove = "P";
            playPap ++;
            resolveMove("P");
        });
        scisBtn = new JButton("Scissors");
        scisBtn.setIcon(new ImageIcon(new ImageIcon("src//scissor.jpg").getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT)));
        scisBtn.addActionListener((ActionEvent ae) ->
        {
            String cpuMove = "S";
            playScis ++;
            resolveMove("S");
        });
        quitBtn = new JButton("Quit"); //need a picture
        quitBtn.setIcon(new ImageIcon(new ImageIcon("src//exit.png").getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT)));
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            System.exit(0);
        });
        topPnl.add(rockBtn);
        topPnl.add(paperBtn);
        topPnl.add(scisBtn);
        topPnl.add(quitBtn);


    }
    private String getCompMove(String playerMove)
    {
        String compMove = "";
        int cpuMove = rnd.nextInt(3);
        if(cpuMove == 0){
            compMove = "R";
        }
        else if(cpuMove == 1){
            compMove = "P";
        }
        else{
            compMove = "S";
        }
        return compMove;


    }
    public void resolveMove(String playerMove)
    {
        String results = "";
        String cpuMove = getCompMove(playerMove);



        if(cpuMove.equalsIgnoreCase("R"))
        {
            if(playerMove.equalsIgnoreCase("R"))
            {
                results = "Rock vs Rock, a tie.";
                tiesTf.setText(tie++ + "");
            }
            else if(playerMove.equalsIgnoreCase("P"))
            {
                results = "Rock vs Paper, Player wins!";
                playWinTf.setText(playerWins++ + "");
            }
            else{
                results = "Rock vs Scissors, Com wins!";
                compWinTf.setText(comWins++ + "");
            }

        }
        else if(cpuMove.equalsIgnoreCase("P"))
        {
            if(playerMove.equalsIgnoreCase("R"))
            {
                results = "Paper vs Rock, Com wins!";
                compWinTf.setText(comWins++ + "");
            }
            else if(playerMove.equalsIgnoreCase("P"))
            {
                results = "Paper vs Paper, a tie.";
                tiesTf.setText(tie++ + "");
            }
            else{
                results = "Paper vs Scissors, Player wins!";
                playWinTf.setText(playerWins++ + "");
            }
        }
        else if(cpuMove.equalsIgnoreCase("S"))
        {
            if(playerMove.equalsIgnoreCase("R"))
            {
                results = "Scissors vs Rock, Player wins!";
                playWinTf.setText(playerWins++ + "");
            }
            else if(playerMove.equalsIgnoreCase("P"))
            {
                results = "Scissors vs Paper, Com wins!";
                compWinTf.setText(comWins++ + "");
            }
            else{
                results = "Scissors vs Scissors, a tie.";
                tiesTf.setText(tie++ + "");
            }

        }
        rpsTA.append("Player: "+ playerMove+ " Computer: "+ cpuMove + "\n   "+ results + "\n");

    }


    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    private void createStatsPnl()
    {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(3,2));

        playWinLbl = new JLabel("Player wins:");
        compWinLbl = new JLabel("Computer wins:");
        tiesLbl = new JLabel("Ties:");
        playWinTf = new JTextField(10);
        compWinTf = new JTextField(10);
        tiesTf = new JTextField(10);

        statsPnl.add(compWinLbl);
        statsPnl.add(compWinTf);
        statsPnl.add(playWinLbl);
        statsPnl.add(playWinTf);
        statsPnl.add(tiesLbl);
        statsPnl.add(tiesTf);

        JTextField PlayWinTf, compWinTf, tiesTf;

    }

}
