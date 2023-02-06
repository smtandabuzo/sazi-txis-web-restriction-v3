package com.example.authenticatingldap.dto;

public class TransmissionRegistrationDto {

	private Long id;
	private String adAccount;
	private String adUniqueNumber;
	private String adFirstName;
	private String adSurname;
	private String adEmailAddress;
	private String txsisRole;

	private String status;

	private String role;

	private String block;

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public TransmissionRegistrationDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdAccount() {
		return adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
	}

	public String getAdUniqueNumber() {
		return adUniqueNumber;
	}

	public void setAdUniqueNumber(String adUniqueNumber) {
		this.adUniqueNumber = adUniqueNumber;
	}

	public String getAdFirstName() {
		return adFirstName;
	}

	public void setAdFirstName(String adFirstName) {
		this.adFirstName = adFirstName;
	}

	public String getAdSurname() {
		return adSurname;
	}

	public void setAdSurname(String adSurname) {
		this.adSurname = adSurname;
	}

	public String getAdEmailAddress() {
		return adEmailAddress;
	}

	public void setAdEmailAddress(String adEmailAddress) {
		this.adEmailAddress = adEmailAddress;
	}

	public String getTxsisRole() {
		return txsisRole;
	}

	public void setTxsisRole(String txsisRole) {
		this.txsisRole = txsisRole;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
