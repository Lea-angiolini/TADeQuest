package grupo8

case class Stats(hp: Int = 0,fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0) extends ModificadorDeStat{
  var mapStats: Map[String,Int] = Map("hp"-> hp, "fuerza"->fuerza, "velocidad"->velocidad, "inteligencia"->inteligencia)
  
  type A = Stats
  
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
    if(mapStats.contains(nombre))
      mapStats += (nombre -> value)
  }
  
  def +(stat: Stats): Stats = {
    val nuevoStat = new Stats
    for( n <- stat.getStats;
         m <- mapStats;
         if (m._1 == n._1) 
        )  
    {
      nuevoStat.set(n._1,n._2+m._2) 
    }
    
    nuevoStat
  }
  
  override def copiar = copy()
  
  def limite(min: Int = 1) = {
    for(m <- mapStats){
      if(m._2 < min)
        set(m._1, min)
    }
    this
  }
}

abstract class ModificadorDeStat extends Copiable {
  def getStat(heroe: Heroe): List[Stats] 
}