import org.apache.commons.codec.binary.Base64;



public class Mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String p = "unoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinqueseiunoduetrequatrocinquesei";
		byte[] b=	Base64.encodeBase64(p.getBytes());
		String s  = Base64.encodeBase64String(p.getBytes());
		
		System.out.println(b);
		System.out.println(new String(b));
		System.out.println(new String (Base64.decodeBase64(s)));
		
	}

}
