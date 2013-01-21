package eu.marcoalbarelli.android.usbtest;

public enum TagMapping {
	
MIFARE_1K ((byte)0x01,"Mifare Standard 1K (as per PCSC std part3)"),
MIFARE_4K ((byte)0x02,"Mifare Standard 4K (as per PCSC std part3)"),
MIFARE_ULTRALIGHT((byte)0x03,"Mifare Ultralight (as per PCSC std part3)"),
SLE55R_XXXX ((byte)0x04,"SLE55R_XXXX (as per PCSC std part3)"),
SR176((byte)0x06,"SR176 (as per PCSC std part3)"),
SRI_X4K((byte)0x07,"SRI X4K (as per PCSC std part3)"),
AT88RF020 ((byte)0x08,"AT88RF020 (as per PCSC std part3)"),
AT88SC0204CRF((byte)0x09,"AT88SC0204CRF (as per PCSC std part3)"),
AT88SC0808CRF((byte)0x0A,"AT88SC0808CRF (as per PCSC std part3)"),
AT88SC1616CRF((byte)0x0B,"AT88SC1616CRF (as per PCSC std part3)"),
AT88SC3216CRF((byte)0x0C,"AT88SC3216CRF (as per PCSC std part3)"),
AT88SC6416CRF((byte)0x0D,"AT88SC6416CRF (as per PCSC std part3)"),
SRF55V10P((byte)0x0E,"SRF55V10P (as per PCSC std part3)"),
SRF55V02P((byte)0x0F,"SRF55V02P (as per PCSC std part3)"),
SRF55V10S((byte)0x10,"SRF55V10S (as per PCSC std part3)"),
SRF55V02S((byte)0x11,"SRF55V02S (as per PCSC std part3)"),
TAG_IT((byte)0x12,"TAG_IT (as per PCSC std part3)"),
LRI512((byte)0x13,"LRI512 (as per PCSC std part3)"),
ICODESLI ((byte)0x14,"ICODESLI (as per PCSC std part3)"),
TEMPSENS ((byte)0x15,"TEMPSENS (as per PCSC std part3)"),
I_CODE1((byte)0x16,"I.CODE1 (as per PCSC std part3)"),
PICOPASS_2K((byte)0x17,"PicoPass 2K (as per PCSC std part3)"),
PICOPASS_2KS((byte)0x18,"PicoPass 2KS (as per PCSC std part3)"),
PICOPASS_16K((byte)0x19,"PicoPass 16K (as per PCSC std part3)"),
PICOPASS_162KS((byte)0x1A,"PicoPass 16Ks (as per PCSC std part3)"),
PICOPASS_16K_8x2((byte)0x1B,"PicoPass 16K(8x2) (as per PCSC std part3)"),
PICOPASS_16KS_8x2((byte)0x1C,"PicoPass 16KS(8x2) (as per PCSC std part3)"),
PICOPASS_32KS_1616((byte)0x1D,"PicoPass 32KS(16+16) (as per PCSC std part3)"),
PICOPASS_32KS_16_8x2((byte)0x1E,"PicoPass 32KS(16+8x2) (as per PCSC std part3)"),
PICOPASS_32KS_8x2_16((byte)0x1F,"PicoPass 32KS(8x2+16) (as per PCSC std part3)"),
PICOPASS_32KS_8x2_8x2((byte)0x20,"PicoPass 32KS(8x2+8x2) (as per PCSC std part3)"),
LRI64 ((byte)0x21,"LRI64 (as per PCSC std part3)"),
I_CODE_UID((byte)0x22,"I.CODE UID (as per PCSC std part3)"),
I_CODE_EPC((byte)0x23,"I.CODE EPC (as per PCSC std part3)"),
LRI12((byte)0x24,"LRI12 (as per PCSC std part3)"),
LRI128((byte)0x25,"LRI128 (as per PCSC std part3)"),
MIFARE_MINI((byte)0x26,"Mifare Mini (as per PCSC std part3)"),
SLE_66R01P((byte)0x27,"my-d move (SLE 66R01P) (as per PCSC std part3)"),
SLE_66RxxP((byte)0x28,"my-d NFC (SLE 66RxxP) (as per PCSC std part3)"),
SLE_66RxxS((byte)0x29,"my-d proximity 2 (SLE 66RxxS) (as per PCSC std part3)"),
SLE_55RxxE((byte)0x2A,"my-d proximity enhanced (SLE 55RxxE) (as per PCSC std part3)"),
SRF_55V01P((byte)0x2B,"my-d light (SRF 55V01P) (as per PCSC std part3)"),
SRF_66V10ST((byte)0x2C,"PJM Stack Tag (SRF 66V10ST) (as per PCSC std part3)"),
SRF_66V10IT((byte)0x2D,"PJM Item Tag (SRF 66V10IT) (as per PCSC std part3)"),
SRF_66V01ST((byte)0x2E,"PJM Light (SRF 66V01ST) (as per PCSC std part3)"),
JEWEL_TAG((byte)0x2F,"Jewel Tag (as per PCSC std part3)"),
TOPAZ_NFC_TAG((byte)0x30,"Topaz NFC Tag (as per PCSC std part3)"),
AT88SC0104CRF((byte)0x31,"AT88SC0104CRF (as per PCSC std part3)"),
AT88SC0404CRF((byte)0x32,"AT88SC0404CRF (as per PCSC std part3)"),
AT88RF01C((byte)0x33,"AT88RF01C (as per PCSC std part3)"),
AT88RF04C((byte)0x34,"AT88RF04C (as per PCSC std part3)"),
I_CODE_SL2((byte)0x35,"i-Code SL2 (as per PCSC std part3");

	public byte specificByte;
	public String denomination;
	
	TagMapping(byte b,String d) {
		this.specificByte = b;
		this.denomination = d;
	}
	
	public static TagMapping findByIdentificativeByte(byte b){
		for(TagMapping output : TagMapping.values()){
			if(output.specificByte == b){
				return output;
			}
		}
		return null;
	}
	
}
