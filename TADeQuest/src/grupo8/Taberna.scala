package grupo8

import scala.util._
import scala.util.control.Breaks._

class Taberna(tablon: Tablon = Tablon(Set())) {
  
  def getTablon = tablon
  
  def entrenar(equipo: Equipo, criterio: (Equipo,Equipo) => Boolean): Equipo = {
    var restantes = tablon
    var equipoEntrenando = equipo
    
    
    while(restantes.tieneMisiones){
      restantes.elegirMision2(equipoEntrenando, criterio) match {
        case Some(m) => {
                         equipoEntrenando = equipoEntrenando.realizarMision(m).get;
                         restantes = restantes.sacarMision(m)
                         }
        case None => restantes = new Tablon
      }      
      
    }
    
    equipoEntrenando
  }
}

