package liu.nice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import liu.nice.dao.OrderDao;
import liu.nice.pojo.Order;

@Service
public class OrderService {
	@Autowired
	OrderDao orderdao;
	
	@Autowired
	public  JSONObject getAllOrders()
	{
		JSONObject jobj = new JSONObject();
		List<Order> order = orderdao.getAllOrders();
		int count = order.size();
        try {        	
            //Ìí¼Ó
        	jobj.put("code", "0");
        	jobj.put("msg", "ÕÅÈý");
        	jobj.put("count", count);
        	jobj.put("data", order);
        } catch (JSONException e) {
            e.printStackTrace();
        }
		return jobj;
	}
	
	public int addOrder(Order order)
	{
		return orderdao.addOrder(order);
	}
}
