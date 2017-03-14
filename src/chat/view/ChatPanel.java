package chat.view;

import chat.controller.ChatController;
import chat.controller.FileController;

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
		setBackground(new Color(34, 139, 34));
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatLabel = new JLabel();
		chatDisplay = new JTextArea(5, 25);
		chatDisplay.setEditable(false);
		chatField = new JTextField(25);
		saveFile = new JButton("Save");
		loadFile = new JButton("Load");
		searchTwitter = new JButton("Search Twitter");		
		postTweet = new JButton("Post to Twitter");
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
		chatDisplay.setEditable(false);
		chatPane.setViewportView(chatDisplay);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
//		this.add(chatDisplay);
		saveFile.setToolTipText("Put a name in the testfield");
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
		baseLayout.putConstraint(SpringLayout.SOUTH, saveFile, -37, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveFile, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadFile, 6, SpringLayout.SOUTH, saveFile);
		baseLayout.putConstraint(SpringLayout.WEST, loadFile, 0, SpringLayout.WEST, saveFile);
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitter, 0, SpringLayout.NORTH, loadFile);
		baseLayout.putConstraint(SpringLayout.WEST, searchTwitter, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, postTweet, 0, SpringLayout.NORTH, saveFile);
		baseLayout.putConstraint(SpringLayout.WEST, postTweet, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, postTweet, 0, SpringLayout.EAST, searchTwitter);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userWords = chatField.getText();
				String chatbotResponse = baseController.useChatbotCheckers(userWords);
				String currentText = chatDisplay.getText(); 
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said" + chatbotResponse +"\n" + currentText);
				chatDisplay.setCaretPosition(0);
				chatField.setText("");
				
				chatDisplay.setCaretPosition(0);
			}
		});
	
		saveFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent click)
			{
				String fileName = chatField.getText();
			
				FileController.saveFile(baseController, fileName.trim(), chatDisplay.getText());
			}
		});
	
		loadFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String fileName = chatField.getText() + ".txt";
				String saved = FileController.readFile(baseController, fileName);
				chatDisplay.setText(saved);
			}
		});
		
		searchTwitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String username = chatField.getText();
				chatDisplay.append(baseController.searchTwitter(username));
			}
		});
	}
}
