Initial Setup Serpics!
===================

Maven Command
---------------------

To execute import data, we can use maven command with one of relative profile which indicate the type of data that system must import.
The command syntax is:

```
mvn exec:java -Dserpics.home=<**classpath**> [-P<**profile**>]
```

**classpath**
:   indicate the path which contains modules.xml file

**profile**
: indicate which type of date must to be imported. 
Values permitted are
**initAll**, **initSample**, **initProject**

Implementation of Task
------------------------

To create a new task to perform import data, we must extend the class *com.serpics.initialsetup.task.AbstractSystemTask* and implement two method 

**doExecuteProjectData**
: To execute when the import type is set to ALL or PROJECT

**doExecuteSampleData**
: To execute when the import type is set to ALL or SAMPLE