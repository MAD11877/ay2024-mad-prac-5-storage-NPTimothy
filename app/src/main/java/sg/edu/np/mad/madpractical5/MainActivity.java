package sg.edu.np.mad.madpractical5;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        Intent receivingEnd = getIntent();
//        String number = receivingEnd.getStringExtra("random");
        String name = receivingEnd.getStringExtra("name");
        String description = receivingEnd.getStringExtra("description");
        int id = receivingEnd.getIntExtra("id",0);
        Log.d("ID", String.valueOf(id));
        Boolean followed = receivingEnd.getBooleanExtra("followed",false);
        Log.d("FOLLOWED", String.valueOf(followed));
//        tvName.setText("MAD" + number);

        // Initialise a new User object
//        User user = new User("John Doe", "MAD Developer", 1, false);
        User user = new User(name, description, id, followed);

        // Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);

        Button btnFollow = findViewById(R.id.btnFollow);
        tvName.setText(name);
        tvDescription.setText(description);

        if (followed) btnFollow.setText("Unfollow");
        else btnFollow.setText("Follow");
        btnFollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setFollowed(!user.getFollowed());

                Button btn = (Button)v;
                if (user.getFollowed()) {
                    btn.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                } else {
                    btn.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }

                DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this, null, null, 1);
                dbHandler.updateUser(user);






            }

        });

        Button btnMessage = findViewById(R.id.btnMessage);
        btnMessage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent activityName = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(activityName);
            }
        });



        // Set the TextViews with the User's name, description and default button message
        tvName.setText(user.getName());
        tvDescription.setText(user.getDescription());
//        btnFollow.setText("Follow");





    }

//    class btnFollowHandler implements Button.OnClickListener {
//        public void onClick(View v) {
//            Button btn = (Button)v;
//            if (btn.getText() == "Follow") {
//                btn.setText("Unfollow");
//            } else btn.setText("Follow");
//
//        }
//    }
}