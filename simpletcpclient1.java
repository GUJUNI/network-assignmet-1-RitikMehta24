
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class simpletcpclient1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to server on localhost:12345
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String message;
            do {
                System.out.print("Enter message: ");
                message = scanner.nextLine();
                out.println(message); // Send message to server
            } while (!message.equals("exit")); // Keep sending messages until "exit" is sent

            in.close();
            out.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}