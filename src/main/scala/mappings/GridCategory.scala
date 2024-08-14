package mappings

import scala.collection.mutable
import scala.scalajs.js.annotation.JSExportTopLevel
import scalajs.js

/**
  * A grid-like mapping of categories against units
  */
trait Grid {
  def categories:Seq[GridCategory]

  def name:String
}

/**
  * A grid that takes a numerical value against each category
  */
trait LevelledGrid extends Grid

/**
  * Represents a column of a mapping grid
  */
trait GridCategory {
  def name:String
  def css:String

  def jsName:String
} 

/**
  * Limits the units that are shown for a particular Grid Category for a course
  */
@JSExportTopLevel("limits")
val limitCourseGridEntries:mutable.Map[String, Map[GridCategory, Seq[String]]] = mutable.Map.empty

@JSExportTopLevel("printCourseCategoryLimits")
def printCategoryLimits() = 
  println(limitCourseGridEntries)

@JSExportTopLevel("limitCourseGridEntries")
def limitCategory(courses:js.Array[String], category:GridCategory, units:js.Array[String]) = {
  for course <- courses do
    limitCourseGridEntries(course) = limitCourseGridEntries.getOrElse(course, Map.empty) + (category -> units.toSeq)
}

