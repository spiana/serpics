import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.springframework.util.Base64Utils;


public class Mytest {

	@Test
	public void test(){
	
		String input = "99";
		System.out.println (Md5Crypt.md5Crypt(input.getBytes()));
		String p = Hex.encodeHexString(Md5Crypt.md5Crypt(input.getBytes()).getBytes());
		System.out.println (p.toUpperCase());
		String p1 = Base64Utils.encodeToString(Md5Crypt.md5Crypt(input.getBytes()).getBytes());
		System.out.println (p1.toUpperCase());
	}
}
