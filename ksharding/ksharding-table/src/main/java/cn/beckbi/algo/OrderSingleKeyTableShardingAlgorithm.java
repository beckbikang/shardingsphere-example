package cn.beckbi.algo;


import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;


public class OrderSingleKeyTableShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> shardingValue) {

        int tableSize = tableNames.size();

        long size = shardingValue.getValue()/10;
        String target_table_name = "order_" + size%tableSize;

        for (String tableName : tableNames) {
            if (tableName.equals(target_table_name)) {
                return tableName;
            }
        }
        return "";
    }



    @Override
    public Collection<String> doSharding(Collection<String> availableTableNames, RangeShardingValue<Long> shardingValue) {
        return availableTableNames;
    }

    @Override
    public Properties getProps(){
        return null;
    }

    @Override
    public void init(Properties props) {

    }

    @Override
    public String getType() {
        return "CLASS_BASED";
    }

}
