package com.emilygranville.videogamelist.View;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.R;

public class VGViewHolder extends RecyclerView.ViewHolder {

    private final IDisplayVGView.Listener listener;

    private VideoGame videoGame;
    private String curConsole;

    private final TextView gameName;

    public VGViewHolder(@NonNull View itemView, IDisplayVGView.Listener listener) {
        super(itemView);

        this.listener = listener;

        Button gameEditBtn = itemView.findViewById(R.id.vg_edit_btn);
        Button gameDeleteBtn = itemView.findViewById(R.id.vg_delete_btn);

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
                VGViewHolder.this.listener.deleteGame(VGViewHolder.this.videoGame, VGViewHolder.this.curConsole);
            }
        });
    }

    public void setValues(VideoGame videoGame, String curConsole) {
        this.videoGame = videoGame;
        gameName.setText(videoGame.getGameName());
        this.curConsole = curConsole;
    }
}
