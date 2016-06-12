package grupo8

class Stats(hp: Int,fuerza: Int, velocidad: Int, inteligencia: Int) {
  var mapStats: collection.mutable.Map[String,Int] = collection.mutable.Map("hp"-> hp, "fuerza"->fuerza, "velocidad"->velocidad, "inteligencia"->inteligencia)
  
  def getStats = {
    mapStats
  }
  
  def get(nombre:String) = {
    mapStats(nombre)
  }
  
  def set(nombre:String, value: Int) {
    mapStats(nombre) = value
  }
  
  def +=(stat: Stats): Stats = {
    for( n <- stat.getStats;
         m <- mapStats;
         if (m._1 == n._1) 
        )  
    {
      mapStats(m._1) += stat.getStats(m._1) 
    }
    
    this
  }
}