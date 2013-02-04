import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


public class Test {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub

			MessageDigest digest = MessageDigest.getInstance("MD5");
			String s = new String("misterantani");
			
			byte[] sc =digest.digest(s.getBytes());
			
			BigInteger b = new BigInteger(1, sc); 
			String result = b.toString(16);
			
			
			System.out.println(result);
			System.out.print(MD5hash(s));
	}
	
	public static String MD5hash(String text) throws NoSuchAlgorithmException {
	    byte[] hash = MessageDigest.getInstance("MD5").digest(text.getBytes());
	    return String.format("%032x",new BigInteger(1, hash));
	}


}
