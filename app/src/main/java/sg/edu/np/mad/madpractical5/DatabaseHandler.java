package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FOLLOWED = "followed";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " BOOLEAN"
                + ")";


        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "name"+random.nextInt(999999999));
            values.put(COLUMN_DESCRIPTION, "description "+random.nextInt(999999999));
            values.put(COLUMN_FOLLOWED, random.nextBoolean());

            db.insert(TABLE_USERS, null, values);

//            usersList.add(new User("name"+random.nextInt(999999999),"description "+random.nextInt(999999999),i+1,random.nextBoolean()));
        }




//        db.close();

    }

    public ArrayList<User> getUsers() {
        String query = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        ArrayList<User> users = new ArrayList<>();



        User user = new User();

        if(cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));

            users.add(user);

            while(cursor.moveToNext()) {
                user = new User();

                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setDescription(cursor.getString(2));
                user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));

                users.add(user);

            }
        }

        cursor.close();

//        db.close();

        return users;

    }

    public void updateUser(User user) {
//        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
//                + COLUMN_ID + " = \"" + user.getId() + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query,null);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        db.update(TABLE_USERS,values,COLUMN_ID+"="+user.getId(),null);

//        return user;
    };

    public User findUser(int id) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_ID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();

        if(cursor.moveToFirst()) {
            user.setId(id);
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            if (cursor.getString(3).equals("1")) user.setFollowed(true);
            else user.setFollowed(false);
        } else return null;

        return user;

    }


}
