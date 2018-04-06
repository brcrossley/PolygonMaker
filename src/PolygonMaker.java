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

    int scale = 50;

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

        //JLabel
        imageLabel = new JLabel();
        updateImage();
        JLabel scaleLabel = new JLabel("Scale: ");

        //JTextFields
        //SCALE
        JTextField scaleField = new JTextField("" + scale, 3);
        scaleField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale = Integer.parseInt(scaleField.getText());
                updateImage();
            }
        });

        //initialize the image


        //end setup
        panel.add(imageLabel);
        panel.add(scaleLabel);
        panel.add(scaleField);
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

        //draw image border
        g.setColor(Color.BLACK);
        g.drawLine(0,0,IMAGE_WIDTH, 0);
        g.drawLine(IMAGE_WIDTH - 1, 0, IMAGE_WIDTH - 1, IMAGE_HEIGHT);
        g.drawLine(IMAGE_WIDTH - 1, IMAGE_HEIGHT - 1, 0, IMAGE_HEIGHT - 1);
        g.drawLine(0, 0, 0, IMAGE_HEIGHT - 1);

        //draw Shape
        g.setColor(Color.RED);
        g.fillOval((IMAGE_WIDTH / 2) - (scale/2), (IMAGE_HEIGHT / 2) - (scale/2), scale, scale);

        imageLabel.setIcon(new ImageIcon(bi));
    }

    public static void main(String[] args) {
        new PolygonMaker();
    }
}
