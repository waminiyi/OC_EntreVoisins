package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {


    @BindView(R.id.details_activity_textView_onPhoto)
    TextView nameOnPhotoTextView;
    @BindView(R.id.details_activity_favourite_button)
    FloatingActionButton addToFavouritesFAB;
    @BindView(R.id.details_activity_favourite_infos_cardview)
    CardView infosCardView;
    @BindView(R.id.details_activity_favourite_about_cardview)
    CardView aboutCardView;
    @BindView(R.id.details_activity_favourite_name_textview)
    TextView nameTextView;
    @BindView(R.id.details_activity_favourite_adress_textview)
    TextView adressTextView;
    @BindView(R.id.details_activity_favourite_phone_textview)
    TextView phoneTextView;
    @BindView(R.id.details_activity_favourite_link_textview)
    TextView linkTextView;
    @BindView(R.id.details_activity_favourite_about_textview)
    TextView aboutTextView;
    @BindView(R.id.details_activity_favourite_description_textview)
    TextView descriptionTextView;
    @BindView(R.id.details_activity_profile_image)
    ImageView profileImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);


    }

    public static void openDetailsActivity(FragmentActivity activity, Neighbour neighbour) {
        Intent intent = new Intent(activity, NeighbourDetailsActivity.class);

        intent.putExtra("id", neighbour.getId());
        intent.putExtra("name", neighbour.getName());
        intent.putExtra("avatarUrl", neighbour.getAvatarUrl());
        intent.putExtra("address", neighbour.getAddress());
        intent.putExtra("phoneNumber", neighbour.getPhoneNumber());
        intent.putExtra("aboutMe", neighbour.getAboutMe());

        ActivityCompat.startActivity(activity, intent, null);
    }
}