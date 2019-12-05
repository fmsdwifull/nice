package liu.nice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import liu.nice.pojo.Order;

public interface OrderDao {
	List<Order> getAllOrders();
	int addOrder(@Param("order") Order order);
}
