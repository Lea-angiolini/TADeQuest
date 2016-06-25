package grupo8

import scala.util._
import scala.util.control.Breaks._

class Taberna(tablon: Tablon = Tablon(Set())) {
  
  def getTablon = tablon
  
  def entrenar(equipo: Equipo): Equipo = {
    var restantes = tablon
    var equipoEntrenando = equipo
    var mision: Mision = ???
    
    while(tablon.tieneMisiones){
      mision = tablon.elegirMision(equipo, (e1,e2) => e1.pozoComun > e2.pozoComun).get      
      equipoEntrenando = equipo.realizarMision(mision) match {
        case Success(e) => e
        case Failure(f) => break
      }
      restantes = tablon.sacarMision(mision)
    }
    
    equipoEntrenando
  }
}

