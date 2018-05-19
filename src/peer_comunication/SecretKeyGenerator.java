package peer_comunication;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecretKeyGenerator {

 public static SecretKey generateSecretKey() {
  KeyGenerator keyGen = null;
  try {
   /*
    * Get KeyGenerator object that generates secret keys for the
    * specified algorithm.
    */
   keyGen = KeyGenerator.getInstance("AES");
  } catch (NoSuchAlgorithmException e) {
   e.printStackTrace();
  }

  /* Initializes this key generator for key size to 256. */
  keyGen.init(256);

  /* Generates a secret key */
  SecretKey secretKey = keyGen.generateKey();

  return secretKey;
 }

 
 //converts the secretKey to a string to be more easy to send the message
 public static String keyToString(SecretKey secretKey) {
  /* Get key in encoding format */
  byte encoded[] = secretKey.getEncoded();

  /*
   * Encodes the specified byte array into a String using Base64 encoding
   * scheme
   */
  String encodedKey = Base64.getEncoder().encodeToString(encoded);

  return encodedKey;
 }

 
 //converts the keyString to SecretKey
 public static SecretKey decodeKeyFromString(String keyStr) {
  /* Decodes a Base64 encoded String into a byte array */
  byte[] decodedKey = Base64.getDecoder().decode(keyStr);

  /* Constructs a secret key from the given byte array */
  SecretKey secretKey = new SecretKeySpec(decodedKey, 0,
    decodedKey.length, "AES");

  return secretKey;
 }


}