package chatbot.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class TableData extends AbstractTableModel{
    List<String> botUtterances = new ArrayList<String>();
    List<String> userUtterances = new ArrayList<String>();
    List<String> column_names = new ArrayList<String>();
    
    public TableData() {
    	column_names.add("Chatbot Utterances");
    	column_names.add("User Utterances");

    	// Load data.
    	loadData();
    }

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return botUtterances.size();
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		return String.class;
	}
	
	@Override
	public String getColumnName(int column) {
		return column_names.get(column);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return botUtterances.get(rowIndex);
		}
		else return userUtterances.get(rowIndex);
	}
	
	public void addLine(String input, String output) {
		botUtterances.add("");
		botUtterances.add(output);
		userUtterances.add(input);
		userUtterances.add("");
		
		this.fireTableRowsInserted(botUtterances.size() - 2, userUtterances.size() - 1);
		
		// Save new data to file.
		saveToFile(input, output);
	}
	
	public void saveToFile(String input, String output) {
		try(FileWriter fw = new FileWriter("data/input.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(input);
			out.println("");
		} catch (IOException e) {
			System.out.println("File not found.");
		}
		
		try(FileWriter fw = new FileWriter("data/output.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("");
			out.println(output);
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	public void loadData() {
		try(FileReader fr = new FileReader("data/input.txt");
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 1000000; i++) {
				String line = br.readLine();
				userUtterances.add(line);

				if (line == null) break;
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
			
		try(FileReader fr = new FileReader("data/output.txt");
				BufferedReader br = new BufferedReader(fr)) {
			for (int i=0; i < 1000000; i++) {
				String line = br.readLine();
				botUtterances.add(line);

				if (line == null) break;
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
}