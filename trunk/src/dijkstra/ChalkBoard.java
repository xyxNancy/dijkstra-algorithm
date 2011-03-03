import java.awt.*;
import java.awt.event.*;

public class ChalkBoard extends Panel {

    private int last_x, last_y;
    public Color currentColor = Color.black;


    public void setMyColor(Color c) {
        currentColor= c;
    }

    ChalkBoard(){

        setBackground(Color.white);
        setSize(400,400);

        addMouseListener( new MouseAdapter() {

        public void mousePressed(MouseEvent e) {

            last_x = e.getX(); last_y = e.getY();
            System.out.println(last_x);
            }
        }
        );

        addMouseMotionListener( new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                g.setColor(currentColor);
                g.drawLine(last_x,last_y,e.getX(),e.getY());
                last_x = e.getX(); last_y = e.getY();
            }
        }
        );

    }

    static public void main(String[] args) {

        Frame f = new Frame("this is a Frame");
        f.setLayout(new BorderLayout());
        f.setSize(450,500);
        f.setBackground(Color.green);
        final ChalkBoard cb = new ChalkBoard();
        f.add(cb, BorderLayout.CENTER);
        cb.setLocation(25,50);

        f.add(new Panel(), BorderLayout.EAST);
        f.add(new Panel(), BorderLayout.NORTH);
        f.add(new Panel(), BorderLayout.WEST);

        Panel pan= new Panel();
        pan.setLayout(new GridLayout());
        f.add(pan, BorderLayout.SOUTH);

        Panel p1= new Panel();
        p1.setLayout(new FlowLayout());
        Panel p2= new Panel();
        p2.setLayout(new FlowLayout());
        pan.add(p1);
        pan.add(p2);


        //-------
        Button b= new Button("Blue");
        //b.setBounds(100, 100, 200, 200);
        p1.add(b);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.out.println("Blue");
                cb.setMyColor(Color.BLUE);
            }
        }
        );

        b= new Button("Red");
        //b.setBounds(100, 100, 200, 200);
        p1.add(b);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
                {
                System.out.println("Red");
                cb.setMyColor(Color.RED);
        }
        }
        );

        b= new Button("Black");
        //b.setBounds(100, 100, 200, 200);
        p1.add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Black");
                cb.setMyColor(Color.BLACK);
            }
        }
        );

        b= new Button("Quit");
        //b.setBounds(100, 100, 200, 200);
        p2.add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Quit");
                System.exit(0);
            }
        }
        );

        f.setVisible(true);
        }
}