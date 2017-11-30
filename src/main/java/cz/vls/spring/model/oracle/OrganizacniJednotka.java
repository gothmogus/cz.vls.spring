package cz.vls.spring.model.oracle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OJ_KONTAKT")
public class OrganizacniJednotka {
	
	@Id
	private Integer ID;
	private Integer S11;
	private Integer VPUJ;
	private String NAZEV;
	private String ULICE;
	private String MESTO;
	private String PSC;
	
	public Integer getS11() {
		return S11;
	}
	public void setS11(Integer s11) {
		S11 = s11;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getVPUJ() {
		return VPUJ;
	}
	public void setVPUJ(Integer vPUJ) {
		VPUJ = vPUJ;
	}
	public String getNAZEV() {
		return NAZEV;
	}
	public void setNAZEV(String nAZEV) {
		NAZEV = nAZEV;
	}
	public String getULICE() {
		return ULICE;
	}
	public void setULICE(String uLICE) {
		ULICE = uLICE;
	}
	public String getMESTO() {
		return MESTO;
	}
	public void setMESTO(String mESTO) {
		MESTO = mESTO;
	}
	public String getPSC() {
		return PSC;
	}
	public void setPSC(String pSC) {
		PSC = pSC;
	}
	@Override
	public String toString() {
		return "OrganizacniJednotky [S11=" + S11 + ", ID=" + ID + ", VPUJ=" + VPUJ + ", NAZEV=" + NAZEV + ", ULICE="
				+ ULICE + ", MESTO=" + MESTO + ", PSC=" + PSC + "]";
	}
	
}
