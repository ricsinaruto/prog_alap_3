package chatbot.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class DialogFrame extends JFrame {
	// GUI elements.
	private JTextField text_field;
	private JButton send_button;
	private JComboBox<Object> combo_box;
	private JPanel center_panel;
	private JTable table;
	private JScrollPane scrollpane;
	
	// References to the different components.
	private Data data;
	private TableData tableData;
	private StringChatbot stringBot;
	private EmbeddingChatbot embeddingBot;
	private JokeChatbot jokeBot;
	
	// Dictionary for chatbot types.
	private HashMap<String, Chatbot> botType;
	
	public DialogFrame(Data data, StringChatbot strBot, EmbeddingChatbot embBot, JokeChatbot jokeBot) {
		// Store references.
		this.data = data;
		this.stringBot = strBot;
		this.embeddingBot = embBot;
		this.jokeBot = jokeBot;
		botType = new HashMap<String, Chatbot>();
		tableData = new TableData();
		
		// Initialize frame.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TalkToMe");
		setSize(new Dimension(1000, 800));
		setResizable(true);
		
		// Initialize components.
		text_field = new JTextField(50);
		send_button = new JButton();
		send_button.setText("Send!");
		send_button.addActionListener(new SendButtonActionListener());

		// Create chatbot array for combo box.
		Object[] chatbots = new Object[3];
		chatbots[0] = "String Retrieval-based Chatbot";
		chatbots[1] = "Embedding Retrieval-based Chatbot";
		chatbots[2] = "Joking Chatbot (caution, really stupid!)";
		combo_box = new JComboBox<Object>(chatbots);
		botType.put((String)chatbots[0], stringBot);
		botType.put((String)chatbots[1], embeddingBot);
		botType.put((String)chatbots[2], jokeBot);
		
		// Create upper panel.
		JPanel upper_panel = new JPanel();
		upper_panel.add(combo_box);
		
		// Create left panel. (sliders)
		JPanel left_panel = new JPanel();
		
		// Create center panel. (conversation)
		initCenterPanel();
		
		// Create right panel. (maybe image)
		JPanel right_panel = new JPanel();
		
		// Create lower panel. (text field and button)
		JPanel lower_panel = new JPanel();
		lower_panel.add(text_field);
		lower_panel.add(send_button);
		
		// Set layout.
		this.setLayout(new BorderLayout());
		this.add(upper_panel, BorderLayout.NORTH);
		this.add(left_panel, BorderLayout.WEST);
		this.add(center_panel, BorderLayout.CENTER);
		this.add(right_panel, BorderLayout.EAST);
		this.add(lower_panel, BorderLayout.SOUTH);
	}
	
	private void initCenterPanel() {
		// Init table.
		table = new JTable(tableData);
		table.setShowGrid(false);
		scrollpane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		center_panel = new JPanel();
		center_panel.add(scrollpane);
	}
	
	public class SendButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String input = text_field.getText();
			String output = botType.get(combo_box.getSelectedItem()).act(input);
			
			tableData.addLine(input, output);
		}	
	}
}
