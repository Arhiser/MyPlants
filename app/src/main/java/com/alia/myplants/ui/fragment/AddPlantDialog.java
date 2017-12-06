package com.alia.myplants.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.alia.myplants.R;
import com.alia.myplants.model.Plant;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Alyona on 05.12.2017.
 */

public class AddPlantDialog extends DialogFragment {
    private static final String TAG = "AddPlantDialog";
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

    private ChangeRealmDataListener listener;

    public interface ChangeRealmDataListener {
        void onAddPlant(Plant plant);
    }

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
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (ChangeRealmDataListener) getActivity();
        } catch (final ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement listener");
        }
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

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mInputName.getText().toString();
                int waterDays = mWaterSpinner.getSelectedItemPosition() + 1;
                Plant plant = new Plant(name, null, waterDays, 0, null);
                listener.onAddPlant(plant);
                getDialog().dismiss();

            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
