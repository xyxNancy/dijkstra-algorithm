/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Romeo
 */
public class GUI extends JFrame implements ActionListener{
    JMenuBar mnBar;
    JMenu mnFile, mnHelp;
    JMenuItem mniNew, mniOpen, mniExit;
    JScrollPane scrGraph, scrSolve;

    JButton Point, Line, Start, End, Step, Solve, Clear;
    JPanel pnButton, pnMain, pnGraph, pnSolve;
    
    public GUI() {
        //Menu
        mnBar = new JMenuBar();
        mnFile = new JMenu("File");
        mnFile.setToolTipText("File");
        mnFile.addActionListener(this);

        mnHelp = new JMenu("Help!!!");
        mnHelp.setToolTipText("Help!!!");
        mnHelp.addActionListener(this);

        mniNew = new JMenuItem("New");
        mniNew.setToolTipText("New File");
        mniNew.addActionListener(this);

        mniOpen = new JMenuItem("Open");
        mniOpen.setToolTipText("Open File");
        mniOpen.addActionListener(this);

        mniExit = new JMenuItem("Exit");
        mniExit.setToolTipText("Exit Application");
        mniExit.addActionListener(this);

        //add Menu
        setJMenuBar(mnBar);
        mnBar.add(mnFile);
        mnBar.add(mnHelp);

        mnFile.add(mniNew);
        mnFile.add(mniOpen);
        mnFile.addSeparator();
        mnFile.add(mniExit);

        //Button
        Point = new JButton("Point");
        Point.addActionListener(this);
        Point.setToolTipText(null);

        Line = new JButton("Line");
        Line.addActionListener(this);
        Line.setToolTipText(null);

        Start = new JButton("Start");
        Start.addActionListener(this);
        Start.setToolTipText(null);

        End = new JButton("End");
        End.addActionListener(this);
        End.setToolTipText(null);

        Step = new JButton("Step");
        Step.addActionListener(this);
        Step.setToolTipText(null);

        Solve = new JButton("Solve");
        Solve.addActionListener(this);
        Solve.setToolTipText(null);

        Clear = new JButton("Clear");
        Clear.addActionListener(this);
        Clear.setToolTipText(null);

        //ScrollPane
        scrGraph = new JScrollPane();
        scrSolve = new JScrollPane();

        //pnButton
        pnButton = new JPanel(new GridLayout(7, 1));
        pnButton.add(Point);
        pnButton.add(Line);
        pnButton.add(Start);
        pnButton.add(End);
        pnButton.add(Step);
        pnButton.add(Solve);
        pnButton.add(Clear);

        //PanelMain
        pnGraph = new JPanel();
        pnGraph.add(scrGraph);
        pnGraph.setBorder(new TitledBorder("Graph"));

        pnSolve = new JPanel();
        pnSolve.add(scrSolve);
        pnSolve.setBorder(new TitledBorder("Solve"));

        pnMain = new JPanel(new GridLayout(1,2));
        pnMain.add(pnGraph);
        pnMain.add(pnSolve);

        setLayout(new BorderLayout());
        Container con = getContentPane();
        con.add(pnMain, BorderLayout.CENTER);
        con.add(pnButton, BorderLayout.WEST);

        setSize(800, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        GUI inter = new GUI();
    }

    public void actionPerformed(ActionEvent e) {
    }

}
