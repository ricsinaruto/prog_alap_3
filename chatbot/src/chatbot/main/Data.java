package chatbot.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Data {
	public List<String[]> source_lines = new ArrayList<String[]>();
	public List<String> target_lines = new ArrayList<String>();
	public HashMap<Integer, HashMap<String, Vector>> vocab = new HashMap<Integer, HashMap<String, Vector>>();
	public HashMap<Integer, List<Vector>> source_embeddings = new HashMap<Integer, List<Vector>>();
	
	
	public Data() {
		// Initialize hashmaps with the different vector sizes.
		loadVocab(50);
		loadVocab(100);
		loadVocab(200);
		loadVocab(300);
		
		// Load sentence representations.
		loadSentenceEmb(50);
		loadSentenceEmb(100);
		loadSentenceEmb(200);
		loadSentenceEmb(300);
		
		// Load the text files.
		loadDataFiles();
	}
	
	private void loadDataFiles() {
		try(FileReader fr = new FileReader("data/fullSource.txt");
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 1000000; i++) {
				String line = br.readLine();
				if (line == null) break;
				
				// Store the vector.
				String[] words = line.split(" ");
				source_lines.add(words);
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
		
		try(FileReader fr = new FileReader("data/fullTarget.txt");
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 1000000; i++) {
				String line = br.readLine();
				if (line == null) break;
				
				// Store the vector.
				target_lines.add(line);
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	private void loadVocab(int size) {
		vocab.put(size, new HashMap<String, Vector>());
		
		String path = "data/glove_" + Integer.toString(size) + "d.txt";
		try(FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 100000; i++) {
				String line = br.readLine();
				if (line == null) break;
				
				// Store the vector.
				String[] words = line.split(" ");
				vocab.get(size).put(words[0], new Vector(Arrays.copyOfRange(words, 1, words.length)));
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	private void loadSentenceEmb(int size) {
		source_embeddings.put(size, new ArrayList<Vector>());
		
		String path = "data/fullSourceEmb_" + Integer.toString(size) + "d.txt";
		
		try(FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 1000000; i++) {
				String line = br.readLine();
				if (line == null) break;
				
				// Store the vector.
				String[] values = line.split(" ");
				source_embeddings.get(size).add(new Vector(values));
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	public class Vector {
		float[] values;
		public Vector(String[] values) {
			this.values = new float[values.length];
			
			for (int i = 0; i < values.length; i++) {
				this.values[i] = Float.parseFloat(values[i]);
			}
		}
		
		public Vector(int length) {
			this.values = new float[length];
			
			for (int i = 0; i < length; i++) {
				this.values[i] = 0;
			}
		}
		
		public Vector divide(int val) {
			for (int i = 0; i < values.length; i++) {
				values[i] = values[i] / val;
			}
			return this;
		}
		
		public Vector add(Vector other) {
			for (int i = 0; i < other.values.length; i++) {
				this.values[i] += other.values[i];
			}
			return this;
		}
		
		public float cosine(Vector other) {
			return (float)(dot_product(this, other)/Math.sqrt(dot_product(this, this))/Math.sqrt(dot_product(other, other)));
		}
		
		public float dot_product(Vector first, Vector second) {
			int output = 0;
			for (int i = 0; i < first.values.length; i++) {
				output += first.values[i] * second.values[i];
			}
			return output;
		}
	}
}
