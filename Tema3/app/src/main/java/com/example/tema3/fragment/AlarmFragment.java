package com.example.tema3.fragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tema3.R;
import com.example.tema3.ToDoActivity;
import com.example.tema3.helper.ReceiverHelper;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmFragment extends Fragment {
    public static final int REQUEST_CODE=101;
    private static final String default_notification_channel_id = "primary_notification_channel" ;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datePickerButton;
    private Button timePickerButton;
    private Button createAlarmButton;
    private TextView choosedDateTextView;
    private TextView choosedTimeTextView;
    private TextView titleAlarmTextView;

    private int yearAlarm;
    private int monthAlarm;
    private int dayAlarm;
    private int hourAlarm;
    private int minuteAlarm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View alarmFragmentView =  inflater.inflate(R.layout.fragment_alarm, container, false);

        datePickerButton = alarmFragmentView.findViewById(R.id.date_picker_button);
        timePickerButton = alarmFragmentView.findViewById(R.id.time_picker_button);
        createAlarmButton = alarmFragmentView.findViewById(R.id.create_alarm_button);
        choosedDateTextView = alarmFragmentView.findViewById(R.id.choosed_date);
        choosedTimeTextView = alarmFragmentView.findViewById(R.id.choosed_time);
        titleAlarmTextView = alarmFragmentView.findViewById(R.id.title_alarm);
        titleAlarmTextView.setText(getTitleIntent());

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                choosedDateTextView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                yearAlarm = year;
                                monthAlarm = monthOfYear;
                                dayAlarm = dayOfMonth;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                choosedTimeTextView.setText(sHour + ":" + sMinute);
                                hourAlarm = sHour;
                                minuteAlarm = sMinute;
                            }
                        }, hour, minutes, DateFormat.is24HourFormat(getActivity()));
                timePickerDialog.show();
            }
        });

        createAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, yearAlarm);
                calendar.set(Calendar.MONTH, monthAlarm);
                calendar.set(Calendar.DAY_OF_MONTH, dayAlarm);
                calendar.set(Calendar.HOUR, hourAlarm);
                calendar.set(Calendar.MINUTE, minuteAlarm);

                scheduleNotification(calendar);

                ToDoFragment toDoFragment = new ToDoFragment();
                ((ToDoActivity)getActivity()).replaceFragment(toDoFragment, R.id.alarm_fragment_layout);
            }
        });

        return alarmFragmentView;
    }

    private void scheduleNotification (Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        Intent notificationIntent = new Intent(getContext(), ReceiverHelper.class);
        notificationIntent.putExtra("alarmTitle", titleAlarmTextView.getText() ) ;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getContext(),"Alarm has been created successfully", Toast.LENGTH_SHORT).show();
    }

    private String getTitleIntent(){
        return getArguments().getString("title");
    }
}
