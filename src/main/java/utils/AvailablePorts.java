package utils;

import java.io.IOException;
import java.net.ServerSocket;

public class AvailablePorts {

    /**
     * Appium needs a port to run
     * This obtain random ports using ServerSocket
     *
     * port=0 means the port number is automatically allocated.
     * Enable the socket option
     *
     * @return int, a random port number
     * @throws IOException
     */
    public int getPort() throws IOException {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        int port = socket.getLocalPort();
        socket.close();
        return port;

    }
}
