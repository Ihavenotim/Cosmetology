package com.cqjtu.cosmetology.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqjtu.cosmetology.entity.User;
import com.cqjtu.cosmetology.dao.UserDao;
import com.cqjtu.cosmetology.service.UserService;
import com.cqjtu.cosmetology.util.HttpClientUtil;
import com.cqjtu.cosmetology.util.ResponseCode;
import com.cqjtu.cosmetology.util.ResponseData;
import io.swagger.models.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-08 16:34:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData toRegister(User user) {
        String username = user.getUsername();
        if (username == null || "".equals(username)) {
            return new ResponseData(ResponseCode.FAIL02);
        }
        if (user.getPhone() == null || user.getPhone() == "") {
            return new ResponseData(ResponseCode.FAIL03);
        }
        if (user.getPassword() == null || user.getPassword() == "") {
            return new ResponseData(ResponseCode.FAIL04);
        }
        //查询用户名是否被注册
        User userByUsername = userDao.queryUserByUsername(username);

        if (userByUsername != null) {
            return new ResponseData(ResponseCode.FAIL05);
        }
        String newPassword = new Md5Hash(user.getPassword(), "cqjtu", 10).toString();

        user.setPassword(newPassword);

        int i = userDao.insert(user);
        if (i != 1) {
            return new ResponseData(ResponseCode.FAIL01);
        }
        return new ResponseData(ResponseCode.SUCCESS02);
    }

    @Override
    public ResponseData toLogin(User user, String code) {
        //判断非空
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            return new ResponseData(ResponseCode.FAIL02);
        }

        if (user.getPassword() == null || user.getPassword() == "") {
            return new ResponseData(ResponseCode.FAIL04);
        }


        //先根据用户名查人
        User userByUsername = userDao.queryUserByUsername(user.getUsername());
        if (userByUsername == null) {
            return new ResponseData(ResponseCode.FAIL06);
        }


        //密码字符串转换为密文
        String newPassword = new Md5Hash(user.getPassword(), "cqjtu", 10).toString();

        if (!userByUsername.getPassword().equals(newPassword)) {
            return new ResponseData(ResponseCode.FAIL06);
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx925621bf6b9c74de&secret=a646d5eb567a96e9189b2d1dcaabf302&js_code="+code+"&grant_type=authorization_code";


      //对url解析
        String result = HttpClientUtil.doGet(url);


      //对result解析
      JSONObject parse = (JSONObject) JSONObject.parse(result);
      String session_key = (String) parse.get("session_key");
      String openid = (String) parse.get("openid");

      //自定义登录状态
      String token = new Md5Hash(session_key,openid).toString();
      userByUsername.setToken(token);
      userByUsername.setOpenid(openid);
      userByUsername.setSessionkey(session_key);
      //更新到数据表
      int i = userDao.update(userByUsername);
      if (i != 1){
          return new ResponseData(ResponseCode.FAIL01);
      }

      return new ResponseData(ResponseCode.SUCCESS03,token);
    }


}
