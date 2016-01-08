name := "sbt-introduction"

version := "0.1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases" at "http://oss.sonatype.org/content/repositories/releases")

libraryDependencies ++= Seq("org.specs2" %% "specs2" % "3.7" % "test")