# My Language

This project was made in the 2018 during the course of Computational Models And Languages with Prof. Enrico Denti at UNIBO (Alma Mater Studiorum Università di Bologna).
This is a small compiler created with java to scan, parse and evaluate a small language for mathematic expression (with long types).

Provide a simple gui interface where you could insert the expression that you want to compile.

## Expression allowed:

* sums
* subtractions
* multiplications
* divisions
* pows
* assignments
* sequences of expressions separated by commas

## Installation

Simply clone or download the repo and run `Main.class` inside `src` folder. There you could find `main(String[] args)` method

## Examples

Remember that identifier on the right of = should have a $ before.

``` 
  ((3 + 5) / 2 ) * 4^3
  x = 5
  y = $x * 2
  x = 5,y = $x + 3,z = $y^3
```
