package courses
import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, ^}

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

type PrereqElement = String | ComplexPrereqElement
enum ComplexPrereqElement:
  @JSExportTopLevel("or")
  case or(a:String, b:String)

  @JSExportTopLevel("choose")
  case choose(i:Int, els:String*)

  @JSExportTopLevel("cp")
  case cp(i:Int)

  @JSExportTopLevel("coreq")
  case coreq(els:PrereqElement*)

extension (el:PrereqElement) {
  // Produces a left-to-right Seq of the unit strings
  def flattened:Seq[String] = el match
    case s:String => Seq(s)
    case ComplexPrereqElement.or(a, b) => Seq(a, b)
    case ComplexPrereqElement.choose(_, els:_*) => els
    case ComplexPrereqElement.cp(_) => Seq.empty
    case ComplexPrereqElement.coreq(els:_*) => els.flattened
} 

extension (els:Seq[PrereqElement]) {
  // Produces a left-to-right Seq of the unit strings
  def flattened:Seq[String] = els.flatMap(_.flattened)

  def stringify:String = 
    els.map({
      case s:String => s
      case ComplexPrereqElement.or(a, b) => s"($a or $b)"
      case ComplexPrereqElement.choose(num, units:_*) => s"($num from ${units.mkString(", ")})"
      case ComplexPrereqElement.cp(num) => s"${num}cp"
      case ComplexPrereqElement.coreq(els:_*) => s"corequisite(${(els.stringify)})"
    }).mkString(", ")
}

def subjCodes(el:PrereqElement):Seq[String] = el match
  case s:String => Seq(s)
  case ComplexPrereqElement.or(a, b) => Seq(a, b)
  case ComplexPrereqElement.choose(_, units:_*) => units
  case ComplexPrereqElement.cp(_) => Seq.empty
  case ComplexPrereqElement.coreq(els:_*) => els.flatMap(subjCodes)

def isMandatoryIn(code:String, el:PrereqElement):Boolean = el match
  case s:String => s == code
  case ComplexPrereqElement.choose(num, units:_*) => num == units.length && units.contains(code)
  case _ => false

/**
 * The definition of a Unit.
 * Note - we can't call this Unit because Unit is a Scala core type!
 */
case class Subject(
 code:String,
 name:String,
 handbookUrl:String,
 cbok: Seq[(CBOK, Int)],
 swebok: Seq[SWEBOK],
 dsbok: Seq[GridCategory],
 other: Seq[GridCategory],
 sfia: Seq[String],
 prereq: Seq[PrereqElement],
 tags: Seq[String] = Seq.empty
) {
  def cbokLevel(cat:CBOK):Int =
    cbok.find(_._1 == cat).map(_._2).getOrElse(0)
}

object Subject {
  def empty(s:String) = Subject("", s , "", Seq.empty, Seq.empty, Seq.empty, Seq.empty, Seq.empty, Seq.empty)
}

val subjects = mutable.Buffer.empty[Subject]

/**
 * Allows our units.js script to add a Unit from JavaScript
 */
@JSExportTopLevel("addUnit")
def addUnit(config:js.Dynamic) = {
  try {
    import js.JSConverters._
    import js.DynamicImplicits.truthValue

    val s = Subject(
      code = config.code.asInstanceOf[String],
      name = config.name.asInstanceOf[String],
      handbookUrl = "",
      cbok = config.cbok.asInstanceOf[js.Array[(CBOK, Int)]].toSeq,
      swebok = if config.swebok then config.swebok.asInstanceOf[js.Array[SWEBOK]].toSeq else Seq.empty,
      dsbok = if config.dsbok then config.dsbok.asInstanceOf[js.Array[GridCategory]].toSeq else Seq.empty,
      other = if config.other then config.other.asInstanceOf[js.Array[GridCategory]].toSeq else Seq.empty,
      sfia = config.sfia.asInstanceOf[js.Array[String]].toSeq,
      prereq = config.prereq.asInstanceOf[js.Array[PrereqElement]].toSeq,
      tags = if config.tags then config.tags.asInstanceOf[js.Array[String]].toSeq else Seq.empty
    )

    subjects.append(s)
  } catch {
    case _ => // TODO: something with the errors
  }
}

@JSExportTopLevel("addUnits")
def addUnits(units:js.Array[js.Dynamic]) = {
  for u <- units do addUnit(u)
}


def unitPage(u:Subject) = <.div(
  <.h1(u.code, " ", u.name),
  <.p(
    <.a(^.href := s"https://handbook.une.edu.au/units/2022/${u.code}?year=2022", "Link to handbook entry")
  ),
  <.h3("CBOK Categories"),
  <.table(
    <.tr(<.th("Category"), <.th("Level")),
    for (cbok, l) <- u.cbok yield
      <.tr(<.td(cbok.name), <.td(l.toString))
  )

)

// Composes a name like ICT100 Computational Thinking from a unit code
// If there's not a unit with that code in the subjects list, leaves the string unchanged
// so Elective stays as Elective
def unitText(code:String) =
  subjects.find(_.code == code).map(s => s"$code ${s.name}").getOrElse(code)

