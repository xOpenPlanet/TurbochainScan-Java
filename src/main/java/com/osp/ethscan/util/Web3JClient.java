package com.osp.ethscan.util;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import com.osp.ethscan.config.EtherConfig;


public class Web3JClient {

	private static String ip = EtherConfig.getIp();

	private Web3JClient() {
		super();
	}

	private volatile static Web3j web3j;
	private volatile static Admin admin;

	public static Web3j getClient() {
		if (web3j == null) {
			synchronized (Web3JClient.class) {
				if (web3j == null) {
					web3j = Web3j.build(new HttpService(ip));
				}
			}
		}
		return web3j;
	}

	public static Admin getAdminClient() {
		if (admin == null) {
			synchronized (Web3JClient.class) {
				if (admin == null) {
					admin = Admin.build(new HttpService(ip));
				}
			}
		}
		return admin;
	}

}
