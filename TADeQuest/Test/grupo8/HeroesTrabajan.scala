package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class HeroesTrabajan {

  var ashe: Heroe = null
  var pikachu: Heroe = null
  var guerrero: Trabajo = null
  var mago: Trabajo = null
  var ladron: Trabajo = null
  
  @Before
  def setup = {
   ashe = new Heroe(new Stats(100,30,40,60)) 
   pikachu = new Heroe(new Stats(100,100,10,10))//Stats(hp: Int,fuerza: Int, velocidad: Int, inteligencia: Int)
   
   //Trabajo(descripcion: String, statBase: Stats, statPrincipal: String)
   guerrero = new Guerrero
   mago = new Mago
   ladron = new Ladron
   }
  
  @Test
  def test_unTrabajoGuerreroModificaStats() = {
    
    pikachu.setTrabajo(guerrero)
    
    assertEquals(pikachu.getStats.get("hp"), 110)
    assertEquals(pikachu.getStats.get("fuerza"), 115)
    assertEquals(pikachu.getStats.get("velocidad"), 10)
    assertEquals(pikachu.getStats.get("inteligencia"), 0)
    
  }
  
  @Test
  def test_unTrabajoMagoModificaStats() = {
    
    ashe.setTrabajo(mago)
    
    assertEquals(ashe.getStats.get("hp"), 100)
    assertEquals(ashe.getStats.get("fuerza"), 10)
    assertEquals(ashe.getStats.get("velocidad"), 40)
    assertEquals(ashe.getStats.get("inteligencia"), 80)
    
  }
  
  @Test
  def test_unTrabajoLadronModificaStats() = {
    
    ashe.setTrabajo(ladron)
    
    assertEquals(ashe.getStats.get("hp"), 95)
    assertEquals(ashe.getStats.get("fuerza"), 30)
    assertEquals(ashe.getStats.get("velocidad"), 50)
    assertEquals(ashe.getStats.get("inteligencia"), 60)
    
  }
  
}