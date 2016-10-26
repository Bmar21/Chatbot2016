package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatbotController 
{
	private Chatbot  stupidBot;
	private ChatViewer chatView;
	
	public ChatbotController()
	{
		stupidBot = new Chatbot("Barack Obama");
		chatView = new ChatViewer();
	}
	
	public void start()
	{
		String response = chatView.collectResponse("What do you want to talk about today?????");
		
		while(stupidBot.lengthChecker(response))
		{
			chatView.displayMessage(useChatbotCheckers(response));
			response = chatView.collectResponse("You are interested in " + response);
		}	
	}
	
	private String useChatbotCheckers(String input)
	{
		String answer = "";
		
		if(stupidBot.contentChecker(input))
		{
			answer += "\nYou know my special secret\n";
		}
		if(stupidBot.memeChecker(input))
		{
			answer += "\nI can has meme?\n";
		}
		
		if(input.length() == 0)
		{
			answer += "Sorry, I dont know anout " + input;
		}
		
		return answer;
	}
}
