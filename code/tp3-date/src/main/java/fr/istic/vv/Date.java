package fr.istic.vv;

class Date implements Comparable<Date> {
    
    public int day;
    public int month;
    public int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if(month < 1 || month > 12 ){
            return false;
        } else {
            if(isLongMonth(month) && (day < 1 || day > 31)){
                return false;
            }
            else {
                if(month == 2 ) {
                    if(isLeapYear(year) && (day < 1 || day > 29)) {
                        return false;
                    } else if ((day < 1 || day > 28)) {
                        return false;
                    }
                } else {
                    if(day < 1 || day > 30){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isLongMonth(int month){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLeapYear(int year) { return year%4 == 0; }

    public Date nextDate() {
        if(isValidDate(this.day +1, this.month, this.year)){
            return new Date(this.day +1, this.month, this.year);
        } else {
            if(isValidDate(1, this.month + 1, this.year)){
                return new Date(1, this.month + 1, this.year);
            }
            return new Date(1, 1, this.year + 1);
        }
    }

    public Date previousDate() {
        if(isValidDate(this.day -1, this.month, this.year)){
            return new Date(this.day -1, this.month, this.year);
        } else {
            if(isValidDate(31, this.month - 1, this.year)){
                return new Date(31, this.month - 1, this.year);
            }
            return new Date(31, 12, this.year - 1);
        }
    }

    public int compareTo(Date other) {
        if(this.day == other.day && (this.month == other.month) && (this.year == other.year)){
            return 0;
        } else{
            if(this.year > other.year){
                return 1;
            } else if (this.year < other.year) {
                return -1;
            } else {
                if(this.month > other.month){
                    return 1;
                } else if (this.month < other.month) {
                    return -1;
                } else {
                    if(this.day > other.day){
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

}