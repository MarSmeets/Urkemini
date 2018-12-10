package de.rub.RKE.keminterfaces;

//Interface for the Output of the KEM, when using the encryption function
public interface KemOutput {
	
	public KemOutputKey getKey();
	
	public KemEncryptedData getData();
}
