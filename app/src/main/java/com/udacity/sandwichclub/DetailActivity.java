package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;



public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;
    private ImageView SandwichImage;
    private TextView Origin,AlsoKnownAs,Description,Ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_detail);

         Origin=findViewById(R.id.origin_tv);
         AlsoKnownAs=findViewById(R.id.also_known_tv);
         Ingredients=findViewById(R.id.ingredients_tv);
         Description=findViewById(R.id.description_tv);
         SandwichImage = findViewById(R.id.image_iv);
         Intent intent = getIntent();
         if (intent == null) {
            closeOnError();
         }
         int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
         if (position == DEFAULT_POSITION) {
             closeOnError();
             return;
         }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
         sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            closeOnError();
            return;
         }
         populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        Picasso.with(this).load(sandwich.getImage()).placeholder(R.mipmap.noimage).into(SandwichImage);
        setTitle(sandwich.getMainName());
        Origin.setText(sandwich.getPlaceOfOrigin());
        AlsoKnownAs.setText( TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        Description.setText(sandwich.getDescription());
        Ingredients.setText(TextUtils.join(", ", sandwich.getIngredients()));

        }
}
