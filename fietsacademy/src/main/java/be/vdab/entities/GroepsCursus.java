package be.vdab.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "groepscursussen")
public class GroepsCursus extends Cursus {
 
  private static final long serialVersionUID = 1L;
  
  @Temporal(TemporalType.DATE) 
  private Date van;
  @Temporal(TemporalType.DATE) 
  private Date tot;
} 