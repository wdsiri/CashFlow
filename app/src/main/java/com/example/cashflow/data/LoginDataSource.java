package com.example.cashflow.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cashflow.data.model.LoggedInUser;
import com.example.cashflow.data.model.Login;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private SQLiteDatabase database;
    private LoginDBOpenHelper loginHelper;
    private String[] allColumns = {
            LoginDBOpenHelper.COLUMN_ID,
            LoginDBOpenHelper.COLUMN_NAME,
            LoginDBOpenHelper.COLUMN_EMAIL,
            LoginDBOpenHelper.COLUMN_PASSWORD };

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public Login createLogin(int id, String name, String email, String password) {
        ContentValues values = new ContentValues();
        values.put(LoginDBOpenHelper.COLUMN_ID, id);
        long insertID = database.insert(LoginDBOpenHelper.TABLE_LOGINS,null,values);
        Cursor cursor = database.query(LoginDBOpenHelper.TABLE_LOGINS,allColumns,LoginDBOpenHelper.COLUMN_ID + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();
        Login newLogin = cursorToLogin(cursor);
        cursor.close();

        return newLogin;
    }

    public void deleteLogin(Login login) {
        long id = login.getId();
        database.delete(LoginDBOpenHelper.TABLE_LOGINS,LoginDBOpenHelper.COLUMN_ID + " = " + id, null);
    }

    private Login cursorToLogin(Cursor cursor) {
        Login login = new Login();
        login.setId(cursor.getLong(0));
        login.setLogin(cursor.getString(1));

        return login;
    }

    public void open() throws SQLException {
        database = loginHelper.getWritableDatabase();

    }

    public  SQLiteDatabase getDatabase() {
        return database;
    }

    public void close(){
        loginHelper.close();
    }
}