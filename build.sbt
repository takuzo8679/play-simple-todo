name := """playapp"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.14"

libraryDependencies ++=Seq(
  guice,
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "2.2.224",
  javaJpa,
  "org.hibernate" % "hibernate-core" % "6.5.2.Final"
)

// This will make sure the persistence.xml will always be in the build jar file
//   which is important when it comes to deploying the application somewhere
PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"