import java.io.*;
import javax.net.ssl.*;

public class SSLClient {

  private static final String HOST = "localhost";

  private static final int PORT = 8080;

  public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket s = (SSLSocket) sf.createSocket(HOST, PORT);
    s.setEnabledCipherSuites(sf.getSupportedCipherSuites());

    /*
    OutputStream out = s.getOutputStream();
    out.write("\nConnection established.\n\n".getBytes());
    out.flush();
    int theCharacter = 0;
    theCharacter = System.in.read();


    while (theCharacter != '~') // The '~' is an escape character to exit
    {
      out.write(theCharacter);
      out.flush();
      theCharacter = System.in.read();
    }

    out.close();
    */
    s.close();
  }
}
