
public class MyServer {
/*
 * This class was written to parse incoming data.
 */
	SignalController sc;
	public MyServer()
	{
		sc = new SignalController();
	}
	// This function checks the first 5 characters and calls
	// the appropriate function
	public  boolean deligate(String pass)
	{
		switch (pass.substring(0, 5))
		{
		case "large":
			return largeWave(pass);
		case "small":
			return smallWave(pass);
		case "stand":
			return standingWave(pass);
		case "stop0":
			return stopStandingWave();
		default:
			return false;
		}

	}

	private boolean stopStandingWave()
	{
		return sc.stopStand();
	}
	public  boolean largeWave(String pass)
	{
		return sc.largeW();
	}

	public  boolean smallWave(String pass)
	{
		return sc.smallW();
	}

	public  boolean standingWave(String pass)
	{
		// The string passed to this function
		// has an integer value appended to it
		// the following two lines isolate that integer
		// and pass it along to be used as the frequency
		// of standing wave
		pass=pass.substring(5,7);
		return sc.standingW(Integer.valueOf(pass));
	}

}
