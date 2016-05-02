# ENCOURAGE

Encourager - the library managing encoding/decoding of smart grid data, and the connection to the RabbitMQ bus. Usage:
```
ant jar
```
It creates the file dist\Encourager.jar, which contains all the functionalities of the Encourager library. After that, it is necessary to copy the file as libs\encourager\Encourager.jar under the other projects folders.


VirtualDevicesModule - the module that takes care of managing the data of the smart grid - see [1]. Usage:
```
ant compile
ant run
```

SupervisoryControlApplicationDummy - a dummy application that acts as a minimal Supervisory Control. It receives request for energy saving actions, it waits 100 ms, afterwards it sends a message. Usage:

```
ant compile
ant run
```

VirtualDevicesModuleCreateDevices - create a simple structure of macrocells / cells / rooms / appliances / devices for the VD module. Usage:

```
ant compile
ant run
```

VirtualDevicesModuleDatabaseHandler - manages the interaction with the DB for logging all smart grid events. It can be used with a real DB, or without it; inthis last case if for testing purposes only, and logging is not done. Usage:

```
ant compile
EITHER: ant -Dapplication.args=withDB run OR ant -Dapplication.args=noDBminimal run
```

MiddlewarePluginDummy - dummy MPG, for testing purposes. A number of MPGs are set up, then a master schedules the execution of the tests. It is necessary to do some programming in the files:

- MultipleMPGTest.java for defining which messages are exchanged, and 

- MultipleMPGTestDriver.java to schedule the messages sent by the MPGs. Usage:

```
ant compile
A NUMBER OF: ant -Dapplication.args=NAME_OF_MPG run
FINALLY, THE MASTER: ant -Dmain.class=encourageperformancetestapp.MultipleMPGTestDriver run
```

Configuration of the RabbitMQ: use the file rabbit config - vhost.json as in:

https://www.rabbitmq.com/img/management/import-export.png

