package org.systemDesign.creationtionalPattern.factory.abstractFactory.dbfactory;

public interface Command {
    void execute(String sql);
}

class MySQLCommand implements Command {
    @Override
    public void execute(String sql) {
        System.out.println("MySQL: Executing SQL -> " + sql);
    }
}

class PostgresSQLCommand implements Command {
    @Override
    public void execute(String sql) {
        System.out.println("PostgresSQL: Executing SQL -> " + sql);
    }
}