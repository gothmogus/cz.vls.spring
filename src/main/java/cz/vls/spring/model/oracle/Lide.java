package cz.vls.spring.model.oracle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIDE_KONTAKTY")
public class Lide {
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	private Integer DIV;
	private String DIVP;
	private Integer UTVAR;
	private String UTVARP;
	private String TITPRED;
	private String JMENO;
	private String PRIJMENI;
	private String TITZA;
	private Integer FUNKCE;
	private String FUNKCEP;
	
	@Column(name = "OSCISLO")
	private Integer oscislo;
	
	private String POHLAVI;
	private Integer ODDELENI;
	private String ODDELENIP;
	private String ULICE;
	private Integer CPOP;
	private String CORI;
	private String OBEC;
	private String PSC;
	private String TEL;
	private String MOBIL;
	private String MAIL;
	private String IPT;
	private String ZKRVOLBA;
	private Integer KATG;
	private Integer VYSTROJ;
	private Integer NAROK;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDIV() {
		return DIV;
	}
	public void setDIV(Integer dIV) {
		DIV = dIV;
	}
	public String getDIVP() {
		return DIVP;
	}
	public void setDIVP(String dIVP) {
		DIVP = dIVP;
	}
	public Integer getUTVAR() {
		return UTVAR;
	}
	public void setUTVAR(Integer uTVAR) {
		UTVAR = uTVAR;
	}
	public String getUTVARP() {
		return UTVARP;
	}
	public void setUTVARP(String uTVARP) {
		UTVARP = uTVARP;
	}
	public String getTITPRED() {
		return TITPRED;
	}
	public void setTITPRED(String tITPRED) {
		TITPRED = tITPRED;
	}
	public String getJMENO() {
		return JMENO;
	}
	public void setJMENO(String jMENO) {
		JMENO = jMENO;
	}
	public String getPRIJMENI() {
		return PRIJMENI;
	}
	public void setPRIJMENI(String pRIJMENI) {
		PRIJMENI = pRIJMENI;
	}
	public String getTITZA() {
		return TITZA;
	}
	public void setTITZA(String tITZA) {
		TITZA = tITZA;
	}
	public Integer getFUNKCE() {
		return FUNKCE;
	}
	public void setFUNKCE(Integer fUNKCE) {
		FUNKCE = fUNKCE;
	}
	public String getFUNKCEP() {
		return FUNKCEP;
	}
	public void setFUNKCEP(String fUNKCEP) {
		FUNKCEP = fUNKCEP;
	}
	
	public Integer getOscislo() {
		return oscislo;
	}
	public void setOscislo(Integer oscislo) {
		this.oscislo = oscislo;
	}
	public String getPOHLAVI() {
		return POHLAVI;
	}
	public void setPOHLAVI(String pOHLAVI) {
		POHLAVI = pOHLAVI;
	}
	public Integer getODDELENI() {
		return ODDELENI;
	}
	public void setODDELENI(Integer oDDELENI) {
		ODDELENI = oDDELENI;
	}
	public String getODDELENIP() {
		return ODDELENIP;
	}
	public void setODDELENIP(String oDDELENIP) {
		ODDELENIP = oDDELENIP;
	}
	public String getULICE() {
		return ULICE;
	}
	public void setULICE(String uLICE) {
		ULICE = uLICE;
	}
	public Integer getCPOP() {
		return CPOP;
	}
	public void setCPOP(Integer cPOP) {
		CPOP = cPOP;
	}
	public String getCORI() {
		return CORI;
	}
	public void setCORI(String cORI) {
		CORI = cORI;
	}
	public String getOBEC() {
		return OBEC;
	}
	public void setOBEC(String oBEC) {
		OBEC = oBEC;
	}
	public String getPSC() {
		return PSC;
	}
	public void setPSC(String pSC) {
		PSC = pSC;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getMOBIL() {
		return MOBIL;
	}
	public void setMOBIL(String mOBIL) {
		MOBIL = mOBIL;
	}
	public String getMAIL() {
		return MAIL;
	}
	public void setMAIL(String mAIL) {
		MAIL = mAIL;
	}
	public String getIPT() {
		return IPT;
	}
	public void setIPT(String iPT) {
		IPT = iPT;
	}
	public String getZKRVOLBA() {
		return ZKRVOLBA;
	}
	public void setZKRVOLBA(String zKRVOLBA) {
		ZKRVOLBA = zKRVOLBA;
	}
	public Integer getKATG() {
		return KATG;
	}
	public void setKATG(Integer kATG) {
		KATG = kATG;
	}
	public Integer getVYSTROJ() {
		return VYSTROJ;
	}
	public void setVYSTROJ(Integer vYSTROJ) {
		VYSTROJ = vYSTROJ;
	}
	public Integer getNAROK() {
		return NAROK;
	}
	public void setNAROK(Integer nAROK) {
		NAROK = nAROK;
	}
	
	public String toString() {
		return this.JMENO + " " + this.PRIJMENI + " => " + this.oscislo;
	}
	
}
