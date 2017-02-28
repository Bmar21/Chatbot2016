package chat.view;

import chat.controller.ChatController;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ChatPanel extends JPanel 
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JLabel chatLabel;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JButton saveFile;
	private JButton loadFile;
	private JButton postTweet;
	private JButton searchTwitter;
	private JScrollPane chatPane;
	
	
	public ChatPanel(ChatController baseController)
	{
		super();
		setBackground(Color.CYAN);
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatLabel = new JLabel();
		chatDisplay = new JTextArea(5, 25);
		chatDisplay.setEditable(false);
		chatField = new JTextField(25);
		saveFile = new JButton();
		loadFile = new JButton();
		searchTwitter = new JButton();
		postTweet = new JButton();
		chatButton = new JButton("Chat with the bot");
		chatPane = new JScrollPane();
		
		
		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupChatDisplay()
	{
		chatDisplay.setEnabled(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
		chatPane.setViewportView(chatDisplay);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
//		this.add(chatDisplay);
		this.add(chatPane);
		this.add(chatButton);
		this.add(chatField);
		this.add(saveFile);
		this.add(loadFile);
		this.add(postTweet);
		this.add(searchTwitter);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 16, SpringLayout.SOUTH, chatPane);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 50, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 50, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatPane, -50, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 21, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 139, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -68, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userWords = chatField.getText();
				String bothResponse = baseController.useChatbotCheckers(userWords);
				//String oldText = chatDisplay.getText();
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said" + bothResponse);
				chatField.setText("");
				
				chatDisplay.setCaretPosition(0);
			}
		});
		}

}
