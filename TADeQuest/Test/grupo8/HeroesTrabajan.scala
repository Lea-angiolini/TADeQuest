package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class HeroesTrabajan extends TAdeQuestPrueba{


  
  @Test
  def test_unTrabajoGuerreroModificaStats() = {
    
    pikachu.setTrabajo(guerrero)
    
    assertEquals(pikachu.getStats.get("hp"), 110)
    assertEquals(pikachu.getStats.get("fuerza"), 115)
    assertEquals(pikachu.getStats.get("velocidad"), 10)
    assertEquals(pikachu.getStats.get("inteligencia"), 1)
    
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
  
  @Test
  def test_dejaUnTrabajoMagoModificaStats() = {
    
    pikachu.setTrabajo(guerrero)
    pikachu.descartarTrabajo
    
    assertEquals(pikachu.getStats.get("hp"), 100)
    assertEquals(pikachu.getStats.get("fuerza"), 100)
    assertEquals(pikachu.getStats.get("velocidad"), 10)
    assertEquals(pikachu.getStats.get("inteligencia"), 10)
    
  }
  
}