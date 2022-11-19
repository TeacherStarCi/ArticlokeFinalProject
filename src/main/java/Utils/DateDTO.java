
package Utils;

public class DateDTO {
  private long date;
  private long hour;
  private long minute;
  private long second;
  private long milisecond;

    @Override
    public String toString() {
        return "DateDTO{" + "date=" + date + ", hour=" + hour + ", minute=" + minute + ", second=" + second + ", milisecond=" + milisecond + '}';
    }

   

    public DateDTO(long date, long hour, long minute, long second, long milisecond) {
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milisecond = milisecond;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getMilisecond() {
        return milisecond;
    }

    public void setMilisecond(long milisecond) {
        this.milisecond = milisecond;
    }

 

   

}
