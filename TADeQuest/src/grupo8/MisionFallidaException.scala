package grupo8

class MisionFallidaException(tarea: Tarea) extends Exception{
  
  def getTarea = tarea
  
  override def toString = "No se pudo realizar la tarea '" + tarea.getDescripcion + "'"
}