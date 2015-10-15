package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.TelefoonNr;

@Entity
@Table(name="campussen")
public class Campus implements Serializable {
  private static final long serialVersionUID = 1L; 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String naam;
  @Embedded 
  private Adres adres; 
  @ElementCollection @CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusid")) 
  @OrderBy("fax") 
  private Set<TelefoonNr> telefoonNrs; 
  
  @OneToMany @JoinColumn(name = "campusid") 
  @OrderBy("voornaam, familienaam") 
  private Set<Docent> docenten; 
    
  public Campus(String naam, Adres adres) {
	docenten = new LinkedHashSet<>(); 
	telefoonNrs = new LinkedHashSet<>();
	setNaam(naam);
    setAdres(adres);
  } 
  
  protected Campus() {}

  public Set<Docent> getDocenten() {
	    return Collections.unmodifiableSet(docenten);
  } 
  
  public void addDocent(Docent docent) {
	    docenten.add(docent);
  } 
  
  public void removeDocent(Docent docent) {
	    docenten.remove(docent);
  }
  
  public Set<TelefoonNr> getTelefoonNrs() {
	  return Collections.unmodifiableSet(telefoonNrs);
  }
  
  public void addTelefoonNr(TelefoonNr telefoonNr) {
	  telefoonNrs.add(telefoonNr);
  } 
	
  public void removeTelefoonNr(TelefoonNr telefoonNr) {
	  telefoonNrs.remove(telefoonNr);
	}
    
  public long getId() {
	return id;
  }

public String getNaam() {
	return naam;
  }

  public Adres getAdres() {
	return adres;
  }

  public void setNaam(String naam) {
	this.naam = naam;
  }

  public void setAdres(Adres adres) {
	this.adres = adres;
  }
  
  
}