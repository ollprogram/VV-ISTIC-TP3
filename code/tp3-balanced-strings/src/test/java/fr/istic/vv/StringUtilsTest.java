package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    /*Space partitioning, String : caracteristics :
    - odd, even size
    - other symbols than ( [ { , only ( [ { , mixed characters
    ... we can find many...
    */

    @Test
    void isNotBalancedOdd() {
        String s = "{([";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void isNotBalancedEven(){
        String s = "{([(";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void isNotBalancedOddLonger() {
        String s = "{([(])}";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void isBalancedEven(){
        String s = "{([()])}";
        assertTrue(StringUtils.isBalanced(s));
    }

    @Test
    void oneElement(){
        String s = "(";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void twoElements(){
        String s = "()";
        assertTrue(StringUtils.isBalanced(s));
    }

    @Test
    void twoElementsBad(){
        String s = "(]";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void mixedCharacters(){
        String s = "[klfsfskj]";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void onlyOther(){
        String s = "klfsfskj";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void onlySymbols(){
        String s = "{()}{}()";
        assertTrue(StringUtils.isBalanced(s));
    }

    @Test
    void onlySymbolsBad(){
        String s = "{()}{}(";
        assertFalse(StringUtils.isBalanced(s));
    }

    /*Tests for predicate coverage to satisfy
    *
    * A=0 B=1 C=1 R=0
    * A=1 B=0 C=1 R=0
    * A=1 B=1 C=0 R=0
    * A=1 B=1 C=1 R=1
    *
    * */

    @Test
    void allSatisfied(){
        String s = "{[()]}";
        assertTrue(StringUtils.isBalanced(s));
    }

    @Test
    void AUnsatisfied(){
        String s = "{[()]";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void BUnsatisfied(){
        String s = "{()]}";
        assertFalse(StringUtils.isBalanced(s));
    }

    @Test
    void CUnsatisfied(){
        String s = "{[(]}";
        assertFalse(StringUtils.isBalanced(s));
    }

}