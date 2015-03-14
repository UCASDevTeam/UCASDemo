package com.ivanchou.ucasdemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.ivanchou.ucasdemo.R;

import java.util.Date;

/**
 * Created by Origa on 2015/3/12.
 */
public class DateAndTimePickerView extends LinearLayout{

    private LayoutInflater mInflater;
    private OnDateAndTimeChangedListener mCallback;
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    private Date mDate;
    private int year = 1900;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public DateAndTimePickerView(Context context) {
        this(context,null);
    }

    public DateAndTimePickerView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public DateAndTimePickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout)mInflater.inflate(R.layout.date_and_time_picker,this,false);
        mDatePicker = (DatePicker) layout.findViewById(R.id.dp_time_picker_date);
        mTimePicker = (TimePicker) layout.findViewById(R.id.tp_time_picker_time);
        addView(layout);
        mCallback = null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int height = Math.max(mDatePicker.getHeight(),mTimePicker.getHeight());

        setMeasuredDimension(widthSize,(MeasureSpec.EXACTLY == heightMode) ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {
        getChildAt(0).layout(i,i2,i3,i4);
    }

    public void initPicker(){
        mTimePicker.setIs24HourView(true);
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                Log.e("TimePicker", "Hour:" + i + "Minute:" + i2);
                mDate.setHours(i);
                mDate.setMinutes(i2);
                hour = i;
                minute = i2;
                mCallback.onDateAndTimeChanged(mDate);
            }
        });

        mDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                Log.e("DatePicker", "year:" + i + "Mouth:" + i2 + "Day:" + i3);
                mDate.setYear(i - 1900);
                mDate.setMonth(i2);
                mDate.setDate(i3);
                year = i - 1900;
                month = i2;
                day = i3;
                mCallback.onDateAndTimeChanged(mDate);
            }
        });

    }

    public void setDate(Date date){
        mDate = date;
        year = mDate.getYear() + 1900;
        month = mDate.getMonth();
        day = mDate.getDate();
        hour = mDate.getHours();
        minute = mDate.getMinutes();
    }

    public void setOnDateAndTimeChangeListener(OnDateAndTimeChangedListener listener){
        mCallback = listener;
    }

    public interface OnDateAndTimeChangedListener{
        public void onDateAndTimeChanged(Date mDate);
    }
}
