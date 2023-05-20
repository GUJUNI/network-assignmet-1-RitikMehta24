import java.io.*;
import java.net.*;
import java.util.Scanner;

public class simpletcpclient2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server");

            // Create input/output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String input;

            while (true) {
                System.out.print("Enter a number or 'exit' to quit: ");
                input = scanner.nextLine();

                out.println(input);

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                String response = in.readLine();
                System.out.println("Server response: " + response);
            }

            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
