package org.zcoreframework.base.encryption;

/**
 * Created by m.hoseininejad on 10/14/2017.
 */
public interface Encryption  {

    String encrypt(String strToEncrypt ,String secretKey);
    String decrypt(String strToDecrypt ,String secretKey);

}
