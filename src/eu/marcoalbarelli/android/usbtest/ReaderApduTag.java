package eu.marcoalbarelli.android.usbtest;

import org.nfctools.api.ApduTag;
import org.nfctools.api.Tag;
import org.nfctools.api.TagType;
import org.nfctools.scio.Command;
import org.nfctools.scio.Response;

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
			// TODO convert the command data into a real commandData array
			byte[] responseData = new byte[1024];
			byte[] commandData = command.getData();
			reader.transmit(slotNum, commandData, commandData.length, responseData, responseData.length);
			// TODO convert the response data array into our response data
			Response response = new Response(0, 0, responseData);
			return response;
		}
		catch (ReaderException e) {
			throw new RuntimeException(e);
		}
	}
}
