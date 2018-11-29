package chatbot.main;

public abstract class Chatbot {
	protected int embDimension = 300;
	protected int dataCoverage;
	
	public Chatbot() {
		
	}
	
	public void setEmbDimension(int embDimension) {
		this.embDimension = embDimension;
	}
	
	public void setDataCoverage(int dataCoverage) {
		this.dataCoverage = dataCoverage;
	}
	
	public String act(String input) {
		String output = getOutput(preprocess(input));
		
		return postprocess(output);
	}
	
	public String getOutput(String input) {
		return "Hi, please select a chatbot type.";
	}
	
	public String preprocess(String input) {
		input = input.toLowerCase();
		//input = input.replaceAll("[^a-z .?!'0-9]", "");
		input = input.replaceAll("[.]", " . ");
		input = input.replaceAll("[?]", " ? ");
		input = input.replaceAll("[!]", " ! ");

		input = input.replaceAll("[ ]'[ ]", " ");
		String pattern = "( ')([a-z])";
		input = input.replaceAll(pattern, "$2".replaceAll("'", ""));
		input = input.replaceAll("n't", " n't");
		pattern = "([^ n])(')([^ t])";
		input = input.replaceAll(pattern, "$1" + " '" + "$3");
		
		return input;
	}
	
	public String postprocess(String output) {
		String[] words = output.split(" ");
		output = "";
		for (int i = 0; i < words.length; i++) {
			if (!words[i].equals("")) output += words[i] + " ";
		}
		
		output = output.replaceAll(" '", "'");
		output = output.replaceAll(" n't", "n't");
		output = output.replaceAll(" [!] ", "! ");
		output = output.replaceAll(" [?] ", "? ");
		output = output.replaceAll(" [.] ", ". ");
		
		output = output.substring(0, 1).toUpperCase() + output.substring(1);
		return output;
	}
}
