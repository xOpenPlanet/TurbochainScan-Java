package com.osp.ethscan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osp.common.json.JsonUtil;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.service.AccountService;

/**
 * 账户控制类
 * 
 * @author zhangmingcheng
 */
@Controller
@RequestMapping("/")
public class AccountController {

	@Autowired
	private AccountService accountServiceImpl;

	/**
	 * 获取当前页账户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accounts", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getAllAccounts(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "25") Integer pagesize) {
		ResponseObject ro = ResponseObject.getInstance();
		ro.setValue("result", accountServiceImpl.getAllAccounts(page, pagesize, ro));
		ro.setMessage("success");
		return JsonUtil.beanToJson(ro);
	}

	@RequestMapping(value = "/get_account_by_address", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getAccountByAddress(@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "25") Integer pagesize) {
		return "";
	}

}
