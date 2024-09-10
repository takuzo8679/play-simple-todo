name := """playapp"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.14"

libraryDependencies ++=Seq(
  guice,
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "2.2.224"
)