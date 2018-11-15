package chatbot.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeChatbot extends Chatbot {
	List<String> jokes = new ArrayList<String>();
	Random rand = new Random();
	
	public JokeChatbot() {
		try(FileReader fr = new FileReader("data/jokes.txt");
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 1000000; i++) {
				String line = br.readLine();
				jokes.add(line);

				if (line == null) break;
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	// Output a random joke.
	public String act(String input) {
		int n = rand.nextInt(jokes.size() - 1);
		
		return jokes.get(n);
	}
}
