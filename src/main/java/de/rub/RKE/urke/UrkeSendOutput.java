package de.rub.RKE.urke;

import de.rub.RKE.userstate.RkeCipherText;
import de.rub.RKE.userstate.RkeSessionKey;

public class UrkeSendOutput {
  RkeSessionKey sessionKey;
  RkeCipherText cipherText;
  
  public UrkeSendOutput(RkeSessionKey sessionKey,  RkeCipherText cipherText) {
	this.sessionKey = sessionKey;
	this.cipherText = cipherText;
  }
  
  public RkeSessionKey getSessionKey() {
	  return sessionKey;
  }
  
  public RkeCipherText getCipherText() {
	  return cipherText;
  }
}
