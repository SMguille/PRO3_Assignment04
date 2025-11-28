package via.pro3.central_registration_server.Model;

import jakarta.persistence.*;

@Entity @Table(name = "part_type") public class PartType
{

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  @Column(name = "name") String name;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}
