package suwa.tool;

import java.io.UnsupportedEncodingException;

import javax.print.attribute.standard.JobKOctets;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import model.CartDto;

public class GreenWorldService {

	public static AllInOne all;
	
	public static void main(String[] args) {
		try {
			initial();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("aioCheckOutALL: " + genAioCheckOutALL());
	}
	
	
	public static void initial() throws UnsupportedEncodingException {
		all = new AllInOne("");
	}
	
	public static String genAioCheckOutAll(CartDto ord) {
		
		return "just come to ask me";

		
	}
	
	
}
