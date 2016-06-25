package grupo8

class TareaFallidaException(tarea: Tarea, equipo: Equipo) extends Exception{
  
  def getTarea = tarea
  
  override def toString = "El equipo '"+ equipo.getNombre + 
                          "' no pudo realizar la tarea '" + tarea.getDescripcion + "'"
}