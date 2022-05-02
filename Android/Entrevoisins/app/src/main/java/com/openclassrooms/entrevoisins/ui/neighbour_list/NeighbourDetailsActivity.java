package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_activity_profile_image)
    ImageView profileImageView;
    @BindView(R.id.details_activity_textView_onPhoto)
    TextInputLayout nameOnPhotoTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}