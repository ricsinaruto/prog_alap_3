package chatbot.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmbeddingTest {
	EmbeddingChatbot chatbot;
	
	@Before
	public void setUp() {
		chatbot = new EmbeddingChatbot(new Data(), new JokeChatbot());
	}
	
	@Test
	public void testOutput() {
		String out = chatbot.getOutput("come on you can at least try a little besides your cigarette . ");
		
		assertEquals(out, " what  s wrong with that  ?  cigarette is the thing i go crazy for  .  ");
	}

}
