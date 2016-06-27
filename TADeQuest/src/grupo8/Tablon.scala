package grupo8

import scala.util._

case class Tablon(misiones: Set[Mision] = Set[Mision]()) {
  
  def getMisiones = misiones
  
  def tieneMisiones = misiones.nonEmpty
  
  def tieneMision(mision: Mision) = misiones.contains(mision)
  
  def agregarMision(mision: Mision) = copy(misiones + mision)
  
  def sacarMision(mision: Mision) = copy(misiones - mision)
    
}