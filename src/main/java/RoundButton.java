import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundButton extends JButton {
    private int radius;
    private Color color;
    public RoundButton(String text, int radius, Color color) {
        super(text);
        this.radius = radius;
        this.color = color;
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillRoundRect(0, 0, super.getSize().width - 1, super.getSize().height - 1, radius, radius);
        super.paintComponent(g2);
        g2.dispose();
    }
}
