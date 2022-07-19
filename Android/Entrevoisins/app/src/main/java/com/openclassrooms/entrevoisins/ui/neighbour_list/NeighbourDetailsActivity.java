package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

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

    @BindView(R.id.details_toolbar)
    Toolbar mToolbar;


    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);


        //ajout de la flêche retour
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mApiService = DI.getNeighbourApiService();

        Intent infosIntent = getIntent();
        mNeighbour = infosIntent.getParcelableExtra("neighbour");
        if (mNeighbour != null) {

            updateDetails();
        }
        updateFabColor();

        addToFavouritesFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mApiService.getFavoritesNeighbours().contains(mNeighbour)){
                    mApiService.addToFavorites(mNeighbour);
                }else {
                    mApiService.deleteFromFavorites(mNeighbour);
                }
                updateFabColor();
            }
        });

    }

    private void updateFabColor(){
        if (mApiService.getFavoritesNeighbours().contains(mNeighbour)){
            addToFavouritesFAB.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.favorite)));
        }else{
            addToFavouritesFAB.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.not_favorite)));
        }
    }

    //méthode qui détruit l'activity et retourne sur la liste lorqu'on appuie le bouton retour
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateDetails() {

        nameOnPhotoTextView.setText(mNeighbour.getName());
        nameTextView.setText(mNeighbour.getName());
        adressTextView.setText(mNeighbour.getAddress());
        phoneTextView.setText(mNeighbour.getPhoneNumber());
        descriptionTextView.setText(mNeighbour.getAboutMe());
        aboutTextView.setText("A propos de moi");

        Picasso.get()
                .load(mNeighbour.getAvatarUrl())
                .into(profileImageView);
    }

    public static void openDetailsActivity(FragmentActivity activity, Neighbour neighbour) {
        Intent intent = new Intent(activity, NeighbourDetailsActivity.class);
        intent.putExtra("neighbour", neighbour);

        ActivityCompat.startActivity(activity, intent, null);
    }

    public Neighbour getNeighbour() {
        return mNeighbour;
    }
}