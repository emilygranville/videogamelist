package com.emilygranville.videogamelist.View;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;
import com.google.android.material.snackbar.Snackbar;

public class VGViewHolder extends RecyclerView.ViewHolder {

    private FragmentDisplayVgViewBinding binding;
    private IDisplayVGView.Listener listener;

    private VideoGame videoGame;

    private TextView gameName;
    private Button gameEditBtn;
    private Button gameDeleteBtn;

    public VGViewHolder(@NonNull View itemView, FragmentDisplayVgViewBinding binding, IDisplayVGView.Listener listener) {
        super(itemView);

        this.binding = binding;
        this.listener = listener;

        gameEditBtn = itemView.findViewById(R.id.vg_edit_btn);
        gameDeleteBtn = itemView.findViewById(R.id.vg_delete_btn);

        gameName = itemView.findViewById(R.id.videogame_name);

        gameEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VGViewHolder.this.listener.editGame(VGViewHolder.this.videoGame);
            }
        });
        gameDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VGViewHolder.this.listener.deleteGame(VGViewHolder.this.videoGame);
            }
        });
    }

    public void setVideoGame(VideoGame videoGame) {
        this.videoGame = videoGame;
        gameName.setText(videoGame.getGameName());
    }
}
