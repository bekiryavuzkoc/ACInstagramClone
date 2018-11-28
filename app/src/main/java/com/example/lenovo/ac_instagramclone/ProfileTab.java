package com.example.lenovo.ac_instagramclone;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,
            edtProfileHobbies,edtProfileFavouriteSports;
    private Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab,
                container, false);
        edtProfileName =view.findViewById(R.id.edtProfileName);
        edtProfileBio =view.findViewById(R.id.edtProfileBio);
        edtProfileProfession =view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies =view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavouriteSports =view.findViewById(R.id.edtProfileFavouriteSports);

        btnUpdateInfo =view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("profileName") == null){
            edtProfileName.setText("");
        } else {
            edtProfileName.setText(parseUser.get("profileName").toString());
        }

        if( parseUser.get("profileBio") == null){
            edtProfileBio.setText("");
        }else {
            edtProfileBio.setText(parseUser.get("profileBio").toString());
        }
        if(parseUser.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }else {
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
        }
        if(parseUser.get("profileHobbies") == null){
            edtProfileHobbies.setText("");
        }else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies").toString());

        }
        if(parseUser.get("profileFavSport")== null){
            edtProfileFavouriteSports.setText("");
        }else {
            edtProfileFavouriteSports.setText(parseUser.get("profileFavSport").toString());
        }



        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("profileBio",edtProfileBio.getText().toString());
                parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies",edtProfileHobbies.getText().toString());
                parseUser.put("profileFavSport",edtProfileFavouriteSports.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Info is updating sir " + edtProfileName.getText().toString());
                progressDialog.show();

                parseUser.saveInBackground(new SaveCallback() {

                    @Override

                    public void done(ParseException e) {

                        if(e==null){

                            Toast.makeText(getContext(),"Info Updated!",Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });

        return view;
    }

}
