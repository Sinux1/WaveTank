
import java.io.*;
import java.net.*;
/*
 * This is the main 'server' side class. Opening
 * and listening on a socket for incoming data,
 * and passing it off for interpretation and evaluation.
 *
 */
public class PiServer {

    public static void main(String[] arrstring) throws IOException {

    	MyServer s = new MyServer();
        int n = 58999;
        ServerSocket serverSocket = new ServerSocket(n);
        do {
            try {
                block3 : do {
                    System.out.println("Waiting for connection on port 58999");
                    Socket socket = serverSocket.accept();
                    System.out.println("Connected to client");
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    do {
                        String string;
                        if ((string = bufferedReader.readLine()) == null)
                        	continue block3;
                        else
                        {
                        	// Argument to close socket and shut down program
                        	if(string.equals("close"))
                        	{
                        		serverSocket.close();
                        		System.exit(0);
                        	}
                        	// Pass string to MyServer to be parsed
                        	s.deligate(string);
                        }
                        printWriter.flush();
                    } while (true);
                } while (true);
            }
            catch (IOException var3_4) {
                System.out.println("Connection terminated.");
                continue;
            }
        } while (true);
    }
}
