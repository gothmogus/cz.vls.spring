package cz.vls.spring.model.mssql;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CNG_Karty")
public class UserCard {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID;
	
	private String Kod;
	private String Serie;
	private String Typ;
	private Integer EvidencniCislo;
	private String Jmeno;
	private String Prijmeni;
	private String Titul;
	private Date PlatnostDo;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getKod() {
		return Kod;
	}
	public void setKod(String kod) {
		Kod = kod;
	}
	public String getSerie() {
		return Serie;
	}
	public void setSerie(String serie) {
		Serie = serie;
	}
	public String getTyp() {
		return Typ;
	}
	public void setTyp(String typ) {
		Typ = typ;
	}
	public Integer getEvidencniCislo() {
		return EvidencniCislo;
	}
	public void setEvidencniCislo(Integer evidencniCislo) {
		EvidencniCislo = evidencniCislo;
	}
	public String getJmeno() {
		return Jmeno;
	}
	public void setJmeno(String jmeno) {
		Jmeno = jmeno;
	}
	public String getPrijmeni() {
		return Prijmeni;
	}
	public void setPrijmeni(String prijmeni) {
		Prijmeni = prijmeni;
	}
	public String getTitul() {
		return Titul;
	}
	public void setTitul(String titul) {
		Titul = titul;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy ", timezone="UTC")
	public Date getPlatnostDo() {
		return PlatnostDo;
	}
	
	public void setPlatnostDo(Date platnostDo) {
		PlatnostDo = platnostDo;
	}
	
	@Override
	public String toString() {
		return "UserCard [ID=" + ID + ", Kod=" + Kod + ", Serie=" + Serie + ", Typ=" + Typ + ", EvidencniCislo="
				+ EvidencniCislo + ", Jmeno=" + Jmeno + ", Prijmeni=" + Prijmeni + ", Titul=" + Titul + ", PlatnostDo="
				+ PlatnostDo + "]";
	}
	
}
