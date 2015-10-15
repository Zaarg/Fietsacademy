package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import be.vdab.enums.Geslacht;

@Entity
@Table(name = "docenten") 
public class Docent implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue 
	private long id;
	
	private String voornaam; 
	private String familienaam;
	private BigDecimal wedde;
	private long rijksRegisterNr;
	
	@Enumerated(EnumType.STRING) private Geslacht geslacht;
	
	@ElementCollection 
	@CollectionTable(name = "docentenbijnamen",	joinColumns = @JoinColumn(name = "docentid") ) 
	@Column(name = "Bijnaam") 
	private Set<String> bijnamen;
	
	@ManyToOne(optional = false) 
	@JoinColumn(name = "campusid") 
	private Campus campus;


	public Docent(String voornaam, String familienaam, BigDecimal wedde, Geslacht geslacht, long rijksRegisterNr) {
		bijnamen = new HashSet<>();
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		setRijksRegisterNr(rijksRegisterNr);
	} 

	protected Docent() {}// default constructor is vereiste voor JPA
		
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public void setVoornaam(String voornaam) {
		if (!isVoornaamValid(voornaam)) {
			throw new IllegalArgumentException();
		}
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		if (!isFamilienaamValid(familienaam)) {
			throw new IllegalArgumentException();
		}
		this.familienaam = familienaam;
	}

	public void setWedde(BigDecimal wedde) {
		if (!isWeddeValid(wedde)) {
			throw new IllegalArgumentException();
		}
		this.wedde = wedde;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public void setRijksRegisterNr(long rijksRegisterNr) {
		if (!isRijksRegisterNrValid(rijksRegisterNr)) {
			throw new IllegalArgumentException();
		}
		this.rijksRegisterNr = rijksRegisterNr;
	}
	
	public void addBijnaam(String bijnaam) {  
		bijnamen.add(bijnaam);
	} 
	
	public void removeBijnaam(String bijnaam) { 
		  bijnamen.remove(bijnaam);
	} 
	
	public Set<String> getBijnamen() {
		return Collections.unmodifiableSet(bijnamen); } 
	
    public static long getSerialversionuid() {
    	return serialVersionUID;
	}
	
    public long getId() {
		return id;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public String getFamilienaam() {
		return familienaam;
	}
	
	public BigDecimal getWedde() {
		return wedde;
	}
	
	public long getRijksRegisterNr() {
		return rijksRegisterNr;
	}
	
	public Geslacht getGeslacht() {
		return geslacht;
	}

	public static boolean isVoornaamValid(String voornaam) { 
		return voornaam != null && ! voornaam.isEmpty();
	} 
	
	public static boolean isFamilienaamValid(String familienaam) {
		return familienaam != null && ! familienaam.isEmpty();
	} 
	
	public static boolean isWeddeValid(BigDecimal wedde) {
		return wedde != null && wedde.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public static boolean isRijksRegisterNrValid(long rijksRegisterNr) {
		long getal = rijksRegisterNr / 100; 
		if (rijksRegisterNr / 1_000_000_000 < 50) {
			getal += 2_000_000_000;
		}
		return rijksRegisterNr % 100 == 97 - getal % 97;
	} 
	
	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(new BigDecimal(100))); 
		wedde = wedde.multiply(factor, new MathContext(2, RoundingMode.HALF_UP)); 
	}
	
	public String getNaam() {
    return voornaam + ' ' + familienaam;
	}
	
	
} 