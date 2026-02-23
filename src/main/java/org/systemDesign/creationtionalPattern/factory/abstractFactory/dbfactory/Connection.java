package org.systemDesign.creationtionalPattern.factory.abstractFactory.dbfactory;

public interface Connection {
    void connect();
}

class MySQLConnection implements Connection{
    @Override
    public void connect() {
        System.out.println("MySQL: Connected to database");
    }
}

class PostgresSQLConnection implements Connection{
    @Override
    public void connect() {
        System.out.println("PostgreSQL: Connected to database");
    }
}
