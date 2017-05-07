import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * An attempt at streamlining my network programming
 * I have minimal network experience.
 * Most of this code was derived from Wagner's Hackintosh
 * assignment for CS 112
 * I need to figure out the best way to close and open new
 * connections without having to instantiate a new instance
 * but as it is, creating an instance and using it to send
 * data one way works as is needed
 * @author Sinux1
 *
 */
public class MySocketUtility
{
	private String ip;
	private int port;
    private Socket s;
    private OutputStreamWriter os;
	private PrintWriter out;


	public MySocketUtility(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
		establishConnection();
		establishOutgoingStream();

	}

	private void establishConnection()
	{
		try {
			s = new Socket(ip, port);
		} catch (UnknownHostException e) {
			System.out.println("Error connecting to host ip address");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error connecting on port, error not ip");
			e.printStackTrace();
		}
	}

	private void establishOutgoingStream()
	{
		try {
			os = new OutputStreamWriter(s.getOutputStream());
			out  = new PrintWriter(os);
		} catch (IOException e) {
			System.out.println("Error instantiating OutputStreamWriter");
			e.printStackTrace();
		}
	}

	public void sendData(String command)
	{
		out.println(command);
		out.flush();
	}

	public void changeConnection(String ip, int port)
	{
		try {
			s.close();
		} catch (IOException e) {
			System.out.print("Unable to close socket");
			e.printStackTrace();
		}

		this.ip = ip;
		this.port = port;
		establishOutgoingStream();
	}
}
