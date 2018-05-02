import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class SSLServer {

  private static final int PORT = 8080;

  public static void main(String[] args) throws Exception {

    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(PORT);
    
    ss.setNeedClientAuth(true);
    ss.setEnabledCipherSuites(ssf.getSupportedCipherSuites());
    
    
    Socket s = ss.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    String line = null;
    while (((line = in.readLine()) != null)) {
      System.out.println(line);
    }
    in.close();
    s.close();
  }
}

