package de.rub.RKE.ecieskem;


import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.crypto.kems.ECIESKeyEncapsulation;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import de.rub.RKE.keminterfaces.KemEncryptedData;
import de.rub.RKE.keminterfaces.KemKeyPair;
import de.rub.RKE.keminterfaces.KemOutput;
import de.rub.RKE.keminterfaces.KemOutputKey;
import de.rub.RKE.keminterfaces.KemPrivateKey;
import de.rub.RKE.keminterfaces.KemPublicKey;
import de.rub.RKE.keminterfaces.KeyEncapsulationAlgorithmus;

import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.KDF1BytesGenerator;
import org.bouncycastle.crypto.params.KeyParameter;


//Implements the Generic KEM. Uses ECIES Kem provided by bouncy castle
public class EciesKem implements KeyEncapsulationAlgorithmus<KemKeyPair, KemPublicKey, KemPrivateKey, KemOutputKey, KemEncryptedData>{	
	
  //Uses the ECIES-Kem provided by the bouncy castle package
  private ECIESKeyEncapsulation kem;
  
  //Uses the KDF1 Bytes generator with SHA2-256
  private KDF1BytesGenerator kdf;
  private SHA256Digest hash;
  
  //Parameters for the Elliptic Curve
  private X9ECParameters ellipticCurvePara;
  private ECDomainParameters ecDomainPara;
  private ECKeyGenerationParameters ecKeyGenPara;
  private ECKeyPairGenerator ecKeyGen;

  public EciesKem() {
	  SecureRandom rnd = new SecureRandom();
	  hash = new SHA256Digest();
	  kdf = new KDF1BytesGenerator(hash);
	  kem = new ECIESKeyEncapsulation(kdf,rnd);
	  
	  //Uses the brainpool-256 curve
	  ellipticCurvePara = TeleTrusTNamedCurves.getByName("brainpoolp256r1");
	  ecDomainPara = new ECDomainParameters(ellipticCurvePara.getCurve(), ellipticCurvePara.getG(), ellipticCurvePara.getN());
  }
  
  //Generates a random EC key pair for the specified curve
  @Override
  public KemKeyPair gen() {
	  SecureRandom rnd = new SecureRandom();
      ecKeyGen = new ECKeyPairGenerator();
	  ecKeyGenPara = new ECKeyGenerationParameters(ecDomainPara, rnd);
	  ecKeyGen.init(ecKeyGenPara);
	  AsymmetricCipherKeyPair keypair = ecKeyGen.generateKeyPair();
	  return new EciesKeyPair(keypair);
  }
  
  //Computes a public key for the provided private key
  @Override
  public KemPublicKey genPublicK(KemPrivateKey privateKey) {
	  //Gets the BigInteger and computes the point for the public key
	  BigInteger d = privateKey.getD();
      ECPoint q = ecDomainPara.getG().multiply(d);

      //Creates the public from the point
      ECPublicKeyParameters publicKey = new ECPublicKeyParameters(q, ecDomainPara);
      
      return new EciesPublicKey(publicKey);
  }
  
  //Generates and encrypts a key
  public KemOutput enc(int keyLen, KemPublicKey publicKey){
	  CipherParameters key;
	  EciesEncryptedData encryptedKey = new EciesEncryptedData();
	  EciesOutputKey outputKey;
	  byte[] encryptedData= new byte[65];
	  
	  //Uses the kem methods to produce the key
	  kem.init((ECPublicKeyParameters)publicKey);
	  key = kem.encrypt(encryptedData, keyLen);
	  
	  encryptedKey.setData(encryptedData);
	  outputKey = new EciesOutputKey((KeyParameter)key);
	  
	  return new EciesOutput(outputKey, encryptedKey);
  }
  
  //Decrypts a key
  public KemOutputKey dec(KemEncryptedData encryptedKey, int keyLen, KemPrivateKey privateKey) {
	  CipherParameters key;
	  byte[] encryptedData = encryptedKey.getData();
	  
	  //Use the kem methods to decrypt the key
	  kem.init((ECPrivateKeyParameters)privateKey);
	  key = kem.decrypt(encryptedData, keyLen);
	  return new EciesOutputKey((KeyParameter)key);
  }
}
