package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    ImageView smallImage;
    ImageView largeImage;
    public UserViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        smallImage = itemView.findViewById(R.id.smallImage);
        largeImage = itemView.findViewById(R.id.largeImage);
    }
}
