package acssite

import com.wbillingsley.veautiful.doctacular._
import com.wbillingsley.veautiful.html._
import org.scalajs.dom

import scala.scalajs.js.annotation._

/** The doctacular site */
val site = Site()

val scaleChallengesToWindow:Boolean = {
  !dom.window.location.search.contains("scale=off")
}


object ITICSESite {


  /** The script that loads the site into the page */
  @JSExportTopLevel("loadSite")
  def main(): Unit = {

    import site.given

    val n = dom.document.getElementById("render-here")
    n.innerHTML = ""

    site.toc = site.Toc(
      "Home" -> site.HomeRoute,

      /* An example of how to add a presentation deck to the site       
      "Presentations" -> site.Toc(
        // "My presentation" is the title to show in the Table of Contents
        // "my-presentation" is the URL fragment to use -- i.e. it'll render at decks/my-presentation
        // decks.myPresentation is the Scala code of the presentation
        "My presentation" -> site.addDeck("my-presentation", decks.myPresentation),
      ),
      */

      "Course structures" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-struct-${u.code}", courses.planPage(u))
          ).toSeq:_*
      ),
      "CBOK Tables" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-cbok-${u.code}", mappings.cbokPage(u))
          ).toSeq:_*
      ),

      "SWEBOK Tables" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-swebok-${u.code}", mappings.swebokPage(u))
          ).toSeq:_*
      ),

      "DSBoK Pages" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-dsbok-${u.code}", mappings.dsbokPage(u))
          ).toSeq:_*
      ),

      "Identity management" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-idverify-${u.code}", mappings.idverifyPage(u))
          ).toSeq:_*
      ),


      /*"Units" -> site.Toc(
        (for u <- courses.subjects yield
          s"${u.code} ${u.name}" -> site.addPage(s"Unit ${u.code}", courses.unitPage(u))
          ).toSeq:_*
      ),*/
    )

    site.home = () => site.renderPage(ui.home)
    Styles.installStyles()
    site.attachTo(n)
  }
}
