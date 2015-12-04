
lazy val commonSettings = Seq(
  organization := "com.stabletechs",
  version := "0.0.1-SNAPSHOT",
  name := "likelib",
  scalaVersion := "2.11.7"
)

val likelib = crossProject.in(file("."))
  .settings(commonSettings:_*)

lazy val root = project.in(file("."))
  .settings(commonSettings:_*)
  .aggregate(likelibJS,likelibJVM)

lazy val likelibJS = likelib.js

lazy val likelibJVM = likelib.jvm
