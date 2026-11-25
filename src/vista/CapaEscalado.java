package vista;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;

public class CapaEscalado extends LayerUI<JComponent> {
    private final int baseW, baseH;
    private BufferedImage buffer;

    public CapaEscalado(int baseW, int baseH) {
        this.baseW = baseW;
        this.baseH = baseH;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if (buffer == null) {
            buffer = new BufferedImage(baseW, baseH, BufferedImage.TYPE_INT_ARGB);
        }

        // 1. Dibujamos todo a tamaño lógico (1024x704), sin escalar
        Graphics2D bg = buffer.createGraphics();
        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, baseW, baseH);
        super.paint(bg, c);
        bg.dispose();

        // 2. Escalamos la imagen entera de una vez
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());

        double scale = Math.min(c.getWidth() / (double) baseW,
                                c.getHeight() / (double) baseH);
        int w = (int) Math.round(baseW * scale);
        int h = (int) Math.round(baseH * scale);
        int ox = (c.getWidth() - w) / 2;
        int oy = (c.getHeight() - h) / 2;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(buffer, ox, oy, w, h, null);
        g2.dispose();
    }

    @Override
    public void paintImmediately(int x, int y, int w, int h, JLayer<? extends JComponent> l) {
        super.paintImmediately(0, 0, l.getWidth(), l.getHeight(), l);
    }
}