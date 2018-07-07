package com.yueqingRMS.business.knowledgebase.text;

public class stringtext {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s2 = "asd/asd.asdas;asd:aqsd；qwcca。casc";
		String[] sArr=s2.split("[;；：:,\\.。/]");
		for(String s:sArr){
            System.out.println(s);
        }
	}

}
