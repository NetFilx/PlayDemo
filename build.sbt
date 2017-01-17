name := "PlayDemo"

version := "1.0"

lazy val `playdemo` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.typesafe.play" %% "play-slick" % "2.0.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

//添加本地的仓库
resolvers += Resolver.mavenLocal

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  