package Sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper<T> extends SQLiteOpenHelper
{
    public static final String tableName1 = "container";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void createTable(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + tableName1 + "(name text)";
        try
        {
            db.execSQL(sql);
        }
        catch (SQLException e)
        {
        }
    }

    public void InsertItem(SQLiteDatabase db)//,String tableName, T insertModel)
    {
        db.beginTransaction();
        try{
            //String sql = "insert into " + tableName + "(name)" + " values('" + name + "')";
            String sql = "INSERT into Site (SiteName,Url,cssQeury) VALUES ('Sample Site','Sample URL','Sample cssQeury')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }

    }

    public void Select(SQLiteDatabase db, String name)
    {
        db.beginTransaction();
        try
        {
            String sql = "insert into " + tableName1 + "(name)" + " values('" + name + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }

    }

    public void UpdateItem(SQLiteDatabase db, String name)
    {
        db.beginTransaction();
        try
        {
            String sql = "insert into " + tableName1 + "(name)" + " values('" + name + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }

    }

    public void DeleteItem(SQLiteDatabase db, String name)
    {
        db.beginTransaction();
        try
        {
            String sql = "insert into " + tableName1 + "(name)" + " values('" + name + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }

    }
}
