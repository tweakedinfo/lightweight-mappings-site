package courses

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

/**
  * A named part of a course of study. For example, a major, a minor, or a term of study
  */
type PlanComponent = (String, Seq[PrereqElement])

/**
  * A composition of plan components
  */
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



type JSPrereqElement = String | PrereqElement

trait JSPlanComponent extends js.Object:
  val name:String
  val units:js.Array[JSPrereqElement]
