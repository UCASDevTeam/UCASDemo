package com.ivanchou.ucasdemo.core.model;

import android.content.Context;
import android.database.Cursor;

import com.ivanchou.ucasdemo.core.dbinfo.BaseEventsDBInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class EventModel {

    public long eventId;// 事件 ID

    public String createdAt;// 事件创建时间

    public int jointed;// 用户对该事件的操作

    public UserModel author;// 事件的发布者

    public String startAt;// 事件的开始时间

    public String endAt;//事件的结束时间

    public String placeAt;// 事件的地点

    public String title;// 事件的主题

    public String content;// 事件的详细内容

    public long tags;// 事件的标签属性

    public String thumbnailPic;

    public String originalPic;

    public EventModel() {

    }

    public void parse(JSONObject jsonObject) throws JSONException {
        eventId = jsonObject.getLong("eventid");
        createdAt = jsonObject.getString("createat");
        jointed = jsonObject.getInt("jointed");
        author.parse(jsonObject.getJSONObject("author"));
        startAt = jsonObject.getString("startat");
        placeAt = jsonObject.getString("placeat");
        title = jsonObject.getString("title");
        content = jsonObject.getString("text");
        tags = jsonObject.getLong("tags");
        thumbnailPic = jsonObject.getString("thumbnailpic");
        originalPic = jsonObject.getString("originalpic");
    }

    public static EventModel fromCursor(Context context, Cursor cursor) {
        EventModel eventModel = new EventModel();
        eventModel.eventId = cursor.getLong(cursor.getColumnIndex(BaseEventsDBInfo.EVENT_ID));
        eventModel.createdAt = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.CREATED_AT));
        eventModel.jointed = cursor.getInt(cursor.getColumnIndex(BaseEventsDBInfo.JOINTED));

        UserModel userModel = new UserModel();
        userModel.userId = cursor.getInt(cursor.getColumnIndex(BaseEventsDBInfo.USER_ID));
        userModel.name = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.USER_NAME));
        userModel.avatar = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.USER_AVATAR));
        eventModel.author = userModel;

        eventModel.startAt = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.START_AT));
        eventModel.placeAt = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.PLACE_AT));
        eventModel.title = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.TITLE));
        eventModel.content = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.CONTENT));
        eventModel.tags = cursor.getLong(cursor.getColumnIndex(BaseEventsDBInfo.TAGS));
        eventModel.thumbnailPic = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.THUMBNAIL_PIC));
        eventModel.originalPic = cursor.getString(cursor.getColumnIndex(BaseEventsDBInfo.ORIGINAL_PIC));
        return eventModel;
    }
}
