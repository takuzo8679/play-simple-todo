name := """playapp"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.14"

libraryDependencies += guice
libraryDependencies ++=Seq(
  guice,
  "org.apache.derby" % "derby" % "10.17.1.0"
)