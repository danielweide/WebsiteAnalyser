package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;



@SuppressWarnings("serial")
public class Frame extends JFrame {
    private JPanel pan;
    private CardLayout layout;

    public Frame(){
      this.setTitle("Website Analyser");
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      layout = new CardLayout();
      pan = new JPanel(layout);
      this.setContentPane(pan);
      this.setVisible(true);
    }

    public JPanel getPane() {
        return this.pan;
    }

    public CardLayout getLayout() {
        return layout;
    }
}
