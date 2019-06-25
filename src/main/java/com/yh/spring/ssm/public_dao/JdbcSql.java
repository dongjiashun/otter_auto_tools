//package com.yh.spring.ssm.public_dao;
//
//import java.sql.SQLException ;
//
//import com.mysql.jdbc.Connection ;
//import com.mysql.jdbc.PreparedStatement ;
//import com.yh.spring.ssm.util.DataBaseConnection ;
//import com.yh.spring.ssm.util.sqlCollection ;
//
///** 
// * @author Administrator
// * @date 创建时间：2017年7月12日 下午2:40:12
// * @version 1.0
// * @since  
// * @return 
// */
//public class JdbcSql {
//	 
//	 
//	 public static int insert(String value) {
//		  sqlCollection.JDBCNUM=1;
//		 new DataBaseConnection();
//		    Connection conn = ( Connection ) DataBaseConnection.getConnection( );
//		    int i = 0;
//		    String sql = 	    		 
//	"	    	replace into `ddy_partner`.dm$daily_stat     " +
//	"			(                                            " +
//	"				`create_time`,                            " +
//	"				`partner_id`,                             " +
//	"				`dm_count` ,                              " +
//	"				`dm_strategy_count`,                      " +
//	"				`dmai_count`,                             " +
//	"				`dmai_strategy_count`,                    " +
//	"				`dm_fee` ,                                " +
//	"				`defer_fee` ,                             " +
//	"				`deposit` ,                               " +
//	"				`append_deposit`,                         " +
//	"				`deal_amount`,                            " +
//	"				`deal_mem_cnt`,                           " +
//	"				`today_deal_amount`,                      " +
//	"				`deal_cnt`,                               " +
//	"				`today_deal_cnt`,                         " +
//	"				`day_profit_loss`,                        " +
//	"				`day_dm_profit_loss`,                     " +
//	"				`day_inv_profit`,                         " +
//	"				`day_cc_cnt`,                             " +
//	"				`day_cc_amount`,                          " +
//	"				`recharge_money`,                         " +
//	"				`withdraw_money`,                         " +
//	"				`strategy_available`,                     " +
//	"				`total_fund`,                             " +
//	"				`day_settle_amount`                       " +
//	"			)                                            " +
//	"		values  " 
// + value;
//		    PreparedStatement pstmt;
//		    try {
//		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
//		        i = pstmt.executeUpdate();
//		        pstmt.close();
//		        conn.close();
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		    }
//		    return i;
//		}
//	 
//}
