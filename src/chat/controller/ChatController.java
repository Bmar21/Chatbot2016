package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatController 
{
	private Chatbot  stupidBot;
	private ChatViewer chatView;
	
	public ChatController()
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
	
	public String useChatbotCheckers(String input)
	{
		String answer = "";
		
		if (!stupidBot.quitChecker(input))
		{
		
			if(stupidBot.contentChecker(input))
			{
				answer += "\nYou know my special secret\n";
			}
			
			if(stupidBot.memeChecker(input))
			{
				answer += "\nI can has meme?\n";
			}
		
			if(!stupidBot.lengthChecker(answer))
				
			{
				answer += "Sorry, I dont know anout " + input;
			}
		
			int canBeRandom = (int) (Math.random() * 7);
			if(canBeRandom % 7 == 0)
			{
				answer += randomTopicGenerator();
			}
		}
		else
		{
			chatView.displayMessage("Thank you chatting with me :D");
			System.exit(0);
		}
		
		return answer;
	}
	
	
	
	private String randomTopicGenerator()
	{
		String random = 
	}























}



