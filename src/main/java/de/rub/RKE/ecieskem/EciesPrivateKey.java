package de.rub.RKE.ecieskem;

import java.math.BigInteger;

import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;

import de.rub.RKE.keminterfaces.KemPrivateKey;


//Datatype/Class for the private Key which is used by the ECIES KEM
public class EciesPrivateKey extends ECPrivateKeyParameters implements KemPrivateKey{

	public EciesPrivateKey(BigInteger arg0, ECDomainParameters arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public EciesPrivateKey(ECPrivateKeyParameters privateK) {
		super(privateK.getD(), privateK.getParameters());
	}
	
	public BigInteger getD() {
		return super.getD();
	}
	
	

}
