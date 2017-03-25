lazy val commonSettings = Seq(
  version := "0.1.3",
  name := "likelib",
  organization := "com.stabletechs",
  scalaVersion := "2.12.1",
  crossScalaVersions := Seq("2.11.8", "2.12.1"),
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  libraryDependencies += "com.lihaoyi" %% "utest" % "0.4.5" % "test",
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
    publishMavenStyle := true,
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
    homepage := Some(url("http://stabletechs.com/")),
    licenses += ("MIT License", url("http://www.opensource.org/licenses/mit-license.php")),
    pomExtra := 
      <developers>
        <developer>
          <id>Voltaire</id>
          <name>Nick Childers</name>
          <url>https://github.com/voltir/</url>
        </developer>
      </developers>
    ,
    pomIncludeRepository := { _ => false } 
  )
  .jsSettings(
    scalaJSStage in Test := FullOptStage
  )

lazy val likelibJS = likelib.js

lazy val likelibJVM = likelib.jvm
