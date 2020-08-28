import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            System.out.println("Server started and listening on port 80");
            Socket socket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedOutputStream dataOut = new BufferedOutputStream(socket.getOutputStream());

            while (true){
                String input = in.readLine();
                System.out.println(input);
                if (input.isEmpty()){
                    System.out.println("Request received");
                    out.println("HTTP/1.1 200 OK");
                    out.println();
                    out.flush();
                    //dataOut.write();
                    break;
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
