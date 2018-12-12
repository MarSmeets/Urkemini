package de.rub.RKE.macinterfaces;

import de.rub.RKE.keminterfaces.KemEncryptedData;

public interface MessageAuthenticationCode <MacTagType, MacKeyType>{
	
	public void setInput(String associatedData, KemEncryptedData encryptedData); 
	
	public MacKeyType generateKey(int keyLength);

	public MacTagType computeTag(MacKeyType key);
	
	public boolean verifyTag(MacKeyType key, MacTagType tag);
}
