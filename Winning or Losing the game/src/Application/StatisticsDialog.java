package Application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Created by Pasindu on 12/11/2016.
 */

public class StatisticsDialog extends JDialog {

    private int wins;
    private int loses;
    private double avg;
    private JPanel panel;
    private JLabel lbWinsName;
    private JLabel lbLostName;
    private JLabel lbWins;
    private JLabel lbLost;
    private JLabel lbAvgName;
    private JLabel lbAvg;
    private File f;

    private PrintWriter printWriter = null;

    private FileWriter fileWriter = null;
    private JButton print;

    public StatisticsDialog(int wins, int loses, double avg, Frame parent, boolean modal) {
        super(parent, modal);
        this.wins = wins;
        this.loses = loses;
        this.avg = avg;

        init();

    }

    private void init() {
        panel = new JPanel(new BorderLayout());
        panel.setLayout(new GridLayout(3, 6));
        JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbWinsName = new JLabel("Wins : ");
        jp1.add(lbWinsName);
        lbWins = new JLabel(wins + "");
        jp1.add(lbWins);
        lbLostName = new JLabel("Lost : ");
        lbLost = new JLabel(loses + "");
        jp2.add(lbLostName);
        jp2.add(lbLost);
        lbAvgName = new JLabel("Avarage credit netted : ");
        lbAvg = new JLabel(avg + "");
        jp3.add(lbAvgName);
        jp3.add(lbAvg);
        JPanel jpWins = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jpLoses = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel jpAvg = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        

        panel.add(jp1);
        panel.add(jp2);
        panel.add(jp3);
        panel.add(lbWins);
        panel.add(lbLost);
        panel.add(lbAvg);
        print = new JButton("Print Statics");
        panel.add(print);

        String value = "Wins : " + wins + "  Lost : " + loses + "   Avarage credit netted : " + avg + "";

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        f = new File(timeStamp + ".txt");
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {

                    fileWriter = new FileWriter(f, false);

                    printWriter = new PrintWriter(fileWriter, true);

                    printWriter.println(value);//write string value to txt file

                } catch (FileNotFoundException e) {

                    System.out.println(e.getMessage());

                } catch (IOException e) {

                    System.out.println(e.getMessage());

                } finally {

                    try {

                        fileWriter.close(); // close the file writer

                    } catch (IOException e) {

                        System.out.println(e.getMessage());

                    }

                }
            }
        });
        this.add(panel);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
