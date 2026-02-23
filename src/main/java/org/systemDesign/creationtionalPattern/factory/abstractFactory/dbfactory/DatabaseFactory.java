package org.systemDesign.creationtionalPattern.factory.abstractFactory.dbfactory;

public interface DatabaseFactory {
    Connection createConnection();
    Command createCommand();
    Transaction createTransaction();
}

class MySQLDatabaseFactory implements DatabaseFactory{

    @Override
    public Connection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public Command createCommand() {
        return new MySQLCommand();
    }

    @Override
    public Transaction createTransaction() {
        return new MySQLTransaction();
    }
}

class PostgresDatabaseFactory implements DatabaseFactory{

    @Override
    public Connection createConnection() {
        return new PostgresSQLConnection();
    }

    @Override
    public Command createCommand() {
        return new PostgresSQLCommand();
    }

    @Override
    public Transaction createTransaction() {
        return new PostgresSQLTransaction();
    }
}
