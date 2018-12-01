package de.rub.keminterfaces;


//Interface for the encrypted Data, which is computed by the KEM
public interface KemEncryptedData {

	public byte[] getData();
	
	public void setData(byte[] input);
}
