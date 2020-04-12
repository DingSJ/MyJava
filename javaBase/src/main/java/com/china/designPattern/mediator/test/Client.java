package com.china.designPattern.mediator.test;

/**
 * 有三种数据库 Mysql、Redis、Elasticsearch，其中的 Mysql 作为主数据库，
 * 当增加一条数据时需要同步到另外两个数据库中；Redis 作为缓存数据库，
 * 当增加一条数据时不需要同步到另外另个数据库；
 * 而 Elasticsearch 作为大数据查询数据库，有一个统计功能，
 * 当增加一条数据时只需要同步到 Mysql
 */
public class Client {
    public static void main(String[] args) {
        AbstractMediator syncMediator = new SyncMediator();
        MysqlDatabase mysqlDatabase = new MysqlDatabase(syncMediator);
        RedisDatabase redisDatabase = new RedisDatabase(syncMediator);
        EsDatabase esDatabase = new EsDatabase(syncMediator);

        syncMediator.setMysqlDatabase(mysqlDatabase);
        syncMediator.setRedisDatabase(redisDatabase);
        syncMediator.setEsDatabase(esDatabase);

        System.out.println("\n---------mysql 添加数据 1，将同步到Redis和ES中-----------");
        mysqlDatabase.add("1");
        mysqlDatabase.select();
        redisDatabase.cache();
        esDatabase.count();

        System.out.println("\n---------Redis添加数据 2，将不同步到其它数据库-----------");
        redisDatabase.add("2");
        mysqlDatabase.select();
        redisDatabase.cache();
        esDatabase.count();

        System.out.println("\n---------ES 添加数据 3，只同步到 Mysql-----------");
        esDatabase.add("3");
        mysqlDatabase.select();
        redisDatabase.cache();
        esDatabase.count();
    }
}