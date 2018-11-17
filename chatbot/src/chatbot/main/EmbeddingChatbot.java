package chatbot.main;

import java.util.ArrayList;
import java.util.List;

import chatbot.main.Data.Vector;


public class EmbeddingChatbot extends StringChatbot {
	private int embDimension = 300;
	public EmbeddingChatbot(Data data, JokeChatbot jokeBot) {
		this.data = data;
		this.jokeBot = jokeBot;
		
		dataCoverage = data.source_lines.size();
		
		// Put some joke words in the list.
		jokeWords.add("joke");
		jokeWords.add("pun");
		jokeWords.add("gag");
		

	}
	
	public void setEmbDimension(int embDimension) {
		this.embDimension = embDimension;
	}
	
	public String getOutput(String input) {
		// Let's see if it contains joke words.
		if (checkJokeWords(input)) return jokeBot.act(input);
		
		// Else do the chatbot specific output
		// Build representation for input.
		int counter = 0;
		Vector sum_vector = data.new Vector(embDimension);
		Vector null_vector = data.new Vector(embDimension);
		for (String word: input.split(" ")) {
			Vector new_vec = data.vocab.get(embDimension).getOrDefault(word, null_vector);
			if (!new_vec.equals(null_vector)) {
				sum_vector.add(new_vec);
				counter += 1;
			}
		}
		// average word embedding.
		sum_vector = sum_vector.divide(counter);
		
		
		float largest_cos = -1;
		int index = 0;
		// Loop through data set, and find closest source utterance.
		for (int i = 0; i < dataCoverage; i++) {
			float current_cos = sum_vector.cosine(data.source_embeddings.get(embDimension).get(i));
			
			if (current_cos > largest_cos) {
				largest_cos = current_cos;
				index = i;
			}
			if (current_cos > 0.99) return data.target_lines.get(i);
		}
		
		return data.target_lines.get(index);
	}

}
