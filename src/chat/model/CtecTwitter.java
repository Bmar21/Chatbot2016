package chat.model;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import chat.controller.ChatController;
import twitter4j.*;

public class CtecTwitter 
{
	private ChatController baseController;
	private Twitter twitterBot;
	private List<String> tweetedWords;
	private List<Status> allTheTweets;
	
	public CtecTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		this.tweetedWords = new ArrayList<String>();
		this.allTheTweets = new ArrayList<Status>();
		this.twitterBot = TwitterFactory.getSingleton();
	}
	
	public void sendTweet(String textToTweet)
	{
		try 
		{
			twitterBot.updateStatus("I Brandon Marlor just tweeted from my Java Chatbot program 2017! #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen! @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}
	
	private String [] createIgnoredWordsArray()
	{
		String [] boringWords;
		int wordCount = 0;
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		while(boringWordScanner.hasNextLine())
		{
			boringWordScanner.nextLine();
			wordCount++;
		}
		boringWordScanner.close();
		
		boringWords = new String [wordCount];
		
		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commanWords.txt"));
		
		for(int index = 0; index < boringWords.length; index++);
		{
			boringWords[index] = boringWordScanner.next();
		}
		boringWordScanner.close();
		
		return  boringWords;
	}
	
	public String getMostCommonWord(String username)
	{
		gatherTheTweets(username);
		turnTweetsToWords();
		removeBlankWords();
		removeBoringWords();
		
		String information = "The tweetcount is " + allTheTweets.size() + " and " + tweetedWords.size() + " and " + username + "'s " + calculateTopWord();
		
		return "";
	}

	private void removeEmptyText()
	{
		for(int index = tweetedWords.size() - 1; index >=0; index
	}
	
	
	
	private void removeBoringWords()
	{
		String [] boringWords = createIgnoredWordsArray();
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boingIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void turnTweetsToWords()
	{
		for(Status currentTweet : allTheTweets)
		{
			String tweetText = currentTweet.getText();
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(tweetWords[index]);
			}
		}
	}
	
	private String removePunctuation(String currentString)
	{
		
		String punctuation = ".,'?!:;\"(){}^[]<>-"; 
		
		String scrubbedString = "";
		for (int i = 0; i < currentString.length(); i++)
		{
			if (punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;
	}
	
	
	private void removeBlankWords()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			if(tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	private void gatherTheTweets(String user)
	{
		tweetedWords.clear();
		allTheTweets.clear();
		int pageCount = 1;
		
//		Paging statusPage = new Paging(1,100);
//		
//		while(pageCount <= 10)
//		{
//			try
//			{
//				allTheTweets.addAll(twitterBot.getUserTimeline(user, statusPage));
//			}
//			catch (TwitterException twitterError)
//			{
//				baseController.handleErrors(twitterError);
//			}
//			pageCount++;
//		}
	}

	private String calculateTopWord()
	{
		String results = "";
		String topWord = "";
		int PopularIndex = 0;
		int popularCount = 0;
		
		for (int index = 0; index < tweetedWords.size(); index++)
		{
			int currentPopularity = 0;
			for (int searched = index + 1; searched < tweetedWords.size(); searched++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)))
				{
					currentPopularity++;
				}
			}
			if(currentPopularity > popularCount)
			{
				popularCount = currentPopularity;
				PopularIndex = index;
				topWord = tweetedWords.get(index);
			}
		}			
		results += "the most popular is: " topWord + ", and it occured " + popularCount + 
				" times out of " + tweetedWords.size() + ", AKA " + (DecimalFormat.getPercentInstance().format((double)poularCount/tweetedWords.size()));
	
	}
	
	












}
