name := "FileStream"
version := "1.0"
scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.1"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.2.1"

mergeStrategy in assembly :={
         case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
        case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
        case "log4j.properties"                                  => MergeStrategy.discard
        case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
        case "reference.conf"                                    => MergeStrategy.concat
        case _                                                   => MergeStrategy.first

}

