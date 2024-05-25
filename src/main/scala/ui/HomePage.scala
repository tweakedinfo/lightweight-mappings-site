package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.*
import acssite.Common

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

var homeMd = """
  |### Default home page text
  |
  |The home page text for this site hasn't been set yet. To set it, 
  |call `setHomePage(markdown)` from a script
""".stripMargin

@JSExportTopLevel("setHomePage")
def setHomePage(text:String) = 
  homeMd = text
  home.rerender()

object home extends VHtmlComponent {
  def render = <.div(Common.markdown(homeMd))
}