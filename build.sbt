organization := "Metrotek"

name := "CentralMaster"

version := "1.0"

scalaVersion := "2.12.2"

val akkaVersion = "2.5.10"

lazy val messages = project.in(file("akka-play-spring-messages"))

lazy val root = (project in file(".")).dependsOn(messages).enablePlugins(PlayJava)
libraryDependencies ++= Seq(
  guice,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion
)

