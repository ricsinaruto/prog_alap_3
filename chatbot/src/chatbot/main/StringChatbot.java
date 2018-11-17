package chatbot.main;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;

public class StringChatbot extends Chatbot {
	protected Data data;
	protected JokeChatbot jokeBot;
	protected List<String> jokeWords = new ArrayList<String>();
	
	private List<Counter<String>> source_counts = new ArrayList<Counter<String>>();
	
	protected int dataCoverage;
	
	public StringChatbot() {}
	
	public StringChatbot(Data data, JokeChatbot jokeBot) {
		this.data = data;
		this.jokeBot = jokeBot;
		
		dataCoverage = data.source_lines.size();
		
		// Put some joke words in the list.
		jokeWords.add("joke");
		jokeWords.add("pun");
		jokeWords.add("gag");
		
		
		// Build word counts.
		buildWordCounts();
	}
	
	public void setDataCoverage(int dataCoverage) {
		this.dataCoverage = dataCoverage;
	}
	
	public void buildWordCounts() {
		for (String[] words: data.source_lines) {
			Counter<String> counter = new Counter<String>();
			for (int i = 0; i < words.length; i++) {
				counter.add(words[i]);
			}
			source_counts.add(counter);
		}
	}
	
	public String getOutput(String input) {
		// Let's see if it contains joke words.
		if (checkJokeWords(input)) return jokeBot.act(input);
		
		// Else do the chatbot specific output
		// Get wourd counts for input.
		Counter<String> inp_counter = new Counter<String>();
		for (String word: input.split(" ")) {
			inp_counter.add(word);
		}
		
		int smallest_sum = 100000;
		int index = 0;
		// Loop through data set, and find closest source utterance.
		for (int i = 0; i < dataCoverage; i++) {
			int current_sum = 0;
			// Check the input side.
			for (String word: inp_counter.counts.keySet()) {
				current_sum += Math.abs(inp_counter.count(word) - source_counts.get(i).count(word));
			}
			
			// Check the input side.
			for (String word: source_counts.get(i).counts.keySet()) {
				current_sum += Math.abs(inp_counter.count(word) - source_counts.get(i).count(word));
			}
			
			if (current_sum < smallest_sum) {
				smallest_sum = current_sum;
				index = i;
			}
			if (current_sum == 0) return data.target_lines.get(i);
		}
		
		return data.target_lines.get(index);
	}
	
	public boolean checkJokeWords(String input) {
		for (String word: input.split(" ")) {
			for (String jokeWord: jokeWords) {
				if (word.equals(jokeWord)) return true;
			}
		}
		
		return false;
	}
	
	public class Counter<T> {
	    final Map<T, Integer> counts = new HashMap<>();

	    public void add(T t) {
	        counts.merge(t, 1, Integer::sum);
	    }

	    public int count(T t) {
	        return counts.getOrDefault(t, 0);
	    }
	}
}
