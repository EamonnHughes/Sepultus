package org.eamonn.trog
package scenes

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter

class HomeControl(home: Home) extends InputAdapter {
  override def touchDown(
      screenX: Int,
      screenY: Int,
      pointer: Int,
      button: Int
  ): Boolean = {
    true
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {

    true
  }

  override def keyDown(keycode: Int): Boolean = {

    true
  }

  override def keyUp(keycode: Int): Boolean = {
    if(keycode == Keys.ENTER) {
      home.player.ready = true
    }

    true
  }
}
