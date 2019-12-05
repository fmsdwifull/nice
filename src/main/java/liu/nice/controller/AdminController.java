package liu.nice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import liu.nice.pojo.Order;
import liu.nice.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	OrderService orderservice;
	
	private static Logger log  = LogManager.getLogger(AdminController.class);
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
	
	@RequestMapping("/addorder")
	//public String addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException
	//{
	////	log.debug("------------33333----------------"+this.getBodytxt(request));
	//	return "------";
	//}	
	public String addOrder(String user_id,String createtime,String note)
	{
		Order order = new Order();
		order.setUser_id(Integer.parseInt(user_id));
		// DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		Timestamp ts = Timestamp.valueOf(createtime); 
		order.setCreatetime(ts);
		order.setNote(note);
		orderservice.addOrder(order);
		log.debug("------------33333----------------"+user_id);
		return "redirect:/admin/index";
	}		
	
	
	/**
     * 获取POST请求中Body参数
     * @param request
     * @return 字符串
	 * @throws IOException 
     */
	String getBodytxt(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		return wholeStr;
	}
}
