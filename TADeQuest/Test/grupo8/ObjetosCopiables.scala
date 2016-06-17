package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class ObjetosCopiables {
  
  @Test
  def copiarUnHeroe = {
    var heroe = new Heroe(new Stats(10,10,10,10))
    var heroeCopiar = heroe.copiar
    
    heroe.setTrabajo(new Guerrero)
    var borrar = new ForjandoUnHeroe
    borrar.setup
    heroe.equipar(borrar.armaduraElegante)
    
    assertTrue(borrar.compararStats(new Stats(2,2,2,10).getStatsFinales,new Stats(1,1,1,0) + new Stats(0,0,0,1) + new Stats(1,1,1,9)))
    assertFalse(borrar.compararStats(heroe.statActual, heroeCopiar.statActual))
    assertTrue(borrar.compararStats(heroe.statActual, new Stats(1,25,40,1)))
    }
  }