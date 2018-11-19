package chatbot.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chatbot.main.StringChatbot.Counter;

public class ChatbotTest {
	StringChatbot chatbot;
	
	@Before
	public void setUp() {
		chatbot = new StringChatbot(new Data(), new JokeChatbot());
	}

	@Test
	public void test_preprocess() {
		String out = chatbot.preprocess("Preprocess this please don't isn't I'D.,,,");
		
		assertEquals(out, "preprocess this please do n't is n't i 'd . ");
	}
	
	@Test
	public void test_postprocess() {
		String out = chatbot.postprocess("preprocess this please do n't is n't i 'd . ");
		
		assertEquals(out, "Preprocess this please don't isn't i'd. ");
	}
	
	@Test
	public void test_joke_words() {
		assertEquals(chatbot.checkJokeWords("hey, i want a pun"), true);
	}
	
	@Test
	public void test_output() {
		String out = chatbot.getOutput("come on you can at least try a little besides your cigarette . ");
		
		assertEquals(out, " what  s wrong with that  ?  cigarette is the thing i go crazy for  .  ");
	}
	
	@Test
	public void test_counter() {
		Counter<String> count = chatbot.new Counter<String>();
		
		count.add("word");
		count.add("word");
		assertEquals(count.count("word"), 2);
		assertEquals(count.count("other"), 0);
	}

}
