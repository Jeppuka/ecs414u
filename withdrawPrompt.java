import java.awt.*;
import java.awt.event.*;
public class withdrawPrompt extends Frame{
    private Button withdraw;

    public withdrawPrompt(){
	this.setLayout(new GridLayout(0,2));

	withdraw = new Button("Withdraw");
	withdraw.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent evt) {
		    ((Frame)(evt.getSource())).dispose();
		}
	    });
    }

    public void addSubmitListener(ActionListener listener){
	withdraw.addActionListener(listener);
    }

    public void activate(){
        this.add(withdraw);
	this.pack(); // Resizes to tightly fit all its components
	this.setLocationRelativeTo(null); // Centers the window on the screen
	this.setVisible(true);
    }
}
