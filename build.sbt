scalaVersion := "3.1.0"

enablePlugins(ScalaNativePlugin)
import scala.scalanative.build._

nativeConfig ~= {
  _.withLTO(LTO.thin)
    .withMode(Mode.releaseFast)
    .withGC(GC.commix)
}