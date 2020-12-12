package com.wkk.learn.java.dynamic.data.source.dao;

import com.wkk.learn.java.dynamic.data.source.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description 订单信息
 * @Author Wangkunkun
 * @Date 2020/12/9 21:25
 */
@Component
public class OrderInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public OrderInfo findById(Long id) {
        List<OrderInfo> query = jdbcTemplate.query("select id, user_id, goods_id, goods_num, amount, order_time, created_at, modified_at from order_info where id =?",
                new Object[]{id}, (rs, rowNum) -> {
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setId(rs.getLong(1));
                    orderInfo.setUserId(rs.getLong(2));
                    orderInfo.setGoodsId(rs.getLong(3));
                    orderInfo.setGoodsNum(rs.getLong(4));
                    orderInfo.setAmount(rs.getBigDecimal(5));
                    orderInfo.setCreatedAt(rs.getLong(6));
                    orderInfo.setModifiedAt(rs.getLong(7));
                    return orderInfo;
                });
        return query == null ? null : query.get(0);
    }

    @Transactional
    public void save(OrderInfo orderInfo) {
        jdbcTemplate.update("INSERT INTO order_info(id, user_id, goods_id, goods_num, amount, order_time, created_at) " +
                        "VALUES (?,?,?,?,?,?,?)", orderInfo.getId(), orderInfo.getUserId(), orderInfo.getGoodsId(),
                orderInfo.getGoodsNum(), orderInfo.getAmount(), orderInfo.getOrderTime(), orderInfo.getCreatedAt());
    }

    public Long queryMaxId() {
        List<Long> query = jdbcTemplate.query("select max(id) from order_info", new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        });
        return query == null ? 0L : query.get(0);
    }
}
