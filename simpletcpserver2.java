import java.io.*;
import java.net.*;

public class simpletcpserver2 {
    public static void main(String[] args) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(1234)) {
                System.out.println("Server started and waiting for client connection...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // Create input/output streams for communication with the client
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String input;
                    while ((input = in.readLine()) != null) {
                        if (input.equalsIgnoreCase("exit")) {
                            break;
                        }

                        try {
                            int number = Integer.parseInt(input);
                            int factorial = calculateFactorial(number);
                            out.println("Factorial of " + number + " is " + factorial);
                        } catch (NumberFormatException e) {
                            out.println("Invalid input. Please enter a number or type 'exit' to quit.");
                        }

                        System.out.println("Received input from client: " + input);
                    }

                    System.out.println("Client disconnected");
                    clientSocket.close();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    private static int calculateFactorial(int number) {
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
