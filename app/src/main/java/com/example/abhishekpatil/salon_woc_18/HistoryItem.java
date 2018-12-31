package com.example.abhishekpatil.salon_woc_18;


public class HistoryItem {

    private String date;
    private String time;
    private String name;
    private String address;
    private String phonenumber;

    public HistoryItem( String date, String time, String name, String address, String phonenumber) {

        this.date = date;
        this.time = time;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    private HistoryItem(){

    }

    public String getDate() {


        return date.substring(0,2)+"/"+date.substring(2,4)+"/"+date.substring(4,date.length());
    }

    public String getTime() {
        if(time.equals("time1")){
            time = "9am-10am";
        }
        if(time.equals("time2")){
            time = "10am-11am";
        }
        if(time.equals("time3")){
            time = "11am-12pm";
        }
        if(time.equals("time4")){
            time = "12pm-1pm";
        }
        if(time.equals("time5")){
            time = "1pm-2pm";
        }
        if(time.equals("time6")){
            time = "2pm-3pm";
        }
        if(time.equals("time7")){
            time = "3pm-4pm";
        }
        if(time.equals("time8")){
            time = "4pm-5pm";
        }
        if(time.equals("time9")){
            time = "5pm-6pm";
        }
        if(time.equals("time10")){
            time = "6pm-7pm";
        }
        if(time.equals("time11")){
            time = "7pm-8pm";
        }
        if(time.equals("time12")){
            time = "8pm-9pm";
        }
        if(time.equals("time13")){
            time = "9pm-10pm";
        }

        return time;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {

        return "+"+phonenumber;
    }
}
