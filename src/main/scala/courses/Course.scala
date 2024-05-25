package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, ^}
import ui.Common

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import ui.*
import mappings.*

type PlanComponent = (String, Seq[PrereqElement])
type Plan = Seq[PlanComponent]

extension (p:Plan) {
  def findComponentName(s:PrereqElement.unit):Option[String] = 
    p.find((_, els) => els.flattened.contains(s)).map((name, _) => name)
}

def isMandatoryInPC(unit:Subject, planComponent: PlanComponent) =
  val (_, els) = planComponent
  els.exists(p => isMandatoryIn(unit.code, p))

def isMandatoryInCourse(unit:Subject, course: Course) =
  course.structure.exists {
    case (_, els) => 
      els.exists(p => isMandatoryIn(unit.code, p))   
  }

// Turns a plan into a sequence of the main string in each row.
// Used to work out what row a unit is in, when drawing pre-req arrows
def flatStrings(plan: Plan):Seq[String] =
  plan.flatMap {
    case (name, els) => Seq(name) ++ els.flatMap(subjCodes)
  }


case class Course(
  code:String, name:String,
  structure: Plan,
  plans: Seq[(String, Plan)]
)

// Limits the rows shown in the CBOK table for a course
val topCbok:mutable.Map[String, Map[CBOK, Seq[String]]] = mutable.Map.empty

type JSPrereqElement = String | PrereqElement

trait JSPlanComponent extends js.Object:
  val name:String
  val units:js.Array[JSPrereqElement]

trait JSCourse extends js.Object:
  val code:String
  val name:String
  val structure:js.Array[JSPlanComponent]
  val plans:js.Dictionary[js.Array[JSPlanComponent]]

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
        name -> jsplan.map(toScala).toSeq
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

@JSExportTopLevel("limitCBOK")
def limitCbok(courses:js.Array[String], category:CBOK, units:js.Array[String]) = {
  for course <- courses do
    topCbok(course) = topCbok.getOrElse(course, Map.empty) + (category -> units.toSeq)
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





