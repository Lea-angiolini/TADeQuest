package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import scala.util._
import com.sun.net.httpserver.Authenticator.Success
import java.util.NoSuchElementException

class HayEquipo extends TAdeQuestPrueba{
  
  // var ashe: Heroe = null
  //var pikachu: Heroe = null
  var iroito: Heroe = null
  //var guerrero: Trabajo = null
  //var mago: Trabajo = null
  //var ladron: Trabajo = null
  var equipo: Equipo = null
  
  @Before
  override def setup = {
    super.setup
   ashe = new Heroe("ashe",new Stats(100,30,40,60)) 
   pikachu = new Heroe("pikachu",new Stats(100,100,10,10))
   iroito = new Heroe("iroito", new Stats(100,100,100,1))
      
   pikachu.setTrabajo(Ladron)
   equipo = new Equipo("equipo charlie")
   }
  
  @Test
  def mejorHeroeSegun(){
    equipo = equipo.obtenerMiembro(ashe)
    equipo = equipo.obtenerMiembro(pikachu)
    
    assertTrue(equipo.mejoresHeroesSegun { heroe => Some(heroe.getStats.get(Inteligencia)) }.contains(ashe))
  }
  
  @Test
  def obtenerItem(){
    
  }
  
  @Test
  def obtenerMiembro(){
    equipo = equipo.obtenerMiembro(ashe)
    equipo = equipo.obtenerMiembro(pikachu)
    
    assertTrue(equipo.heroes.contains(ashe))
    assertTrue(equipo.heroes.contains(pikachu))
  }
  
  @Test
  def reemplazarMiembro(){
    equipo = equipo.obtenerMiembro(ashe)
    equipo = equipo.obtenerMiembro(pikachu)
    
    equipo = equipo.reemplazarMiembro(iroito, ashe)
    
    assertTrue(!equipo.heroes.contains(ashe))
    assertTrue(equipo.heroes.contains(pikachu))
    assertTrue(equipo.heroes.contains(iroito))
  }
  
  @Test
  def lider(){
    
    ashe = ashe.setTrabajo(Mago)
    
    equipo = equipo.obtenerMiembro(ashe)
    equipo = equipo.obtenerMiembro(pikachu)
    equipo = equipo.obtenerMiembro(iroito)
    
    assertTrue(equipo.lider() match{
      case Some(x) if x == ashe => true
      case _ => false
    })
    
    var soyIgualDeGrosaQueAshe = new Heroe("soyIgualDeGrosaQueAshe",new Stats(100,30,40,60)) 
    soyIgualDeGrosaQueAshe = soyIgualDeGrosaQueAshe.setTrabajo(Mago)
    
    equipo = equipo.obtenerMiembro(soyIgualDeGrosaQueAshe)
    
    assertTrue(equipo.lider() match{
      case Some(x) => false
      case None => true
    })
    
    var equipo2 = new Equipo("")
    
        
    assertTrue(equipo2.lider() match{
      case Some(x) => false
      case None => true
    })
    
  }
  
  @Test
  def hacerMisionPelearContraMounstro  {
    pikachu = pikachu.setTrabajo(Ladron)
    
    equipo = equipo.obtenerMiembro(coco)
    equipo = equipo.obtenerMiembro(ashe)
    equipo = equipo.obtenerMiembro(pikachu)
    
//    equipo.lider() match {
//      case Some(h) => println(h)
//      case None => println("no hay lider claro")
//    }
    
    var mision = new Mision(Set(pelearContraMonstruo), equipo => equipo.obtenerItem(armaduraElegante))
    
    //(Equipo,Throwable)
    var (equipo2,estado) = equipo.realizarMision(mision)

   assertTrue(equipo2.heroes.find { _.id == "pikachu" }.get.inventario.items("armadura") == armaduraElegante)
   assertEquals(65, equipo2.heroes.find { _.id == "pikachu" }.get.getStats.get(HP))
  }
  
  @Test
  def hacerMisionForzarPuerta{
    
    pikachu = pikachu.setTrabajo(Ladron)
    ashe = ashe.setTrabajo(Mago)
    coco = coco.setTrabajo(Guerrero)

    equipo = equipo.obtenerMiembro(ashe)
    equipo = equipo.obtenerMiembro(pikachu)
    equipo = equipo.obtenerMiembro(coco)
        
    var mision = new Mision(Set(forzarPureta), equipo => equipo.obtenerMiembro(iroito))
    var (eq,estado) = equipo.realizarMision(mision)
    
    
    assertTrue(compararStats(eq.heroes.find{_.id == "pikachu"}.get.getStats, new Stats(95,100,20,10)))
    assertTrue(compararStats(eq.heroes.find{_.id == "ashe"}.get.getStats, new Stats(100,10,40,80)))
    assertTrue(eq.heroes.contains(iroito))
    assertTrue(compararStats(new Stats(100,10,40,80),eq.heroes.find{_.id == "ashe"}.get.getStats))

  }
  
  @Test
  def hacerMisionRobarTalisman{
    
    pikachu = pikachu.setTrabajo(Ladron)
    
    equipo = equipo.obtenerMiembro(pikachu)
    
    //equipo = equipo.obtenerItem(talismanDelMinimalismo)
    var mision = new Mision(Set(new robarTalisman(talismanDedicacion)), equipo => equipo.obtenerItem(talismanMaldito))
    
    var (eq,es) = equipo.realizarMision(mision)
    //println(eq)
//    
    assertTrue(eq.heroes.find{_.id == "pikachu"}.get.inventario.talismanes.contains(talismanDedicacion))
  }
}