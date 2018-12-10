package de.rub.RKE.ecieskem;

import de.rub.RKE.keminterfaces.KemOutput;

//Datatype/Class for the Output which is produced by the ECIES KEM when calling the encryption function
public class EciesOutput implements KemOutput{
  EciesOutputKey key;
  EciesEncryptedData encryptedKey;
  
  public EciesOutput(EciesOutputKey inputKey,  EciesEncryptedData inputEncryptedKey) {
	  key= inputKey;
	  encryptedKey = inputEncryptedKey;
  }
  
  public EciesOutputKey getKey() {
	  return key;
  }
  public EciesEncryptedData getData() {
	  return encryptedKey;
  }
  
}
