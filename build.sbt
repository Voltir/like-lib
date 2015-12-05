lazy val commonSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  name := "likelib",
  organization := "com.stabletechs",
  scalaVersion := "2.11.7",
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  libraryDependencies += "com.lihaoyi" %% "utest" % "0.3.1" % "test",
  testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val root = project.in(file("."))
  .settings(commonSettings:_*)
  .settings(
    publish := {},
    publishLocal := {}
  )
  .aggregate(likelibJS,likelibJVM)

lazy val likelib = crossProject.in(file("."))
  .settings(commonSettings:_*)
  .jsSettings(
    scalaJSStage in Test := FullOptStage
  )


lazy val likelibJS = likelib.js

lazy val likelibJVM = likelib.jvm
