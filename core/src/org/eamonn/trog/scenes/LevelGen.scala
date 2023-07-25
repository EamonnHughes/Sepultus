package org.eamonn.trog
package scenes

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonn.trog.Scene
import org.eamonn.trog.procgen.{GeneratedMap, Level}

import scala.util.Random

class LevelGen(player: Player, game: Option[Game]) extends Scene {
  var cameraLocation: Vec2 = Vec2(0, 0)
  var genMap = GeneratedMap(45, 6, 10, .25f)
  var doneGenerating = false
  var level = new Level

  override def init(): InputAdapter = {
    new LevelGenControl(this)
  }
  override def update(delta: Float): Option[Scene] = {
    if (!doneGenerating) { doneGenerating = genMap.generate() }
    else {
      if (level.walkables.isEmpty) {
        level = genMap.doExport()
        player.location = level.upLadder.copy()
        player.destination = level.upLadder.copy()
      }
    }
    var gameNew = new Game(level, player)
    if (game.nonEmpty) {
      game.foreach(g => {
        gameNew = g
      })
    }
    gameNew.descending = false
    gameNew.enemies = List.empty
    gameNew.allSpawned = false
    gameNew.level = level

    if (doneGenerating && level.walkables.nonEmpty) {
      Some(gameNew)
    } else None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    Text.mediumFont.setColor(Color.WHITE)
    Text.mediumFont.draw(
      batch,
      "Generating Floor...",
      0,
      Geometry.ScreenHeight / 2
    )
  }
}
class LevelGenControl(gen: LevelGen) extends InputAdapter {}
