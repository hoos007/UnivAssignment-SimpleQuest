package org.techtown.termproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBControl {
    private SQLiteDatabase database;
    private String tableName;

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void insertRecord(todo data)
    {
        Integer id = null;
        Cursor cursor = database.rawQuery("select max(_id) from todoTable where date(date) = date(?)",new String[] {data.getDate().split(" ")[0]});
        cursor.moveToNext();
        id = cursor.getInt(0);
        if(id == null) id = 0;
        database.execSQL("insert into "+tableName
                + "(_id, title, content, date, important) " +
                "values ("+(id+1)+",'"+data.getTitle()+"','"+data.getContent()+"','"+data.getDate()+"',"+data.getImportant()+")");

    }

    public void updateRecord(todo data, String currDate)
    {
        Integer id = null;
        if(data.getDate().equals(currDate))
        {
            id = data.get_Id();
        }
        else
        {
            Cursor cursor = database.rawQuery("select max(_id) from todoTable where date(date) = date(?)",new String[] {data.getDate().split(" ")[0]});
            cursor.moveToNext();
            id = cursor.getInt(0);
            if(id == null) id = 0;
            id++;
        }
        database.execSQL("update "+tableName+" set _id = "+id+" ,title = '"+data.getTitle()+"', content = '"+data.getContent()+"', date = '"+data.getDate()+"', important = "+data.getImportant()+" where _id = "+data.get_Id()+" and date(date) = date('"+currDate.split(" ")[0]+"')");
    }

    public void deleteRecord(int id, String currDate)
    {
        database.execSQL("DELETE FROM "+tableName+" WHERE _id = " + id+" and date(date) = date('"+currDate.split(" ")[0]+"')");
    }

    public List<todo> selectAllRecord(String time) {
        List<todo> todoList = new ArrayList<todo>();

        Cursor cursor = database.rawQuery("select _id, title, content, strftime('%Y-%m-%d %H:%M', date), important from "+tableName+" where date(date) = date(?)", new String[] {time});
        int recordCount = cursor.getCount();
        if(recordCount > 0)
        {
            for (int i = 0; i< recordCount; i++) {
                cursor.moveToNext();
                todo data = new todo();
                data.set_Id(cursor.getInt(0));
                data.setTitle(cursor.getString(1));
                data.setContent(cursor.getString(2));
                data.setDate(cursor.getString(3));
                data.setImportant(cursor.getInt(4));

                todoList.add(data);
            }
        }
        else
        {
            todoList = null;
        }


        return todoList;
    }
}
