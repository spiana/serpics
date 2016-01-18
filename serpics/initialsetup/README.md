Initial Setup Serpics!
===================

Maven Command
---------------------

To execute import data wecan use maven command with one of relative profile  which indicate the type of data that system must import.
The command syntax is:

```
mvn exec:java -Dserpics.home=<classpath> [-P<profile>]
```

classpath
:   indicate the path which contains modules.xml file

profile
: indicate which type of date must to be imported. 
Values permitted are
**initAll**, **initSample**, **initProject**