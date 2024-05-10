package main.util;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        if(month < 1 || month > 12)
            throw new RuntimeException("unexpected month in date: " + month);
        if(day < 1 || day > 31)
            throw new RuntimeException("unexpected day in date: " + month);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYearDiff(Date d) {
        return Math.abs(d.getYear() - this.year);
    }

    public int getMonthDiff(Date d) {
        return (d.getYear() - this.year) * 12 + d.getMonth() - this.month;
    }

    public int getDayDiff(Date d) {
        // TODO
        return 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date date)) return false;
        return year == date.year && month == date.month && day == date.day;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }
}
