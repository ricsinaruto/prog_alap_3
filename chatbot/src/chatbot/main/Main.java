package chatbot.main;

import chatbot.main.DialogFrame;

public class Main {
	static public void main(String[] args) {
		// Load and process the data.
		Data data = new Data();
		
		// Initialize chatbots.
		JokeChatbot jokeBot = new JokeChatbot();
		StringChatbot stringBot = new StringChatbot(data, jokeBot);
		EmbeddingChatbot embeddingBot = new EmbeddingChatbot(data, jokeBot);
		
		DialogFrame df = new DialogFrame(data, stringBot, embeddingBot, jokeBot);
		df.setVisible(true);
	}
}

