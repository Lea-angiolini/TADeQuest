package grupo8

class Taberna(tablon: Set[Mision] = Set[Mision]()) {
  
  def getTablon = tablon
  
  def elegirMision(equipo: Equipo): Boolean = true
}