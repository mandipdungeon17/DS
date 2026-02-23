package org.systemDesign.creationtionalPattern.builder;

public class SQLQuery {
    private final String table;
    private final String columns;
    private final String whereClause;
    private final String orderBy;
    private final int limit;

    private SQLQuery(SQLQueryBuilder builder){
        this.table = builder.table;
        this.columns = builder.columns;
        this.whereClause = builder.whereClause;
        this.orderBy = builder.orderBy;
        this.limit = builder.limit;
    }
    public static class SQLQueryBuilder{
        private final String table;
        private String columns = "*";
        private String whereClause = "";
        private String orderBy = "";
        private int limit = 0;

        public SQLQueryBuilder(String table){
            this.table = table;
        }

        public SQLQueryBuilder select(String columns){
            this.columns = columns;
            return this;
        }
        public SQLQueryBuilder where(String condition){
            this.whereClause = condition;
            return this;
        }
        public SQLQueryBuilder orderBy(String columns){
            this.orderBy = columns;
            return this;
        }
        public SQLQueryBuilder limit(int limit){
            this.limit = limit;
            return this;
        }
        public SQLQuery build(){
            return new SQLQuery(this);
        }
    }

    public String toSql(){
        StringBuilder sql = new StringBuilder("SELECT " + columns + " FROM " + table);
        if(!this.whereClause.isEmpty()){
            sql.append(" WHERE ").append(this.whereClause);
        }
        if(!this.orderBy.isEmpty()){
            sql.append(" ORDER BY ").append(this.orderBy);
        }
        if(this.limit > 0){
            sql.append(" LIMIT ").append(this.limit);
        }
        return sql.toString();
    }
}

class DatabaseClient{
    public static void main(String[] args) {
        SQLQuery sqlQuery = new SQLQuery.SQLQueryBuilder("users")
                .select("id, name, email")
                .where("age > 25")
                .orderBy("name ASC")
                .limit(10)
                .build();
        System.out.println(sqlQuery.toSql());
    }
}
