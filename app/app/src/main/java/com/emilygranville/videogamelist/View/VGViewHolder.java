package com.emilygranville.videogamelist.View;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;
import com.google.android.material.snackbar.Snackbar;

public class VGViewHolder extends RecyclerView.ViewHolder {

    FragmentDisplayVgViewBinding binding;

    TextView gameName;
    Button gameEditBtn;
    Button gameDeleteBtn;

    public VGViewHolder(@NonNull View itemView, FragmentDisplayVgViewBinding binding) {
        super(itemView);

        //this.binding = binding;

        gameEditBtn = itemView.findViewById(R.id.vg_edit_btn);
        gameDeleteBtn = itemView.findViewById(R.id.vg_delete_btn);

        gameName = itemView.findViewById(R.id.videogame_name);

//        gameEditBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Click", Snackbar.LENGTH_LONG).show();
//            }
//        });
//        gameDeleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Click", Snackbar.LENGTH_LONG).show();
//            }
//        });
    }

}
