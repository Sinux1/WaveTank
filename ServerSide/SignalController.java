import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;
/*
 *
 */
public class SignalController
{

	private GpioController gpio;
	private GpioPinDigitalOutput pin0;
	private GpioPinDigitalOutput pin2;
	GpioPinPwmOutput pwm;
/*
 * The frequency converting method needs to be tested
 * with a meter with half way decent accuracy.
 * The pwm pin will be read by an arduino, and the only
 * way to be sure of the voltage is to test it. I have
 * a feeling it will need to be rewritten.
 * As it is, two pins are digital binary, the third is pwm.
 * Either of the two binary pins on, witht he other off, and pwm at 0,
 * tells the arduino to fire a single time,
 * either large or small depending on the pin.
 * If both binary pins are on, a repeating fire is maintained,
 * of a magnitude dependent on the voltage of the pwm pin.
 */

	public SignalController()
	{
		// Create GPIO controller
		gpio = GpioFactory.getInstance();
		Pin pin = CommandArgumentParser.getPin(
                RaspiPin.class,
                RaspiPin.GPIO_01);
		pwm = gpio.provisionPwmOutputPin(pin);
		com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
        com.pi4j.wiringpi.Gpio.pwmSetClock(500);
        pwm.setPwm(0);
		// Set all pins to low at startup
		pin0 = gpio.provisionDigitalOutputPin
				(RaspiPin.GPIO_00, "MyLED", PinState.LOW);
		pin2 = gpio.provisionDigitalOutputPin
				(RaspiPin.GPIO_02, "MyLED", PinState.LOW);
		// Set initial voltage to 0


		// Set shutdown state for all three pins
		pin0.setShutdownOptions(true, PinState.LOW);
		pin2.setShutdownOptions(true, PinState.LOW);

		// Set all three pins to off
		pin0.low();
		pin2.low();


	}

	public boolean largeW()
	{
		pin0.pulse(1000, true);
		pin0.low();

		return true;

	}

	public boolean smallW()
	{
		pin2.pulse(1000, true);
		pin2.low();

		return true;

	}

	public boolean standingW(int frequency)
	{
		// Turn frequency into percentage of max voltage
		int percentOfMaxV = convertFrequency(frequency);
			pin0.high();
			pin2.high();
			pwm.setPwm(percentOfMaxV);

		return true;
	}

	public boolean stopStand()
	{
		pin0.low();
		pin2.low();
		pwm.setPwm(0);
		return true;
	}

	public int convertFrequency(int frequency)
	{
		return frequency == 30? 0 :
			frequency == 35? 200:
				frequency == 40? 350:
					frequency == 45? 500:
						frequency == 50? 650:
							frequency == 55? 800:
								frequency == 60? 900: -1;
	}
}
