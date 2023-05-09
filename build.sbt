// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.

ThisBuild / githubWorkflowBuild := Seq(
  WorkflowStep.Sbt(List("g8Test"), name = Some("Test generated template"))
)
ThisBuild / githubWorkflowJavaVersions := Seq(
  JavaSpec(JavaSpec.Distribution.Adopt, "8"),
  JavaSpec(JavaSpec.Distribution.Adopt, "11"),
  JavaSpec(JavaSpec.Distribution.Adopt, "17")
)
ThisBuild / githubWorkflowPublishTargetBranches := Seq.empty

lazy val root = project
  .in(file("."))
  .settings(
    name := "http4s-g8",
    Test / test := {
      val _ = (Test / g8Test).toTask("").value
    },
    scriptedLaunchOpts ++= List(
      "-Xms1024m",
      "-Xmx1024m",
      "-XX:ReservedCodeCacheSize=128m",
      "-Xss2m",
      "-Dfile.encoding=UTF-8"
    ),
    resolvers += Resolver.url(
      "typesafe",
      url("https://repo.typesafe.com/typesafe/ivy-releases/")
    )(Resolver.ivyStylePatterns),
  )
