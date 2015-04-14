name := """play-ddp-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
	javaJdbc,
	javaEbean,
	cache,
	javaWs,
	"com.github.fdimuccio" %% "play2-sockjs" % "0.3.1"
)