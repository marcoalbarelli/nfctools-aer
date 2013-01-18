package eu.marcoalbarelli.android.usbtest;

import org.nfctools.api.TagType;
import org.nfctools.mf.ul.Type2NfcTagListener;
import org.nfctools.ndef.NdefOperationsListener;

import com.acs.smartcard.Reader;
import com.acs.smartcard.Reader.OnStateChangeListener;
import com.acs.smartcard.ReaderException;

public class ReaderStateChangeListener implements OnStateChangeListener {

	private Reader mReader;
	private NdefOperationsListener ndefOperationsListener;

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
				ReaderApduTag apduTag = new ReaderApduTag(TagType.MIFARE_ULTRALIGHT, null, mReader, slotNum);
				Type2NfcTagListener nfcTagListener = new Type2NfcTagListener(ndefOperationsListener);
				nfcTagListener.handleTag(apduTag);
			}
			catch (ReaderException e) {
				//basically a IllegalState Exception in case we forgot 
				//to properly set-up the reader
				e.printStackTrace();
			}
		}
	}
}
