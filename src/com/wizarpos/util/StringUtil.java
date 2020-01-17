package com.wizarpos.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.iisysgroup.poslib.commons.TripleDES;
import com.itex.richard.payviceconnect.model.SessionKey;
import com.itex.richard.payviceconnect.wrapper.PayviceServices;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StringUtil
{
	private static final String HexChars = "1234567890abcdefABCDEF";
	public static final int LCD_WIDTH = 16;

	/** A table of hex digits */
	public static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  /**
   * Convert a nibble to a hex character
   * 
   * @param nibble
   *          the nibble to convert.
   */
  public static char toHexChar(int nibble)
  {
    return hexDigit[(nibble & 0xF)];
  }

  /**
  * Method trim space
  *
  * @param oldString The string to be format.
  *
  */
  public static String trimSpace(String oldString) 
  {
    if (null == oldString)
      return null;
    if (0 == oldString.length())
      return "";
      
    StringBuffer sbuf = new StringBuffer();
    int oldLen = oldString.length();
    for(int i = 0; i < oldLen; i++)
    {
      if (' ' != oldString.charAt(i))
        sbuf.append(oldString.charAt(i));
    }     
    String returnString = sbuf.toString();
    sbuf = null;
    return returnString;
  }
   
  /**
  * Method convert byte[] to String
  *
  * @param abyte0 The string to be format.
  *
  */
  public static String toString(byte abyte0[])
  {
    if(null == abyte0)
      return null;
    else
      return new String(abyte0);
  }

  /**
  * Method fill string
  *
  * @param formatString The string to be format.
  *
  */
  public static String fillString(String formatString, int length, char fillChar, boolean leftFillFlag)
  {
    if (null == formatString)
    {
      formatString = "";
    }
    int strLen = formatString.length();
    if (strLen >= length)
    {
      if (true == leftFillFlag)  // left fill 
        return formatString.substring(strLen - length, strLen);
      else
        return formatString.substring(0, length);
    } else
    {
      StringBuffer sbuf = new StringBuffer();
      int fillLen = length - formatString.length();
      for (int i = 0; i < fillLen; i++)
      { 
        sbuf.append(fillChar);
      }
      
      if (true == leftFillFlag)  // left fill 
      {
        sbuf.append(formatString);
      } else
      {
        sbuf.insert(0, formatString);
      }
      String returnString = sbuf.toString();
      sbuf = null;
      return returnString;
    }
  }

  /**
  * Method fill string
  *
  * @param formatString The string to be format.
  *
  */
  public static String fillSpace(String formatString, int length)
  {
    return fillString(formatString, length, ' ', false);
  }


  public static String getPrintableLine(String a, String b){
    int length = 32;
    int a_length = a.length();
    int b_length = b.length();

    int total_length = a_length + b_length;
    int space_length = length - total_length;

    StringBuilder stringBuilder = new StringBuilder(32);
    stringBuilder.append(a);
    for (int i = 0; i < space_length; i++){
      stringBuilder.append(" ");
    }
    stringBuilder.append(b);

    Log.d("Final string", stringBuilder.toString());

    return stringBuilder.toString();
  }
  public static  String getClientRef(final Context context, String rrn){
    if(SharedPreferenceUtils.INSTANCE.getSessionKey(context).isEmpty()){
      getSsKey(context);
    }
    String paylad = "{" +
            "    \"sessionKey\": \""+SharedPreferenceUtils.INSTANCE.getSessionKey(context)+"\"," +
            "    \"timestamp\": \"Y-m-d H:i:s.u\"," +
            "    \"rrn\": \""+rrn+"\"," +
            "    \"randomString\":\""+getRandom(30)+"\"\n" +
            "}";

    return new String(Base64.encodeBase64(paylad.getBytes()));
  }

  public static  String getRandom(int length){
    if(length <= 0){
      length = 11;
    }
    String er = "";
    for(int i = 0; i < length; i++){
      double c = Math.random();
      int d = Math.getExponent(c);
      er += d;
    }
    return er;
  }



  public static void getSsKey(final Context context){
       /* final AlertDialogBuilder alertDialogBuilder = new AlertDialogBuilder(context);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Getting Session key...");
        progressDialog.setCancelable(false);
        progressDialog.show();*/
    Log.i("okh", "GettingSesionKey");
    SharedPreferenceUtils preferenceUtils = SharedPreferenceUtils.INSTANCE;
    PayviceServices.getInstance(context).GetSesionParam(new SessionKey.Request(preferenceUtils.getPayviceWalletId(context),preferenceUtils.getTerminalId(context),
            preferenceUtils.getPayviceUsername(context),preferenceUtils.getPayvicePassword(context),"ANDROIDPOS", Build.ID))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<SessionKey.Response>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @Override
              public void onNext(SessionKey.Response response) {
//                        progressDialog.dismiss();
                if(!response.getError()){
                  SharedPreferenceUtils.INSTANCE.saveSessionKey(context, response.getSessionKey());
                  Log.i("okh","SeesionKEySaved");
                           /* alertDialogBuilder.message("Completed"+ response.getMessage());
                            alertDialogBuilder.positiveButton("Ok", new Function1<DialogInterface, Unit>() {
                                @Override
                                public Unit invoke(DialogInterface dialogInterface) {
                                    dialogInterface.dismiss();
                                    return null;
                                }
                            });
                            alertDialogBuilder.show();*/
                }else {
                           /* alertDialogBuilder.message("Error occured with error message "+ response.getMessage());
                            alertDialogBuilder.positiveButton("Ok", new Function1<DialogInterface, Unit>() {
                                @Override
                                public Unit invoke(DialogInterface dialogInterface) {
                                    dialogInterface.dismiss();
                                    return null;
                                }
                            });
                            alertDialogBuilder.show();*/
                }
              }

              @Override
              public void onError(Throwable e) {
                        /*progressDialog.dismiss();
                        alertDialogBuilder.message("Error occured ");
                        alertDialogBuilder.positiveButton("Ok", new Function1<DialogInterface, Unit>() {
                            @Override
                            public Unit invoke(DialogInterface dialogInterface) {
                                dialogInterface.dismiss();
                                return null;
                            }
                        });

                        alertDialogBuilder.show();*/
                Log.i("okh","Session key Failed");
              }

              @Override
              public void onComplete() {

              }
            });
  }


  /**
  * Method Format string
  *
  * @param formatString The string to be format.
  *
  */
    public static String fillZero(String formatString, int length)
    {
      return fillString(formatString, length, '0', true);
    }

  /**
       * @param s source string (with Hex representation)
       * @return byte array
       */
  public static byte[] hexString2bytes (String s) 
  {
    if (null == s)
      return null;
  
    s = trimSpace(s);
    
    if (false == isHexChar(s, false))
      return null;
      
    return hex2byte (s, 0, s.length() >> 1);
  }

  /**
   * @param   s       source string
   * @param   offset  starting offset
   * @param   len     number of bytes in destination (processes len*2)
   * @return  byte[len]
   */
  public static byte[] hex2byte (String s, int offset, int len) {
      byte[] d = new byte[len];
      int byteLen = len * 2;
      for (int i=0; i < byteLen; i++) {
        int shift = (i%2 == 1) ? 0 : 4;
        d[i>>1] |= Character.digit(s.charAt(offset+i), 16) << shift;
      }
      return d;
  }
  
  private static void appendHex(StringBuffer stringbuffer, byte byte0)
  {
    stringbuffer.append(toHexChar(byte0 >> 4));
    stringbuffer.append(toHexChar(byte0));
  }

  public static String toHexString(byte abyte0[], int beginIndex, int endIndex, boolean spaceFlag)
  {
    if(null == abyte0)
      return null;
    if(0 == abyte0.length)
      return "";
    StringBuffer sbuf = new StringBuffer();
    appendHex(sbuf, abyte0[beginIndex]);
    for(int i = (beginIndex + 1); i < endIndex; i++)
    {
      if (spaceFlag)
        sbuf.append(" ");
      appendHex(sbuf, abyte0[i]);
    }
    String returnString = sbuf.toString();
    sbuf = null;
    return returnString;
  }

  public static String toHexString(byte abyte0[], boolean spaceFlag)
  {
    if(null == abyte0)
      return null;
    return toHexString(abyte0, 0, abyte0.length, spaceFlag);
  }

  /**
  * Method Check String 
  *
  * @param hexString The string to be format.
  *
  */  
  public static boolean isHexChar(String hexString, boolean trimSpaceFlag) 
  {
    if (null == hexString || 0 == hexString.length())
      return false;
  
    if (trimSpaceFlag)
      hexString = trimSpace(hexString);
      
    if (hexString.length() % 2 != 0)
      return false;
    int hexLen = hexString.length();
    for(int i = 0; i < hexLen; i++)
    {
      if (HexChars.indexOf(hexString.charAt(i)) < 0)
        return false;
    }
    
    return true;
  }

  public static String padleft(String s, int len, char c) {
    s = s.trim();
    if (s.length() > len)
      return null;
    StringBuilder d = new StringBuilder(len);
    int fill = len - s.length();
    while (fill-- > 0)
      d.append(c);
    d.append(s);
    return d.toString();
  }

  public static boolean isHexChar(String hexString) 
  {
    return isHexChar(hexString, true);
  }


  /**
   * 3DES Encrypt the data
   *
   * @param key  Key to encrypt with
   * @param data Data to be encrypted
   * @return Hex string of encrypted data
   */
  public static String tripleDesEncrypt(String key, String data) {
    //checkNotNull(key);
    //checkNotNull(data);
    int len = data.length();
    //checkArgument(len == 16 || len == 32 || len == 48, "Invalid data for 3DES Encrypt");

    try {
      byte[] keyBytes = Hex.decodeHex(key.toCharArray());
      byte[] desKey = new byte[24];
      System.arraycopy(keyBytes, 0, desKey, 0, 16);
      System.arraycopy(keyBytes, 0, desKey, 16, 8);

      DESedeKeySpec keySpec = new DESedeKeySpec(desKey);

      SecretKey secretKey = SecretKeyFactory.getInstance("DESede").generateSecret(keySpec);

      Cipher ecipher = Cipher.getInstance("DESede/ECB/NoPadding");
      ecipher.init(Cipher.ENCRYPT_MODE, secretKey);

      byte[] encryptedData = ecipher.doFinal(Hex.decodeHex(data.toCharArray()));
      return hex(encryptedData).toUpperCase();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }





  public static String hex(byte[] data) {

    StringBuilder sb = new StringBuilder();
    for (byte b : data) {
      sb.append(Character.forDigit(((b & 240)) >> 4, 16));
      sb.append(Character.forDigit(((b & 15)), 16));

    }
    return sb.toString();
  }

  public static String threeDesDecrypt(String encryptedToken, String key) {

    Log.d("okh  About to do desDecrypt ", "pinblock1>>>>>> stringCipherNewUserKey : " +  encryptedToken);
    Log.d("okh  About to do desDecrypt", "pinblock1>>>>>>> pinblock : " +  key);

    byte[] mkB = TripleDES.hexToByte(key + key.substring(0, 16));
    SecretKey keyse = TripleDES.readKey(mkB);


    return TripleDES.Decrypt(keyse, encryptedToken);
  }


  // 转成16进制
  public static byte[] StrToHexByte(String str) {
    if (str == null)
      return null;
    else if (str.length() < 2)
      return null;
    else {
      int len = str.length() / 2;
      byte[] buffer = new byte[len];
      for (int i = 0; i < len; i++) {
        buffer[i] = (byte) Integer.parseInt(
                str.substring(i * 2, i * 2 + 2), 16);
      }
      return buffer;
    }
  }

  public static String bytes2HexString(byte[] b) {
    String ret = "";
    for (byte element : b) {
      String hex = Integer.toHexString(element & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      ret += hex.toUpperCase();
    }
    return ret;
  }

  //%%%%%%%%%%%%%%%%%%%%%% HEX to ASCII %%%%%%%%%%%%%%%%%%%%%%
  public static String convertHexToString(String hex){

    String ascii="";
    String str;

    // Convert hex string to "even" length
    int rmd,length;
    length=hex.length();
    rmd =length % 2;
    if(rmd==1)
      hex = "0"+hex;

    // split into two characters
    for( int i=0; i<hex.length()-1; i+=2 ){

      //split the hex into pairs
      String pair = hex.substring(i, (i + 2));
      //convert hex to decimal
      int dec = Integer.parseInt(pair, 16);
      str=CheckCode(dec);
      ascii=ascii+" "+str;
    }
    return trimSpace(ascii);
  }

  public static String CheckCode(int dec){
    String str;

    //convert the decimal to character
    str = Character.toString((char) dec);

    if(dec<32 || dec>126 && dec<161)
      str="n/a";
    return str;
  }

  public static String join(String[] str, String separator){
    String retval = "";
    for (String s: str){ retval+= separator + s;}
    return retval.replaceFirst(separator, "");
  }
}
