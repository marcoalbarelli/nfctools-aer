package eu.marcoalbarelli.android.usbtest;

import java.util.List;

import org.nfctools.ndef.NdefOperations;
import org.nfctools.ndef.NdefOperationsListener;
import org.nfctools.ndef.Record;
import org.nfctools.ndef.wkt.records.UriRecord;

import android.util.Log;

public class LoggingNdefOperationsListener implements NdefOperationsListener {

	private static final String NFCTOOLS = "NFCTOOLS";

	@Override
	public void onNdefOperations(NdefOperations ndefOperations) {
		if (ndefOperations.isFormatted()) {
			if (ndefOperations.hasNdefMessage()) {
				List<Record> messages = ndefOperations.readNdefMessage();
				Log.i(NFCTOOLS, "Found "+messages.size()+" NDEF records");
				for (Record record : messages) {
					Log.i(NFCTOOLS, "NDEF: " + record);
				}
				
				UriRecord uriRecord = new UriRecord("http://www.grundid.de/nfc");
				ndefOperations.writeNdefMessage(uriRecord);
				
			}
			else {
				Log.i(NFCTOOLS, "no messages on TAG");
				UriRecord uriRecord = new UriRecord("http://www.grundid.de/nfc");
				ndefOperations.writeNdefMessage(uriRecord);
			}
		}
		else
			Log.i(NFCTOOLS, "not formatted");
	}
}
