import com.google.zxing.WriterException;

import java.io.IOException;
public class Auth {

	public static void main (String secretKey, String email)  throws IOException, WriterException {	
                System.out.println(secretKey);
//		String email = "harisashfaq15@gmail.com";
		String companyName = Utils.companyName;
		String barCodeUrl = Utils.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
		Utils.createQRCode(barCodeUrl, "QRCode.png", 400, 400);
	}
}