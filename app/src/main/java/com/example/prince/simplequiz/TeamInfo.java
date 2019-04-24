package com.example.sonu.simplequiz;

/**
 * Created by sonu on 02/19/2019.
 */

public class TeamInfo
{

    String teamname , correct ,  wrong,phonenumber ,time;

    public TeamInfo(String teamname,String phonenumber, String correct, String wrong, String time) {
        this.teamname = teamname;
        this.phonenumber=phonenumber;
        this.correct = correct;
        this.wrong = wrong;
        this.time = time;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getPhonenumber(){return phonenumber;}
    public void setPhonenumber(String phonenumber){this.phonenumber=phonenumber;}

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }
}
