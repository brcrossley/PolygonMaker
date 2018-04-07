import java.awt.*;
import java.util.ArrayList;

public class DrawnPolygon {
    int x, y;
    ArrayList<Point> points;

    public DrawnPolygon(int x, int y) {
        this.x = x;
        this.y = y;
        points = new ArrayList<Point>();
    }

    public void addPoint(int px, int py) {
        Point p = new Point((x + px),(y + py));
        points.add(p);
    }

    public void render(Graphics g) {
        Point renderedPoints[] = new Point[points.size()];
        renderedPoints = points.toArray(renderedPoints);

        for(int i = 0; i < renderedPoints.length - 1; i++) {
            if(renderedPoints[i+1] != null) {
                g.drawLine(renderedPoints[i].x, renderedPoints[i].y, renderedPoints[i + 1].x, renderedPoints[i + 1].y);
            }
        }
    }
}
