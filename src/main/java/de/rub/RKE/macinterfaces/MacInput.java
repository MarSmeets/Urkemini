package de.rub.RKE.macinterfaces;


import de.rub.RKE.keminterfaces.KemEncryptedData;

public abstract class MacInput {
    private String associatedData;
    private KemEncryptedData encryptedData;
    
    public MacInput() {
    	//Empty constructor
    }

    public MacInput(String associatedData, KemEncryptedData encryptedData) {
    	this.associatedData = associatedData;
    	this.encryptedData = encryptedData;
    }
}

