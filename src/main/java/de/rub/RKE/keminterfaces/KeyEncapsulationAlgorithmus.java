package de.rub.RKE.keminterfaces;


//Generic Interface for the KEM
//All used DataTypes can be chosen, when implementing the KEM
public interface KeyEncapsulationAlgorithmus<KeypairType, PublicKeyType, PrivateKeyType ,
                                             SymmetricKeyType, EncryptedDataType>{
  
  public KeypairType gen();
  
  //TODO: Discuss/Find useful Type for seed or "Seedtype"
  public KeypairType genWithSeed(byte[] seed);
  
  public PublicKeyType genPublicK(PrivateKeyType privateK);
  
  public KemOutput enc(PublicKeyType pulicK);
  
  public SymmetricKeyType dec(PrivateKeyType privkey, EncryptedDataType c);

}
