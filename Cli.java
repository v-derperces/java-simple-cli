import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Exception;
import java.util.List;
import java.util.Random;

public class Cli {

    // The main method is the entry point of the program. Rules regarding the main method:
    //     - public: so the JVM can access it from "outside"
    //     - static: so it can be called without creating an object (class scoped)
    //     - void: it doesn't return a value (aka procedure)
    //     - main: the required method name
    //     - String[] args: so it can receive command-line arguments
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // Listen to the standard input (console)
		System.out.print("> "); // Prompt
		HashMap<String,String> aliases = new HashMap<String,String>();
		aliases.put("logout", "exit");
		aliases.put("print", "echo");

		while (true) { // Infinite loop
			String command = scanner.nextLine(); // Get input from console as a string
			String[] decomposition = command.split(" ", 2);
			String arguments = "";
			if(decomposition.length >= 2){
				command = decomposition[0];
				arguments = decomposition[1].stripLeading();
			}
			String output = ""; // A variable named output of type String
			if(aliases.containsKey(command)){
				command = aliases.get(command);
			}
			if (command.equals("exit")) {
				break; // Forces exit of the while loop
			}else if (command.equals("date")) {
				output = LocalDate.now().toString();
			}else if (command.equals("time")) {
				output = LocalTime.now().toString();
			}else if (command.equals("datetime")){
				output = LocalDateTime.now().toString();
			}else if (command.equals("useraccount")){
				output = System.getProperty("user.name");
			}else if (command.equals("userhome")){
				output = System.getProperty("user.home");
			}else if (command.equals("os")){
				output = System.getProperty("os.name") + " (" + System.getProperty("os.version") + ")";
			}else if (command.equals("printenv")){
				if(arguments.isEmpty()){
					Map<String,String> variables = System.getenv();
					for (Map.Entry<String,String> variable : variables.entrySet()){
						output += variable.getKey() + "=" + variable.getValue() + "\n";	
					}
				}else {
					String value = System.getenv(arguments);
					if (value != null){
						output = value;
					}
				}
			}else if (command.equals("echo")){
				output = arguments;
			}else if (command.equals("ls")){
				File file = new File(arguments);
				if (file.isDirectory()) {
					String[] files = file.list();
					for(String f : files){
					output += f + "\n";
					}
				}

			}else if (command.equals("chuck")) {
				FileReader fileReader = null;
				try{
					File quotes = new File("./chuck.txt");
					fileReader = new FileReader(quotes);
					List<String> lines = fileReader.readAllLines();
					Random random = new Random();
					int randomNumber = random.nextInt(lines.size());
					output = lines.get(randomNumber);
				}catch(IOException ioe){
					IO.println("Cannot open file: "  + ioe.getMessage());
				}catch(Exception e){
					IO.println("Error while opening file: " + e.getMessage());
				}finally{
					if(fileReader != null) {
						try{
							fileReader.close();
						}catch(Exception e){
							IO.println("Cannot close file: " + e.getMessage());
						}
					}
				}

			}else {
				// String concatenation
				output = "Command '" + command + "' not found.";
			}
			System.out.println(output); // Print with new line (ln)
			System.out.print("> "); // Prompt
		}
		scanner.close(); // Best practice, always close a stream when no more needed
		System.out.println("Bye!");
    }


}

