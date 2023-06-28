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

@JSExportTopLevel("ITICSESite")
object ITICSESite {
  /** The script that loads the site into the page */
  @JSExport
  @main def main(): Unit = {

    import site.given

    val n = dom.document.getElementById("render-here")
    n.innerHTML = ""

    site.toc = site.Toc(
      "Home" -> site.HomeRoute,
      "Presentations" -> site.Toc(
        // "Aug 2022" -> site.addDeck("aug2022", planning.aug2022deck),
        "ASCILITE 2022" -> site.addDeck("ascilite2022", planning.ascilite2022deck),
        "ACDICT 2023" -> site.addDeck("acdict2023", planning.acdict2023deck),
      ),


      "Course structures" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-struct-${u.code}", courses.planPage(u))
          ).toSeq:_*
      ),
      "CBOK Tables" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-cbok-${u.code}", courses.cbokPage(u))
          ).toSeq:_*
      ),

      "SWEBOK Tables" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-swebok-${u.code}", courses.swebokPage(u))
          ).toSeq:_*
      ),

      "DSBoK Pages" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-dsbok-${u.code}", courses.dsbokPage(u))
          ).toSeq:_*
      ),

      "Identity management" -> site.Toc(
        (for u <- courses.courses yield
          s"${u.code} ${u.name}" -> site.addPage(s"course-idverify-${u.code}", courses.idverifyPage(u))
          ).toSeq:_*
      ),


      /*"Units" -> site.Toc(
        (for u <- courses.subjects yield
          s"${u.code} ${u.name}" -> site.addPage(s"Unit ${u.code}", courses.unitPage(u))
          ).toSeq:_*
      ),*/
    )

    site.home = () => site.renderPage(courses.home)
    Styles.installStyles()
    site.attachTo(n)
  }
}
