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
    val funcion = { heroe: Heroe => heroe.equipar(item).getStatPrincipal.getOrElse(0) - heroe.getStatPrincipal.getOrElse(0) }
    
    mejorHeroeSegun(funcion) match {
      case Some(h)  => if (funcion(h) > 0 ) {copy(heroes = heroes.filterNot(x => x.id == h.id).+:(h.equipar(item)))} else {vender(item)}
      case None => {vender(item)}
    }
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
    mejorHeroeSegun(heroe => heroe.getStatPrincipal.getOrElse(0))
  }
  
  def realizarTarea(tarea: Tarea): Try[Equipo] = {
    tarea.realizarla(this)
  }
  
  def realizarMision(mision: Mision): (Equipo,Throwable) = {
    mision.realizarTareas(this)
  }

}