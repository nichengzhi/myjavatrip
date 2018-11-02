package scala_learin

object formatout {
  // f: Int => Int transform a ineger to another integer, take function as output
  def transform(x: Int, f: Int => Int): Int ={
    f(x)
  }
  def main(args: Array[String]){
    val piSinglePrecision : Float = 3.14159265f
    val numberOne : Int = 1                         //> numberOne  : Int = 1
    val truth : Boolean = true                      //> truth  : Boolean = true
    val letterA : Char = 'a' 
    println(f"Pi is about $piSinglePrecision%.3f")
    //add f is format output like printf in other language
     // Substituting in variables:
    println(s"I can use the s prefix to use variables like $numberOne $truth $letterA")
    //use s to take variable in string
    //> I can use the s prefix to use variables like 1 true a
    // Substituting expressions (with curly brackets):
    //{} also can put variable and equation
    println(s"The s prefix isn't limited to variables; I can include any expression. Like ${1+2}")
    println(s"${truth}")
    // Using regular expressions:
    val theUltimateAnswer: String = "To life, the universe, and everything is 42."
                                                    //> theUltimateAnswer  : String = To life, the universe, and everything is 42.
                                                    //| 
    val pattern = """.* ([\d]+).*""".r              //> pattern  : scala.util.matching.Regex = .* ([\d]+).*
    val pattern(answerString) = theUltimateAnswer   //> answerString  : String = 42
    val answer = answerString.toInt                 //> answer  : Int = 42
    println(answer)  
    val number = 4                                  //> number  : Int = 3
    number match {
    	case 1 => println("One")
    	case 2 => println("Two")
    	case 3 => println("Three")
    	case _ => println("Something else")//_is the default condition
 	  }   
    //lambda as an input
    println(transform(3, (x: Int) => x *3))
    transform(2, x => {val y = x * 2; y * y})
    val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
    println(shipMap("Kirk"))
    println(shipMap.contains("nige"))
    println(util.Try(shipMap("Archer")) getOrElse "Unknown")
  }
  
}