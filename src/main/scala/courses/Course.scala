package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, ^, VHtmlContent}
import ui.Common

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import ui.*
import mappings.*

type CoursePage = (String, String, (Course) => VHtmlContent)

/**
  * A representation of a course (degree)
  *
  * @param code e.g. BCOMP
  * @param name e.g. Bachelor of Computer Science
  * @param structure the sequence of elements that need completing, e.g. majors and minors
  * @param plans possible orderings in which students might take the subjects (e.g. part-time starting in trimester 1)
  */
case class Course(
  code:String, name:String,
  structure: Plan,
  plans: Seq[(String, Plan)],
  pages: Seq[CoursePage] = Seq.empty
)


trait JSCourse extends js.Object:
  val code:String
  val name:String
  val structure:js.Array[JSPlanComponent]
  val plans:js.Dictionary[js.Array[JSPlanComponent]]
  val pages:js.UndefOr[js.Array[CoursePage]]

given Conversion[Seq[JSPrereqElement], Seq[PrereqElement]] with 
  def apply(j:Seq[JSPrereqElement]) = j.map {
    case s:String => PrereqElement.unit(s)
    case e:PrereqElement => e
  }

given toScala:Conversion[JSPlanComponent, PlanComponent] with
  def apply(j:JSPlanComponent) = (j.name, j.units.toSeq)

val courses = mutable.Buffer.empty[Course]

var handbookUrl: Option[String => String] = None

@JSExportTopLevel("setHandbookUrl")
def setHandbookUrl(f:js.Function1[String, String]) = 
  handbookUrl = Some(f)



@JSExportTopLevel("addCourse")
def addCourse(config:JSCourse): Unit = {
  try {
    val c = Course(
      code = config.code,
      name = config.name,
      structure = config.structure.map(toScala).toSeq,
      plans = for (name, jsplan) <- config.plans.toSeq yield
        name -> jsplan.map(toScala).toSeq,
      pages = config.pages.toOption.toSeq.flatten
    )

    courses.append(c)
  } catch {
    case x:Throwable =>
      println("Failed to load a course!")
      println(x.getMessage())
      // TODO: handle errors
  }
}

@JSExportTopLevel("addCourses")
def addCourses(courses:js.Array[JSCourse]): Unit = {
  for c <- courses do addCourse(c)
}



import ui.*

def planPage(c:Course) = <.div(
  <.h1(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
  Common.markdown(
    """ The structure of the course is illustrated below. 
      | Trimester-by-trimester plans are visualised further down the page. 
      | 
      | * Advanced units are tagged "Advanced"
      | * Integrated and Applied units (Criterion E) are tagged "Capstone" 
      |""".stripMargin
  ),
  <.h3("Structure"),
  PlanPrereqWidget(c.structure),
  <.h3("Trimester-by-trimester plans"),
  Common.markdown(
    """ 
      | * A thicker border indicates a mandatory unit. 
      |   Note that in some cases, there is a choice between two core units (causing neither to appear marked as mandatory here).
      |   These can be seen more clearly in the course structure illustration at the top of the page. 
      | * Fixed pre-requisites are shown in red. 
      | * Prerequisites that include some choice (i.e. assumed knowledge) are shown in grey.
      | * Click on a unit to show only prerequisite lines that unit is involved in.
      | * Click on the unit again (or on the background) to deselect it.
      |""".stripMargin
  ),
  <.div(
    HPlanChooser(c)
  )

)





