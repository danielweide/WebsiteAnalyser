// package ensimag.GUI.buttons;


import java.awt.event.MouseEvent;
import java.text.ParseException;




public class SubmitButton extends AbstractGUIButton {

  public SubmitButton(View myPan) {
    super(myPan, "Submit");
  }

  @Override
  public void mouseClicked(MouseEvent event){
      System.out.println("Button clicked");
      ((View) getMyPane()).evaluateURL();
      getMyPane().revalidate();
  }
}
