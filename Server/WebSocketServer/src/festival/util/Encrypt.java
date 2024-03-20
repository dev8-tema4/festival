package festival.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public String getEncrypt(String password) {
        try {
            // MessageDigest를 이용하여 SHA-256 해시 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());

            // byte 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : encodedHash) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
