package com.irzaevrustam.geocoderlib;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AddressDialog extends Dialog {
	public interface AddressDialogListener {
		public void onAddressSelected(GeocoderItem address);
		public void onAddressFailed(String message);
		public void onAddressCanceled();
	}

    private final Button mPrevButton;
	private final Button mNextButton;
	private final Button mSelectButton;
	private final TextView mAddressTextView;
	private GeocoderResult mItems = new GeocoderResult();
	private int mCurrentSelectedId = -1;
	private final AddressDialogListener mListener;

	public AddressDialog(Context context, GeocoderResult list, AddressDialogListener listener) {
		super(context, R.style.ThemeDialogTranslucent);
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		getWindow().getAttributes().y = Math.max(display.getWidth(), display.getHeight());

		mItems = list;
		mListener = listener;
		mCurrentSelectedId = 0;

        View mView = getLayoutInflater().inflate(R.layout.address_selector, null);
		mPrevButton = (Button) mView.findViewById(R.id.prev_button);
		mNextButton = (Button) mView.findViewById(R.id.next_button);
		mSelectButton = (Button) mView.findViewById(R.id.select_button);
		mAddressTextView = (TextView) mView.findViewById(R.id.current_address_text_view);

		controlButton();

		if (!changeAddress()) {
			return;
		}

		mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				-- mCurrentSelectedId;
				controlButton();
				changeAddress();
			}
		});

		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				++ mCurrentSelectedId;
				controlButton();
				changeAddress();
			}
		});

		mSelectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onAddressSelected(mItems.getItem(mCurrentSelectedId));
				dismiss();
			}
		});

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(mView);
		setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				mListener.onAddressCanceled();
			}
		});
		show();
	}

	private boolean changeAddress() {
		if (mCurrentSelectedId != -1) {
			mAddressTextView.setText(mItems.getItem(mCurrentSelectedId).getFormattedAddress());
		} else {
			mListener.onAddressFailed("Oops. We had crashed.");
			return false;
		}

		return true;
	}

	private void controlButton() {
		if (mItems.size() == 1) {
			mPrevButton.setEnabled(false);
			mNextButton.setEnabled(false);
			return;
		}
		
		if (mCurrentSelectedId == 0) {
			mPrevButton.setEnabled(false);
			mNextButton.setEnabled(true);
		} else if (mCurrentSelectedId == mItems.size() - 1) {
			mNextButton.setEnabled(false);
			mPrevButton.setEnabled(true);
		} else {
			mPrevButton.setEnabled(true);
			mNextButton.setEnabled(true);
		}
	}
}