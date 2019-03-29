package com.wizarpos.emvsample.keys;

import com.wizarpos.util.ByteConvert;
import com.wizarpos.util.ByteUtil;
import com.wizarpos.util.CommonUtils;

public class KeyInfo {
    private  byte[] keyData;
    private byte[] publicKeyHost;
    private byte[] random;
    private byte[] sinature;

    public KeyInfo(KeyData data, byte[] pkey, byte[] rand, byte[] sign, AuthInfo authInfo) throws PINPadException {
        this.keyData = data.getKey(authInfo);
        this.publicKeyHost = pkey;
        this.random = rand;
        this.sinature = sign;
    }

    public byte[] getKeyInfo(){
        byte[] res = new byte[0];
        CommonUtils.append(keyData,res,res.length);
        CommonUtils.append(publicKeyHost,res,res.length);
        CommonUtils.append(random,res,res.length);
        CommonUtils.append(sinature,res,res.length);
        return res;
    }
}
