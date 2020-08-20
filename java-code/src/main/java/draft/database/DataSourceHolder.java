package draft.database;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

/**
 * @author cbdyzj
 * @since 2018-07-22
 */
public abstract class DataSourceHolder {

    public static DataSource get() {
        var ds = new SingleConnectionDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("password");
        return ds;
    }
}
