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
    
    mejoresHeroesSegun(criterio).headOption match {
      case Some(h)  => if (criterio(h) > 0 ) {reemplazarMiembro(h.equipar(item), h)} else {vender(item)}
      case None => {vender(item)}//TODO ver fold
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
    mejorHeroeSegun(heroe => heroe.getValorStatPrincipal.getOrElse(0))
  }
  
  def realizarTarea(tarea: Tarea): Try[Equipo] = {
    tarea.realizarla(this)
  }
  
  def realizarMision(mision: Mision): Try[Equipo] = {
    mision.realizarTareas(this)
  }
  
  def entrenar(tablon: Tablon, criterio: (Equipo,Equipo) => Boolean): Equipo = {
    var restantes = tablon
    var equipoEntrenando = this
    
    while(restantes.tieneMisiones){
      elegirMision(restantes, criterio) match {
        case Some(m) => {
                         equipoEntrenando = equipoEntrenando.realizarMision(m).get;
                         restantes = restantes.sacarMision(m)
                         }
        case None => restantes = new Tablon
      }      
      
    }
    
    equipoEntrenando
  }
  
  def elegirMision(tablon: Tablon, criterio: (Equipo,Equipo) => Boolean): Option[Mision] = {
        
    val misionElegida = tablon.getMisiones.reduce (
                      (provisoria,siguiente) =>      
                        (provisoria.realizarTareas(this), siguiente.realizarTareas(this)) match {
                        case (Failure(t),Success(equipo)) => siguiente
                        
                        case (Success(equipo1),Success(equipo2)) => {
                           if(criterio(equipo1,equipo2))
                               provisoria
                           else
                               siguiente
                            }
                        
                        case (_,Failure(t2)) => provisoria
                        })
                        
    misionElegida.realizarTareas(this) match {
      case Success(e) => Some(misionElegida)
      case Failure(t) => None
    }
  }

}