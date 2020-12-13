# hmily实现分布式事务示例

  使用dubbo做PRC框架，通过hmily实现分布式事务

* 初始化脚本: [init.sql](https://github.com/wkk1994/JAVA-000/blob/main/hmily-xa-demo/init.sql)

* 启动方式
  * 启动账户服务：[AccountBootApplication.java](https://github.com/wkk1994/JAVA-000/blob/main/hmily-xa-demo/account-service-demo/src/main/java/com/wkk/learn/account/service/demo/AccountBootApplication.java)
  * 启动商品服务：[GoodsBootApplication.java](https://github.com/wkk1994/JAVA-000/blob/main/hmily-xa-demo/goods-service-demo/src/main/java/com/wkk/learn/goods/service/demo/GoodsBootApplication.java)
  * 启动订单服务：[OrderBootApplication.java](https://github.com/wkk1994/JAVA-000/blob/main/hmily-xa-demo/order-service-demo/src/main/java/com/wkk/learn/java/order/service/demo/OrderBootApplication.java)
  * 发送请求：`curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://127.0.0.1:8183/order?userId=1001&goodsId=2001&num=1'`
