package liu.nice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import liu.nice.pojo.Order;
import liu.nice.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderservice;
	
	@RequestMapping("/index")
	public String index()
	{
		return "page/admin";
	}
	@RequestMapping("/getorders")
	@ResponseBody
	public JSONObject getOrder()
	{
		
		return orderservice.getAllOrders();
	}
	
}
