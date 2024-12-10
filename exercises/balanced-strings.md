# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. On peut identifier beaucoup de caractéristiques différentes, 
nous avons cherché à identifier les plus simples/différentes: 
 - caractéristique de la taille : 2 blocks (pair, impair)
 - caractéristique du symbole : 3 blocks (que des symboles attendus, que des symboles inconnus, les deux)

Tout en essayant d'identifier des cas un peu critiques.

2. Pour voir la couverture de tests en live, on a juste à lancer junit avec la couverture de tests dans intelliJ par example.

3. Le prédicat ```return counts[0] == 0 && counts[1] == 0 && counts[2] == 0;```
contient 3 inconnues. On peut le récrire sous la forme ```A && B && C```
Avec la méthode de fixage des valeurs on obtient :

pour A : 
- A=0 B=1 C=1 R=0
- A=1 B=1 C=1 R=1

pour B :
- A=1 B=0 C=1 R=0
- A=1 B=1 C=1 R=1

pour C:
- A=1 B=1 C=0 R=0
- A=1 B=1 C=1 R=1

on peut simplifier en :
- A=0 B=1 C=1 R=0
- A=1 B=0 C=1 R=0
- A=1 B=1 C=0 R=0
- A=1 B=1 C=1 R=1

Ce qui nous fait 4 chemins minimum à traverser.

La méthode et la condition étant assez simples, on peut se passer d'outils pour évaluer
notre coverage sur les predicats et rajouter des tests en conséquence pour satisfaire chacun des chemins.

4. ```mvn clean test-compile org.pitest:pitest-maven:mutationCoverage```