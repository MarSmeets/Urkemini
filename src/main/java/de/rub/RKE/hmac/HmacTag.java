package de.rub.RKE.hmac;

import java.util.Arrays;

import de.rub.RKE.macinterfaces.*;

public class HmacTag implements MacTag{
   byte[] tag;
   
   public HmacTag(byte[] tag) {
	   this.tag = Arrays.copyOf(tag, tag.length);
   }
   
   public void setTag(byte[] tag){
	 this.tag = Arrays.copyOf(tag, tag.length);
   }
   
   public byte[] getTag() {
	   return tag;
   }
   
   public boolean isEqual(MacTag tag) {
	 if(Arrays.equals(this.tag ,tag.getTag()))
		 return true;
		 else return false;
   }
}
