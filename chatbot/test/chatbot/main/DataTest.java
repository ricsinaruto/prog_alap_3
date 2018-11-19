package chatbot.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chatbot.main.Data.Vector;

public class DataTest {
	Data data;
	
	@Before
	public void setUp() {
		data = new Data();
	}

	@Test(expected=ArithmeticException.class)
	public void test_cosine_excpetion() {
		
		Vector test = data.new Vector(10);
		Vector test2 = data.new Vector(10);
		
		float cosine = test.cosine(test2);
	}
	
	@Test
	public void test_cosine() {
		Vector test = data.new Vector(new String[] {"1", "1"});
		Vector test2 = data.new Vector(new String[] {"-1", "-1"});
		
		float cosine = test.cosine(test2);
		assertEquals(cosine, -1, 0.001);
	}
	
	@Test
	public void test_add() {
		Vector test = data.new Vector(new String[] {"1", "1"});
		Vector test2 = data.new Vector(new String[] {"-1", "-1"});
		
		test = test.add(test2);
		
		assertEquals(test.values[0], 0, 0.001);
		assertEquals(test.values[1], 0, 0.001);
	}
	
	@Test
	public void test_divide() {
		Vector test = data.new Vector(new String[] {"2", "2"});
		Vector test2 = data.new Vector(new String[] {"1", "1"});
		
		test = test.divide(2);
		assertEquals(test.values[0], test2.values[0], 0.001);
		assertEquals(test.values[1], test2.values[1], 0.001);
	}
	
	@Test
	public void test_product() {
		Vector test = data.new Vector(new String[] {"1", "0"});
		Vector test2 = data.new Vector(new String[] {"0", "1"});
		
		assertEquals(test.dot_product(test, test2), 0, 0.001);
	}

}
