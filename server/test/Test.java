// package test;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextField;
import java.text.ParseException;
import javax.swing.JOptionPane;




// public class Test {
//   private static PartieFrame window;
//   private static JPanel page;
//
//   public static void main(String[] args){
//
//    // LoginFrame loginFrame = new LoginFrame();
//    //  PartieFrame partieFrame = new PartieFrame();
//     this.window = new PartieFrame();
//     this.page = new View();
//     this.window.getPane().add(this.loginPage, "LOGIN");
//
//
//   }
// }

@SuppressWarnings("serial")
class PartieFrame extends JFrame {
    private JPanel pan;
    private CardLayout layout;

    public PartieFrame(){
      this.setTitle("Chess");
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      layout = new CardLayout();
      pan = new View();
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



@SuppressWarnings("serial")
class View extends JPanel {
  private JTextField urlField;
  // private String mark = "";
  private JLabel mark = new JLabel("");

	View() {
	    super();
	    System.out.println("Page enter : Login");
	    this.setOpaque(true);
	    this.setBackground(new Color(150, 150, 150));

	    miseAJour();
	}


	public void miseAJour() {
	    this.removeAll();
      urlField = new JTextField(20);
      this.add(new JLabel("URL"));
      this.add(urlField);
	    // JComboBox playerChoice = new JComboBox(players.toArray());
	    // this.add(playerChoice);
      this.add(this.mark);

	    SubmitButton button = new SubmitButton(this);
	    this.add(button);
	    this.updateUI();
	}

  public void evaluateURL(){
    try {
      this.mark.setText( WebAnalyser.evaluate(urlField.getText()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Une erreur est survenue dans le format de la date ", "Erreur!",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
      return;
		}
    System.out.println("Submit");
    // this.miseAJour();
  }

}
