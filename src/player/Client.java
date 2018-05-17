package player;

import javax.net.ssl.*;

import utils.Utils;

import java.io.OutputStream;


public class Client {

  public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket s = (SSLSocket) sf.createSocket(Utils.HOST, Utils.PORT);
    s.setEnabledCipherSuites(sf.getSupportedCipherSuites());


    OutputStream out = s.getOutputStream();
    int theCharacter = 0;
    theCharacter = System.in.read();

    while (theCharacter != '~') // The '~' is an escape character to exit
    {
      out.write(theCharacter);
      out.flush();
      theCharacter = System.in.read();
    }

    out.close();

    s.close();
  }
}
