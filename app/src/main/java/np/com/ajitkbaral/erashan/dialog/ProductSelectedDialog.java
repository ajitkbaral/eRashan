package np.com.ajitkbaral.erashan.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.listener.CommunicationListener;

public class ProductSelectedDialog extends DialogFragment {

    private Button cancel, minus, plus, addToCart;
    private int defaultQuantity = 1, minimumQuantity = 1, maxQuantity = 15;
    private TextView quantityText;
    private CommunicationListener communicationListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity) {
            activity = (Activity) context;
            communicationListener = (CommunicationListener) activity;
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_prodcut_selected, null);
        builder.setView(view);


        final Dialog dialog = builder.create();


        quantityText = view.findViewById(R.id.quantity);
        minus = view.findViewById(R.id.minus);
        plus = view.findViewById(R.id.plus);
        addToCart = view.findViewById(R.id.add_to_cart);

        quantityText.setText(String.valueOf(defaultQuantity));

        changeQuantity();

        cancel = view.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                communicate();
            }
        });


        return dialog;
    }

    private void changeQuantity() {

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(quantityText.getText().toString());

                if (quantity <= minimumQuantity) {
                    quantity = defaultQuantity;

                } else {
                    quantity = --quantity;
                }

                quantityText.setText(String.valueOf(quantity));

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(quantityText.getText().toString());

                if (quantity < maxQuantity) {
                    quantity = ++quantity;
                }
                quantityText.setText(String.valueOf(quantity));
            }
        });
    }

    private void communicate() {
//        communicationListener.communicate("add", "Item added to the cart", defaultQuantity);
    }
}
