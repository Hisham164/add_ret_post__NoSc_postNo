package com.example.publish_posts_firebase.adapters;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.publish_posts_firebase.R;
import com.example.publish_posts_firebase.models.Modelpost;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

public class Adapterpost extends RecyclerView.Adapter<Adapterpost.Myholder> {


    Context context;
    List<Modelpost> postlist;

    public Adapterpost(Context context, List<Modelpost> postlist) {
        this.context = context;
        this.postlist = postlist;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, parent, false);


        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder myholder, int position) {

        String uid = postlist.get(position).getUid();
        String uEmail = postlist.get(position).getuEmail();
        String uName = postlist.get(position).getuName();
        String uDp = postlist.get(position).getuDp();
        String pid = postlist.get(position).getpId();
        String ptitle = postlist.get(position).getpTitle();
        String pDescription = postlist.get(position).getpDescr();
        String pImage = postlist.get(position).getpImage();
        String pTimestamp = postlist.get(position).getpTime();

        //convert timestamp to dd/mm/yyyy : hh:mm aa/pp
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimestamp));
        String pTime = DateFormat.format("dd/mm/yyyy hh:mm aa", calendar).toString();
        //set data
        myholder.uNametv.setText(uName);
        myholder.pTimetv.setText(pTime);
        myholder.pTitle.setText(ptitle);
        myholder.pdescription.setText(pDescription);


        //set user dp
        try {
            Picasso.get().load(uDp).placeholder(R.drawable.ic_tag_faces_black).into(myholder.uPicuterIv);
        } catch (Exception e) {

        }

        if (pImage.equals("noImage")) {
            myholder.pimageiv.setVisibility(View.GONE);
        } else {

            try {

                Picasso.get().load(pImage).into(myholder.pimageiv);

            } catch (Exception e) {

            }

        }


        myholder.starfavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "add to favorit", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }


    //view holder class
    public class Myholder extends RecyclerView.ViewHolder {

        //view from row_post
        ImageView uPicuterIv, pimageiv;
        TextView uNametv, pTimetv, pTitle, pdescription, pliketv;
        ImageButton starfavorit;


        public Myholder(@NonNull View itemView) {
            super(itemView);

            uPicuterIv = itemView.findViewById(R.id.upictureiv);
            pimageiv = itemView.findViewById(R.id.pImageIv);
            uNametv = itemView.findViewById(R.id.unametv);
            pTimetv = itemView.findViewById(R.id.utimetv);
            pTitle = itemView.findViewById(R.id.ptitletv);
            pdescription = itemView.findViewById(R.id.pDescriptionEt);
            pliketv = itemView.findViewById(R.id.pliketv);
            starfavorit = itemView.findViewById(R.id.starfavorit);


        }
    }
}
