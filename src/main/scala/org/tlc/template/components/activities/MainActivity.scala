package org.tlc.template.components.activities

import android.app.Activity
import android.os.Bundle
import android.widget.{Button, LinearLayout, TextView}
import macroid.Contexts
import macroid.FullDsl._
import org.tlc.template.ui.tweaks.MainTweaks

/**
 * Author: @aguestuser
 * Date: 4/22/15
 * License: GPLv2 (https://www.gnu.org/licenses/gpl-2.0.html)
 */

class MainActivity extends Activity with Contexts[Activity] { // include implicit contexts

  var greeting = slot[TextView]

  override def onCreate(savedInstanceState: Bundle) = {
    super .onCreate(savedInstanceState)

    val view = l[LinearLayout](
      w[Button] <~
        text("Click me") <~
        On.click {
          greeting <~ show
        },
      w[TextView] <~
        wire(greeting) <~
        MainTweaks.greeting("Hello!")
    ) <~ MainTweaks.orient

    setContentView { getUi { view } }
  }
}
