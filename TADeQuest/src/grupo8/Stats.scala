package grupo8

class Stats(hp: Int = 0,fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0) extends ModificadorDeStat{
  var mapStats: collection.mutable.Map[String,Int] = collection.mutable.Map("hp"-> hp, "fuerza"->fuerza, "velocidad"->velocidad, "inteligencia"->inteligencia)
  
  def getStats = {
    mapStats
  }
  
  def getStat(heroe: Heroe) = {
    List(this)
  }
  
  def get(nombre:String) = {
    mapStats(nombre)
  }
  
  def set(nombre:String, value: Int) {
    mapStats(nombre) = value
  }
  
  def +(stat: Stats): Stats = {
    for( n <- stat.getStats;
         m <- mapStats;
         if (m._1 == n._1) 
        )  
    {
      mapStats(m._1) += stat.getStats(m._1) 
    }
    
    this
  }
  
  def limite(min: Int = 1){
    for(m <- mapStats){
      if(m._2 < min)
        mapStats(m._1) = min
    }
  }
}

abstract class ModificadorDeStat {
  def getStat(heroe: Heroe): List[Stats] 
}