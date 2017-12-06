package com.alia.myplants.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.alia.myplants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alyona on 05.12.2017.
 */

public class AddPlantDialog extends DialogFragment {
    @BindView(R.id.add_done)
    ImageButton mDoneButton;

    @BindView(R.id.add_cancel)
    ImageButton mCancelButton;

    @BindView(R.id.input_name)
    EditText mInputName;

    @BindView(R.id.water_days)
    Spinner mWaterSpinner;

    @BindView(R.id.fertilizer_days)
    Spinner mFertSpinner;

    @BindView(R.id.input_notes)
    EditText mInputNotes;

    public AddPlantDialog() {
    }

    public static AddPlantDialog newInstance() {
        Bundle args = new Bundle();
        //  args.putSerializable(ARG_NAME, name);
        //  args.putSerializable(ARG_TIME, time);
        AddPlantDialog dialog = new AddPlantDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialogTheme);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_new_plant, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
