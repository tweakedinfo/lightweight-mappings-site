package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._

enum IdentityVerification(val name:String) extends GridCategory:
    case ProctoredExam extends IdentityVerification("Proctored online exam")
    case TurnItIn extends IdentityVerification("TurnItIn")
    case Oral extends IdentityVerification("Oral or live assessment")
    case Video extends IdentityVerification("Video submissions")
    case PersonalisedAssessment extends IdentityVerification("Personalised assessment")
    case Project extends IdentityVerification("Project work")
    case GroupWork extends IdentityVerification("Group work")
    case DataTrails extends IdentityVerification("Data trails")

    def css = "identity"

@JSExportTopLevel("idverify")
@JSExportAll 
val idverify = (for e <- IdentityVerification.values yield e.productPrefix -> e).toMap.toJSDictionary