# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1. On peut tout d'abord identifier que tout ce déroule dans trois domaines.

 - Le domaine des jours, des entiers povant être inférieurs à 1, entre 1 et 31, puis tout ce qui est supérieur à 31.
 - Le domaine des mois, des entiers également composé de trois parties, [1,3,5,7,8,10,12] les mois avec 31 jour, [4,6,9,11] les mois avec 30 jours et enfin le mois de février [2] contenant 28 ou 29 jours en fonction de si l'année en cous est bisextile.
 - Le dernier domaine est celui de l'année, 2 domaine sont possibles, le premier étant les années bisextile (tout les années divisible par 4), et le reste

 2. Statement Coverage = 19/22 = 86% de couverture il nous faut donc rajouter un test pour obtenir une bonne couverture. Le problème est que compareTo test pas toutes les instructions

 3. Dans mon code, dans la fonction "isValideDate()"", j'ai cette condition :
 
        isLongMonth(month) && (day < 1 || day > 31)

    D'après le Base Choice Coverage il faudrait pour tester au mieux la condition au minimum quatres tests différents car si on pose A && (B || C), il faudrait :

    Si A = Faux, 1 test car le résultat sera faux pour n'importe quel valeur de B ou C
    Si A = Vrai, 3 test car il y a tout d'abord le cas ou B et C sont Faux mais aussi les deux cas ou B ou C vaut Vrai.

    Après vérification de nos tests, nous avons décidé de rajouter le test ci dessous, car les tests ne vérifiait pas le cas suivant A = Vrai, B = Faux, C = Vrai. 

        @Test
        public void testIsValid6(){
            assertFalse(Date.isValidDate(0,10,1));
        }
 4. 
    
