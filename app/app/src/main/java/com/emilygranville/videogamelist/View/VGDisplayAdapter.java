package com.emilygranville.videogamelist.View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class VGDisplayAdapter extends RecyclerView.Adapter<VGViewHolder>{

    List<VideoGame> videoGameList;
    FragmentDisplayVgViewBinding binding;
    IDisplayVGView.Listener listener;

    public VGDisplayAdapter(List<VideoGame> videoGameList, IDisplayVGView.Listener listener,
                            FragmentDisplayVgViewBinding binding) {
        this.videoGameList = videoGameList;
        this.listener = listener;
        this.binding = binding;
    }

    /**
     * Called when RecyclerView needs a new {@link VGViewHolder} of the given type to represent
     * an item.
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(VGViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(VGViewHolder, int)
     */
    @NonNull
    @Override
    public VGViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.videogame_card, parent, false);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "clicked item", Snackbar.LENGTH_LONG).show();
//            }
//        });

        return new VGViewHolder(itemView, this.binding, this.listener);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link VGViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link VGViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(VGViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VGViewHolder holder, int position) {
        holder.setVideoGame(videoGameList.get(holder.getAdapterPosition()));
//        holder.gameName.setText(videoGameList.get(position).getGameName());
//        holder.gameEditBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateGame(videoGameList.get(holder.getAdapterPosition()).getGameId(), holder.getAdapterPosition());
//            }
//        });
//        holder.gameDeleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                VGDisplayAdapter.this.listener.deleteGame(videoGameList.get(holder.getAdapterPosition()));
//                //deleteGame(holder.getAdapterPosition());
//            }
//        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return videoGameList.size();
    }

    /**
     * Updates an item in the list to reflect edits
     * @param position position
     */
    public void updateGame(int videoGameID, int position) {
        //videoGameList.set(position, videoGame);
        notifyItemChanged(position);
        Snackbar.make(this.binding.getRoot(), "Update", Snackbar.LENGTH_LONG).show();
    }
//
//    /**
//     * Updates an item in the list to reflect edits
//     * @param position position of game to delete
//     */
//    //: see if this can take in the position instead of searching for it
//    public void deleteGame(int position) {
////        videoGameList.remove(position);
////        notifyItemChanged(position);
//        Snackbar.make(this.binding.getRoot(), "Delete", Snackbar.LENGTH_LONG).show();
//    }
}
