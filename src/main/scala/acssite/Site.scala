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

    val courseTocs = (for course <- courses.courses.toSeq yield s"${course.name}" -> site.Toc(
      (
        "Course structure" -> site.addPage(s"${course.code}", courses.planPage(course))
      ) +: (
        for (title, frag, func) <- course.pages yield title -> site.addPage(s"${course.code}-${frag}", func(course))        
      )*

    ))  

    site.toc = site.Toc(
      (
        ("Home" -> site.HomeRoute) +: courseTocs

        /* An example of how to add a presentation deck to the site       
        "Presentations" -> site.Toc(
          // "My presentation" is the title to show in the Table of Contents
          // "my-presentation" is the URL fragment to use -- i.e. it'll render at decks/my-presentation
          // decks.myPresentation is the Scala code of the presentation
          "My presentation" -> site.addDeck("my-presentation", decks.myPresentation),
        ),
        */
        
      )*
    )

    site.home = () => site.renderPage(ui.home)
    Styles.installStyles()
    site.attachTo(n)
  }
}
