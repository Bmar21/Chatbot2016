package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame; 
import chat.model.CtecTwitter;

public class ChatController 
{
	private Chatbot  stupidBot;
	private ChatViewer chatView;
	private ChatFrame baseFrame;
	private CtecTwitter tweetBot;
	
	public ChatController()
	{
		stupidBot = new Chatbot("Barack Obama");
		chatView= new ChatViewer();
		baseFrame= new ChatFrame(this);
	}
	
	public void start()
	{
//		String response = chatView.collectResponse("What do you want to talk about today?????");
//		
//		while(stupidBot.lengthChecker(response))
//		{
//			chatView.displayMessage(useChatbotCheckers(response));
//			response = chatView.collectResponse("You are interested in " + response);
//		}	
//		
//		if("".equals(""))
//		{
			
//		}	
	}
	
	public void handleErrors(Exception currentException)
	{
		chatView.displayMessage("An error has occurred. Details provided next.");
		chatView.displayMessage(currentException.getMessage());
	}
	
	public ChatViewer getPopup()
	{
		return chatView;
	}
	
	public ChatFrame getBaseFrame()
	{
		return null;
	}
	
	public Chatbot getChatbot()
	{
		return null;
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
			chatView.displayMessage("Thank you for chatting with me :D");
			System.exit(0);
		}
		
		return answer;
	}
	
	public String searchTwitter(String name)
	{
		String results = "THe top word from user: " + name + " is: ";
		results += tweetBot.getMostCommonWord(name);
		
		return results;
	}

    private String randomTopicGenerator()
	{
		String randomTopic ="";
		int random = (int) (Math.random() * 7);
		
		switch (random)
		{
		case 0:
			randomTopic = "Did you hear about the daft punk beastie boys mix?";
			break;
		case 1: 
			randomTopic = "Can you bring me some Sriracha?";
			break;
		case 2:
			randomTopic = "Time for some industrial!";
			break;
		case 3:
			randomTopic = "Reading novels is fantastic";
			break;
		case 4:
			randomTopic = "Computatinal and algorithmic thinkning for the win";
			break;
		case 5:
			randomTopic = "I love Java";
			break;
		case 6:
			randomTopic = "Time to run";
			break;
		default:
			randomTopic = "This can't be happening!";
			break;
		}
		
		return randomTopic;
	}

}



