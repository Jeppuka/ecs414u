/**
* ECS414U - Object Oriented Programming
* Queen Mary University of London, 2021/22.
* <p>
* Week 5 lab session.
*/

import java.awt.*;
import java.awt.event.*;


public class BankingApp extends Frame{

    /*
     * We will use this to print messages to the user.
     */
    private static TextArea infoArea = new TextArea("BankingApp 0.5");

    public static void print(String text){
	infoArea.setText(text);
    }
    //---


    private Agent agent;
    private Panel clientButtonsPanel;


    /**
     * This method prints the names of all clients.
     */
    public void printClients(){
	String text = agent.getListOfClientNames();
	print(text);
    }

    /**
     * This method prints the information of the client with the given index.
     */
    public void printClientInfo(int index){
	String text = agent.getClientInfo(index);
	print(text);
    }

    /**
     * This method takes all the necessary steps when a client is added.
     */
    public void addClient(String name){
	agent.addClient(new Client(name));

	// Uncomment for R3
	int numClients = agent.getNumberOfClients();
	Button btn = new Button("Client " + numClients);
	clientButtonsPanel.add(btn);
  btn.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent evt) {
      print(agent.getClientInfo(numClients-1));
    }
  });
	this.setVisible(true); // Just to refresh the frame, so that the button shows up
    }

    public BankingApp(){

	this.agent = new Agent();
	this.setLayout(new FlowLayout());

	// Make this button work
        Button reportButton=new Button("Print client list");

        reportButton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent evt) {

            printClients(); //prints the list of clients
          }
        });
	this.add(reportButton);

	// Make this button work
        Button addClientButton=new Button("Add client");
	addClientButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt) {

		    Prompt acp = new Prompt();
        TextField textName;
        textName = new TextField();
		    acp.add(textName);

        acp.addSubmitListener(new ActionListener(){ //makes a new listener to execute when the submit button is pressed
          public void actionPerformed(ActionEvent evt) { //This code actually adds the name to the client list

            addClient(textName.getText());
          }
        });



		    acp.activate();
		}
	    });


        this.add(addClientButton);

	// Make this button work
        Button depositButton = new Button("Deposit");

        depositButton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent evt) {

              depositPrompt dp = new depositPrompt(); //makes a new window similar to the Prompt when adding a new client but for adding funds to clients accounts

              TextField clientName, depositField;
              clientName = new TextField();
              dp.add(new Label("Name: "));
              dp.add(clientName);
              dp.add(new Label("Amount: "));
              depositField = new TextField();
              dp.add(depositField);


              dp.addSubmitListener(new ActionListener(){ //makes a new listener to execute when the deposit button is pressed
                public void actionPerformed(ActionEvent evt) { //This code actually adds the name to the client list

                    if(agent.deposit(clientName.getText(), Integer.parseInt(depositField.getText())) == false){
                      print("Account not found. Please try again.");
                    }
                    else{
                      print("Funds have been successfully deposited.");
                    }



                }
              });



              dp.activate();
          }
            });

	this.add(depositButton);

  // Make this button work
        Button withdrawButton = new Button("Withdraw");

        withdrawButton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent evt) {

              withdrawPrompt wp = new withdrawPrompt(); //makes a new window similar to the Prompt when adding a new client but for adding funds to clients accounts

              TextField clientName, withdrawField;
              clientName = new TextField();
              wp.add(new Label("Name: "));
              wp.add(clientName);
              wp.add(new Label("Amount: "));
              withdrawField = new TextField();
              wp.add(withdrawField);


              wp.addSubmitListener(new ActionListener(){ //makes a new listener to execute when the deposit button is pressed
                public void actionPerformed(ActionEvent evt) { //This code actually adds the name to the client list

                    if(agent.deposit(clientName.getText(), Integer.parseInt(withdrawField.getText())*-1) == false){
                      print("Account not found. Please try again.");
                    }
                    else{
                      print("Funds have been successfully withdrawn.");
                    }

                }
              });

              wp.activate();
          }
            });

  this.add(withdrawButton);


  // Make this button work
        Button removeButton = new Button("Remove Person");

        removeButton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent evt) {

              removePrompt rp = new removePrompt(); //makes a new window similar to the Prompt when adding a new client but for adding funds to clients accounts

              TextField clientName, withdrawField;
              clientName = new TextField();
              rp.add(new Label("Name: "));
              rp.add(clientName);

              rp.addSubmitListener(new ActionListener(){ //makes a new listener to execute when the deposit button is pressed
                public void actionPerformed(ActionEvent evt) { //This code actually adds the name to the client list

                    //add code here which will remove the agent corresponding to the name
                    String[] tempNames = new String[agent.getNumberOfClients()-1];
                    int[] tempFunds = new int[agent.getNumberOfClients()-1];

                    String tempName;
                    String tempFundString;
                    int tempFund;

                    String accountRemove;
                    int count = 0;
                    for(int x = 0; x<agent.getNumberOfClients();x++){

                      //these will be able to get the string formates of all of the names and their corresponding funds, so I can create a new list and add all the
                      //people back which were not removed and not add the person who is removed
                        tempName = agent.getClientInfo(x).substring(agent.getClientInfo(x).indexOf(" ")+1, agent.getClientInfo(x).indexOf("\n"));
                        tempFundString = agent.getClientInfo(x).substring(agent.getClientInfo(x).indexOf("\n")+8, agent.getClientInfo(x).length()-1);
                        tempFund = Integer.parseInt(tempFundString);

                        accountRemove = clientName.getText();

                        if(accountRemove.equals(tempName)){

                        }
                        else{
                          tempNames[count] = tempName;
                          tempFunds[count] = tempFund;
                          count++;
                        }

                    }
                    agent = new Agent();
                    clientButtonsPanel.removeAll();
                    for(int x=0; x<tempNames.length;x++){
                        addClient(tempNames[x]);
                        agent.deposit(tempNames[x], tempFunds[x]);
                    }
                    print("Account has been removed.");



                }
              });

              rp.activate();
          }
            });

  this.add(removeButton);

	// Output console
	infoArea.setEditable(false);
	this.add(infoArea);

	// Client button panel
	// Uncomment for R3
	clientButtonsPanel = new Panel();
	clientButtonsPanel.setLayout(new GridLayout(0,1));
	clientButtonsPanel.setVisible(true);
	this.add(clientButtonsPanel);


	// We add a couple of clients of testing purposes
	this.addClient("Alice Alison");
	this.addClient("Bob Robertson");

	// This is just so the X button closes our app
	WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);

	this.setSize(500,500);// Self explanatory
	this.setLocationRelativeTo(null); // Centers the window on the screen
	this.setVisible(true);// Self explanatory

    }

    public static void main(String[] args){
	new BankingApp();
    }
}
