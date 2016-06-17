package grupo8

import scala.util._

class Mision(tareas: Set[Tarea]) {
  
  def getTareas = tareas
}


abstract class Tarea(facilidad: (Equipo,Heroe) => Option[Int], cambios: Heroe => Unit){

  def getFacilidad = facilidad
  
  def puedeRealizarla(equipo: Equipo, heroe: Heroe): Boolean = {
    facilidad(equipo, heroe) match {
      case Some(int) => true
      case None => false
    }
  }
  
  def facilidadPara(equipo:Equipo, heroe: Heroe): Option[Int] = {
    facilidad(equipo, heroe)
  }
  
  def realizarla(heroe: Heroe) {println(heroe.getStatBase);cambios(heroe);heroe.getStatBase.set("hp", 9838483);println(heroe.getStatBase)}
  
}

class pelearContraMonstruo() extends Tarea({(e,h) => e.lider() match {
                                                                      case Some(h) if(h.getTrabajo.descripcion == "Guerrero") => Some(20)
                                                                      case _ => Some(10)
                                                                      }} , 
                                            {(x) => println(x.getStatBase.get("hp")); x.getStatBase.set("hp", x.getStatBase.get("hp") - 1); println(x.getStatBase.get("hp"))})