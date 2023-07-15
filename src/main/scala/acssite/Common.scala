/**
 * Local copy of standard boilerplate for talks.
 *    Future library updates will make this unnecessary.
 */

package acssite

import com.wbillingsley.veautiful.html.{<, DElement, Markup, DHtmlContent, ^, VHtmlComponent}
import com.wbillingsley.veautiful.doctacular.{DeckBuilder, VSlides}

import scala.collection.mutable
import scala.scalajs.js

/**
 * Common UI components to all the views
 */
object Common {

  given markdownGenerator:Markup = new Markup({ (s:String) => js.Dynamic.global.marked.parse(s).asInstanceOf[String] })

  def markdown(s:String) = markdownGenerator.Fixed(s)

  /** A component that can load Markdown from a url and render it */
  case class LoadMd(url:String) extends VHtmlComponent {

    import scala.concurrent.ExecutionContext.Implicits.global
    import js.Thenable.Implicits._
    import org.scalajs.dom
    import scala.util.*

    val responseText = for
      response <- dom.fetch("home.md")
      text <- response.text()
    yield text

    override def render = 
      responseText.value match 
        case Some(Success(text)) => <.div(markdown(text))
        case Some(Failure(ex)) => <.div(ex.getMessage)
        case None => <.div(s"Loading markdown from $url")

    responseText.onComplete((_) => rerender())
  }

  def downloadFromGitHub(project:String, user:String="UNEcosc250"):DHtmlContent = {
    <.a(
      ^.cls := "btn btn-secondary",
      ^.href := s"https://github.com/$user/$project/archive/master.zip",
      ^.attr("aria-label") := s"Download $project as zip",
      <("i")(^.cls := "material-con", "cloud_download"), "Download"
    )
  }

  def downloadGitHubStr(project:String, user:String="UNEcosc250"):String = {
    s"<a href='https://github.com/$user/$project/archive/master.zip' aria-label='Download $project as zip'>Download the project as a zip file</i></a>"
  }

  def cloneGitHubStr(project:String, user:String="UNEcosc250"):String = {
    s"`git clone https://github.com/$user/$project.git`"
  }

  /** Used in many of Will's slide decks */
  val willCcBy:String =
    """
      |<p>Written by Will Billingsley</p>
      |
      |<a rel="license" href="http://creativecommons.org/licenses/by/3.0/au/">
      |  <img alt="Creative Commons Licence" style="border-width:0" src="https://i.creativecommons.org/l/by/3.0/au/88x31.png" /></a><br />
      |  This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/au/">Creative Commons Attribution 3.0 Australia License</a>.
      |""".stripMargin



}
