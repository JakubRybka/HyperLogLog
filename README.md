# HyperLogLog
Implementation using Scala language of HyperLogLog algorithm in a case of finding number of distinct words in a text. 
This probabilistic algorithm is efficient in terms of memory and calculation complexity, and accurate for large datasets. 
It uses stochastic counting to find number of different elements in every dataset.
Accuracy can be manipulated with change of parameteres of the program.

## How to use
First scala compiler must be installed, one must follow intsruction from following site: 
https://docs.scala-lang.org/getting-started/index.html

To run a program on some text file following commands are needed:
```
scalac HyperLogLog.scala
scala HyperLogLog <filename>
```
