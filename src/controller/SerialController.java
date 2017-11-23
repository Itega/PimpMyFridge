package controller;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import model.Model;
import view.View;

import java.util.Enumeration;


/**
 * The type Serial controller.
 */
public class SerialController implements SerialPortEventListener {

    /**
     * The Serial port.
     */
    SerialPort serialPort;
    private static final String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM5", // Windows
    };


    private BufferedReader input;
    /** The output stream to the port */
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 115200;


    /**
     * The Is connected.
     */
    public Boolean isConnected = false;

    private static SerialController instance = null;
    private DataController data;
    private Model model;

    /**
     * Get instance serial controller.
     *
     * @param model the model
     * @param view  the view
     * @return the serial controller
     */
    public static SerialController getInstance(Model model, View view){
        if(instance == null){
            instance = new SerialController(model, view);
        }
        return instance;
    }

    private SerialController(Model model, View view) {
        data = new DataController(model, view);
        this.model = model;
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", "COM5");
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    this.isConnected = true;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Send data.
     *
     * @param data the data
     */
    public synchronized void sendData(String data){
        try {
            output.write(data.getBytes());
            output.flush();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Send consigne.
     *
     * @param consigne the consigne
     */
    public synchronized void sendConsigne(int consigne){
        sendData("consigne:"+consigne);
    }


    /**
     * Close.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }


    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                data.processData(input.readLine());
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }
}