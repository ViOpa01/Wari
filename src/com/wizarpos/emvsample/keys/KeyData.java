package com.wizarpos.emvsample.keys;

import com.wizarpos.util.ByteConvert;
import com.wizarpos.util.CipherUtils;
import com.wizarpos.util.CommonUtils;
import com.wizarpos.util.StringUtil;

public class KeyData {
   private byte keyType;
   private byte maskeyId;
   private byte keyLen;
   private byte reserved;
   private byte[] masterKey;

   public KeyData(int keyType, int keyIndex, int keylen, String key){
       this.keyLen = new Integer("" + keylen).byteValue();
       this.keyType = new Integer("" + keyType).byteValue();
       this.maskeyId = new Integer("" + keyIndex).byteValue();
       this.masterKey = StringUtil.hexString2bytes(key);
       this.reserved = 0;
   }

   public byte[] getKey(AuthInfo authInfo) throws PINPadException {
       byte[] res = new byte[]{keyType,maskeyId,keyLen,reserved};
       CommonUtils.append(masterKey,res,res.length);
       return CipherUtils.encrypt(authInfo.getPemCert(),res);
   }
}
