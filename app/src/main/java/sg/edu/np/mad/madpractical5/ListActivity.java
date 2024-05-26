package sg.edu.np.mad.madpractical5;


import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });





        Random random = new Random();

//        addUsers();
        ArrayList<User> usersList = InitialiseDB();
        Log.d("INIT","TRUE");
//        for (int i = 0; i < 20; i++) {
//            usersList.add(new User("name"+random.nextInt(999999999),"description "+random.nextInt(999999999),i+1,random.nextBoolean()));
//        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter userAdapter = new UserAdapter(usersList, ListActivity.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

//
//        ImageView image = findViewById(R.id.image);
//        image.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
//
//
//                builder.setTitle("Profile");
//                builder.setMessage("MADness");
//                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        Random random = new Random();
//                        int randomInteger = random.nextInt(999999);
//
//                        Intent activityName = new Intent(ListActivity.this, MainActivity.class);
//                        activityName.putExtra("random", Integer.toString(randomInteger));
//                        startActivity(activityName);
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        return;
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//
//            }
//        });






    }

    public ArrayList<User> InitialiseDB() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
//        dbHandler.addUsers();
        return dbHandler.getUsers();
    }


}