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
    val n = dom.document.getElementById("render-here")
    n.innerHTML = ""

    site.toc = site.Toc(
      "Home" -> site.HomeRoute,
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
      "Units" -> site.Toc(
        (for u <- courses.subjects yield
          s"${u.code} ${u.name}" -> site.addPage(s"Unit ${u.code}", courses.unitPage(u))
          ).toSeq:_*
      ),
    )

    site.home = () => site.renderPage(courses.home)
    Styles.installStyles()
    site.attachTo(n)
  }
}

val frontPage = unique(<.div(
  <.h1("A site for my ITiCSE Talk in 2021"),
  <.p(
    """
      | Bla bla bla
      |""".stripMargin
  )
))