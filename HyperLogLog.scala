import scala.io.Source
import scala.util.hashing.{MurmurHash3 => mh3}
import scala.math._

object HyperLogLog {
  
  def rho (x:Int):Int = {//Function rho to get number of leading zeros. 
    Integer.numberOfLeadingZeros(x) + 1
  }
  
  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      val alpha = 0.7203
      val m = pow(2,10).toInt
      val file = args(0)
      val fileSource = Source.fromFile(file)
      val words = fileSource.mkString.split("\\W+").map(_.toLowerCase).toList
      fileSource.close()
      val Zj = Array.fill(m)(0)//Array of buckets.
      val hashes = words.map(s=>mh3.stringHash(s))

      //Stochastic Counting Algorithm used to approximately count distinct elements in a list.
      for(i<-hashes){
        val temp = rho(i)
	//Splitting whole list into buckets to achieve greater precision.
        val index = abs(i%m)
        if(Zj(index)<temp)
          Zj(index) = temp
      }

      //Calculating mean value of distinct elements in each bucket.
      val nZj = Zj.map(pow(2,_))
      
      //harmonic mean of distinct elements from buckets multiplied by number of buckets.
      val harmonicZ = (m*m/nZj.map(1/_).sum*alpha).toInt

      println(s"Number of distinct words: $harmonicZ")
    }
  }
}