import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class simpletcpserver1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Listening on port 12345
            System.out.println("Server is listening on port 12345...");

            Socket clientSocket = serverSocket.accept(); // Wait for client connection
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) { // Keep reading messages from client
                System.out.println("Received message: " + message);
                if (message.equals("exit")) { // If client sends "exit", break loop and close connection
                    System.out.println("Closing connection...");
                    break;
                }
            }

            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
