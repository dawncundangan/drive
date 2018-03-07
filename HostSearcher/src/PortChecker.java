import java.net.Socket;
import java.util.Scanner;
public class PortChecker { //by Ian Culanag
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter server: ");
		String server = input.nextLine();
		input.close();
		int[] port = {20,22,23,25,53,67,69,80};
		
		for (int count = 0; count <= 7; count++) {
			try {
				Socket socket = new Socket(server, port[count]);
				System.out.println(server + ": " + port[count] + " is open!");
				socket.close();
			} catch (java.io.IOException e) {
				System.err.println(server + ": " + port[count] + " is closed.");
			}
		}
		System.out.println("Process completed.");
	}
}