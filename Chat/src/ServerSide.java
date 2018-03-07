import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program represents the Server side for the chat program. The program must be run 
 * first before the client so port 2728 is enabled. The Server then waits for a client 
 * to connect and begins the chat. If the client says "bye", the chat will end.
 * 
 * @author Culanag, Ian
 * @author Cundangan, Dawn
 *
 */
public class ServerSide {
    public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int portNumber = 2728;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Opened server at port number 2728. Waiting for client...");
            Socket clientSocket = serverSocket.accept();
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            String outputLine;
            String fromServer = "Server: ";
			pw.println("You are now chatting with Server. Say \"bye\" to end chat.");
            while ((inputLine = br.readLine()) != null) {
            	if (inputLine.equals("bye")) {
            		System.out.println("Client has disconnected!");
            		clientSocket.close();
                    serverSocket.close();
            		System.exit(0);
            	}
            	System.out.println(inputLine);
                System.out.print(fromServer);
                outputLine = input.nextLine();
                pw.println(fromServer + outputLine);
            }
            clientSocket.close();
            serverSocket.close();
            input.close();
        } catch (IOException e) {
            System.out.println("Can't listen to port number " + portNumber);
        }
    }
}

