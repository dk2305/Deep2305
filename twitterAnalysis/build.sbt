// JMJPFU
// 25-Mar-2017 : This is the build.sbt file required for the Twitter project

	lazy val root = (project in file(".")).
	settings(
		name := "TwitterPopularTags",
		version := "1.0",
		scalaVersion := "2.11.8",
		mainClass in Compile := Some("myPackage.TwitterPopularTags")
		)

	libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % "1.5.2",
	"org.apache.spark" %% "spark-streaming" % "1.5.2" % "provided",
	"org.apache.spark" %% "spark-streaming-twitter" % "1.5.2"
	)

// META-INF discarding

	mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
	{
	case PathList("META-INF", xs @ _*) => MergeStrategy.discard
	case x => MergeStrategy.first
	}
}
retrieveManaged in ThisBuild := true
