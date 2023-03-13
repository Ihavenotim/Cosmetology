package com.cqjtu.cosmetology.service.impl;

import com.cqjtu.cosmetology.dao.UserDao;
import com.cqjtu.cosmetology.entity.Order;
import com.cqjtu.cosmetology.dao.OrderDao;
import com.cqjtu.cosmetology.service.OrderService;
import com.cqjtu.cosmetology.util.ResponseCode;
import com.cqjtu.cosmetology.util.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2023-03-09 14:47:07
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Long id) {
        return this.orderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param order       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Order> queryByPage(Order order, PageRequest pageRequest) {
        long total = this.orderDao.count(order);
        return new PageImpl<>(this.orderDao.queryAllByLimit(order, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order insert(Order order) {
        this.orderDao.insert(order);
        return order;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        this.orderDao.update(order);
        return this.queryById(order.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orderDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData createOrder(Order order, String token) {
        String openid=userDao.queryOpenidByToken(token);
        orderDao.insert(order);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(new Date());

        order.setOrderstate("0");
        order.setOrderstate(simpleDateFormat.format(new Date()));
        order.setOpenid(openid);

        int i= orderDao.insert(order);
        if (i!=1){
            return new ResponseData(ResponseCode.FAIL01);
        }
        return new ResponseData(ResponseCode.SUCCESS04);

    }

    @Override
    public ResponseData queryOrderByState(String state, String token) {
       // 根据自定义的登录状态获取openid
        String openid=userDao.queryOpenidByToken(token);
        try { //根据openid确认
            List<Order> orders= orderDao.queryOrderByStateAndOpenid(openid, state);
            return new ResponseData(ResponseCode.SUCCESS01,orders);
        }catch (Exception e){

            return new ResponseData(ResponseCode.FAIL01);
        }

    }
}
