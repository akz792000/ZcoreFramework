package org.zcoreframework.base.encryption;

/**
 * Created by m.hoseininejad on 10/14/2017.
 */
public class EncryptFactory {

    public static Encryption getEncrypt(String encryptType) {

        switch (encryptType) {
            case "AES": {
                return new AES();
            }
            default:
                return null;
        }

    }

}
