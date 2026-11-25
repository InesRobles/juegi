package vista;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class RatonEscalado extends JComponent implements MouseListener, MouseMotionListener {
    private final JComponent layer;   // el JLayer (da el tamaño real)
    private final JComponent holder;  // contenedor en coordenadas lógicas 1024x704
    private final int baseW, baseH;
    private Component hover, pressTarget;

    public RatonEscalado(JComponent layer, JComponent holder, int baseW, int baseH) {
        this.layer = layer;
        this.holder = holder;
        this.baseW = baseW;
        this.baseH = baseH;
        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private Point toLogical(MouseEvent e) {
        Point p = SwingUtilities.convertPoint(this, e.getPoint(), layer);
        double s = Math.min(layer.getWidth() / (double) baseW, layer.getHeight() / (double) baseH);
        int ox = (int) ((layer.getWidth()  - baseW * s) / 2);
        int oy = (int) ((layer.getHeight() - baseH * s) / 2);
        return new Point((int) ((p.x - ox) / s), (int) ((p.y - oy) / s));
    }

    private Component targetAt(Point lp) {
        if (lp.x < 0 || lp.y < 0 || lp.x >= baseW || lp.y >= baseH) return null;
        return SwingUtilities.getDeepestComponentAt(holder, lp.x, lp.y);
    }

    private void send(Component t, MouseEvent e, int id, Point lp) {
        if (t == null) return;
        Point q = SwingUtilities.convertPoint(holder, lp, t);
        t.dispatchEvent(new MouseEvent(t, id, e.getWhen(), e.getModifiersEx(),
                q.x, q.y, e.getClickCount(), e.isPopupTrigger(), e.getButton()));
    }

    private void updateHover(MouseEvent e, Point lp) {
        Component t = targetAt(lp);
        if (t != hover) {
            send(hover, e, MouseEvent.MOUSE_EXITED, lp);
            send(t, e, MouseEvent.MOUSE_ENTERED, lp);
            hover = t;
        }
    }

    @Override public void mouseMoved(MouseEvent e) {
        Point lp = toLogical(e);
        updateHover(e, lp);
        send(hover, e, MouseEvent.MOUSE_MOVED, lp);
    }
    @Override public void mouseDragged(MouseEvent e) {
        Point lp = toLogical(e);
        send(pressTarget, e, MouseEvent.MOUSE_DRAGGED, lp);
    }
    @Override public void mousePressed(MouseEvent e) {
        Point lp = toLogical(e);
        updateHover(e, lp);
        pressTarget = hover;
        send(pressTarget, e, MouseEvent.MOUSE_PRESSED, lp);
    }
    @Override public void mouseReleased(MouseEvent e) {
        Point lp = toLogical(e);
        send(pressTarget, e, MouseEvent.MOUSE_RELEASED, lp);
        if (pressTarget != null && pressTarget == targetAt(lp)) {
            send(pressTarget, e, MouseEvent.MOUSE_CLICKED, lp);
        }
        pressTarget = null;
        updateHover(e, lp);
    }
    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { updateHover(e, toLogical(e)); }
    @Override public void mouseExited(MouseEvent e) {
        send(hover, e, MouseEvent.MOUSE_EXITED, toLogical(e));
        hover = null;
    }
}