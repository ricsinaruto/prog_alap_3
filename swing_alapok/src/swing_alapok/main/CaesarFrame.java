package swing_alapok.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaesarFrame extends JFrame {
	private JTextField upper_text_field;
	private JTextField lower_text_field;
	private int focus_text_field;
	private JButton button;
	private JComboBox<Object> combo_box;
	
	public CaesarFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SwingLab");
		setSize(new Dimension(400, 110));
		setResizable(true);
		
		// Initialize components.
		upper_text_field = new JTextField(20);
		upper_text_field.getDocument().addDocumentListener(new InputFieldKeyListener());
		upper_text_field.addFocusListener(new FocusDemo());
		lower_text_field = new JTextField(20);
		lower_text_field.addFocusListener(new FocusDemo());
		//lower_text_field.setEditable(false);
		button = new JButton();
		button.setText("Code!");
		button.addActionListener(new OkButtonActionListener());
		
		// Create alphabet object array.
		Object[] alphabet = new Object[26];
		for (int i = 0; i < 26; i++) alphabet[i] = (char)('A' + i);
		combo_box = new JComboBox<Object>(alphabet);
		
		// Create upper panel.
		JPanel upper_panel = new JPanel();
		upper_panel.add(combo_box);
		upper_panel.add(upper_text_field);
		upper_panel.add(button);
		
		// Create lower panel.
		JPanel lower_panel = new JPanel();
		lower_panel.add(new JLabel("Output:"));
		lower_panel.add(lower_text_field);
		
		// Set layout.
		GridLayout grid_lay = new GridLayout(2, 0);
		this.setLayout(grid_lay);
		this.add(upper_panel);
		this.add(lower_panel);
	}
	
	public class OkButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (focus_text_field == 0) {
				String out = caesarCode(upper_text_field.getText(), (char)combo_box.getSelectedItem());
				lower_text_field.setText(out);
			}
			else {
				String out = caesarDeCode(lower_text_field.getText(), (char)combo_box.getSelectedItem());
				upper_text_field.setText(out);
			}
			
		}
		
	}
	
	public class FocusDemo implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			//System.out.println(e.getComponent());
			if (((JTextField)e.getComponent()).equals(upper_text_field)) {
				focus_text_field = 0;
			}
			else {
				focus_text_field = 1;
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			
		}
		
	}
	
	public class InputFieldKeyListener implements DocumentListener {
		@Override
		public void changedUpdate(DocumentEvent arg0) {
			String out = caesarCode(upper_text_field.getText(), (char)combo_box.getSelectedItem());
			lower_text_field.setText(out);
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String out = caesarCode(upper_text_field.getText(), (char)combo_box.getSelectedItem());
			lower_text_field.setText(out);
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			String out = caesarCode(upper_text_field.getText(), (char)combo_box.getSelectedItem());
			lower_text_field.setText(out);
			
		}
		
	}
	
	static private String caesarCode(String input, char offset) {
		char _offset = String.valueOf(offset).toUpperCase().charAt(0);
		char[] out = input.toUpperCase().toCharArray();
		for (int i = 0; i < out.length; i++) {
			out[i] += _offset - 'A';
			if (out[i] > 'Z') {
				out[i] -= 'Z' - 'A' + 1;
			}
		}
		return new String(out);
	}
	
	static private String caesarDeCode(String input, char offset) {
		char _offset = String.valueOf(offset).toUpperCase().charAt(0);
		char[] out = input.toUpperCase().toCharArray();
		for (int i = 0; i < out.length; i++) {
			if (out[i] - (_offset - 'A') < 'A' ) {
				out[i] += 'Z' - 'A' + 1;
			}
			out[i] -= _offset - 'A';
		}
		return new String(out);
	}
}
