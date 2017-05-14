package Application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Created by Pasindu on 12/11/2016.
 */

public class SlotMachine extends JFrame {

    private JPanel panel;
    private JPanel reelPannel;
    private Border border;
    private Reel reel;
    private JLabel reel1;
    private JLabel reel2;
    private JLabel reel3;
    private JButton btSpin;
    private JButton btReset;
    private JButton btBetOne;
    private JButton btBetMax;
    private JButton btAddCoin;
    private JPanel creaditAreaPanel;
    private JLabel creaditAreaLabel;
    private JLabel creaditAreaName;
    private JPanel statisticsPanel;
    private JButton btStatistics;
    private JPanel betAreaPanel;
    private JLabel betAmount;
    private JLabel betAmountName;
    private JPanel jp1;
    private JPanel bottomPanel;
    private int credit = 0;
    private int bet = 0;
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;
    private boolean isSpin;
    private int wins;
    private int loses;
    private int totalGames;
    private double avg;
    private int totalCreaditSpend;
    private int reel1Value;
    private int reel2Value;
    private int reel3Value;
    private int betValue;

    public SlotMachine() {
        super("Winning or Losing Game");
        credit = 10;
        bet = 0;
        isSpin = false;
        initialize();

    }

    private void initialize() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        reelPannel = new JPanel(new GridLayout(1, 3));
        border = LineBorder.createGrayLineBorder();
        bottomPanel = new JPanel(new GridLayout(2, 2));
        reel = new Reel();
        reel1 = new JLabel();
        reel1.setBorder(border);

        JPanel reel1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        reel1.setIcon(reel.spin()[0].getImage());
        reel2 = new JLabel();
        reel2.setBorder(border);

        JPanel reel2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        reel2.setIcon(reel.spin()[0].getImage());
        reel3 = new JLabel();
        reel3.setBorder(border);

        JPanel reel3Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        reel3.setIcon(reel.spin()[0].getImage());

        reel1Panel.add(reel1);
        reel2Panel.add(reel2);
        reel3Panel.add(reel3);

        reelPannel.add(reel1Panel);
        reelPannel.add(reel2Panel);
        reelPannel.add(reel3Panel);

        btSpin = new JButton("Spin");
        btAddCoin = new JButton("Add Coin");
        btReset = new JButton("Reset");
        btBetOne = new JButton("Bet One");
        btBetMax = new JButton("Bet Max");

        btSpin.setEnabled(false);
        btReset.setEnabled(false);
        creaditAreaPanel = new JPanel();
        creaditAreaPanel.setLayout(new GridLayout(2, 2));
        creaditAreaPanel.setBorder(new TitledBorder("Credit Area"));

        creaditAreaName = new JLabel("Credit : ");
        JPanel creditNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel creditAmountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        creditNamePanel.add(creaditAreaName);
        creaditAreaLabel = new JLabel(credit + "");
        creditAmountPanel.add(creaditAreaLabel);

        creaditAreaPanel.add(creditNamePanel);
        creaditAreaPanel.add(creditAmountPanel);
        JPanel creditBtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        creditBtPanel.add(btAddCoin);
        creaditAreaPanel.add(creditBtPanel);
        bottomPanel.add(creaditAreaPanel);

        JPanel spinBtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        spinBtPanel.add(btSpin);
        bottomPanel.add(spinBtPanel);

        betAreaPanel = new JPanel();
        betAreaPanel.setLayout(new GridLayout(4, 1));

        betAreaPanel.setBorder(new TitledBorder("Bet Area"));

        betAmountName = new JLabel("Bet Amount :");
        betAmount = new JLabel("0");
        jp1 = new JPanel(new FlowLayout());
        jp1.add(betAmountName);
        jp1.add(betAmount);
        betAreaPanel.add(jp1);
        JPanel betOneBtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel betMaxBtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel resetBtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        betOneBtPanel.add(btBetOne);
        betMaxBtPanel.add(btBetMax);
        resetBtPanel.add(btReset);

        betAreaPanel.add(betOneBtPanel);
        betAreaPanel.add(betMaxBtPanel);
        betAreaPanel.add(resetBtPanel);

        bottomPanel.add(betAreaPanel);

        statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new GridLayout(4, 1));

        statisticsPanel.setBorder(new TitledBorder("Statistics"));
        btStatistics = new JButton("Statistics");
        JPanel statisticsBtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statisticsBtPanel.add(btStatistics);

        btStatistics.setEnabled(false);

        statisticsPanel.add(statisticsBtPanel);

        bottomPanel.add(statisticsPanel);

        panel.add(reelPannel, BorderLayout.PAGE_START);
        panel.add(bottomPanel, BorderLayout.CENTER);
        this.add(panel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btAddCoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit++;
                creaditAreaLabel.setText(credit + "");
            }
        });

        btBetOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                betOne(e);
            }
        });

        btBetMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               betMax(e);
            }
        });

        btReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bet > 0) {
                    credit += bet;
                    bet = 0;
                    creaditAreaLabel.setText(credit + "");
                    btReset.setEnabled(false);

                    betAmount.setText(bet + "");
                    btSpin.setEnabled(false);
                    btBetMax.setEnabled(true);

                }
            }
        });

        btSpin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            spinReels();
            }
        });

        btStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStatistics(e);
            }
        });

        reel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopSpin();
            }
        });

        reel2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopSpin();
            }
        });

        reel3.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopSpin();
            }
        }
        );

    }

    private void betOne(ActionEvent e) {
        if (credit > 0) {
            btReset.setEnabled(true);

            bet++;
            credit--;
            creaditAreaLabel.setText(credit + "");

            betAmount.setText(bet + "");
            btSpin.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sorry not enough credits, please add credits and try again", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void betMax(ActionEvent e) {
        if (credit >= 3) {
            btReset.setEnabled(true);
            bet += 3;
            credit -= 3;
            creaditAreaLabel.setText(credit + "");

            betAmount.setText(bet + "");
            btSpin.setEnabled(true);
            btBetMax.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Sorry not enough credits, please add credits and try again", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStatistics(ActionEvent e) {
        avg=totalCreaditSpend/totalGames;
        StatisticsDialog dialog = new StatisticsDialog(wins, loses, avg,this,true);
        dialog.setVisible(true);
    }

    private void spinReels() {
        bet=0;
        Random r = new Random();
        betValue = Integer.parseInt(betAmount.getText());
        betAmount.setText("0");
        btSpin.setEnabled(false);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                timer1 = new Timer(50, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int num = r.nextInt(6) + 2;
                        reel1.setIcon(reel.getSymbol(num).getImage());
                        reel1Value = num;
                    }
                });
                timer1.start();
                isSpin = true;
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                timer2 = new Timer(50, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int num = r.nextInt(6) + 2;
                        reel2.setIcon(reel.getSymbol(num).getImage());
                        reel2Value = num;
                    }
                });
                timer2.start();
                isSpin = true;
            }
        });

        thread2.start();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run(){

                    timer3 = new Timer(50, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int num = r.nextInt(6) + 2;
                            reel3.setIcon(reel.getSymbol(num).getImage());
                            reel3Value = num;
                        }
                    });
                    timer3.start();
                    isSpin = true;
                }

        });

        thread3.start();
btStatistics.setEnabled(true);
    }

    private void stopSpin() {
        if (isSpin) {
            totalGames++;
            timer1.stop();
            timer2.stop();
            timer3.stop();
            isSpin = false;
            btSpin.setEnabled(false);
            btReset.setEnabled(false);
            btBetMax.setEnabled(true);
            if ((reel1Value == reel2Value) && (reel1Value == reel3Value) && (reel2Value == reel3Value)) {
                JOptionPane.showMessageDialog(this, "Congratulation You won "+(betValue * reel1Value) + " credis..!");
                credit += (betValue * reel1Value);
                creaditAreaLabel.setText(credit + "");
                totalCreaditSpend += betValue;
                wins++;
            } else if ((reel1Value == reel2Value) || (reel1Value == reel3Value) || (reel2Value == reel3Value)) {
                JOptionPane.showMessageDialog(this, "You Got Free Spin !");
                spinReels();
                betAmount.setText(betValue + "");
            } else {
                JOptionPane.showMessageDialog(this, "You Lose!");
                loses++;
                totalCreaditSpend += betValue;

            }
        }

    }

    public static void main(String[] args) {

        new SlotMachine().setVisible(true);
    }

}
