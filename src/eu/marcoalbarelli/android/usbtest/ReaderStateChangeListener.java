package eu.marcoalbarelli.android.usbtest;

import org.nfctools.api.NfcTagListener;
import org.nfctools.api.TagType;
import org.nfctools.mf.classic.MfClassicNfcTagListener;
import org.nfctools.mf.ul.Type2NfcTagListener;
import org.nfctools.ndef.NdefOperationsListener;

import android.util.Log;

import com.acs.smartcard.Reader;
import com.acs.smartcard.Reader.OnStateChangeListener;
import com.acs.smartcard.ReaderException;

public class ReaderStateChangeListener implements OnStateChangeListener {

	private Reader mReader;
	private NdefOperationsListener ndefOperationsListener;
	
	private static final int tagIdentifierBytePositionInATR = 14; //not really convinced about this variable name
	private static final String TAG = ReaderStateChangeListener.class.getSimpleName();

	public ReaderStateChangeListener(Reader mReader, NdefOperationsListener ndefOperationsListener) {
		this.mReader = mReader;
		this.ndefOperationsListener = ndefOperationsListener;
	}

	@Override
	public void onStateChange(int slotNum, int prevState, int currState) {
		if (prevState < Reader.CARD_UNKNOWN || prevState > Reader.CARD_SPECIFIC) {
			prevState = Reader.CARD_UNKNOWN;
		}
		if (currState < Reader.CARD_UNKNOWN || currState > Reader.CARD_SPECIFIC) {
			currState = Reader.CARD_UNKNOWN;
		}
		if (currState == Reader.CARD_PRESENT) {
			try {
				mReader.power(slotNum, Reader.CARD_WARM_RESET);
				mReader.setProtocol(slotNum, Reader.PROTOCOL_T0 | Reader.PROTOCOL_T1);
				
				// TODO identify the tag using the attributes
				//	byte[] atr = mReader.getAtr(slotNum);
				// for now let's assume it is a ultralight tag
				
				byte[] atr = mReader.getAtr(slotNum);
				
				TagType tagType = null;
				NfcTagListener tagListener = null;
				ReaderApduTag apduTag = null;
				TagMapping thisTag = TagMapping.findByIdentificativeByte(atr[tagIdentifierBytePositionInATR]);
				switch(thisTag){
				case MIFARE_1K:
					 tagType = TagType.MIFARE_CLASSIC_1K;
					 apduTag = new ReaderApduTag(tagType, null, mReader, slotNum);
					 tagListener = new MfClassicNfcTagListener(ndefOperationsListener);					 
					break;
				case MIFARE_4K:
					tagType = TagType.MIFARE_CLASSIC_4K;
					apduTag = new ReaderApduTag(tagType, null, mReader, slotNum);
					tagListener = new MfClassicNfcTagListener(ndefOperationsListener);					 
					break;
				case MIFARE_ULTRALIGHT:
					tagType = TagType.MIFARE_ULTRALIGHT;
					apduTag = new ReaderApduTag(tagType, null, mReader, slotNum);
					tagListener = new Type2NfcTagListener(ndefOperationsListener);
					break;
				default:
					Log.d(TAG,"unhandled tag (for now)");
					break;
				}
				tagListener.handleTag(apduTag);
				
				
			}
			catch (ReaderException e) {
				//basically a IllegalState Exception in case we forgot 
				//to properly set-up the reader
				e.printStackTrace();
			}
		}
	}
}
