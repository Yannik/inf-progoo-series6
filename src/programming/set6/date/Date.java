package programming.set6.date;

public class Date {

    private final int year;

    private final int month;

    private final int day;

    private final static String[] MONTH = {null, "Januar", "Februar", "März", "April", "Mai",
            "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

    public static int getDaysInMonth(int year, int month) {
        if (month > 12 || month < 1) {
            return 0;
        }
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    public static boolean validate(int year, int month, int day) {
        // as getDaysInMonth returns 0 if the month does not exist,
        // this also validates the month
        return day <= getDaysInMonth(year, month) && day > 0;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public Date(int year, int month, int day) {
        if (!validate(year, month, day)) {
            throw new IllegalArgumentException("This is not a valid date.");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public int dayOfYear() {
        int days = this.day;
        for (int i = 1; i < this.month; i++) {
            days += Date.getDaysInMonth(this.year, i);
        }
        return days;
    }

    public int sameYearDiff(Date other) {
        if (other.getYear() != this.year) {
            return 0;
        }

        if (this.month == other.getMonth()) {
            return other.getDay() - this.day;
        }

        Date greater, lesser;
        if (other.getMonth() > this.month || (other.getMonth() == this.month && other.getDay() > this.day)) {
            greater = other;
            lesser = this;
        } else {
            greater = this;
            lesser = other;
        }

        // add the days already gone by in the greater month
        int days = greater.getDay();
        // add the remaining days of the lesser month
        days += Date.getDaysInMonth(lesser.getYear(), lesser.getMonth()) - lesser.getDay();

        // add the months in between
        for (int month = lesser.month + 1; month < greater.month; month++) {
            days += Date.getDaysInMonth(lesser.year, month);
        }

        // if this month was the greater one, return the inverted number of days
        if (this.month == greater.getMonth()) {
            return -days;
        }

        return days;
    }

    public String toString() {
        return String.format("%s %d, %d", MONTH[this.month], this.day, this.year);
    }
}
