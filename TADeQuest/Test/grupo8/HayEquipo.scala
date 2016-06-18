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
   ashe = new Heroe(new Stats(100,30,40,60)) 
   pikachu = new Heroe(new Stats(100,100,10,10))
   iroito = new Heroe(new Stats(100,100,100,1))
   
   guerrero = new Guerrero
   mago = new Mago
   ladron = new Ladron
   
   pikachu.setTrabajo(ladron)
   equipo = new Equipo("equipo charlie")
   }
  
  @Test
  def mejorHeroeSegun(){
    equipo.obtenerMiembro(ashe)
    equipo.obtenerMiembro(pikachu)
    
    assertTrue(equipo.mejoresHeroesSegun { heroe => heroe.getStats.get("inteligencia") }.contains(ashe))
  }
  
  @Test
  def obtenerItem(){
    
  }
  
  @Test
  def obtenerMiembro(){
    equipo.obtenerMiembro(ashe)
    equipo.obtenerMiembro(pikachu)
    
    assertTrue(equipo.heroes.contains(ashe))
    assertTrue(equipo.heroes.contains(pikachu))
  }
  
  @Test
  def reemplazarMiembro(){
    equipo.obtenerMiembro(ashe)
    equipo.obtenerMiembro(pikachu)
    
    equipo.reemplazarMiembro(iroito, ashe)
    
    assertTrue(!equipo.heroes.contains(ashe))
    assertTrue(equipo.heroes.contains(pikachu))
    assertTrue(equipo.heroes.contains(iroito))
  }
  
  @Test
  def lider(){
    
    ashe.setTrabajo(mago)
    
    equipo.obtenerMiembro(ashe)
    equipo.obtenerMiembro(pikachu)
    equipo.obtenerMiembro(iroito)
    
    assertTrue(equipo.lider() match{
      case Some(x) if x == ashe => true
      case _ => false
    })
    
    //TODO No hay lider claro
    var soyIgualDeGrosaQueAshe = new Heroe(new Stats(100,30,40,60)) 
    soyIgualDeGrosaQueAshe.setTrabajo(mago)
    
    equipo.obtenerMiembro(soyIgualDeGrosaQueAshe)
    
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
  def hacerMisiones  {
    equipo.obtenerMiembro(ashe)
    //equipo.obtenerMiembro(pikachu)
    equipo.obtenerMiembro(iroito)
    //var mision = new Mision(Set(new pelearContraMonstruo), equipo => equipo.ObtenerItem(armaduraElegante))
    //equipo.realizarMision(mision)
    ashe.setTrabajo(new Mago)
    pikachu.setTrabajo(new Ladron)
    iroito.setTrabajo(new Ladron)
    //ashe.equipar(palitoMagico)
    
    equipo.ObtenerItem(armaduraElegante)
//    println(ashe.getStats)
//    println(ashe.getInventario.items)
//    println(pikachu.getStats)
//    println(pikachu.getTrabajo.descripcion)
//    println(pikachu.getInventario.items)
//    println(iroito.getStats)
//    println(iroito.getInventario.items)   
  }

}