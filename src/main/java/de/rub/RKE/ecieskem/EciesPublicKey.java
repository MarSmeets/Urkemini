package de.rub.RKE.ecieskem;

import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import de.rub.RKE.keminterfaces.KemPublicKey;

//Datatype/Class for the public Key which is used by the ECIES KEM
public class EciesPublicKey extends ECPublicKeyParameters implements KemPublicKey{

	public EciesPublicKey(ECPoint arg0, ECDomainParameters arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public EciesPublicKey(ECPublicKeyParameters publicK) {
		super(publicK.getQ(), publicK.getParameters());
	}

}
