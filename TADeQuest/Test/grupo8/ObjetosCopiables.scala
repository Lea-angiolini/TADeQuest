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
    
    assertFalse(borrar.compararStats(heroe.statActual, heroeCopiar.statActual))
    }
  }