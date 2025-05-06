package com.emilygranville.videogamelist.View;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
    private final TextView consolesDisplay;

    /**
     * Constructor for VGViewHolder
     * @param itemView the item to display
     * @param listener the listener for changes to the item
     */
    public VGViewHolder(@NonNull View itemView, IDisplayVGView.Listener listener) {
        super(itemView);

        this.listener = listener;

        Button gameEditBtn = itemView.findViewById(R.id.vg_edit_btn);
        Button gameDeleteBtn = itemView.findViewById(R.id.vg_delete_btn);

        this.gameName = itemView.findViewById(R.id.videogame_name);
        this.consolesDisplay = itemView.findViewById(R.id.consoles_txt);

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vis = VGViewHolder.this.consolesDisplay.getVisibility();
                if (vis == GONE) {
                    VGViewHolder.this.consolesDisplay.setVisibility(VISIBLE);
                } else {
                    VGViewHolder.this.consolesDisplay.setVisibility(GONE);
                }
            }
        });
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

    /**
     * Sets the values to the VGViewHolder
     * @param videoGame the game to display
     * @param curConsole the current console to display
     */
    public void setValues(VideoGame videoGame, String curConsole) {
        this.videoGame = videoGame;
        this.gameName.setText(videoGame.getGameName());
        this.curConsole = curConsole;
        String consoleText = "Consoles:\n";
        for(String consoles : videoGame.getConsoles()) {
            consoleText += "\u2022 Bullet " + consoles + "\n";
        }
        consoleText = consoleText.substring(0, consoleText.length()-1);
        this.consolesDisplay.setText(consoleText);
    }
}
