package courses
import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, ^}

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

import mappings.*
import ui.*

enum PrereqElement:
  @JSExportTopLevel("unit")
  case unit(code:String)

  case or(a:PrereqElement.unit, b:PrereqElement.unit)

  case choose(num:Int | (Int, Int), els:Seq[PrereqElement.unit])

  @JSExportTopLevel("cp")
  case cp(i:Int)

  case coreq(els:Seq[PrereqElement])

  private var _notes:Seq[(String, String)] = Seq.empty

  def notes = _notes

  @JSExport
  def withNote(symbol:String, note:String):PrereqElement = 
    _notes = _notes :+ (symbol -> note)
    this

@JSExportTopLevel("or")
def or(a:String, b:String) = PrereqElement.or(PrereqElement.unit(a), PrereqElement.unit(b))

@JSExportTopLevel("choose")
def choose(num: Int | js.Array[Int], els:String*) = num match {
  case i:Int => PrereqElement.choose(i, els.map(PrereqElement.unit.apply))
  case a:js.Array[Int] => 
    if a.length == 2 then PrereqElement.choose(a(0) -> a(1), els.map(PrereqElement.unit.apply)) else 
      throw new IllegalArgumentException("Choose ranges must contain a from and a to number (i.e. there must be two elements in the array)")
}

@JSExportTopLevel("coreq")
def coreq(els:JSPrereqElement*) = PrereqElement.coreq(els)

extension (u:PrereqElement.unit) {
  def toSubject = subjects.find(_.code == u.code).getOrElse(Subject.empty(u.code))
}

extension (el:PrereqElement) {
  // Produces a left-to-right Seq of the unit strings
  def flattened:Seq[PrereqElement.unit] = el match
    case PrereqElement.unit(code) => Seq(PrereqElement.unit(code))
    case PrereqElement.or(a, b) => Seq(a, b)
    case PrereqElement.choose(_, els) => els
    case PrereqElement.cp(_) => Seq.empty
    case PrereqElement.coreq(els) => els.flattened
} 

extension (els:Seq[PrereqElement]) {
  // Produces a left-to-right Seq of the unit strings
  def flattened:Seq[PrereqElement.unit] = els.flatMap(_.flattened)

  def stringify:String = 
    els.map({
      case s:PrereqElement.unit => s.code
      case PrereqElement.or(a, b) => s"($a or $b)"
      case PrereqElement.choose((from, to), units) => s"($from-$to from ${units.mkString(", ")})"
      case PrereqElement.choose(num, units) => s"($num from ${units.mkString(", ")})"
      case PrereqElement.cp(num) => s"${num}cp"
      case PrereqElement.coreq(els) => s"corequisite(${(els.stringify)})"
    }).mkString(", ")
}

def subjCodes(el:PrereqElement):Seq[String] = el match
  case s:PrereqElement.unit => Seq(s.code)
  case PrereqElement.or(a, b) => Seq(a.code, b.code)
  case PrereqElement.choose(_, units) => units.map(_.code)
  case PrereqElement.cp(_) => Seq.empty
  case PrereqElement.coreq(els) => els.flatMap(subjCodes)

def isMandatoryIn(code:String, el:PrereqElement):Boolean = el match
  case s:PrereqElement.unit => s.code == code
  case PrereqElement.choose((from, to), units) => to == units.length && units.contains(code)
  case PrereqElement.choose(num, units) => num == units.length && units.contains(code)
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
  def empty(s:String) = Subject("", s, "", Seq.empty, Seq.empty, Seq.empty, Seq.empty, Seq.empty, Seq.empty)
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
      prereq = config.prereq.asInstanceOf[js.Array[String | PrereqElement]].map({ 
        case s:String => PrereqElement.unit(s)
        case p:PrereqElement => p
      }).toSeq,
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

