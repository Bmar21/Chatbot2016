package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatController;
import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	 private ChatController baseController;
	 private ChatPanel basePanel;
	 private ChatFrame baseFrame;
	 
	 public ChatFrame(ChatController baseController)
	 {
		 super();
		 this.baseController = baseController;
		 basePanel = new ChatPanel(baseController);
		 
		 setupFrame();
	 }
	 
	 public void setUp() throws Exception 
	 {
		 baseFrame = new ChatFrame(new ChatController());
	 }
	 
	 private void setupFrame()
	 {
		 this.setContentPane(basePanel);
		 this.setTitle("Chatbot 2016");
		 this.setSize(new Dimension(600,400));
		 this.setResizable(false);
		 this.setVisible(true);
		 
	 }
}
