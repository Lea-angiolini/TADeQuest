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
    
  }

}