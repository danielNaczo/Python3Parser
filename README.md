# Python3Parser [![build](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven.yml/badge.svg)](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven.yml) [![Publish package to the Maven Central Repository](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/danielNaczo/Python3Parser/actions/workflows/maven-publish.yml)
A java-based Python3-Parser

The *Python3Parser*-Project allows you to parse arbitrary Python3-Code in Java. By generating abstract syntax trees (*ASTs*)
you can analyze, process and generate Python3-Code. This project offers a *PrettyPrinter* to transform ASTs back to
Python3 source code. 


## Import Python3Parser into your Project --- OUTDATED (is currently migrated from JCenter to Maven Central)
To include the *Python3Parser* into your project just add the following *dependency* into your Maven-Project:

```
<dependency>
   <groupId>com.github.python3parser</groupId>
   <artifactId>python3parser</artifactId>
   <version>1.0.6</version>
</dependency>
```

Make sure you import the latest release, which you can find in https://github.com/danielNaczo/Python3Parser/releases.

When you use Gradle, add the following lines to build.gradle:

```
repositories {
    mavenLocal()
}

dependencies {
    compile(group: 'com.github.python3parser', name: 'python3parser', version: '1.0.6')
}
```

If you have a Scala-based project you have to add the following lines into your *build.sbt* file:

```
libraryDependencies += "com.github.python3parser" % "python3parser" % "1.0.6"
resolvers += Resolver.mavenLocal
