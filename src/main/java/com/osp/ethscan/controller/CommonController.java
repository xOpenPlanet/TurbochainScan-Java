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
import com.osp.ethscan.model.BlockDetailInfo;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.model.TransactionBriefInfo;
import com.osp.ethscan.model.TransactionInfo;
import com.osp.ethscan.service.AccountService;
import com.osp.ethscan.service.BlockService;
import com.osp.ethscan.service.EthService;
import com.osp.ethscan.service.TransactionService;
import com.osp.ethscan.util.BaseUtil;

/**
 * 公共控制类
 * 
 * @author zhangmingcheng
 */
@Controller
@RequestMapping("/")
public class CommonController {

	@Autowired
	private BlockService blockServiceImpl;

	@Autowired
	private TransactionService transactionServiceImpl;

	@Autowired
	private EthService ethServiceImpl;

	@Autowired
	private AccountService accountServiceImpl;

	/**
	 * 区块链简述
	 * 
	 * @return
	 */
	@RequestMapping(value = "/summary", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String summary(){
		ResponseObject ro = ResponseObject.getInstance();
		BigInteger height = blockServiceImpl.getCurrentBlockHeight();
		ro.setValue("block_height", height);
		ro.setValue("txn_count", transactionServiceImpl.countAccountsTransaction());
		ro.setValue("hashrate", ethServiceImpl.getHashrate());
		ro.setValue("difficulty", blockServiceImpl.getBlockDifficulty(height));
		return JsonUtil.beanToJson(ro);
	}

	/**
	 * 搜索
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String search(@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "25") Integer pagesize) {
		ResponseObject ro = ResponseObject.getInstance();
		// key=区块高度
		if (BaseUtil.isNumeric(key) == true) {
			BlockDetailInfo blockDetailInfo = blockServiceImpl.getBlockByNumber(new BigInteger(key), ro);
			if (blockDetailInfo != null) {
				ro.setMessage("block");
				ro.setValue("result", blockDetailInfo);
			} else {
				ro.setMessage("Unable to locate Block #" + key);
			}
		} else if (key.length() == 66) {// key=交易hash
			TransactionBriefInfo transactionBriefInfo = transactionServiceImpl.geTransactionByHash(key, ro);
			if (transactionBriefInfo != null) {
				ro.setValue("result", transactionBriefInfo);
				ro.setMessage("tx");
			} else {
				ro.setMessage("Sorry, we are unable to locate this Transaction Hash");
			}
		} else if (key.length() == 42) {// key=账户地址
			if (accountServiceImpl.existUser(key) == true) {
				List<TransactionInfo> transactionLists = transactionServiceImpl.getTxListByAddress(key, page, pagesize,
						ro);
				ro.setValue("result", transactionLists);
				ro.setMessage("address");
			} else {
				ro.setMessage("Sorry, we are unable to locate this address");
			}
		} else {
			ro.setMessage(
					"The search string you entered was:\n" + key + "\nSorry! This is an invalid search string.\n");
		}
		return JsonUtil.beanToJson(ro);
	}

	/**
	 * 组织最近14天的交易信息
	 * 
	 * @param hash
	 * @return
	 */
	@RequestMapping(value = "/tx_chart_info", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getChartOfTxns() {
		ResponseObject ro = ResponseObject.getInstance();
		transactionServiceImpl.organizeChartOfTxns(ro);
		ro.setMessage("success");
		return JsonUtil.beanToJson(ro);
	}
}
