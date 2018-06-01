package com.osp.ethscan.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osp.common.json.JsonUtil;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.model.TransactionBriefInfo;
import com.osp.ethscan.model.TransactionInfo;
import com.osp.ethscan.service.TransactionService;

/**
 * 账户控制类
 * 
 * @author zhangmingcheng
 */
@Controller
@RequestMapping("/")
public class TransactionController {

	@Autowired
	private TransactionService transactionServiceImpl;

	/**
	 * 根据账户地址取得其交易列表
	 * 
	 * @param address
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/txlist_accounts", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTxListByAddress(@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "25") Integer pagesize) {
		ResponseObject ro = ResponseObject.getInstance();
		List<TransactionInfo> transactionLists = transactionServiceImpl.getTxListByAddress(address, page, pagesize, ro);
		ro.setValue("result", transactionLists);
		return JsonUtil.beanToJson(ro);
	}

	/**
	 * 根据区块高度取得区块的交易列表
	 * 
	 * @param module
	 * @param address
	 * @return
	 */
	@RequestMapping(value = "/txlist_block_number", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTxListByBlockNumber(@RequestParam(value = "number", defaultValue = "") String number,
			@RequestParam(value = "age", defaultValue = "") String age,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize) {
		ResponseObject ro = ResponseObject.getInstance();
		// 取得所有交易信息
		if (number.equals("") == true) {
			ro.setValue("result", transactionServiceImpl.getCurrentPageTxList(page, pagesize, ro));
		} else {
			List<TransactionInfo> transactionLists = transactionServiceImpl
					.getTxListByBlockNumber(new BigInteger(number), age, ro);
			ro.setValue("result", transactionLists);
		}
		ro.setMessage("success");
		return JsonUtil.beanToJson(ro);
	}

	/**
	 * 根据交易hash取得交易详细信息
	 * 
	 * @param hash
	 * @return
	 */
	@RequestMapping(value = "/tx_brief_info", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getTxBriefInfo(@RequestParam(value = "hash", defaultValue = "") String hash) {
		ResponseObject ro = ResponseObject.getInstance();
		TransactionBriefInfo transactionBriefInfo = transactionServiceImpl.geTransactionByHash(hash, ro);
		ro.setValue("result", transactionBriefInfo);
		ro.setMessage("success");
		return JsonUtil.beanToJson(ro);
	}
}
