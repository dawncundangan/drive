import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This program will represent the Client's side for the ServerSide program. 
 * IP address "192.168.5.49" and port number 2728 are set by default. When the Server is 
 * up, the Client will connect to the enabled port and begin the chat. If the client wishes 
 * to end the chat, he/she must type "bye" and it the program will exit, closing the 
 * socket and disconnecting from the server.
 * 
 * @author Culanag, Ian
 * @author Cundangan, Dawn
 *
 */
public class ClientSide {
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        String hostName = "192.168.5.49";
		int portNumber = 2728;		

        try {
        	Socket socket = new Socket(hostName, portNumber);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String fromServer;
			String fromClient = "Client: ";		
            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                System.out.print(fromClient);
                String message = input.nextLine();
                if (message.equals("bye")){
                	pw.println("bye");
                	System.out.println("You have been disconnected from the server!");
                    socket.close();
                	System.exit(0);
                }
                pw.println(fromClient + message);
           }
            input.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Couldn't find the host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't establish connection to " + hostName + " at " + portNumber);
            System.exit(1);
        }
    }
}