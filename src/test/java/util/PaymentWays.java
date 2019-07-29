package util;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付方式Hash存储
 * @author KaiKang
 */
public class PaymentWays {
	/**
	 * key,机构号
	 * value,机构名称
	 */
	private static final Map<String, String> paymentWaysMap;
	static {
		paymentWaysMap = new HashMap<String, String>();
		paymentWaysMap.put("0", "微信");
		paymentWaysMap.put("1", "支付宝");
		paymentWaysMap.put("700000000000003", "中国工商银行");
		paymentWaysMap.put("700000000000002", "中国农业银行");
		paymentWaysMap.put("700000000000001", "中国银行");
		paymentWaysMap.put("700000000000004", "中国建设银行");
		paymentWaysMap.put("700000000000006", "中国交通银行");
		paymentWaysMap.put("700000000000011", "中国邮政储蓄银行");
		paymentWaysMap.put("700000000000010", "招商银行");
		paymentWaysMap.put("700000000000013", "中信银行");
		paymentWaysMap.put("700000000000015", "华夏银行");
		paymentWaysMap.put("700000000000008", "中国民生银行");
		paymentWaysMap.put("700000000000009", "兴业银行");
		paymentWaysMap.put("700000000000014", "平安银行");
		paymentWaysMap.put("700000000000005", "中国光大银行");
		paymentWaysMap.put("700000000000012", "广发银行");
		paymentWaysMap.put("700000000000007", "浦发银行");
		paymentWaysMap.put("700000000000022", "青岛银行");
		paymentWaysMap.put("700000000000019", "深圳发展银行");
		paymentWaysMap.put("700000000000044", "徽商银行");
		paymentWaysMap.put("700000000000020", "东亚银行");
	}

	/**
	 * 通过机构号，查询机构名称
	 * @param bankInstNo 机构号
	 * @return
	 */
	public static String bankInstName(String bankInstNo) {
		String bankInstName = paymentWaysMap.get(bankInstNo);
		return bankInstName == null?"银联支付":bankInstName;
	}

}
