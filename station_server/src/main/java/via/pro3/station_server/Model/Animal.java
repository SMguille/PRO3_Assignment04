package via.pro3.station_server.Model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Animal
{
  public Animal(int animalId, OffsetDateTime arrivalAt, float liveWeight,
      int origin)
  {
    this.animalId = animalId;
    this.arrivalAt = arrivalAt;
    this.liveWeight = liveWeight;
    this.origin = origin;
  }

  private final int animalId;
  private final OffsetDateTime arrivalAt;
  private final float liveWeight;
  private final int origin;

  public int getAnimalId()
  {
    return animalId;
  }

  public OffsetDateTime getArrivalAt()
  {
    return arrivalAt;
  }

  public float getLiveWeight()
  {
    return liveWeight;
  }

  public int getOrigin()
  {
    return origin;
  }
}
