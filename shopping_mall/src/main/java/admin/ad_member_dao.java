package admin;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ad_member_dao {
	int aidx;
	String aid, apass, aname, aemail, atel, adepart, aposition, adate, isgrant;
	
	public ArrayList<Object> data(){
		ArrayList<Object> record = new ArrayList<Object>();
		record.add(this.getAidx());
		record.add(this.getAid());
		record.add(this.getApass());
		record.add(this.getAname());
		record.add(this.getAemail());
		record.add(this.getAdepart());
		record.add(this.getAposition());
		record.add(this.getAdate());
		record.add(this.getIsgrant());
		
		return record;
	}
}
