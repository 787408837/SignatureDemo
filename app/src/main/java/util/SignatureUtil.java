package util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Klein
 * @version 1.1.5
 * @date 2018/12/6 11:19
 */
public class SignatureUtil {
    public final static String MD5 = "MD5";
    public final static String SHA1 = "SHA1";
    public final static String SHA256 = "SHA256";

    public static String getSignatureStr(Context context, String mPackageName, String mType) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(mPackageName, PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance(mType);
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (byte digestByte : publicKey) {
                hexString.append((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3).toUpperCase(Locale.US));
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getSignatureStrSymbol(Context context, String mPackageName, String mType) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(mPackageName, PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance(mType);
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}

