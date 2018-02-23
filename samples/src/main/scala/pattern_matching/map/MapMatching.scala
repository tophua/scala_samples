package pattern_matching.map

//# pattern-matching #map

object MapMatching extends App {

  val theMap = Map(
                      "name1"->"value1",
                      "name2"->"value2",
                      "name3"->"value3"
                  )

  // #1
    //转换为Map[Int，Int]
    val intMaps = theMap map {   // convert to Map[Int,Int]

      case ("name1", "value1" )  => (1,2)
      case _  => (0,0)

    }

    println( intMaps )  // Map(1 -> 2, 0 -> 0)


}
