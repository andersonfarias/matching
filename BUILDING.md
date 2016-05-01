## Building Matching

### Unix / OS X

Prerequisites:

* `Java 8 JDK`
* `Maven` 3.2 or newer

Go to the source folder where you cloned the repo and execute the following command:

```text
$ mvn package
```
You should see something similiar to this:

```text
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 10.538 s
[INFO] Finished at: 2016-05-01T15:44:04-03:00
[INFO] Final Memory: 11M/126M
[INFO] ------------------------------------------------------------------------
```

This will download the project's dependencies, compile the source files and produce a executable .jar file called **matching-1.0.0.jar** at the target folder.

You can now use this file to execute the program.
