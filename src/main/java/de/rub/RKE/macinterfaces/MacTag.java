package de.rub.RKE.macinterfaces;



public interface MacTag {

	public void setTag(byte[] tag);
	public byte[] getTag();
	public boolean isEqual(MacTag tag);
}
