package com.example.calenderdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener{
	public String dateFormat, logInID;
	public String[] weekDays;
	public String[] NextPreWeekday;
	public String dateFormate;
	public String firstDayOfWeek;
	public String lastDayOfWeek;
	private TextView textViewServiceApp;
	private TextView textViewWeekCalender;
	private TextView textViewPrevDate;
	private TextView textViewDate;
	private TextView textViewNextDate;
	private TextView textViewSun;
	private TextView textViewMon;
	private TextView textViewTue;
	private TextView textViewWed;
	private TextView textViewThu;
	private TextView textViewFri;
	private TextView textViewSat;
	private ImageView nextMonth,previousMonth;
 String date="2015-02-16";
    ListView schedulelvList; 
    ArrayList<Schedulemodel> testItems;
    ScheduleListAdapter adapter;
	public int weekDaysCount = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//textViewPrevDate = (TextView) findViewById(R.id.textViewPrevDate);
		textViewDate = (TextView) findViewById(R.id.currentMonth);
		//textViewNextDate = (TextView) findViewById(R.id.textViewNextDate);
		textViewSun = (TextView) findViewById(R.id.textViewSun);
		textViewMon = (TextView) findViewById(R.id.textViewMon);
		textViewTue = (TextView) findViewById(R.id.textViewTue);
		textViewWed = (TextView) findViewById(R.id.textViewWed);
		textViewThu = (TextView) findViewById(R.id.textViewThu);
		textViewFri = (TextView) findViewById(R.id.textViewFri);
		textViewSat = (TextView) findViewById(R.id.textViewSat);
		nextMonth = (ImageView) findViewById(R.id.nextMonth);
		previousMonth = (ImageView)findViewById(R.id.prevMonth);
		nextMonth.setOnClickListener(this);
		previousMonth.setOnClickListener(this);

		
		NextPreWeekday = getWeekDay();
		firstDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[0]);
		
		lastDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[6]);
		textViewDate.setText( CommonMethod.convertWeekDaysMonth(NextPreWeekday[6]));
		textViewSun.setText(CommonMethod.convertWeekDays(NextPreWeekday[0])
				+ "\nSun");
		textViewMon.setText(CommonMethod.convertWeekDays(NextPreWeekday[1])
				+ "\nMon");
		textViewTue.setText(CommonMethod.convertWeekDays(NextPreWeekday[2])
				+ "\nTue");
		textViewWed.setText(CommonMethod.convertWeekDays(NextPreWeekday[3])
				+ "\nWeb");
		textViewThu.setText(CommonMethod.convertWeekDays(NextPreWeekday[4])
				+ "\nThu");
		textViewFri.setText(CommonMethod.convertWeekDays(NextPreWeekday[5])
				+ "\nFri");
		textViewSat.setText(CommonMethod.convertWeekDays(NextPreWeekday[6])
				+ "\nSat");
		textViewSun.setOnClickListener(this);
		textViewMon.setOnClickListener(this);
		textViewTue.setOnClickListener(this);
		textViewWed.setOnClickListener(this);
		textViewThu.setOnClickListener(this);
		textViewFri.setOnClickListener(this);
		textViewSat.setOnClickListener(this);
		schedulelvList = (ListView)findViewById(R.id.schedule_list);
		 /*testItems= new ArrayList<Schedulemodel>();
	       testItems.add(new Schedulemodel("IIT-M", "Maths","90"));
	        testItems.add(new Schedulemodel("IIT-P", "Physics","90"));
	        testItems.add(new Schedulemodel("IIT-C", "Chemisty","90"));
			adapter = new ScheduleListAdapter(testItems,MainActivity.this);
		 schedulelvList.setAdapter(adapter);*/
		 /*testItems= new ArrayList<Schedulemodel>();*/
		 new AsyncCallWS().execute();
		changeBackgroundOfCurrentDate();
	}
	public String[] getWeekDay() {

		Calendar now = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] days = new String[7];
		int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
		now.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days[i] = format.format(now.getTime());
			now.add(Calendar.DAY_OF_MONTH, 1);
		}

		return days;

	}
	@SuppressLint("SimpleDateFormat")
	public String[] getWeekDayNext() {

		weekDaysCount++;
		Calendar now1 = Calendar.getInstance();
		Calendar now = (Calendar) now1.clone();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] days = new String[7];
		int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
		now.add(Calendar.WEEK_OF_YEAR, weekDaysCount);
		now.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days[i] = format.format(now.getTime());
			now.add(Calendar.DAY_OF_MONTH, 1);
		}

		return days;

	}

	@SuppressLint("SimpleDateFormat")
	public String[] getWeekDayPrev() {

		weekDaysCount--;
		Calendar now1 = Calendar.getInstance();
		Calendar now = (Calendar) now1.clone();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] days = new String[7];
		int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
		now.add(Calendar.WEEK_OF_YEAR, weekDaysCount);
		now.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days[i] = format.format(now.getTime());
			now.add(Calendar.DAY_OF_MONTH, 1);
		}

		return days;

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.nextMonth:
			NextPreWeekday = getWeekDayNext();
			firstDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[0]);
			lastDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[6]);
			textViewDate.setText(/*firstDayOfWeek + "-" + lastDayOfWeek + " "
					+*/ CommonMethod.convertWeekDaysMonth(NextPreWeekday[6]));
			textViewSun.setText(CommonMethod.convertWeekDays(NextPreWeekday[0])
					+ "\nSun");
			textViewMon.setText(CommonMethod.convertWeekDays(NextPreWeekday[1])
					+ "\nMon");
			textViewTue.setText(CommonMethod.convertWeekDays(NextPreWeekday[2])
					+ "\nTue");
			textViewWed.setText(CommonMethod.convertWeekDays(NextPreWeekday[3])
					+ "\nWeb");
			textViewThu.setText(CommonMethod.convertWeekDays(NextPreWeekday[4])
					+ "\nThu");
			textViewFri.setText(CommonMethod.convertWeekDays(NextPreWeekday[5])
					+ "\nFri");
			textViewSat.setText(CommonMethod.convertWeekDays(NextPreWeekday[6])
					+ "\nSat");
			changeBackgroundOfCurrentDate();
			break;
		case R.id.prevMonth:
			NextPreWeekday = getWeekDayPrev();
			firstDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[0]);
			lastDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[6]);
			textViewDate.setText(/*firstDayOfWeek + "-" + lastDayOfWeek + " "
					+*/ CommonMethod.convertWeekDaysMonth(NextPreWeekday[6]));
			textViewSun.setText(CommonMethod.convertWeekDays(NextPreWeekday[0])
					+ "\nSun");
			textViewMon.setText(CommonMethod.convertWeekDays(NextPreWeekday[1])
					+ "\nMon");
			textViewTue.setText(CommonMethod.convertWeekDays(NextPreWeekday[2])
					+ "\nTue");
			textViewWed.setText(CommonMethod.convertWeekDays(NextPreWeekday[3])
					+ "\nWeb");
			textViewThu.setText(CommonMethod.convertWeekDays(NextPreWeekday[4])
					+ "\nThu");
			textViewFri.setText(CommonMethod.convertWeekDays(NextPreWeekday[5])
					+ "\nFri");
			textViewSat.setText(CommonMethod.convertWeekDays(NextPreWeekday[6])
					+ "\nSat");
			changeBackgroundOfCurrentDate();
			break;
		case R.id.textViewSun:
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[0]) , Toast.LENGTH_LONG).show();
			//date=CommonMethod.convertFormattedDate(NextPreWeekday[0]);
			//new AsyncCallWS().execute();
			//schedulelvList.setAdapter(adapter);
			break;
		case R.id.textViewMon:
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[1]) , Toast.LENGTH_LONG).show();
			//date=CommonMethod.convertFormattedDate(NextPreWeekday[1]);
			new AsyncCallWS().execute();
			break;
		case R.id.textViewTue:
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[2]) , Toast.LENGTH_LONG).show();
			break;
		case R.id.textViewWed:
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[3]) , Toast.LENGTH_LONG).show();
			break;
		case R.id.textViewThu:
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[4]) , Toast.LENGTH_LONG).show();
			break;
		case R.id.textViewFri:
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[5]) , Toast.LENGTH_LONG).show();
			break;
		case R.id.textViewSat:
			//testItems = getTestDetails();
			//schedulelvList.setAdapter(new ScheduleListAdapter(testItems,MainActivity.this));
			Toast.makeText(MainActivity.this,CommonMethod.convertFormattedDate(NextPreWeekday[6]) , Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
		
	}
	private class AsyncCallWS extends AsyncTask<String, Void, String> {
		
		
        @Override
        protected String doInBackground(String... params) {
                        //Invoke webservice
        	 //Create Webservice class object
            WebServiceCall com = new WebServiceCall();
             
            // Initialize variables
           // String date   = "2015-02-16";
            String partnerid = "3A8A70D5E3B7BA0F";
            String studentid   = "4FD309D7F6FECDE4D4023D474EE2E3A6";
             
            //Call Webservice class method and pass values and get response
            String aResponse = com.getConvertedWeight("GetStudentTimeTableData", partnerid, date, studentid);  
             
            //Alert message to show webservice response
            /*Toast.makeText(getApplicationContext(), weight+" Gram= "+aResponse+" Kilograms",
               Toast.LENGTH_LONG).show();*/
                
            Log.i("AndroidExampleOutput", "----"+aResponse);
            
           
             
            return aResponse;
            }
 
        @Override
        protected void onPostExecute(String result) {
        	String name=null,startTime=null,endTime=null,
        			SubjectName=null,ModuleName=null,SubModuleName=null,StudentName=null,
        		     Location=null,createdOn=null,LastNode=null,StartTimeOnly=null,EndTimeOnly=null,
        		     AttendanceStatus=null,StudentImagePath=null,FacultyName=null,
        		     FacultyImagePath=null,TimeTableDate=null,BatchName=null;
           boolean status=false;
        	int subjectId=0,moduleId=0,subModuleId=0,StudentId=0,Id=0,natureId=0,
        			TimeTableId=0,BatchId=0,YearSessionId=0,MonthSessionId=0;
        	 if(result != null){
             	try{
             		JSONArray jsonArray = new JSONArray(result);
             		for(int i=0;i<jsonArray.length();i++){
             		JSONObject jsonObject = (JSONObject)jsonArray.getJSONObject(i);
             		status = jsonObject.getBoolean("status");
             		 name =jsonObject.getString("name");
             		 startTime = jsonObject.getString("startTime");
             		 endTime =jsonObject.getString("endTime");
             		subjectId= jsonObject.getInt("subjectId");
             		 SubjectName =jsonObject.getString("SubjectName");
             		moduleId =jsonObject.getInt("moduleId");
             		ModuleName = jsonObject.getString("ModuleName");
             		subModuleId = jsonObject.getInt("subModuleId");
             		SubModuleName =jsonObject.getString("SubModuleName");
             		StudentId=jsonObject.getInt("StudentId");
             		StudentName=jsonObject.getString("StudentName");
             		Location=jsonObject.getString("Location");
             		createdOn = jsonObject.getString("createdOn");
             		Id =jsonObject.getInt("subModuleId");
             		LastNode=jsonObject.getString("LastNode");
             		natureId =jsonObject.getInt("natureId");
             		StartTimeOnly = jsonObject.getString("StartTimeOnly");
             		EndTimeOnly = jsonObject.getString("EndTimeOnly");
             		AttendanceStatus = jsonObject.getString("AttendanceStatus");
             		//StudentImagePath = jsonObject.getString("ImagePath");
             		FacultyName =jsonObject.getString("FacultyName");
             		FacultyImagePath =jsonObject.getString("FacultyImagePath");
             		TimeTableDate = jsonObject.getString("TimeTableDate");
             		TimeTableId = jsonObject.getInt("TimeTableId");
             		BatchId =jsonObject.getInt("BatchId");
             		BatchName = jsonObject.getString("TimeTableDate");
             		YearSessionId = jsonObject.getInt("YearSessionId");
             		MonthSessionId =jsonObject.getInt("MonthSessionId");
                        testItems= new ArrayList<Schedulemodel>();
                        Schedulemodel model = new Schedulemodel(""+status,name, ModuleName);
                        testItems.add(model);
                        adapter = new ScheduleListAdapter(testItems,MainActivity.this);
                        schedulelvList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
             		/*Schedulemodel model = new Schedulemodel(name,BatchName, SubjectName);
	                 testItems.add(model);
	                 adapter = new ScheduleListAdapter( testItems,MainActivity.this);
	         		schedulelvList.setAdapter(adapter);
	         		adapter.notifyDataSetChanged();*/
             		}
             	}catch(Exception e){
             		e.printStackTrace();
             	}
             }Log.i("ParseString", status+""+name+""+startTime+""+endTime+""+
         			SubjectName+""+ModuleName+""+SubModuleName+""+StudentName+""+
          		     Location+""+createdOn+""+LastNode+""+StartTimeOnly+""+EndTimeOnly+""+
          		     AttendanceStatus+""+StudentImagePath+""+FacultyName+""+
          		     FacultyImagePath+""+TimeTableDate+""+BatchName);
        	Toast.makeText(MainActivity.this, status+""+name+""+startTime+""+endTime+""+
        			SubjectName+""+ModuleName+""+SubModuleName+""+StudentName+""+
       		     Location+""+createdOn+""+LastNode+""+StartTimeOnly+""+EndTimeOnly+""+
       		     AttendanceStatus+""+StudentImagePath+""+FacultyName+""+
       		     FacultyImagePath+""+TimeTableDate+""+BatchName,
                    Toast.LENGTH_LONG).show();
        	
        }
 
        @Override
        protected void onPreExecute() {
                       
        }
 
       
 
    
}
	
	//method for change background color of current date
	private void changeBackgroundOfCurrentDate(){
		//get currentDate
				Calendar c = Calendar.getInstance();
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
				String currentDate = df1.format(c.getTime());
				Toast.makeText(MainActivity.this, currentDate, Toast.LENGTH_LONG).show();
				if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[0]))){
					textViewSun.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[1]))){
					textViewMon.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[2]))){
					textViewTue.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[3]))){
					textViewWed.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[4]))){
					textViewThu.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[5]))){
					textViewFri.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else if(currentDate.equals(CommonMethod.convertFormattedDate(NextPreWeekday[6]))){
					textViewSat.setBackgroundColor(Color.parseColor("#2EB7ED"));
				}
				else
					textViewSat.setBackgroundColor(Color.parseColor("#d8d8d0"));
	}
	
	
	
}
