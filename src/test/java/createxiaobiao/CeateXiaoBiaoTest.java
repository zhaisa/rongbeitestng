package createxiaobiao;

import addpackage.CreateXiaoBiao;

public class CeateXiaoBiaoTest {
public static void main(String[] args) throws Exception {
	CreateXiaoBiao ct=new CreateXiaoBiao();
			
		for(int i=1;i<3;i++){
			try {
				ct.createXiaoBiao("3", 3, 2018, 0, 15);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
}
}