package package_final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimulationConfigTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void getNumberOfDrivers() {
    SimulationConfig config1 = new SimulationConfig(50, 25);
    assertEquals(50, config1.getNumberOfDrivers());
  }

  @Test
  void getNumberOfRides() {
    SimulationConfig config2 = new SimulationConfig(50, 100);
    assertEquals(100, config2.getNumberOfRides());
  }
}
