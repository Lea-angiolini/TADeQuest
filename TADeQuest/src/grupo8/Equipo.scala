package grupo8

import scala.util._

case class Equipo(nombre: String, heroes: List[Heroe] = List(), pozoComun: Int = 0){
  
  def adherirAlPozoComun(valor: Int): Equipo = copy(pozoComun = pozoComun+valor)
 
  def getNombre: String = nombre
  
  def getHeroes: List[Heroe] = heroes
  
  def vender(item: Item): Equipo = {
    adherirAlPozoComun(item.getValor)
  }
    
  def mejoresHeroesSegun(cuantificador: (Heroe => Int)): List[Heroe] = {
    heroes.filter { cuantificador(_) == heroes.map { cuantificador(_) }.max }
  }
  
  def mejorHeroeSegun(cuantificador: (Heroe => Int)): Option[Heroe] = { 
    mejoresHeroesSegun(cuantificador) match {
      case h :: Nil => Some(h)
      case _ => None
    }
  }
  
  def obtenerItem(item: Item): Equipo = {
    val criterio = { heroe: Heroe => heroe.equipar(item).getValorStatPrincipal.getOrElse(0) - heroe.getValorStatPrincipal.getOrElse(0)}
    
    mejoresHeroesSegun(criterio).headOption
                                .filter(criterio(_) > 0 )
                                .fold(vender(item))(h => reemplazarMiembro(h.equipar(item), h))
  }
 
  def obtenerMiembro(heroe: Heroe): Equipo = {
    if(!heroes.exists {_.id == heroe.id})
      copy(heroes = heroes.+:(heroe))
    else
      this
  }
  
  def reemplazarMiembro(miembroNuevo: Heroe, miembroViejo: Heroe): Equipo = {
    var listaNueva = heroes.filter { _.id != miembroViejo.id }
    copy(heroes = listaNueva.+:(miembroNuevo)) 
  } 
  
  def lider():Option[Heroe] = {
    mejorHeroeSegun(heroe => heroe.getValorStatPrincipal.getOrElse(0))
  }
  
  def realizarTarea(tarea: Tarea): Try[Equipo] = {
    tarea.realizarla(this)
  }
  
  def realizarMision(mision: Mision): Try[Equipo] = {
    mision.realizarTareas(this)
  }
  
  def elegirMision(tablon: Tablon, criterio: (Equipo,Equipo) => Boolean): Option[Mision] = {
        
    val misionesRealizables = tablon.getMisiones.map { mision => (mision,mision.realizarTareas(this)) }
                                                .filter { TuplaMisionEstado => TuplaMisionEstado._2.isSuccess }
                                                
    val comparador = Ordering.fromLessThan[Equipo]((equipo1,equipo2) => criterio(equipo1,equipo2)).reverse
    
    Try(misionesRealizables.maxBy(TuplaMisionEstado => TuplaMisionEstado._2.get)(comparador)._1).toOption
     
  }
  
  def entrenar(tablon: Tablon, criterio: (Equipo,Equipo) => Boolean): Equipo = {
    //tablon.realizarMisiones(elegirMision(_, criterio))(this)(realizarMision(_).get)
    tablon.realizarMisionesConCriterio(elegirMision(_, criterio))(this)
  }

}