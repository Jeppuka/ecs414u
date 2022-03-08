import java.awt.*;
import java.awt.event.*;
public class depositPrompt extends Frame{
    private Button deposit;

    public depositPrompt(){
	this.setLayout(new GridLayout(0,2));

	deposit = new Button("Deposit");
	deposit.addActionListener(new ActionListener() {
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
	deposit.addActionListener(listener);
    }

    public void activate(){
        this.add(deposit);
	this.pack(); // Resizes to tightly fit all its components
	this.setLocationRelativeTo(null); // Centers the window on the screen
	this.setVisible(true);
    }
}
