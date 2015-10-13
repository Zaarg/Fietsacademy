package be.vdab.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("I")
public class IndividueleCursus extends Cursus { 
  private static final long serialVersionUID = 1L;
  private int duurtijd;
}
