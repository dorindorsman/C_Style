package com.dorin.c_style.Fregments;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.User;
import com.dorin.c_style.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {


    private CircleImageView Profile_IMG_User;
    private MaterialTextView Profile_LBL_UserName;
    private MaterialTextView Profile_LBL_UserFirstName;
    private MaterialTextView Profile_LBL_UserLastName;
    private MaterialTextView Profile_LBL_UserPhoneNumber;
    private MaterialButton Profile_BTN_EditProfile;

    private AppCompatActivity activity;
    private UserDataManager userDataManager;
    private FirebaseAuth myAuth;

    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile, container, false);

        findViews(view);
        initButtons();
        userDataManager = UserDataManager.getInstance();
        loadData();

        return view;

    }

    private void initButtons() {
        Profile_BTN_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replace activity ?
            }
        });
    }

    private void loadData() {
        User user = userDataManager.getMyUser();
        Glide.with(this).load(user.getUserPic()).into(Profile_IMG_User);
        Profile_LBL_UserName.setText(user.getUserFirstName()+" "+user.getUserLastName());
        Profile_LBL_UserFirstName.setText(user.getUserFirstName());
        Profile_LBL_UserLastName.setText(user.getUserLastName());
        Profile_LBL_UserPhoneNumber.setText(user.getUserPhoneNumber());
    }

    private void findViews(View view) {

        Profile_IMG_User=view.findViewById(R.id.Profile_IMG_User);
        Profile_LBL_UserName=view.findViewById(R.id.Profile_LBL_UserName);
        Profile_LBL_UserFirstName=view.findViewById(R.id.Profile_LBL_UserFirstName);
        Profile_LBL_UserLastName=view.findViewById(R.id.Profile_LBL_UserLastName);
        Profile_LBL_UserPhoneNumber=view.findViewById(R.id.Profile_LBL_UserPhoneNumber);
        Profile_BTN_EditProfile=view.findViewById(R.id.Profile_BTN_EditProfile);
    }
}