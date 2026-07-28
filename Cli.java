import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

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
		while (true) { // Infinite loop
			String command = scanner.nextLine(); // Get input from console as a string
			String output = ""; // A variable named output of type String
			if (command.equals("exit")) {
				break; // Forces exit of the while loop
			}else if (command.equals("date")) {
				output = LocalDate.now().toString();
			}else if (command.equals("time")) {
				output = LocalTime.now().toString();
			}else if (command.equals("datetime")){
				output = LocalDateTime.now().toString();
			}else if (command.equals("useraccount")){
				output = System.getProperties().getProperty("user.name");
			}else if (command.equals("userhome")){
				output = System.getProperties().getProperty("user.home");
			}else if (command.equals("os")){
				output = System.getProperties().getProperty("os.name") + " (" + System.getProperties().getProperty("os.version") + ")";
			}else if (command.startsWith("printenv")){
				String v = command.replace("printenv", "").trim();
				output = "";
				if(System.getenv(v) != null){
					output = System.getenv(v);
				}
			}else if (command.startsWith("echo") && (command.length() == "echo".length() || Character.isSpaceChar(command.charAt("echo".length())))){
				output = command.replace("echo", "").stripLeading();
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

