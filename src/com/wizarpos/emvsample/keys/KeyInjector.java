package com.wizarpos.emvsample.keys;


import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.ByteConvert;
import com.wizarpos.util.CommonUtils;
import com.wizarpos.util.Logger;

import java.util.Arrays;

public class KeyInjector {

    public AuthInfo getAuthInfo() throws PINPadException {
        return new AuthInfo();
    }

    public void importKeyInfo(byte[] keyInfo) throws PINPadException {
        Logger.info("Begin importing key info...");
        PinPadInterface.open();
        try {
            int r = PinPadInterface.importTMK(keyInfo);
            if (r < 0) {
                throw new PINPadException("Import terminal master key failed. Result: " + r);
            }
        } finally {
            PinPadInterface.close();
        }
        Logger.info("Import key info successfully.");
    }

    public boolean validateMasterKey(byte[] validData) throws PINPadException {
        Logger.info("Begin validating master key...");
        PinPadInterface.open();
        try {
            int len = 0;
            int masterKeyId = validData[len++];
            int userKeyId = validData[len++];
            int userKeyType = validData[len++];
            int cipherUserKeyLen = ByteConvert.byte2int2(validData, len);
            len += 2;
            byte[] cipherUserKey = CommonUtils.subBytes(validData, len, cipherUserKeyLen);
            len += cipherUserKeyLen;
            int checkValueLen = ByteConvert.byte2int2(validData, len);
            len += 2;
            byte[] checkValue = CommonUtils.subBytes(validData, len, checkValueLen);
            len += checkValueLen;

            Logger.debug(CommonUtils.toHex(checkValue));
            int r = 0;
            if (r < 0) {
                throw new PINPadException("Update user key failed. Result: " + r);
            }
            int msgDataLen = ByteConvert.byte2int2(validData, len);
            len += 2;
            byte[] msgData = CommonUtils.subBytes(validData, len, msgDataLen);
            len += msgDataLen;
            Logger.debug("Msg data: " + CommonUtils.toHex(msgData));


            int cipherDataLen = ByteConvert.byte2int2(validData, len);
            len += 2;
            byte[] cipherData = CommonUtils.subBytes(validData, len, cipherDataLen);
            len += cipherDataLen;

            // This is important

            byte[] cipherText = new byte[4096];
            r = PinPadInterface.encrypt(msgData, msgDataLen, cipherText);
            byte[] enData = CommonUtils.subBytes(cipherText, 0, r);
            Logger.debug("Cipher data: " + CommonUtils.toHex(enData));

            return Arrays.equals(cipherData, enData);
        } finally {
            PinPadInterface.close();
        }
    }

    public boolean validateDukptKey(byte[] validData) throws PINPadException {
        Logger.info("Begin validating dukpt key...");
        PinPadInterface.open();
        try {
            int r =0;
            if (r < 0) {
                throw new IllegalArgumentException("Select key error. " + r);
            }
            byte[] data = CommonUtils.toBytes("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f");
            byte[] out = new byte[4096];
            r = PinPadInterface.encrypt(data, data.length, out);
            if (r < 0) {
                throw new IllegalArgumentException("Encrypt with mode error. " + r);
            }
            byte[] cdata = CommonUtils.subBytes(out, 0, r);
            byte[] expected = CommonUtils.toBytes("690810543E10E192A6ABB463CB8C0543CBCB80124FD130C99BE89CC534C00227FFFF9876543210E00000");

            return Arrays.equals(cdata, expected);
        } finally {
            PinPadInterface.close();
        }
    }
}
