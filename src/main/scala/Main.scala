import scala.util.matching.Regex
import scala.io.Source
enum Comparator:
  case LTU, GTU, LT, GT // Less than unsigned, Greater than unsigned, Less than, Greater than

object Jimmy {
  val COMPARATORS: Map[String, Comparator] = Map.apply(
    "<u"->Comparator.LTU,
    ">u"->Comparator.GTU,
    "<"->Comparator.LT,
    ">"->Comparator.GT
  )
  val ERROR_MESSAGE = "Git Gud"

  def err = println(ERROR_MESSAGE) 
}

def getLeftRight(a: scala.util.matching.Regex.Match, separator: String): (String, String) = 
  val l = a.toString().split(separator).map(_.filterNot(_.isWhitespace))
  (l(0), l(1))

def getLeftRightAndComparator(a: scala.util.matching.Regex.Match, markerStart: String, markerEnd: String): (String, String, Option[Comparator]) =
  val l = a.toString().replace(markerStart, "").replace(markerEnd, "").split(" ").map(_.filterNot(_.isWhitespace))
  (l(0), l(2), Jimmy.COMPARATORS.get(l(1)))

def compile(code: String, lineNumber: Int) =
""".*\+=.*""".r.findFirstMatchIn(code) match
  case Some(a) =>{
    val (left, right) = getLeftRight(a, "\\+=")

    """^\d*$""".r.findFirstMatchIn(right) match
      case Some(a) => {
        println(s"addi $$$left, $$$left, $right")
      }
      case None => {
        println(s"add $$$left, $$$left, $$$right")
      }
  }
  case None => {
    """.*=.*""".r.findFirstMatchIn(code) match
      case Some(a) => {
        val (left, right) = getLeftRight(a, "=")
        println(s"addi $$$left, $$zero, $right")
      }
      case None => {
        """if(.*)""".r.findFirstMatchIn(code) match
        case Some(a) => {
          """(.*)""".r.findFirstMatchIn(a.toString()) match
            case Some(a2) => {
              val (left, right, comparator) = getLeftRightAndComparator(a2, "(", ")")
              comparator match
                case Some(c) => {
                  c match {
                    case Comparator.LTU => println("Less than unsigned !")
                    case Comparator.GTU => println("Greater than unsigned !")
                    case Comparator.LT => println("Less than !")
                    case Comparator.GT => println("Greater than !")
                  }
                }
                case None => Jimmy.err
            }
            case None => println("ERROR: Nothing in the () of if statement")
        }
        case None => {
          """.*£.*""".r.findFirstMatchIn(code) match
            case Some(a) => {
              println(code.replace("£", "#"))
            }
            case None => Jimmy.err
        }

      }
  }

@main def hello(filename: String): Unit =
  var lineNumber: Int = 0
  for (line <- Source.fromFile(filename).getLines) {
      compile(line, lineNumber)
      lineNumber+=1
  }
