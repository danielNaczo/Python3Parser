# Python3Parser [![Build Status](https://travis-ci.org/danielNaczo/Python3Parser.svg?branch=master)](https://travis-ci.org/danielNaczo/Python3Parser)
A java-based Python3-Parser

The *Python3Parser*-Project allows you to parse arbitrary Python3-Code in Java. By generating abstract syntax trees (*ASTs*)
you can analyze, process and generate Python3-Code. This project offers a *PrettyPrinter* to transform ASTs back to
Python3 source code. 


## Import Python3Parser into your Project
To include the *Python3Parser* into your project just add the following *dependency* into your Maven-Project:

```
<dependency>
   <groupId>com.github.python3parser</groupId>
   <artifactId>python3parser</artifactId>
   <version>1.0.2</version>
</dependency>
```

Make sure you import the latest release, which you can find in https://github.com/danielNaczo/Python3Parser/releases.

When you use Gradle, import the following line into your Gradle-Project:

```
compile 'com.github.python3parser:python3parser:1.0.2'
```

If you have a Scala-based project you have to add the following lines into your *build.sbt* file:

```
libraryDependencies += "com.github.python3parser" % "python3parser" % „1.0.2“
resolvers += Resolver.bintrayRepo("danielnaczo", "Python3Parser")
