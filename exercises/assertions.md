# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.


2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Le calcul (3 * .4) ne donne pas exactement 1.2. Cela est dû  à une légère imprécision liée au fait que certains nombres à virgule ne peuvent pas être représentés en binaire.   
Pour régler ce problème, il faudrait ajouter dans la comparaison un seuil d'erreur (autour de 1*10^-10).

2. AssertEquals vérifie que deux valeurs sont égales tandis que assertSame vérifie que deux valeurs ont la même référence.

        Integer a = 127;
        Integer b = 127;
        assertEquals(a, b);
        assertSame(a, b); 

    Dans cet exemple, on a (a) et (b) qui ont pour même valeur 127, ce qui fait passer le premier test à True. Ensuite, le deuxième test passe également car les références sont identiques car elles font partie de même plage de cache des entiers.

3. Fail peut-être utilisé autrement, comme dans ce premier cas ici présent :

        @Test
        public void test() {
            fail("non implémenté");
        }   

    Dans ce premier cas, il est utilisé pour rappeler que ce test n'a pas été implémenté.

        @Test
        public void testExécutionThread() throws InterruptedException {
            Thread thread = new Thread(() -> {
                try {
                    if (true) {
                        throw new RuntimeException("erreur dans le thread");
                    }
                } catch (RuntimeException e) {
                    fail("exception : " + e.getMessage());
                }
            });
            thread.start();
            thread.join();
        }

    Dans cet autre cas, il est utilisé pour un résultat attendu dans un thread.

        @Test
        public void testnNbResult() {
            try {
                int res = nbResult();
                assertEquals(0, res);
            } catch (Exception e) {
                fail("Exception : " + e.getMessage());
            }
        }

    Enfin, dans ce dernier cas, il est utilisé pour capturer une exception non gérée pendant le déroulé de l'exécution.

4. Cette nouvelle manière de faire permet de mieux vérifier le contenu d'une exception. Que se soit au niveau du type ou du message retourné.
