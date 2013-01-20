package eu.marcoalbarelli.android.usbtest;

import java.util.Iterator;
import java.util.List;

import org.nfctools.NfcException;
import org.nfctools.ndef.NdefOperations;
import org.nfctools.ndef.NdefOperationsListener;
import org.nfctools.ndef.Record;

import android.util.Log;

public class NdefReader implements NdefOperationsListener {

	private final static String TAG = NdefReader.class.getSimpleName();
	
	
	private static final int START_ID = 1;
	private static int counter = START_ID;
	
	
	@Override
	public void onNdefOperations(NdefOperations ndefOperations) {
		try{
		if (ndefOperations.isWritable()) {
			//System.out.println("Writing NDEF data...");
			List<Record> records = ndefOperations.readNdefMessage();
			Iterator<Record> iterator = records.iterator();
			while(iterator.hasNext()){
				Record record = iterator.next();
				Log.d(TAG,record.toString());
			}
		} else {
			Log.e(TAG,"Tag not readable, maybe not a Ultralight?");
		}
//			int nextId = getNextId();
//			UriRecord record = new UriRecord("schema://domain/value/"+nextId);
//			if (ndefOperations.isFormatted()){
//				ndefOperations.writeNdefMessage(record);
//			    ndefOperations.makeReadOnly();
//			}else{
//				ndefOperations.format(record);
//			}
//			System.out.println("Done tag "+record.getUri());
//		}else{
//			System.out.println("Tag not writable");
//		}
			
		}catch(NfcException e){
			e.printStackTrace();
			System.out.println("Troppo veloce, ripassalo più lentamente");
			rollbackId();
		}
	}
	
	private int getNextId(){
		return counter++;
	}
	
	private void rollbackId(){
		if(counter>START_ID){
			counter--;
		} else {
			counter=START_ID;
		}
	}

}
