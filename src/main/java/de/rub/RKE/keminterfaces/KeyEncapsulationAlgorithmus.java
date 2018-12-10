package de.rub.RKE.keminterfaces;


//Generic Interface for the KEM
//All used DataTypes can be chosen, when implementing the KEM
public interface KeyEncapsulationAlgorithmus<KeypairType, PublicKeyType, PrivateKeyType ,
                                             SymmetricKeyType, EncryptedDataType>{
  
  public KeypairType gen();
  
  public PublicKeyType genPublicK(PrivateKeyType privateK);
  
  public KemOutput enc(int keyLen, PublicKeyType pulicK);
  
  public SymmetricKeyType dec(EncryptedDataType c, int keyLen, PrivateKeyType privkey);

}
