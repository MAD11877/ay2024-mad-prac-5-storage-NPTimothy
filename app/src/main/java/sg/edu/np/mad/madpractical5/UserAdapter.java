package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> data;
    private ListActivity activity;

    public UserAdapter(ArrayList<User> listObjects, ListActivity listActivity) {
        data = listObjects;
        activity = listActivity;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_activity_list,
                parent,
                false);
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User u = data.get(position);
        holder.name.setText(u.getName());
        holder.description.setText(u.getDescription());
//        holder.smallImage.setImageResource(android.R.drawable.sym_def_app_icon);
        holder.smallImage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);


                builder.setTitle("Profile");
                builder.setMessage("MADness");
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler dbHandler = new DatabaseHandler(activity, null, null, 1);

                        Intent activityName = new Intent(activity, MainActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("name", u.getName());
                        extras.putString("description", u.getDescription());
                        extras.putInt("id", u.getId());
                        extras.putBoolean("followed", dbHandler.findUser(u.getId()).getFollowed());
//                        Log.d("FOLLOWED ADAPTER", String.valueOf(dbHandler.findUser(u.getId()).getFollowed()));

//                        activityName.putExtra("random", Integer.toString(randomInteger));
                        activityName.putExtras(extras);
                        startActivity(activity, activityName, extras);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        if (!u.getName().endsWith("7")) holder.largeImage.setVisibility(View.GONE);
    }

    public int getItemCount() {
        return data.size();
    }
}
