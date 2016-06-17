package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class HayEquipo {
  
  var ashe: Heroe = null
  var pikachu: Heroe = null
  var iroito: Heroe = null
  var guerrero: Trabajo = null
  var mago: Trabajo = null
  var ladron: Trabajo = null
  var equipo: Equipo = null
  
  @Before
  def setup = {
   ashe = new Heroe(new Stats(100,30,40,60)) 
   pikachu = new Heroe(new Stats(100,100,10,10))
   iroito = new Heroe(new Stats(100,100,100,1))
   
   guerrero = new Guerrero
   mago = new Mago
   ladron = new Ladron
   
   equipo = new Equipo("equipo charlie")
   }
  
  @Test
  def mejorHeroeSegun(){
    equipo.obtenerMiembro(ashe)
    equipo.obtenerMiembro(pikachu)
    
    assertTrue(equipo.mejorHeroeSegun { heroe => heroe.getStats.get("inteligencia") } match{
      case Some(x) if x._1 == ashe => true
      case _ => false
    })
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
      case Some(x) if x._1 == ashe => true
      case _ => false
    })
    
    //TODO No hay lider claro
//    var soyIgualDeGrosaQueAshe = new Heroe(new Stats(100,30,40,60)) 
//    soyIgualDeGrosaQueAshe.setTrabajo(mago)
//    
//    equipo.obtenerMiembro(soyIgualDeGrosaQueAshe)
//    
//    assertTrue(equipo.lider() match{
//      case Some(x) => false
//      case None => true
//    })
  }

}