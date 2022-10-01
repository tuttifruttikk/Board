import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class UserIO {
	private File file = null;

	public UserIO(String path)  {
		file = new File(path);
	}

	public synchronized HashMap<String, String> loadFromFile() {
		HashMap<String, String> users = new HashMap<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String login = reader.readLine();
			String password = reader.readLine();
			while (login != null) {
				users.put(login, password);
				login = reader.readLine();
				password = reader.readLine();
			}
			reader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return users;
	}

	public synchronized void saveToFile(HashMap<String, String> users)  {
		try {
			resetFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (HashMap.Entry<String, String> entry : users.entrySet()) {
				writer.write(entry.getKey() + "\n");
				writer.write(entry.getValue() + "\n");
			}
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public synchronized  void resetFile() throws FileNotFoundException {
	      PrintWriter writer = new PrintWriter(file);
	      writer.print("");
	      writer.close();
	}	
}
