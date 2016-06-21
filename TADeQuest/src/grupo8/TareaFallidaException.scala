package grupo8

class TareaFallidaException(tarea: Tarea) extends Exception{
  
  def getTarea = tarea
  
  override def toString = "No se pudo realizar la tarea '" + tarea.getDescripcion + "'"
}