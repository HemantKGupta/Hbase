import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class IncrementSingleExample {

  public static void main(String[] args) throws IOException {
    Configuration conf = HBaseConfiguration.create();

    HBaseHelper helper = HBaseHelper.getHelper(conf);
    helper.dropTable("counters");
    helper.createTable("counters", "daily");
    HTable table = new HTable(conf, "counters");

    long cnt1 = table.incrementColumnValue(Bytes.toBytes("20110101"), 
      Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1);
    long cnt2 = table.incrementColumnValue(Bytes.toBytes("20110101"), 
      Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1);

    long current = table.incrementColumnValue(Bytes.toBytes("20110101"), 
      Bytes.toBytes("daily"), Bytes.toBytes("hits"), 0);

    long cnt3 = table.incrementColumnValue(Bytes.toBytes("20110101"), 
      Bytes.toBytes("daily"), Bytes.toBytes("hits"), -1);
    System.out.println("cnt1: " + cnt1 + ", cnt2: " + cnt2 +
      ", current: " + current + ", cnt3: " + cnt3);
  }
}
