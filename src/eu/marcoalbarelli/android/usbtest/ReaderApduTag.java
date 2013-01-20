package eu.marcoalbarelli.android.usbtest;

import org.nfctools.api.ApduTag;
import org.nfctools.api.Tag;
import org.nfctools.api.TagType;
import org.nfctools.scio.Command;
import org.nfctools.scio.Response;
import org.nfctools.spi.acs.Apdu;
import org.nfctools.utils.NfcUtils;

import android.util.Log;

import com.acs.smartcard.Reader;
import com.acs.smartcard.ReaderException;

public class ReaderApduTag extends Tag implements ApduTag {

	private Reader reader;
	private int slotNum;

	public ReaderApduTag(TagType tagType, byte[] generalBytes, Reader reader, int slotNum) {
		super(tagType, generalBytes);
		this.reader = reader;
		this.slotNum = slotNum;
	}

	@Override
	public Response transmit(Command command) {
		try {
			byte[] responseData = new byte[32];
			byte[] commandData = convertCommand(command);
			Log.i("NFCTOOLS", "Sending: "+NfcUtils.convertBinToASCII(commandData));
			int responseLength = reader.transmit(slotNum, commandData, commandData.length, responseData, responseData.length);
			Log.i("NFCTOOLS", "Received: "+ responseLength+" | "+NfcUtils.convertBinToASCII(responseData));
			Response response = convertToResponse(responseData, responseLength);
			return response;
		}
		catch (ReaderException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Response convertToResponse(byte[] responseData, int length) {
		byte[] data = new byte[length-2];
		System.arraycopy(responseData, 0, data, 0, length-2);
		int sw1 = responseData[length-2] & 0xff;
		int sw2 = responseData[length-1] & 0xff;
		return new Response(sw1, sw2, data);
	}

	private byte[] convertCommand(Command command) {
		if (command.hasData()) {
			// TODO not sure if this conversion is correct
			byte[] buffer = new byte[command.getLength() + 4];
			buffer[0] = (byte)Apdu.CLS_PTS;
			buffer[1] = (byte)command.getInstruction();
			buffer[2] = (byte)command.getP1();
			buffer[3] = (byte)command.getP2();
			System.arraycopy(command.getData(), 0, buffer, 4, command.getLength());
			return buffer;
		}
		else {
			byte[] buffer = new byte[5];
			buffer[0] = (byte)Apdu.CLS_PTS;
			buffer[1] = (byte)command.getInstruction();
			buffer[2] = (byte)command.getP1();
			buffer[3] = (byte)command.getP2();
			buffer[4] = (byte)command.getLength();
			return buffer;
		}
	}
}
