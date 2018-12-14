package de.rub.RKE.keminterfaces;


//Interface for the encrypted Data, which is computed by the KEM
public interface KemEncryptedData {

	//TODO:Discuss if we can require that classes work on bytes[]. Might change to abstract class otherwise.
	public byte[] getData();
	
	public void setData(byte[] input);
}
