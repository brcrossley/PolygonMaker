import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PolygonMaker {

    BufferedImage bi;
    final int GUI_WIDTH = 500;
    final int GUI_HEIGHT = 300;
    final int IMAGE_WIDTH = 200;
    final int IMAGE_HEIGHT = 200;

    JLabel imageLabel;
    DrawnPolygon p;

    int newPointX;
    int newPointY;

    public PolygonMaker() {
        //set up frame
        JFrame frame = new JFrame("Polygon Maker");
        JPanel panel = new JPanel();
        frame.setSize(GUI_WIDTH, GUI_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //set up BufferedImage
        bi = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        p = new DrawnPolygon(IMAGE_WIDTH / 2, IMAGE_HEIGHT / 2);

        //JLabel
        imageLabel = new JLabel();
        updateImage();
        JLabel newPointLabel = new JLabel("New Point: ");
        JLabel newPointXLabel = new JLabel("X: ");
        JLabel newPointYLabel = new JLabel("Y: ");

        //JTextFields
        //NEW POINT
        JTextField newPointXField = new JTextField("" + newPointX, 3);
        JTextField newPointYField = new JTextField("" + newPointY, 3);

        //JButtons
        JButton newPointBtn = new JButton("Add New Point");
        newPointBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPointX = Integer.parseInt(newPointXField.getText());
                newPointY = Integer.parseInt(newPointYField.getText());
                p.addPoint(newPointX, newPointY);
                newPointXField.setText("0");
                newPointYField.setText("0");
                updateImage();
            }
        });


        //end setup
        panel.add(imageLabel);
        panel.add(newPointLabel);
        panel.add(newPointXLabel);
        panel.add(newPointXField);
        panel.add(newPointYLabel);
        panel.add(newPointYField);
        panel.add(newPointBtn);
        frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);
    }

    //using scale for testing purposes
    private void updateImage() {
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setBackground(new Color(0, 0, 0, 0));
        g.clearRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHints(rh);

        //draw image border and coordinate plane
        g.setColor(Color.BLACK);
        g.drawLine(0,0,IMAGE_WIDTH, 0);
        g.drawLine(IMAGE_WIDTH - 1, 0, IMAGE_WIDTH - 1, IMAGE_HEIGHT);
        g.drawLine(IMAGE_WIDTH - 1, IMAGE_HEIGHT - 1, 0, IMAGE_HEIGHT - 1);
        g.drawLine(0, 0, 0, IMAGE_HEIGHT - 1);

        g.drawLine(IMAGE_WIDTH / 2, 0, IMAGE_WIDTH / 2, IMAGE_HEIGHT);
        g.drawLine(0, IMAGE_HEIGHT /2, IMAGE_WIDTH, IMAGE_HEIGHT / 2);

        //draw Shape
        g.setColor(Color.RED);
        p.render(g);

        imageLabel.setIcon(new ImageIcon(bi));
    }

    public static void main(String[] args) {
        new PolygonMaker();
    }
}
