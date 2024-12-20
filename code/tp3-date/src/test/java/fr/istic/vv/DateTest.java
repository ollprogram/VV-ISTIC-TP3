package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.istic.vv.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {


    Date d1;
    Date d2;
    Date d3;
    Date d4;
    Date d5;
    Date d6;
    Date d7;
    Date d8;
    Date d9;
    Date d10;
    Date d11;
    Date d12;

    public DateTest(){
        
    }

    @BeforeEach
    public void init(){

        d1 = new Date(1,1,-4);
        d2 = new Date(31,1,-1547);
        d3 = new Date(30,6,45);
        d4 = new Date(7, 5, 204);

        d5 = new Date(2,1,-4);
        d6 = new Date(1,2,-1547);
        d7 = new Date(1,7,45);
        d8 = new Date(8, 5, 204);

        d9 = new Date(31, 12, -5);
        d10 = new Date(30, 1, -1547);
        d11 = new Date(6, 5, 204);

    }

    /*  isValid test  */

    @Test
    public void testIsValid1(){
        assertFalse(Date.isValidDate(-1,1,1));
    }

    @Test
    public void testIsValid2(){
        assertFalse(Date.isValidDate(31,-11,1));
    }

    @Test
    public void testIsValid3(){
        assertFalse(Date.isValidDate(36,1,1));
    }

    @Test
    public void testIsValid4(){
        assertFalse(Date.isValidDate(7,45,1));
    }

    @Test
    public void testIsValid5(){
        assertTrue(Date.isValidDate(1,12,11));
    }

    @Test
    public void testIsValid6(){
        assertFalse(Date.isValidDate(0,10,1));
    }


    /*  isLeapYear test  */

    @Test
    public void testIsLeapYear1(){
        assertTrue(Date.isLeapYear(-4));
    }
    @Test
    public void testIsLeapYear2(){
        assertTrue(Date.isLeapYear(2012));
    }
    @Test
    public void testIsLeapYear3(){
        assertFalse(Date.isLeapYear(41));
    }
    @Test
    public void testIsLeapYear4(){
        assertFalse(Date.isLeapYear(-6));
    }


    /*  nextDate tests */

    @Test
    public void testNextTest1(){
        Date d = d1.nextDate();
        assertEquals(d.day, d5.day);
        assertEquals(d.month, d5.month);
        assertEquals(d.year, d5.year);
    }

    @Test
    public void testNextTest2(){
        Date d = d2.nextDate();
        assertEquals(d.day, d6.day);
        assertEquals(d.month, d6.month);
        assertEquals(d.year, d6.year);
    }

    @Test
    public void testNextTest3(){
        Date d = d3.nextDate();
        assertEquals(d.day, d7.day);
        assertEquals(d.month, d7.month);
        assertEquals(d.year, d7.year);
    }

    @Test
    public void testNextTest4(){
        Date d = d4.nextDate();
        assertEquals(d.day, d8.day);
        assertEquals(d.month, d8.month);
        assertEquals(d.year, d8.year);
    }

    /* previousDate tests */
    @Test
    public void testPrevious1(){
        Date d = d1.previousDate();
        assertEquals(d.day, d9.day);
        assertEquals(d.month, d9.month);
        assertEquals(d.year, d9.year);
    }

    @Test
    public void testPrevious2(){
        Date d = d2.previousDate();
        assertEquals(d.day, d10.day);
        assertEquals(d.month, d10.month);
        assertEquals(d.year, d10.year);
    }

    @Test
    public void testPrevious3(){
        Date d = d4.previousDate();
        assertEquals(d.day, d11.day);
        assertEquals(d.month, d11.month);
        assertEquals(d.year, d11.year);
    }

    /* Test CompareTo */

    @Test
    public void testCompare1(){
        Date d = new Date(1,1,-4);
        assertEquals(d.compareTo(d1),0);
    }

    @Test
    public void testCompare2(){
        assertEquals(d2.compareTo(d5),-1);
    }

    @Test
    public void testCompare3(){
        assertEquals(d8.compareTo(d4),1);
    }

    /* Test ajouté */

    @Test
    public void testCompare4(){
        Date d = new Date(1,1,-5);
        assertEquals(d.compareTo(d1),-1);
    }

    @Test
    public void testCompare5(){

        Date d = new Date(31,2,-1547);
        assertEquals(d2.compareTo(d),-1);
    }

    @Test
    public void testCompare6(){
        Date d = new Date(8, 4, 204);
        assertEquals(d8.compareTo(d),1);
    }

    /* Test isLongMonth */

    @Test
    public void test1(){
        assertTrue(Date.isLongMonth(1));
    }
    @Test
    public void test2(){
        assertTrue(Date.isLongMonth(3));
    }
    @Test
    public void test3(){
        assertTrue(Date.isLongMonth(5));
    }
    @Test
    public void test4(){
        assertTrue(Date.isLongMonth(7));
    }
    @Test
    public void test5(){
        assertTrue(Date.isLongMonth(8));
    }
    @Test
    public void test6(){
        assertTrue(Date.isLongMonth(10));
    }
    @Test
    public void test7(){
        assertTrue(Date.isLongMonth(12));
    }
    @Test
    public void test8() {
        assertFalse(Date.isLongMonth(11));
    }
}
