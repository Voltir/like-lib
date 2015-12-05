
lazy val commonSettings = Seq(
  organization := "com.stabletechs",
  version := "0.0.1-SNAPSHOT",
  name := "likelib",
  scalaVersion := "2.11.7",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  libraryDependencies += "com.lihaoyi" %% "utest" % "0.3.1",
  testFrameworks += new TestFramework("utest.runner.Framework")
)

val likelib = crossProject.in(file("."))
  .settings(commonSettings:_*)

lazy val root = project.in(file("."))
  .settings(commonSettings:_*)
  .aggregate(likelibJS,likelibJVM)

lazy val likelibJS = likelib.js

lazy val likelibJVM = likelib.jvm
