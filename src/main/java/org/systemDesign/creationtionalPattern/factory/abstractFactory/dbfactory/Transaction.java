package org.systemDesign.creationtionalPattern.factory.abstractFactory.dbfactory;

public interface Transaction {
    void begin();
    void commit();
}

class MySQLTransaction implements Transaction {
    @Override
    public void begin() {
        System.out.println("MySQL: Transaction started");
    }

    @Override
    public void commit() {
        System.out.println("MySQL: Transaction Committed");
    }

}

class PostgresSQLTransaction implements Transaction {
    @Override
    public void begin() {
        System.out.println("PostgresSQL: Transaction started");
    }

    @Override
    public void commit() {
        System.out.println("PostgresSQL: Transaction Committed");
    }
}