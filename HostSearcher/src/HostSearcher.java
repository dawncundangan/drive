import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
public class HostSearcher { //by Ian Culanag
	public static void main(String[] args) { 
		Scanner input = new Scanner(System.in);
		int hostnum = 1;
		String choice = "Y";
		do {
			System.out.print("Host " + hostnum + " - Type IP address/Hostname: ");
			String hostname = input.next();
		
			try {
				InetAddress[] addr = InetAddress.getAllByName(hostname);
				int number = addr.length;
				
				System.out.printf("%-2s%-15s%n","","Number of Hosts/IPs: " + number);
				System.out.printf("%-2s%-14s%n","","IP Address/es for " + hostname);
				int count = 0;
				for (InetAddress address : addr){
					count++;
					String ip = address.toString().split("/")[1];			
					System.out.printf("%-4s%-1s%-1s%-14s%n","",count,") ",ip);
					
				}
				hostnum++;
			} catch (UnknownHostException e) {
				System.out.println("The host does not exist.");
			}
			System.out.print("Search another? [y/n]: ");
			choice = input.next();
		} while (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes"));
		input.close();
		System.out.print("Exit!");
	}
}