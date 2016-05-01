Matching
=======

Matching is a solution to the [coding challenge](http://sortable.com/challenge/) proposed by the company called Sortable.

The problem is know as Object Matching (also referred to as duplicate identification, record linkage, entity resolution or reference reconciliation) and is a crucial task for data integration and data cleaning. The task is to detect multiple representations of the same real-world object. This is a challenging task particularly for objects that are highly heterogeneous and of limited data quality, e.g., regarding completeness and consistency of their descriptions.

The goal is to match product listings from a 3rd party retailer, e.g. “Nikon D90 12.3MP Digital SLR Camera (Body Only)” against a set of known products, e.g. “Nikon D90”.

## Reasoning

The solution is made of the following parts:

1. **Data Input**: this is the first part of the program where it's expected that the user enter three required arguments: the products file, the listings file and the result file to output. This arguments are then validated, and if everything goes fine, it goes to the next step.

2. **Blocking**: The standard (naive) approach to find matches in n input objects is to compare each object with every other object. This requires lots of comparisons, basically a complete Cartesian product (OA × OB ) is examined. The resulting quadratic complexity of O(n2) results in infeasible execution times in particular for large input sets. Therefore, an initial step in the matching process called blocking is commonly applied to reduce the search space to a small subset of the most likely matching object pairs.
 
Blocking approaches semantically partition the input data into blocks of similar objects and restrict object matching to objects of the same block.

In this project, the products and listings are separated in blocks based on their manufacturer, so we don't need to compare every single product with every single listings, we just need to compare all the projects and all listings that refer to same manufacturer.

3. **Execution**: this is the third and last step where the computation happens. Since we have the data partitioned into different blocks, we can execute the computation of each block in parallel. Each parallel computation here is called a task.

The task being executed here, for each block, is a string comparison between each product's name + model + familiy against each listing title. It's used a combination of the following algorithms to determine the similarity of each product with each listing, the best matching is selected (if above some pre-defined threshold):

* [Levenshtein Distance](https://en.wikipedia.org/wiki/Levenshtein_distance)
* [QGram](https://en.wikipedia.org/wiki/Gram%E2%80%93Schmidt_process)
* [Jaro Winkler Distance](https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance)

## Executing Matching

To execute the algorithm, you can either build from the source and execute it or used the pre-compiled version at the bin folder.
If you want to build from source, see the next section for details.

### Unix / OS X

Prerequisites:
* `Java 8`

Executing:
Go the the folder where you have the executable file (matching.1-0-0.jar) and execute the following command:

```text
$ java -jar matching-1.0.0.jar -listings $PATH_TO_LISTINGS_FILE/listings.txt -products $PATH_TO_PRODUCTS_FILE/products.txt -result $PATH_TO_OUTPUT_FILE/result.txt
```
Just replace the ```$PATH_TO_LISTINGS_FILE```, ```$PATH_TO_PRODUCTS_FILE``` and ```$PATH_TO_OUTPUT_FILE``` at the command the hit enter.

Example:

```text
java -jar target/matching-1.0.0.jar -listings src/main/resources/listings.txt -products src/main/resources/products.txt -result /Users/andersonfarias/Downloads/result.txt
```

You shoud see, in your console, the following output after the execution:

```text
Loading Data.
Executing Blocking.
Preparing tasks for parallel execution.
Executing.
Outputing result.
Done!
```

The result of the execution were saved at the specified result argument. 

If you have any doubts, you can execute the program with the ```-help``` parameter for help. It should print the following at your console:

```text
usage: matching
 -help             print the help
 -listings <arg>   input file with the listings
 -products <arg>   input file with the products
 -result <arg>     output file
```

## Building Matching

See [BUILDING.md](BUILDING.md) for instructions on how to build Matching from source.
