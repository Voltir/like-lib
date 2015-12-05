lazy val commonSettings = Seq(
  version := "0.1.0",
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
  .settings(
    scmInfo := Some(ScmInfo(
      url("https://github.com/Voltir/like-lib"),
      "scm:git:git@github.com/Voltir/like-lib.git",
      Some("scm:git:git@github.com/Voltir/like-lib.git"))
    ),
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
       Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    sonatypeProfileName := "com.stabletechs",
    pomExtra := (
      <developers>
        <developer>
          <id>Voltaire</id>
          <name>Nick Childers</name>
          <url>https://github.com/voltir/</url>
        </developer>
      </developers>
    ),
    pomIncludeRepository := { _ => false } 
  )
  .jsSettings(
    scalaJSStage in Test := FullOptStage
  )

lazy val likelibJS = likelib.js

lazy val likelibJVM = likelib.jvm
