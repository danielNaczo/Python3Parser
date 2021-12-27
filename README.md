# Python3Parser [![build](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven.yml/badge.svg)](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven.yml) [![Publish package to the Maven Central Repository](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven-publish.yml)
A java-based Python3-Parser

The *Python3Parser*-Project allows you to parse arbitrary Python3-Code in Java. By generating abstract syntax trees (*ASTs*)
you can analyze, process and generate Python3-Code. This project offers a *PrettyPrinter* to transform ASTs back to
Python3 source code. 


## Import Python3Parser into your Project
To include the *Python3Parser* into your project choose the latest version of the parser on https://search.maven.org/artifact/io.github.danielnaczo/python3parser and integrate the *Python3Parser* with the help of your build tool.

The following shows how you integrate the parser into the most common build tools. Make sure you replace the *X.X.X* with the latest version.

### Maven
To include the *Python3Parser* into your Maven project just add the following dependency into your *pom.xml*:

```
<dependency>
  <groupId>io.github.danielnaczo</groupId>
  <artifactId>python3parser</artifactId>
  <version>X.X.X</version>
</dependency>
```

### Gradle
When you use Gradle, add the following lines to your build.gradle:

```
dependencies {
    compile(group: 'io.github.danielnaczo', name: 'python3parser', version: 'X.X.X')
}
```

### Scala SBT
If you have a Scala-based project you have to add the following code into your *build.sbt* file:

```
libraryDependencies += "io.github.danielnaczo" % "python3parser" % "X.X.X"
```

If you use another build tool select the latest version on https://search.maven.org/artifact/io.github.danielnaczo/python3parser and integrate the parser with the corresponding lines of code.
