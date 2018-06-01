package com.osp.ethscan.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 以太坊配置文件
 * 
 * @author zhangmingcheng
 */
@Configuration
@PropertySource("classpath:ethscan.properties")
public class EtherConfig {

	private static String ip;

	@Value("${web3j.client.ip}")
	private String web3jClientIp;

	@PostConstruct
	public void init() {
		EtherConfig.ip = this.web3jClientIp;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		EtherConfig.ip = ip;
	}

	public String getWeb3jClientIp() {
		return web3jClientIp;
	}

	public void setWeb3jClientIp(String web3jClientIp) {
		this.web3jClientIp = web3jClientIp;
	}

}
