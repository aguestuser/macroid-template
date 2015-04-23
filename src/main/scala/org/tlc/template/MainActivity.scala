package org.tlc.template

import android.app.Activity
import android.os.Bundle
import android.widget._
import macroid.FullDsl._
import macroid._
import macroid.contrib._

import scala.language.postfixOps

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


// layouts are composable!
object MainLayouts {

  def layout1(implicit ctx: ActivityContext) =
    l[LinearLayout]( // `l` aliases `layout`
      w[TextView], // `w` aliases `widget`
      w[ImageView],
      w[Button]
    )

  def layout2(implicit ctx: ActivityContext) =
    l[FrameLayout](
      w[ProgressBar]
    )


  def comboLayout(implicit ctx: ActivityContext) =
    l[FrameLayout](
      layout1,
      layout2
    )
}

// tweaks are composable!
object MainTweaks
{

  def greeting(greeting: String)(implicit appCtx: AppContext) =
    TextTweaks.large +
      text(greeting) +
      hide

  def orient(implicit appCtx: AppContext) =
    landscape ? horizontal | vertical


}