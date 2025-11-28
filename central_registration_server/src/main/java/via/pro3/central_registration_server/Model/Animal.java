package via.pro3.central_registration_server.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "animal", schema = "pro3_assignment_station1")
public class Animal
{
  @Id // Designates this field as the primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "arrival_at", nullable = false)
  private LocalDate arrivalAt;

  @Column(name = "live_weight_kg", nullable = false)
  private Float liveWeight;

  @Column(name = "origin_id")
  private Integer origin;

  protected Animal() {}

  public Animal(LocalDate arrivalAt, Float liveWeight, Integer origin)
  {
    this.arrivalAt = arrivalAt;
    this.liveWeight = liveWeight;
    this.origin = origin;
  }

  public Integer getId() { return id; }
  public LocalDate getArrivalAt() { return arrivalAt; }
  public Float getLiveWeight() { return liveWeight; }
  public Integer getOrigin() { return origin; }

  @Override public String toString()
  {
    return "Animal{" + "id=" + id + ", arrivalAt=" + arrivalAt + ", liveWeight="
        + liveWeight + ", origin=" + origin + '}';
  }
}