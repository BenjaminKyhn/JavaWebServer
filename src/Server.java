import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            System.out.println("Server started and listening on port 80");
            Socket socket = serverSocket.accept();

            BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter responseHeaders = new PrintWriter(socket.getOutputStream());
            DataOutputStream responseStream = new DataOutputStream(socket.getOutputStream());

            while (true){
                String input = request.readLine();
                System.out.println(input);
                if (input.isEmpty()){
                    System.out.println("Request received");
                    responseHeaders.println("HTTP/1.1 200 OK");
                    responseHeaders.println("Content-Type: text/html; charset=utf-8");
                    responseHeaders.println();
                    responseHeaders.flush();

                    responseStream.writeBytes("<h1>En webserver</h1><br>");
                    responseStream.writeBytes("<h2>En overskrift</h2>");
                    responseStream.writeBytes("<p>Et afsnit.</p>");
                    responseStream.writeBytes("<img src=\"https://i.imgur.com/40lws61.jpg\">");
                    responseStream.flush();

                    responseHeaders.close();
                    responseStream.close();
                    System.out.println("Sent to the browser");
                    break;
                }
            }
        }
        catch (SocketException e){
            System.out.println("Connection closed after successful response-request");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
