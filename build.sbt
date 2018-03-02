name := """akka-central"""

organization := "central"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.4"

val akkaVersion = "2.5.10"

lazy val root = (project in file(".")).enablePlugins(PlayJava).settings(
  libraryDependencies ++= Seq(
    guice,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion
  )
)
