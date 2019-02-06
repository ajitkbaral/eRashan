package np.com.ajitkbaral.erashan.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import np.com.ajitkbaral.erashan.R;

public class OrderPlacementDialog extends DialogFragment {


    private Button cancel, confirm;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_order_placement, null);
        builder.setView(view);


        final Dialog dialog = builder.create();

        cancel = view.findViewById(R.id.cancel);
        confirm = view.findViewById(R.id.confirm);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return dialog;
    }


    private void communicate() {
//        communicationListener.communicate("add", "Item added to the cart", defaultQuantity);
    }
}
