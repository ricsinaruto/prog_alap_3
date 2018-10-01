package cmd.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import java.util.LinkedList;

public class Main {
	static boolean exit = false;
	static File wd = new File(System.getProperty("user.dir"));

	static public void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while (!exit) {
			pwd(null);
			String command = br.readLine();
			String[] cmd = command.split(" ");
			
			if (cmd[0].equals("exit")) {
				exit(cmd);
			}
			else if (cmd[0].equals("pwd")) {
				pwd(cmd);
			}
			else if (cmd[0].equals("cd")) {
				cd(cmd);
			}
			else if (cmd[0].equals("ls")) {
				ls(cmd);
			}
			else if (cmd[0].equals("mkdir")) {
				mkdir(cmd);
			}
			else if (cmd[0].equals("cp")) {
				cp(cmd);
			}
			else if (cmd[0].equals("head")) {
				head(cmd);
			}
			else if (cmd[0].equals("mv")) {
				mv(cmd);
			}
			else if (cmd[0].equals("cat")) {
				cat(cmd);
			}
			else if (cmd[0].equals("wc")) {
				wc(cmd);
			}
			else if (cmd[0].equals("length")) {
				length(cmd);
			}
			else if (cmd[0].equals("tail")) {
				tail(cmd);
			}
			else if (cmd[0].equals("grep")) {
				grep(cmd);
			}
		}
		
		br.close();
	}
	
	static public void exit(String[] cmd) {
		exit = true;
	}
	
	static public void pwd(String[] cmd) throws IOException {
		System.out.println(wd.getCanonicalPath());
	}
	
	static public void cd(String[] cmd) throws IOException {
		if (cmd[1].equals("..")) {
			wd = wd.getParentFile();
		}
		else {
			File temp_wd = new File(wd, cmd[1]);
			if (temp_wd.isDirectory()) {
				wd = temp_wd;
			}
			else {
				throw new IOException("No such directory.");
			}
		}
	}
	
	static public void ls(String[] cmd) {
		File[] list_files = wd.listFiles();
		
		if (cmd.length>1) {
			if (cmd[1].equals("-l")) {
				for (File file : list_files) {
					String is_dir = new String("f");
					if (file.isDirectory()) {
						is_dir = "d";
					}
					long len = file.length();
					System.out.println(file.getName() + " " + len + " " + is_dir);
				}
			}
		}
		else {
			for (File file : list_files) {
				System.out.println(file.getName());
			}
		}
	}
	
	static public void mkdir(String[] cmd) throws IOException{
		File new_dir = new File(wd, cmd[1]);
		if (new_dir.isDirectory()) {
			throw new IOException("Directory already exists.");
		}
		else {
			new_dir.mkdir();
		}
	}
	
	static public void cp(String[] cmd) throws IOException{
		File file = new File(wd, cmd[1]);
		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			FileInputStream input_stream = new FileInputStream(file);
			FileOutputStream output_stream = new FileOutputStream(new File(wd, cmd[2]));
			
			int data;
			while ((data = input_stream.read()) != -1) {
				output_stream.write(data);
			}
			
			input_stream.close();
			output_stream.close();
		}
	}
	
	static public void head(String cmd[]) throws IOException{
		int n = 10;
		File file = new File(wd, cmd[1]);
		if (cmd[1].equals("-n")) {
			n = Integer.parseInt(cmd[2]);
			file = new File(wd, cmd[3]);
		}
		
		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			FileReader file_reader = new FileReader(file);
			BufferedReader reader = new BufferedReader(file_reader);
			
			for (int i=0; i<n; i++) {
				String line = reader.readLine();
				if (line == null) break;
				System.out.println(line);
			}
			
			reader.close();
			file_reader.close();
		}
	}
	
	static public void mv(String cmd[]) throws IOException {
		File start_file = new File(wd, cmd[1]);
		File dest = new File(wd, cmd[2]);
		if (!start_file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			start_file.renameTo(dest);
		}
	}
	
	static public void cat(String cmd[]) throws IOException {
		File file = new File(wd, cmd[1]);
		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			FileReader file_reader = new FileReader(file);
			BufferedReader reader = new BufferedReader(file_reader);
			
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				System.out.println(line);
			}
			
			reader.close();
			file_reader.close();
		}
	}
	
	static public void wc(String cmd[]) throws IOException {
		File file = new File(wd, cmd[1]);
		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			FileReader file_reader = new FileReader(file);
			BufferedReader reader = new BufferedReader(file_reader);
			
			int rows = 0;
			int words = 0;
			int chars = 0;
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				
				rows += 1;
				words += line.split(" ").length;
				chars += line.length();
			}
			
			System.out.println("Rows: " + rows + ", Words: " + words + ", Chars: " + chars);
			
			reader.close();
			file_reader.close();
		}
	}
	
	static public void length(String cmd[]) throws IOException {
		File file = new File(wd, cmd[1]);
		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			System.out.println(file.length());
		}
	}
	
	static public void tail(String cmd[]) throws IOException{
		int n = 10;
		File file = new File(wd, cmd[1]);
		if (cmd[1].equals("-n")) {
			n = Integer.parseInt(cmd[2]);
			file = new File(wd, cmd[3]);
		}
		
		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			FileReader file_reader = new FileReader(file);
			BufferedReader reader = new BufferedReader(file_reader);
			LinkedList<String> line_list = new LinkedList<String>();
			
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				line_list.add(line);
			}
			reader.close();
			file_reader.close();
			
			for (int i=0; i<n; i++) {
				String line = line_list.pollLast();
				if (line == null) break;
				System.out.println(line);
			}
		}
	}
	
	static public void grep(String cmd[]) throws IOException{
		File file = new File(wd, cmd[2]);

		if (!file.isFile()) {
			throw new IOException("File does not exist.");
		}
		else {
			FileReader file_reader = new FileReader(file);
			BufferedReader reader = new BufferedReader(file_reader);

			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				if (line.matches(cmd[1])) {
					System.out.println(line);
				}
			}
			reader.close();
			file_reader.close();
		}
	}
}
