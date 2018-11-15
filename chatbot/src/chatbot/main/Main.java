package chatbot.main;

import chatbot.main.DialogFrame;

public class Main {
	static public void main(String[] args) {
		// Load and process the data.
		Data data = new Data();
		
		// Initialize chatbots.
		StringChatbot stringBot = new StringChatbot();
		EmbeddingChatbot embeddingBot = new EmbeddingChatbot();
		JokeChatbot jokeBot = new JokeChatbot();
		
		DialogFrame df = new DialogFrame(data, stringBot, embeddingBot, jokeBot);
		df.setVisible(true);
	}
}

