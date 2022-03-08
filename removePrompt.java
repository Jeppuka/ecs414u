import java.awt.*;
import java.awt.event.*;
public class removePrompt extends Frame{
    private Button remove;

    public removePrompt(){
	this.setLayout(new GridLayout(0,2));

	remove = new Button("Remove Person");
	remove.addActionListener(new ActionListener() {
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
	remove.addActionListener(listener);
    }

    public void activate(){
        this.add(remove);
	this.pack(); // Resizes to tightly fit all its components
	this.setLocationRelativeTo(null); // Centers the window on the screen
	this.setVisible(true);
    }
}
