package com.example.smartclassroom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatDialogFragment;

public class Enter_Room_Controller extends AppCompatDialogFragment {
    private EditText room_number;
    private Button enter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view=  inflater.inflate(R.layout.enter_room, null);

        builder.setView(view).setTitle("Enter Room Number : ").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                room_number = view.findViewById(R.id.enter_room_roomText);
            }
        });

        room_number = view.findViewById(R.id.enter_room_roomText);
        enter = view.findViewById(R.id.enter_room_enterButton);

        return builder.create();
    }
}
