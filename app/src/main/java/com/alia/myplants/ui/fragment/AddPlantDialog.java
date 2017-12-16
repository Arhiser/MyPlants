package com.alia.myplants.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.alia.myplants.R;
import com.alia.myplants.model.Plant;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Alyona on 05.12.2017.
 */

public class AddPlantDialog extends DialogFragment {
    private static final String TAG = "AddPlantDialog";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    private ChangeRealmDataListener listener;
    private String currentPhotoPath;

    @BindView(R.id.add_done)
    ImageButton doneButton;

    @BindView(R.id.add_cancel)
    ImageButton cancelButton;

    @BindView(R.id.add_image_btn)
    ImageButton addImgButton;

    @BindView(R.id.small_image_view)
    ImageView smImageView;

    @BindView(R.id.input_name)
    EditText inputName;

    @BindView(R.id.input_water)
    Spinner waterSpinner;

    @BindView(R.id.input_fertilizer)
    Spinner fertSpinner;

    @BindView(R.id.input_description)
    EditText inputDescription;

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
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_plant, container, false);
        ButterKnife.bind(this, view);

        doneButton.setOnClickListener(v -> {
            String name = inputName.getText().toString();
            String notes = inputDescription.getText().toString();
            int waterDays = waterSpinner.getSelectedItemPosition() + 1;
            int fertDays = fertSpinner.getSelectedItemPosition() + 1;

            if (name.trim().equals("") && currentPhotoPath.trim().equals("")) {
                Snackbar.make(v, R.string.input_check, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return;
            }
            Plant plant = new Plant(name, currentPhotoPath, waterDays, fertDays, notes);
            listener.onAddPlant(plant);
            getDialog().dismiss();

        });
        cancelButton.setOnClickListener(v -> getDialog().dismiss());

        addImgButton.setOnClickListener(v -> fetchImageFromCamera());
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            addImgButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";

        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getResources().getString(R.string.app_name).replaceAll("\\s", ""));
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = new File(storageDir, imageFileName);
        currentPhotoPath = imageFileName;
        return image;
    }

    private void fetchImageFromCamera() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            requestStoragePermission();
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();

            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        getResources().getString(R.string.authority),
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void requestStoragePermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Snackbar.make(getView(), R.string.request_storage,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, view -> requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE)).show();

        } else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

}
