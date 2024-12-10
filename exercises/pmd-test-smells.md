# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Pour répondre à cette question, nous avons décidé d'utiliser apache commons-math comme projet à checker. L'une des règles où PMD détecte des smells est JUnitUseExpected. La commande suivante a permis à PMD de trouver environ 1000 cas de violation de cette règle.

    pmd check -d ~/Documents/VV/commons-math/ -R category/java/bestpractices.xml/JUnitUseExpected -f xml > smells.xml

Comme le montre la fin de la commande, le résultat de ce check se trouve [ici](/smells.xml)

Analysons maintenant, une violation du fichier :

    <file name="/home/ubuntuside/Documents/VV/commons-math/commons-math-legacy-core/src/test/java/org/apache/commons/math4/legacy/core/MathArraysTest.java">
    <violation beginline="191" endline="197" begincolumn="9" endcolumn="10" rule="JUnitUseExpected" ruleset="Best Practices" package="org.apache.commons.math4.legacy.core" class="MathArraysTest" method="testCheckOrder" externalInfoUrl="https://docs.pmd-code.org/pmd-doc-7.8.0/pmd_rules_java_bestpractices.html#junituseexpected" priority="3">
    In JUnit4, use the @Test(expected) annotation to denote tests that should throw exceptions
    </violation>

Cette partie indique que mon fichier MathArraysTest.java doit utiliser l'annotation @Test(expected) au lieu d'utiliser un try/catch. Maintenant, regardons ce qu'il se passe réellement dans la classe de test.


    @Test
    public void testCheckOrder() {
        MathArrays.checkOrder(new double[] {-15, -5.5, -1, 2, 15},
                             MathArrays.OrderDirection.INCREASING, true);
        MathArrays.checkOrder(new double[] {-15, -5.5, -1, 2, 2},
                             MathArrays.OrderDirection.INCREASING, false);
        MathArrays.checkOrder(new double[] {3, -5.5, -11, -27.5},
                             MathArrays.OrderDirection.DECREASING, true);
        MathArrays.checkOrder(new double[] {3, 0, 0, -5.5, -11, -27.5},
                             MathArrays.OrderDirection.DECREASING, false);

        try {
            MathArrays.checkOrder(new double[] {-15, -5.5, -1, -1, 2, 15},
                                 MathArrays.OrderDirection.INCREASING, true);
            Assert.fail("an exception should have been thrown");
        } catch (NonMonotonicSequenceException e) {
            // Expected
        }
    }   

En effet, le test utilise bien un try/catch, il pourrait être donc remplacé par :

    @Test(expected = NonMonotonicSequenceException.class)
    public void testCheckOrder() {
        MathArrays.checkOrder(new double[] {-15, -5.5, -1, 2, 15},
                             MathArrays.OrderDirection.INCREASING, true);
        MathArrays.checkOrder(new double[] {-15, -5.5, -1, 2, 2},
                             MathArrays.OrderDirection.INCREASING, false);
        MathArrays.checkOrder(new double[] {3, -5.5, -11, -27.5},
                             MathArrays.OrderDirection.DECREASING, true);
        MathArrays.checkOrder(new double[] {3, 0, 0, -5.5, -11, -27.5},
                             MathArrays.OrderDirection.DECREASING, false);
        MathArrays.checkOrder(new double[] {-15, -5.5, -1, -1, 2, 15},
                                 MathArrays.OrderDirection.INCREASING, true);
        
    }


