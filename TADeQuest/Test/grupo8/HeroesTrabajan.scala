package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class HeroesTrabajan extends TAdeQuestPrueba{


  
  @Test
  def test_unTrabajoGuerreroModificaStats() = {
    
    pikachu = pikachu.setTrabajo(Guerrero)
    
    assertEquals(pikachu.getStats.get(HP), 110)
    assertEquals(pikachu.getStats.get(Fuerza), 115)
    assertEquals(pikachu.getStats.get(Velocidad), 10)
    assertEquals(pikachu.getStats.get(Inteligencia), 1)
    
  }
  
  @Test
  def test_unTrabajoMagoModificaStats() = {
    
    ashe = ashe.setTrabajo(Mago)
    
    assertEquals(ashe.getStats.get(HP), 100)
    assertEquals(ashe.getStats.get(Fuerza), 10)
    assertEquals(ashe.getStats.get(Velocidad), 40)
    assertEquals(ashe.getStats.get(Inteligencia), 80)
    
  }
  
  @Test
  def test_unTrabajoLadronModificaStats() = {
    
    ashe = ashe.setTrabajo(Ladron)
    
    assertEquals(ashe.getStats.get(HP), 95)
    assertEquals(ashe.getStats.get(Fuerza), 30)
    assertEquals(ashe.getStats.get(Velocidad), 50)
    assertEquals(ashe.getStats.get(Inteligencia), 60)
    
  }
  
  @Test
  def test_dejaUnTrabajoMagoModificaStats() = {
    
    pikachu = pikachu.setTrabajo(Guerrero)
    pikachu = pikachu.descartarTrabajo
    
    assertEquals(pikachu.getStats.get(HP), 100)
    assertEquals(pikachu.getStats.get(Fuerza), 100)
    assertEquals(pikachu.getStats.get(Velocidad), 10)
    assertEquals(pikachu.getStats.get(Inteligencia), 10)
  }
  
}