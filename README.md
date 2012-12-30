GeocoderLIB
===========

Android library for Google geocoding.
It supports multisearch and address selection dialog.

Just allow your Activity to implement GeocoderListener and generate all 
neccessary overloads:

public class AddressActivity extends Activity implements GeocoderListener {
  public void onAddressGotStatus(GeocodingStatus status) {
  }

  public void onAddressGotFailedStatus(final String message) {
	}

	public void onAddressGotResult(GeocoderResult result) {
	}

	public void onAddressGotResult(GeocoderItem result) {
	}

	public void onAddressGotResults(GeocoderResult arg0) {
	}

	public void onAddressZeroResult() {
	}

  public void onConnectionFailed() {
  }
}

To execute a search just do the following:
new ValidateAddress(getApplicationContext(), this, true).validate("Lenina Ave 4"); 
