package chatbot.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;


public class DialogFrame extends JFrame {
	// GUI elements.
	private JTextField text_field;
	private JButton send_button;
	private JComboBox<Object> combo_box;
	private JComboBox<Object> combo_dimension;
	private JPanel center_panel;
	private JTable table;
	private JScrollPane scrollpane;
	
	// References to the different components.
	private TableData tableData;
	private StringChatbot stringBot;
	private EmbeddingChatbot embeddingBot;
	
	// Dictionary for chatbot types.
	private HashMap<String, Chatbot> botType;
	
	public DialogFrame(Data data, StringChatbot strBot, EmbeddingChatbot embBot, JokeChatbot jokeBot) {
		// Store references.
		this.stringBot = strBot;
		this.embeddingBot = embBot;
		botType = new HashMap<String, Chatbot>();
		tableData = new TableData();
		
		// Initialize frame.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TalkToMe");
		setSize(new Dimension(1280, 720));
		setResizable(true);
		
		// Initialize components.
		JLabel type_here = new JLabel("Type your input here:");
		JLabel bot_type_label = new JLabel("Select a chatbot type:");
		JLabel dimension_label = new JLabel("Choose number of dimensions for sentence vector:");
		JLabel data_slider = new JLabel("How much data to use:");
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
		upper_panel.add(bot_type_label);
		upper_panel.add(combo_box);
		
		// Create left panel. (sliders)
		JPanel left_panel = new JPanel();
		// Create combobox for dimension selection.
		Object[] dimensions = new Object[4];
		dimensions[0] = 50;
		dimensions[1] = 100;
		dimensions[2] = 200;
		dimensions[3] = 300;
		combo_dimension = new JComboBox<Object>(dimensions);
		combo_dimension.addActionListener(new comboListener());
		left_panel.add(dimension_label);
		left_panel.add(combo_dimension);
		
		
		
		// Create center panel. (conversation)
		initCenterPanel();
		
		// Create right panel.
		JPanel right_panel = new JPanel();
		// Create data slider.
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, data.source_lines.size(), 1000);
		slider.addChangeListener(new SliderListener());
		slider.setMajorTickSpacing(20000);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		right_panel.add(data_slider);
		right_panel.add(slider);
		
		// Create lower panel. (text field and button)
		JPanel lower_panel = new JPanel();
		lower_panel.add(type_here);
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
		table.setFillsViewportHeight(true);
		table.setDefaultRenderer(String.class, new WordWrapCellRenderer());
		scrollpane = new JScrollPane(table);
		
		center_panel = new JPanel();
		center_panel.add(scrollpane);
	}
	
	public class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent arg0) {
			JSlider slid = (JSlider)arg0.getSource();
			botType.get(combo_box.getSelectedItem()).setDataCoverage((int)slid.getValue());
			
		}
	}
	
	public class comboListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			botType.get(combo_box.getSelectedItem()).setEmbDimension((int)combo_dimension.getSelectedItem());
		}
	}
	
	public class SendButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String input = text_field.getText();
			String output = botType.get(combo_box.getSelectedItem()).act(input);
			
			tableData.addLine(input, output);
		}	
	}
	
	public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
		WordWrapCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (value!=null) {
				setText(value.toString());
				if (!value.toString().equals("")) {
			        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
			        
			        if (table.getRowHeight(row) != getPreferredSize().height) {
			            table.setRowHeight(row, getPreferredSize().height);
			        }
				}
			}
	        return this;
		}
		
	}
}
