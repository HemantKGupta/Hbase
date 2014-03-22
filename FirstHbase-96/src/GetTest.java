import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;


public class GetTest {

	public static void main(String[] args) {
		// The code assume a tbale created already by command:
		// create 'firsttable', 'colf1'
		// And also  has a row by key row1
		
		Configuration config = HBaseConfiguration.create();
		HTable table=null;
		Result result=null;
		Get g = null;
		
		try {
			table = new HTable(config, "firsttable");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] row1 = Bytes.toBytes("row1");
		g = new Get(row1);
		
		try {
			result = table.get(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Get: " + result);
	}

}