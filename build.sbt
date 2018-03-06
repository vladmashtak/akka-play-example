organization := "Metrotek"

name := "CentralMaster"

version := "1.0"

scalaVersion := "2.12.2"

val akkaVersion = "2.5.10"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
libraryDependencies ++= Seq(
  guice,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion
)

