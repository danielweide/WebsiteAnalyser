// package ensimag.GUI.buttons;


import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.MouseListener;


public abstract class AbstractGUIButton extends JButton implements MouseListener {
  private JPanel myPan;

  public AbstractGUIButton(JPanel myPan, String label) {
	super(label);
    this.addMouseListener(this);
    this.myPan = myPan;
  }

  @Override
  public abstract void mouseClicked(MouseEvent event);

  public void mouseEntered(MouseEvent event) {}
  public void mouseExited(MouseEvent event) {}
  public void mousePressed(MouseEvent event) {}
  public void mouseReleased(MouseEvent event) {}

  protected JPanel getMyPane(){
	  return this.myPan;
  }
}
