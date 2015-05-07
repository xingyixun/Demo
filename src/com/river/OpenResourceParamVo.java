package com.river;

import java.io.Serializable;

public class OpenResourceParamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;   //�û�ID
	
	private String username;  //�û���
	
	private String serviceId;  //����ID
	
	private String configPasswd;   //������������
	
	private String configAddr;     //��֤��ַ
	
	private int masterNum;      //������
	
	private boolean isNeedSlave;   //�Ƿ���Ҫ�ӿ�
	
    private int  slaveRate;       //�ӿ����
    
    private boolean isNeedDistributeTrans;  //�Ƿ���Ҫ�ֲ�ʽ�������

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getConfigPasswd() {
		return configPasswd;
	}

	public void setConfigPasswd(String configPasswd) {
		this.configPasswd = configPasswd;
	}

	public String getConfigAddr() {
		return configAddr;
	}

	public void setConfigAddr(String configAddr) {
		this.configAddr = configAddr;
	}

	public int getMasterNum() {
		return masterNum;
	}

	public void setMasterNum(int masterNum) {
		this.masterNum = masterNum;
	}

	public boolean isNeedSlave() {
		return isNeedSlave;
	}

	public void setNeedSlave(boolean isNeedSlave) {
		this.isNeedSlave = isNeedSlave;
	}


	public int getSlaveRate() {
		return slaveRate;
	}

	public void setSlaveRate(int slaveRate) {
		this.slaveRate = slaveRate;
	}

	public boolean isNeedDistributeTrans() {
		return isNeedDistributeTrans;
	}

	public void setNeedDistributeTrans(boolean isNeedDistributeTrans) {
		this.isNeedDistributeTrans = isNeedDistributeTrans;
	}
    
}
