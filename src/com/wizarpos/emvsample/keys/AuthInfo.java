package com.wizarpos.emvsample.keys;


import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.ByteConvert;
import com.wizarpos.util.CipherUtils;
import com.wizarpos.util.CommonUtils;

import org.bouncycastle.openssl.PEMReader;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

/**
 * The authenticate information from PINPad HSM module. It's unique for every key injecting transaction.
 */
public class AuthInfo {
    public static final int LEN_PUB_KEY_LEN = 4;
    public static final int LEN_PUB_KEY = 4096;
    public static final int LEN_RANDOM = 32;
    public static final int LEN_SN_LEN = 1;
    public static final int LEN_SN = 31;
    public static final int LEN_SIG = 256;

    public static final int MSG_SIZE = LEN_PUB_KEY_LEN + LEN_PUB_KEY + LEN_RANDOM + LEN_SN_LEN + LEN_SN + LEN_SIG;

    private byte[] msg = new byte[MSG_SIZE];
    private byte[] pubKeyLen;
    private byte[] pubKey;
    private byte[] random;
    private byte[] snLen;
    private byte[] sn;
    private byte[] sig;

    private X509Certificate cert;
    private byte[] rsn;

    public AuthInfo() throws PINPadException {
        PinPadInterface.open();
        try {
            int r = PinPadInterface.getAuthInfo(msg);
            if (r < 0) {
                throw new PINPadException("Get auth info failed. Result: " + r);
            }
        } finally {
            PinPadInterface.close();
        }
        parseRawData();
        parseSn();
        parseCert();
        verifySignature();
    }

    private void parseRawData() {
        int len = 0;
        pubKeyLen = CommonUtils.subBytes(msg, len, LEN_PUB_KEY_LEN);
        len += LEN_PUB_KEY_LEN;

        pubKey = CommonUtils.subBytes(msg, len, LEN_PUB_KEY);
        len += LEN_PUB_KEY;

        random = CommonUtils.subBytes(msg, len, LEN_RANDOM);
        len += LEN_RANDOM;

        snLen = CommonUtils.subBytes(msg, len, LEN_SN_LEN);
        len += LEN_SN_LEN;

        sn = CommonUtils.subBytes(msg, len, LEN_SN);
        len += LEN_SN;

        sig = CommonUtils.subBytes(msg, len, LEN_SIG);
        len += LEN_SIG;
    }

    private void parseCert() throws PINPadException {
        byte[] certBytes = CommonUtils.subBytes(pubKey, 0, ByteConvert.byte2int4(pubKeyLen, false));
        PEMReader pemReader = new PEMReader(new InputStreamReader(new ByteArrayInputStream(certBytes)));
        try {
            cert = ((X509Certificate) pemReader.readObject());
            // verify the cert validity
            cert.checkValidity(new Date());
        } catch (Exception e) {
            throw new PINPadException("Read certificate error", e);
        }
    }

    private void parseSn() {
        this.rsn = Arrays.copyOf(sn, snLen[0]);
    }

    private void verifySignature() throws PINPadException {
        int snLength = getSN().length;
        byte[] data = new byte[snLength + random.length];
        CommonUtils.append(getSN(), data, 0);
        CommonUtils.append(random, data, snLength);
        boolean r = CipherUtils.verifySig(getPemCert().getPublicKey(), data, sig);
        if (!r) {
            throw new PINPadException("Verify signatrure error.");
        }
    }

    /**
     * Get the PubKeyP in simple certificate in PEM format.
     * The certificate is issued by POS Root Public Key.
     *
     * @return
     */
    public byte[] getPubKeyPEMBuffer() {
        return pubKey;
    }

    public X509Certificate getPemCert() {
        return this.cert;
    }

    /**
     * Get the random number of this key injecting transaction.
     *
     * @return
     */
    public byte[] getRandom() {
        return random;
    }

    /**
     * Get the serial number of POS.
     *
     * @return
     */
    public byte[] getSN() {
        return rsn;
    }

    /**
     * Get the signature of the SN and the Random number.
     *
     * @return
     */
    public byte[] getSignature() {
        return sig;
    }

    public byte[] getMsg() {
        return msg;
    }
}
