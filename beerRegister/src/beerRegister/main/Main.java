package beerRegister.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class Main {
	static boolean exit = false;
	static public List<Beer> beer_list = new ArrayList<Beer>();

	static public void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (!exit) {
			String command = br.readLine();
			String[] cmd = command.split(" ");
			
			if (cmd[0].equals("exit")) {
				exit(cmd);
			}
			else if (cmd[0].equals("add")) {
				add(cmd);
			}
			else if (cmd[0].equals("list")) {
				list(cmd);
			}
			else if (cmd[0].equals("save")) {
				save(cmd);
			}
			else if (cmd[0].equals("save")) {
				load(cmd);
			}
			else if (cmd[0].equals("delete")) {
				delete(cmd);
			}
			
		}
		br.close();
	}
	
	static public void exit(String[] cmd) {
		exit = true;
	}
	
	static public void add(String[] cmd) {
		beer_list.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
	}
	
	static public void list(String[] cmd) {
		if (cmd.length > 1) {
			if (cmd[1].equals("name")) {
				Collections.sort(beer_list, new NameComparator());
			}
			else if (cmd[1].equals("style")) {
				Collections.sort(beer_list, new StyleComparator());
			}
			else if (cmd[1].equals("strength")) {
				Collections.sort(beer_list, new StrengthComparator());
			}
		}

		for (int i=0; i<beer_list.size(); i++) {
			beer_list.get(i).print();
		}
	}
	
	static public void save(String[] cmd) throws IOException {
		FileOutputStream f = new FileOutputStream(cmd[1]);
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(beer_list);
		out.close();
	}
	
	static public void load(String[] cmd) throws IOException {
		FileInputStream f = new FileInputStream(cmd[1]);
		ObjectInputStream in = new ObjectInputStream(f);
		try {
			beer_list = (List<Beer>)in.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to read object.");
		}
		in.close();
	}
	
	static public void delete(String[] cmd) {
		Collections.sort(beer_list, new NameComparator());
		int i = Collections.binarySearch(beer_list, new Beer(cmd[1], null, null), new NameComparator());
		beer_list.remove(i);
	}
}
