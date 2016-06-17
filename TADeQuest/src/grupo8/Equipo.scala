package grupo8

case class Equipo(nombre: String, var heroes: List[Heroe] = List()) extends Copiable{
  
  type A = Equipo
  override def copiar = copy(heroes = heroes.map(_.copiar))
  
  var pozoComun: Int = 0
   
  def pozoComun_+=(valor: Int) = pozoComun += valor
 
  def getNombre: String =   nombre
  
  def getHeroes: List[Heroe] = heroes
  
  def vender(item: Item) {
    //pozoComun_+=(item.valor)
  }
  
  def ObtenerItem(item: Item){
    val funcion = { heroe: Heroe => heroe.copiar.equipar(item).getStatPrincipal - heroe.getStatPrincipal }
    
    val mejorHeroe = mejorHeroeSegun { funcion }
    mejorHeroe match {
      case x :: _ if funcion {x} > 0 => x.equipar(item)
      case _ => vender(item)
    }
  }
  
  def obtenerMiembro(heroe: Heroe){
    this.heroes = this.getHeroes ::: List(heroe)
  }
  
  def reemplazarMiembro(miembroNuevo: Heroe, miembroViejo: Heroe){
    this.heroes = this.getHeroes.filter(_ != miembroViejo)
    this.obtenerMiembro(miembroNuevo) 
  }
  
  def mejorHeroeSegun(cuantificador: (Heroe => Int)): List[Heroe] = {  
    heroes.filter { cuantificador(_) == heroes.map { cuantificador(_) }.max }       
  }
  
  def lider():Option[Heroe] = {
    val lista = this.mejorHeroeSegun { heroe => heroe.getStatPrincipal }
    
    return lista match {
      case valor :: Nil => Some(valor)
      case _ => None
    }  
  }
}