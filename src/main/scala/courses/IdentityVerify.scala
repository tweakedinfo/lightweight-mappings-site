package courses

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}

enum IdentityVerification(val name:String):
    case ProctoredExam extends IdentityVerification("Proctored online exam")
    case Video extends IdentityVerification("Video submissions")
    case PersonalisedAssessment extends IdentityVerification("Personalised assessment")
    case Project extends IdentityVerification("Project work")
    case GroupWork extends IdentityVerification("Group work")
    case DataTrails extends IdentityVerification("Data trails")

@JSExportTopLevel("idverify")
@JSExportAll 
object IDVerify {
    val ProctoredExam = IdentityVerification.ProctoredExam
    val Video = IdentityVerification.Video
    val PersonalisedAssessment = IdentityVerification.PersonalisedAssessment
    val Project = IdentityVerification.Project
    val GroupWork = IdentityVerification.GroupWork
    val DataTrails = IdentityVerification.DataTrails
}
