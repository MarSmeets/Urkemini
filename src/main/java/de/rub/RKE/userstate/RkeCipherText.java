package de.rub.RKE.userstate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.rub.RKE.keminterfaces.KemEncryptedData;
import de.rub.RKE.macinterfaces.MacTag;

public class RkeCipherText {
  KemEncryptedData encryptedData;
  MacTag tag;
  
  public RkeCipherText(KemEncryptedData encryptedData, MacTag tag){
	this.encryptedData = encryptedData;
	this.tag = tag;
  }
  
  public KemEncryptedData getEncryptedData() {
	  return encryptedData;
  }
  
  public MacTag getMacTag() {
	  return tag;
  }
  
  public byte[] getCipherTextBytes() {
	  byte[] cipherTextBytes = new byte[encryptedData.getData().length+tag.getTag().length];
	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
	  try {
		outputStream.write(encryptedData.getData());
		outputStream.write(tag.getTag());
		cipherTextBytes = outputStream.toByteArray( );
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  return cipherTextBytes;
  }
}
